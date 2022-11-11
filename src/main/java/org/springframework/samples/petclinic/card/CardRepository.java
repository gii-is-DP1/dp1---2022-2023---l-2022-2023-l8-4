package org.springframework.samples.petclinic.card;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query ( "SELECT c FROM Card c" )
    List<Card> getCards();

}
