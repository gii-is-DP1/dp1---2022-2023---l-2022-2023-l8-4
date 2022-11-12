package org.springframework.samples.petclinic.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private CardRepository cr;

    @Autowired
    public CardService( CardRepository cr)
    {
        this.cr = cr;
    }

    public List<Card> getDeck()
    {
        return cr.getCards();
    }

}

