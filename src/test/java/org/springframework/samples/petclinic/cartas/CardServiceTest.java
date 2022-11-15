package org.springframework.samples.petclinic.cartas;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardRepository;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

	@Mock
	private CardRepository cardRepository;
	
	private CardService cardService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		cardService = new CardService(cardRepository);
		
		Card card1 = new Card();
		Card card2 = new Card();
		card1.setId(1);
		card2.setId(2);
		card1.setIcons("AVISPA CEBRA ABEJA CIGUEÑA ZORRO LOBO BUHO CANGURO");
		card2.setIcons("CANGURO ORCA PEREZOSO TORTUGA CARACOL GALLINA CANGREJO PINGUINO");
		List<Card> cards = new ArrayList<Card>(); cards.add(card1); cards.add(card2);
		
		when(cardRepository.getCards()).thenReturn(cards);
		when(cardRepository.getCardById(1)).thenReturn(card1);
	}
	
	@Test
	public void shouldGetCards() {
		try {
			assertEquals(cardService.getDeck().get(0).getId(), 1);
			assertEquals(cardService.getDeck().get(1).getId(), 2);
			assertEquals(cardService.getDeck().get(0).getIcons(), "AVISPA CEBRA ABEJA CIGUEÑA ZORRO LOBO BUHO CANGURO");
			assertEquals(cardService.getDeck().get(1).getIcons(), "CANGURO ORCA PEREZOSO TORTUGA CARACOL GALLINA CANGREJO PINGUINO");
			
		} catch(Exception e) {
			fail();
		}
	}
}
