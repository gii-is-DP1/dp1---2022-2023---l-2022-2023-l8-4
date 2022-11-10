package org.springframework.samples.petclinic.carta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaServicio {

    private CartaRepositorio cr;

    @Autowired
    public CartaServicio( CartaRepositorio cr)
    {
        this.cr = cr;
    }

    public List<Carta> getBaraja()
    {
        return cr.getCartas();
    }

}

