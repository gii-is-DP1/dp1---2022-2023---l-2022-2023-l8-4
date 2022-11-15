package org.springframework.samples.petclinic.cartas;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardRepository;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest ( includeFilters = @ComponentScan.Filter( Card.class ) )
public class CardsTest
{

    @Autowired
    CardRepository cr;

    @Test
    public void existenCartas()
    {
        assertFalse( cr.getCards().size() == 57, "No se han cargado todas las cartas" );
    }

    @Test
    public void retrieveCard()
    {
        assertFalse( cr.getCardById( 5 ).getId() == 5, "Se esperaba la carta 5");
    }

}
