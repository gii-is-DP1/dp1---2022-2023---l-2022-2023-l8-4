package org.springframework.samples.petclinic.playergamedata;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardRepository;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameMode;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerGameDataService {

	private PlayerGameDataRepository playerGameDataRepository;
	private CardRepository cardRepository;

	@Autowired
	public PlayerGameDataService(PlayerGameDataRepository playerGameDataRepository,CardRepository cardRepository) {
		this.playerGameDataRepository = playerGameDataRepository;
		this.cardRepository = cardRepository;
	}

	@Transactional(readOnly=true)
	public Collection<PlayerGameData> getAll() {
		return playerGameDataRepository.findAll();
	}

	public PlayerGameData getByIds(Integer gameId, Integer playerId) {
		return playerGameDataRepository.findByIds(gameId, playerId);
	}

	public void winPoint(Integer gameId, Integer playerId) {
		PlayerGameData data= playerGameDataRepository.findByIds(gameId,playerId);
		Integer points=data.getPointsNumber();
		data.setPointsNumber(points+1);
        this.savePlayerGameData( data );
	}

	public void changeCardsEstandar(Integer gameId, Integer playerId, Integer middleCardId) {
		PlayerGameData data = playerGameDataRepository.findByIds(gameId, playerId);
		Card middleCard = cardRepository.getCardById(middleCardId);
		data.setActualCard(middleCard);
		savePlayerGameData(data);
	}
	
	public void changePlayerCardElFoso(Integer gameId, Integer playerId) {
		PlayerGameData data = playerGameDataRepository.findByIds(gameId, playerId);
		List<Card> cards= data.getActualCards().stream().collect(Collectors.toList());
		cards.remove(0);
		data.setActualCards(cards);
		data.setActualCard(data.getActualCards().stream().collect(Collectors.toList()).get(0));
		savePlayerGameData(data);
	}

    public void initGameParamsEstandar(Integer middleCardId, PlayerGameData pgd, Player player, Game game) {
        pgd.setGame(game);
        pgd.setPlayer(player);
        pgd.setWinner( false );
        pgd.setPointsNumber( 0 );
        Card middleCard = cardRepository.getCardById(middleCardId);
        pgd.setActualCard(middleCard);        
        savePlayerGameData( pgd );
    }

    public void initGameParamsElFoso(List<Card> deck, PlayerGameData pgd, Player player, Game game) {
        
        Integer playerNum= game.getPlayers().size();
        Integer numCartasJugador= 56/playerNum;
        List<Card> cards= new ArrayList<>();
        int i=0;
        while(i<numCartasJugador) {
        	cards.add(deck.get(0));
        	deck.remove(0);
        	i++;
        }
        pgd.setActualCards(cards);
        pgd.setActualCard(pgd.getActualCards().stream().collect(Collectors.toList()).get(0));
        pgd.setPlayer(player);
        pgd.setGame(game);
        pgd.setWinner( false );
        pgd.setPointsNumber( 0 );
        savePlayerGameData( pgd );
    }
    
	public void setWinner(Integer gameId, Integer playerId) {
		PlayerGameData data = playerGameDataRepository.findByIds(gameId, playerId);
		data.setWinner(true);
        savePlayerGameData( data );
	}

	@Transactional
	public void savePlayerGameData(PlayerGameData playerGameData) throws DataAccessException {
		this.playerGameDataRepository.save(playerGameData);
	}

    public List<PlayerGameData> getById(Integer gameId) {
        return this.playerGameDataRepository.getDataByGameId( gameId );
    }
}
