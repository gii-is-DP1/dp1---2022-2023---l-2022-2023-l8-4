package org.springframework.samples.petclinic.game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.playergamedata.PlayerGameData;
import org.springframework.samples.petclinic.playergamedata.PlayerGameDataService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/games")
public class GameController {

	Logger logger = LoggerFactory.getLogger(GameController.class);

	private static final String GAME_DETAILS = "games/gameDetails";
	private static final String VIEW_CREATION_FORM = "games/createGame";
	private static final String VIEW_GAME_LIST_FINALIZED = "games/listGamesFinalized";
	private static final String VIEW_GAME_LIST_IN_PROGRESS = "games/listGamesInProgress";
	private static final String GAME_JOIN_VIEW = "games/joinGame";
	private static final String GAME_BOARD = "games/board";
	private static final String GAME_RESULTS = "games/results";
	private GameService gameService;
	private CardService cardService;
	private PlayerService playerService;
	private PlayerGameDataService playerGameDataService;

	@Autowired
	public GameController(GameService gameService, CardService cardService, PlayerService playerService, PlayerGameDataService playerGameDataService) {
		this.gameService = gameService;
		this.cardService = cardService;
		this.playerService = playerService;
		this.playerGameDataService = playerGameDataService;
	}

	private Game initGame() {
		Game game = new Game();
		game.setGameState(GameState.INITIATED);
		game.setGameMode(GameMode.ESTANDAR);
		game.setDate(LocalDate.now());
		return game;
	}
	
	@GetMapping("/description")
	@ResponseBody
	public Map<String, GameModeData> getMinigamesDescription() {
		Map<String, GameModeData> map = new HashMap<String, GameModeData>();
		for (GameMode gameMode : GameMode.values()) {
			map.put(gameMode.toString(), gameMode.getGameModeData());
		}
		return map;
	}

	@GetMapping(value = "/new")
	public String iniciarFormulario(Map<String, Object> model) {
		Game game = initGame();
		List<GameMode> gameModes = Arrays.asList(GameMode.values());
		model.put("game", game);
		model.put("gameModes", gameModes);
		return VIEW_CREATION_FORM;
	}

	@PostMapping(value = "/new")
	public String proccessForm(Authentication authentication, @ModelAttribute("game") @Valid Game game, BindingResult result) throws DataAccessException, Exception {
		if (result.hasErrors()) {
			return VIEW_CREATION_FORM;
		}

		game.setDate(LocalDate.now());
		game.setGameState(GameState.INITIATED);
		game.setGameCode(ThreadLocalRandom.current().nextInt(0, 10000 + 1));
		game.setCards(cardService.getDeck());
		addCurrentPlayerToGame(authentication.getName(),game);
		logger.info("Dobble::INFO::Game Lobby " + game.getGameCode() + " has been created. Lobby creator = " + authentication.getName());
		return "redirect:/games/" + game.getId();
	}

	@GetMapping("/{gameId}")
	public ModelAndView initLobby(@PathVariable("gameId") int gameId) throws Exception {
		ModelAndView mav = new ModelAndView(GAME_DETAILS);
		mav.addObject("creator", true);

		Game game = this.gameService.getGameById(gameId);
		gameService.randomizeDeck(gameId);

		mav.addObject("game", game);

		return mav;
	}

	@GetMapping(value = "/finalized")
	public String listFinishGames(Map<String, Object> model, @RequestParam Map<String, Object> params) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) : 0;

		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Game> pageGamesFinalized= gameService.getGamesFinalized(pageRequest);

		int totalPages = pageGamesFinalized.getTotalPages();
		List<Integer> pages=new ArrayList<>();
		if(totalPages > 0) {
			pages= IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		}

        model.put( "games", pageGamesFinalized.getContent());
        model.put( "pages", pages);
        model.put( "current", page + 1);
        model.put( "next", page + 2);
        model.put( "prev", page);
        model.put( "last", totalPages);
		return VIEW_GAME_LIST_FINALIZED;

	}

	@GetMapping(value = "/inProgress")
	public String listInProgressGames(Map<String, Object> model, @RequestParam Map<String, Object> params) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) : 0;

		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Game> pageGamesInProgress= gameService.getGamesInProgress(pageRequest);

		int totalPages = pageGamesInProgress.getTotalPages();
		List<Integer> pages=new ArrayList<>();
		if(totalPages > 0) {
			pages= IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		}

        model.put( "games", pageGamesInProgress.getContent());
        model.put( "pages", pages);
        model.put( "current", page + 1);
        model.put( "next", page + 2);
        model.put( "prev", page);
        model.put( "last", totalPages);
		return VIEW_GAME_LIST_IN_PROGRESS;

	}

	@GetMapping("/join")
	public ModelAndView joinGames() throws Exception {
		ModelAndView mav = new ModelAndView(GAME_JOIN_VIEW);
		mav.addObject("gameCode",0);
		return mav;
	}

    @PostMapping("/join")
    public String joinGame(Authentication  authentication, @ModelAttribute("gameCode") int gameCode) throws Exception {
        Game game = gameService.getGameByCode(gameCode);
        if( game.getPlayers().contains( playerService.getPlayerByUsername( authentication.getName() ) ) || game.getPlayers().size() >= 4 || game.getGameState().equals( GameState.IN_PROGRESS ) )  {
            return "redirect:/games/error";
        }
        addCurrentPlayerToGame(authentication.getName(),game);

        return "redirect:/games/join/" + game.getGameCode() + "/" + this.playerService.getPlayerByUsername( authentication.getName() ).getId();
    }

	@GetMapping("/join/{gameCode}/{playerId}")
	public ModelAndView joinGameCode(@PathVariable("gameCode") int gameCode, @PathVariable("playerId") Integer playerId ) throws Exception {
		ModelAndView mav = new ModelAndView(GAME_DETAILS);
		mav.addObject("creator", false);

		Game game = this.gameService.getGameByCode(gameCode);
		mav.addObject("game",game);
        mav.addObject( "playerId", playerId );
        mav.addObject( "gameState", game.getGameState().toString().toUpperCase());

		return mav;
	}

	//The game middle card is always the top card of the deck that is shuffled at the start of that game
	@GetMapping(value = "/board/{gameId}/{playerId}/{middleCardId}")
	public ModelAndView clickCard(@PathVariable("gameId") Integer gameId,@PathVariable("playerId") Integer playerId,@PathVariable("middleCardId") Integer middleCardId) throws DataAccessException, NoSuchEntityException{
        PlayerGameData pgd= this.playerGameDataService.getByIds(gameId, playerId);
		Game game = this.gameService.getGameById(gameId);
        ModelAndView mav = new ModelAndView(GAME_BOARD );
		playerGameDataService.winPoint(gameId, playerId);
		
		if(game.getGameMode()==GameMode.ESTANDAR) {
			playerGameDataService.changePlayerCardEstandar(gameId, playerId, middleCardId);
			gameService.deleteCardFromDeckEstandar(gameId, new ArrayList<>(game.getCards()));
			if (game.getCards().size() == 0 ) {
				ModelAndView end= new ModelAndView( GAME_RESULTS );
				end.addObject("gameId",gameId);
				end.addObject("playerId",playerId);
				return end;
			}
		}
		
		else if(game.getGameMode()==GameMode.EL_FOSO) {
			gameService.changeGameCardElFoso(gameId, pgd);
			playerGameDataService.removePlayerCardElFoso(gameId, playerId);
			if (pgd.getActualCards().size() == 0 ) {
				ModelAndView end= new ModelAndView( GAME_RESULTS );
				end.addObject("gameId",gameId);
				end.addObject("playerId",playerId);
				return end;
			}
			
		}
		
		mav.addObject("game", game);
		CopyOnWriteArrayList<PlayerGameData> data= new CopyOnWriteArrayList<>();
		for(Player player:game.getPlayersInternal()){
			PlayerGameData playerGameData = this.playerGameDataService.getByIds(game.getId(),player.getId());
			if ( player.getId().equals( playerId ) )
			{
				mav.addObject("player", player.getId());
				mav.addObject( "playerName", player.getUser().getUsername());
				mav.addObject( "playerCard", playerGameData.getActualCard() );
			}
			data.add(playerGameData);
		}
		mav.addObject("players", data);
		mav.addObject( "card", game.getCards().stream().findFirst().get());
		mav.addObject( "cardId", game.getCards().stream().findFirst().get().getId() );
		return mav;
	}
    @GetMapping(value = "/board/{gameId}/{playerId}")
    public ModelAndView enterGame(@PathVariable( "gameId" ) Integer gameId, @PathVariable( "playerId" ) Integer playerId) throws DataAccessException, NoSuchEntityException{
        ModelAndView mav = new ModelAndView(GAME_BOARD);

        Game game = this.gameService.getGameById( gameId );
        
        if(game.getGameMode()==GameMode.ESTANDAR) {
        	if (game.getCards().size() == 0 ) {
        		ModelAndView end = new ModelAndView(GAME_RESULTS);
        		end.addObject("gameId", gameId);
        		end.addObject("playerId", playerId);
        		return end;
        	}
        }
        else if(game.getGameMode()==GameMode.EL_FOSO) {
			for(Player p:game.getPlayersInternal()) {
				PlayerGameData pgd= this.playerGameDataService.getByIds(gameId, p.getId());
						if(pgd.getActualCards().size()==0) {
							ModelAndView end= new ModelAndView( GAME_RESULTS );
							end.addObject("gameId",gameId);
							end.addObject("playerId",playerId);
							return end;
						}
			}
				
			
		}

        CopyOnWriteArrayList<PlayerGameData> players= new CopyOnWriteArrayList<>();
        for ( Player player:game.getPlayersInternal() ) players.add( this.playerGameDataService.getByIds( game.getId(), player.getId() ) );

        mav.addObject("players", players );
        mav.addObject("game", game);

        Card card = this.gameService.getGameById( gameId ).getCards().stream().findFirst().get();
        mav.addObject( "card",  card);
        mav.addObject( "cardId", card.getId() );

        mav.addObject( "playerName", this.playerService.showPlayerById( playerId ).getUser().getUsername() );
        mav.addObject("player", playerId );

        PlayerGameData gameData = this.playerGameDataService.getByIds( gameId,  playerId );

        mav.addObject( "playerCard", gameData.getActualCard() );

        return mav;
    }

    @GetMapping(value = "/board/{gameId}")
    public ModelAndView startGame(Authentication  authentication, @PathVariable("gameId") Integer gameId ) throws DataAccessException, NoSuchEntityException{
    	ModelAndView mav = new ModelAndView(GAME_BOARD);
    	Game game= gameService.getGameById(gameId);
        game.setGameState( GameState.IN_PROGRESS );
        this.gameService.saveGame( game );
    	gameService.randomizeDeck(gameId);
        CopyOnWriteArrayList<Card> deck= new CopyOnWriteArrayList<>(game.getCards());
        
    	Collection<Player> players= game.getPlayersInternal();
        CopyOnWriteArrayList<PlayerGameData> list = new CopyOnWriteArrayList<>();
    	if(game.getGameMode()==GameMode.ESTANDAR) {
    		for( Player player : new ArrayList<>(players)){
                PlayerGameData pgd= new PlayerGameData();
                playerGameDataService.initGameParamsEstandar(deck.get(0).getId(), pgd, player, game);
                gameService.deleteCardFromDeckEstandar(gameId, deck);
                deck.remove(0);
                list.add( pgd );
    		}
    	}
    	else if(game.getGameMode()==GameMode.EL_FOSO) {
    		for( Player player : new ArrayList<>(players)){
                PlayerGameData pgd= new PlayerGameData();
                playerGameDataService.initGameParamsElFoso(deck, pgd, player, game);
                gameService.deleteCardsFromDeckElFoso(gameId, deck);
                list.add( pgd );
    		}
    		game.setCards(deck);
    	}
    	game.setGameState(GameState.IN_PROGRESS);
    	mav.addObject("players", new ArrayList<>(list));
        mav.addObject("game", game);
        mav.addObject( "card", deck.get(0) );
        mav.addObject( "cardId", deck.get(0).getId() );
        Player mainPlayer = players.stream().findFirst().get();

        mav.addObject("player", mainPlayer.getId() );
        mav.addObject( "playerName", mainPlayer.getUser().getUsername());
        mav.addObject( "playerCard", playerGameDataService.getByIds(gameId, mainPlayer.getId()).getActualCard() );

        return mav;
    }
    
    @PostMapping("/results/{gameId}/{playerId}")
    public String postResults(Authentication  authentication, @PathVariable("gameId") Integer gameId, @PathVariable("playerId") Integer playerId) throws Exception {
    	Game game = this.gameService.getGameById(gameId);
    	Player gamePlayer =  this.playerService.showPlayerById(playerId);
        PlayerGameData gameData = this.playerGameDataService.getByIds(gameId, playerId);
    	gameService.saveResults(game, gamePlayer, gameData);

        return "redirect:/games/results/" + gameId + "/" + playerId;
    }
    
    @GetMapping("/results/{gameId}/{playerId}")
    public ModelAndView showResults(Authentication  authentication, @PathVariable("gameId") Integer gameId, @PathVariable("playerId") Integer playerId) throws Exception {
    	Game game = this.gameService.getGameById(gameId);
    	Player gamePlayer =  this.playerService.showPlayerById(playerId);
        PlayerGameData gameData = this.playerGameDataService.getByIds(gameId, playerId);
    	ModelAndView mav = gameService.getResults(game, gamePlayer, gameData, GAME_RESULTS);
    	mav.addObject("show", true);
        return mav;
    }

    private void addCurrentPlayerToGame(String username, Game game) throws Exception {
		Player player = this.playerService.getPlayerByUsername( username );
		this.gameService.addPlayerToGame(player, game);
	}

}
