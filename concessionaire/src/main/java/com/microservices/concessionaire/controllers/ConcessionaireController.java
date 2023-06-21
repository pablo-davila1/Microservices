package com.microservices.concessionaire.controllers;

import com.microservices.concessionaire.dtos.ConcessionaireDto;
import com.microservices.concessionaire.entities.Concessionaire;
import com.microservices.concessionaire.services.ConcessionaireServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/concessionaire")
public class ConcessionaireController {
    @Autowired
    private ConcessionaireServices concessionaireServices;
    @Autowired
    private ModelMapper modelMapper;

    @PutMapping
    public ResponseEntity<ConcessionaireDto> Create (@RequestBody ConcessionaireDto concessionaireDto)
    {
        if(concessionaireDto.equals(null))
            return new ResponseEntity<ConcessionaireDto>(HttpStatus.BAD_REQUEST);
        Concessionaire newConcessionarie = this.modelMapper.map(concessionaireDto, Concessionaire.class);
        this.concessionaireServices.Create(newConcessionarie);
        return ResponseEntity.ok().body(concessionaireDto);
    }

    @GetMapping
    public ResponseEntity<List<ConcessionaireDto>> GetAll ()
    {
        List<ConcessionaireDto> concessionaires = this.concessionaireServices.GetAll().get()
                .stream().map( c -> this.modelMapper.map(c, ConcessionaireDto.class) )
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(concessionaires);
    }
}
