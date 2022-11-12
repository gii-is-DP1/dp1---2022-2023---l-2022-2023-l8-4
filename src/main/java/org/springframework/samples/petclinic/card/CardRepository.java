package org.springframework.samples.petclinic.card;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query ( "SELECT c FROM Card c" )
    List<Card> getCards();

}
