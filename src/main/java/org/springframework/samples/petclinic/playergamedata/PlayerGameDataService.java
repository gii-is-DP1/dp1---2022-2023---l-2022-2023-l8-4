package org.springframework.samples.petclinic.playergamedata;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardRepository;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerGameDataService {

	private PlayerGameDataRepository playerGameDataRepository;
	private CardRepository cardRepository;

	@Autowired
	public PlayerGameDataService(PlayerGameDataRepository playerGameDataRepository, CardRepository cardRepository) {
		this.playerGameDataRepository = playerGameDataRepository;
		this.cardRepository = cardRepository;
	}

	@Transactional(readOnly=true)
	public Collection<PlayerGameData> getAll() {
		return playerGameDataRepository.findAll();
	}

	@Transactional(readOnly=true)
	public PlayerGameData getByIds(Integer gameId, Integer playerId) {
		return playerGameDataRepository.findByIds(gameId, playerId);
	}

	@Transactional
	public void winPoint(Integer gameId, Integer playerId) {
		PlayerGameData playerGameData= playerGameDataRepository.findByIds(gameId,playerId);
		Integer points=playerGameData.getPointsNumber();
		playerGameData.setPointsNumber(points+1);
        this.savePlayerGameData( playerGameData );
	}

	@Transactional
	public void initGameParamsEstandar(Integer middleCardId, PlayerGameData pgd, Player player, Game game) {
		pgd.setGame(game);
		pgd.setPlayer(player);
		pgd.setWinner( false );
		pgd.setPointsNumber( 0 );
		Card middleCard = cardRepository.getCardById(middleCardId);
		pgd.setActualCard(middleCard);
		savePlayerGameData( pgd );
	}


	@Transactional
	public void changePlayerCardEstandar(Integer gameId, Integer playerId, Integer middleCardId) {
		PlayerGameData playerGameData = playerGameDataRepository.findByIds(gameId, playerId);
		Card middleCard = cardRepository.getCardById(middleCardId);
		playerGameData.setActualCard(middleCard);
		savePlayerGameData(playerGameData);
	}

	@Transactional
	public void initGameParamsElFoso(List<Card> deck, PlayerGameData playerGameData, Player player, Game game) {
		List<Card> cards = getCardsToPlayer(deck, game);
		playerGameData.setActualCards(cards);
		playerGameData.setActualCard(playerGameData.getActualCards().stream().collect(Collectors.toList()).get(0));
		playerGameData.setPlayer(player);
		playerGameData.setGame(game);
		playerGameData.setWinner( false );
		playerGameData.setPointsNumber( 0 );
		savePlayerGameData( playerGameData );
	}

	@Transactional(readOnly=true)
	private List<Card> getCardsToPlayer(List<Card> deck, Game game) {
		Integer playersNumber= game.getPlayers().size();
		Integer CardsPlayerNumber= 56/playersNumber;
		List<Card> cards= new ArrayList<>();
		int i=0;
		while(i < CardsPlayerNumber) {
			cards.add(deck.get(0));
			deck.remove(0);
			i++;
		}
		return cards;
	}

	@Transactional
	public void removePlayerCardElFoso(Integer gameId, Integer playerId) {
		PlayerGameData data = playerGameDataRepository.findByIds(gameId, playerId);
		List<Card> cards= data.getActualCards().stream().collect(Collectors.toList());
		cards.remove(0);
		data.setActualCards(cards);
		if(data.getActualCards().size()!=0) {
			data.setActualCard(data.getActualCards().stream().collect(Collectors.toList()).get(0));
		}
		savePlayerGameData(data);
	}



	@Transactional
	public void setWinner(Integer gameId, Integer playerId) {
		PlayerGameData data = playerGameDataRepository.findByIds(gameId, playerId);
		data.setWinner(true);
        savePlayerGameData( data );
	}

	@Transactional
	public void savePlayerGameData(PlayerGameData playerGameData) throws DataAccessException {
		this.playerGameDataRepository.save(playerGameData);
	}

	@Transactional(readOnly=true)
    public List<PlayerGameData> getById(Integer gameId) {
        return this.playerGameDataRepository.getDataByGameId( gameId );
    }

    public List<PlayerGameData> addPlayersDataIntoGame(Integer gameId, Integer playerId, Collection<Player> players ) {

        CopyOnWriteArrayList<PlayerGameData> playersData = new CopyOnWriteArrayList<>();
        for(Player player: players){
            PlayerGameData playerGameData = this.getByIds( gameId, player.getId() );
            if ( !player.getId().equals( playerId ) )
            {
                playersData.add(playerGameData);
                continue;
            }
            playersData.add(0, playerGameData );

        }
        return playersData;
    }
}
