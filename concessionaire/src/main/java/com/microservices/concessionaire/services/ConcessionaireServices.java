package com.microservices.concessionaire.services;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.microservices.concessionaire.entities.Concessionaire;
import com.microservices.concessionaire.repositories.ConcessionaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConcessionaireServices {
    @Autowired
    private ConcessionaireRepository concessionaireRepository;

    public Optional<Concessionaire> Create (Concessionaire concessionaire)
    {
        this.concessionaireRepository.save(concessionaire);
        return Optional.of(concessionaire);
    }
    public Optional<List<Concessionaire>> GetAll ()
    {
        return Optional.of(this.concessionaireRepository.findAll());
    }

}
