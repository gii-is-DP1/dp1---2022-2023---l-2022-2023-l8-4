package org.springframework.samples.petclinic.card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query ( "SELECT c FROM Card c" )
    List<Card> getCards();

    @Query( "SELECT c from Card c WHERE c.id = :cardId")
    Card getCardById(@Param("cardId") int cardId);
}
