package org.springframework.samples.petclinic.playergamedata;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardRepository;
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
	}
	
	public void changeCards(Integer gameId, Integer playerId, Integer middleCardId) {
		PlayerGameData data = playerGameDataRepository.findByIds(gameId, playerId);
		Card middleCard = cardRepository.getCardById(middleCardId);
		
		data.setActualCard(middleCard);
		cardRepository.getCards().remove(middleCard);
	}
	
	public void setWinner(Integer gameId, Integer playerId) {
		PlayerGameData data = playerGameDataRepository.findByIds(gameId, playerId);
		data.setWinner(true);
	}

}
