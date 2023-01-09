package org.springframework.samples.petclinic.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private CardRepository cardRepository;

    @Autowired
    public CardService( CardRepository cardRepository)
    {
        this.cardRepository = cardRepository;
    }

    public List<Card> getDeck()
    {
        return cardRepository.getCards();
    }

    public Card findCardById(int cardId) { return cardRepository.getCardById( cardId); }
}

