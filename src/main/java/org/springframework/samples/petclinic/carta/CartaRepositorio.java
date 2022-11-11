package org.springframework.samples.petclinic.carta;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartaRepositorio extends CrudRepository<Carta, Integer> {

    @Query ( "SELECT c FROM Carta c" )
    List<Carta> getCartas();

}
