package org.springframework.samples.petclinic.card;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CardsTest
{

    @Autowired
    private CardRepository cr;

    @Test
    public void existenCartas()
    {
        assertEquals( cr.getCards().size(), 57, "No se han cargado todas las cartas" );
    }

    @Test
    public void retrieveCard()
    {
    	assertEquals( cr.getCardById( 5 ).getId(), 5, "Se esperaba la carta 5");
    }

}
