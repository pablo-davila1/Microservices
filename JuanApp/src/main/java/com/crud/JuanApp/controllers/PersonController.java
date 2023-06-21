package com.crud.JuanApp.controllers;

import com.crud.JuanApp.dto.motorCycleDtos.MotorCycleDto;
import com.crud.JuanApp.dto.personDtos.PersonDto;
import com.crud.JuanApp.entities.Person;
import com.crud.JuanApp.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private ModelMapper modelMapper;

    @PutMapping
    public ResponseEntity<PersonDto>Create (@RequestBody PersonDto personDto){
        Person newPerson = this.modelMapper.map(personDto, Person.class);
        if(personDto.equals(null))
            return new ResponseEntity<PersonDto>(HttpStatus.BAD_REQUEST);
        this.personService.CreatePerson(newPerson);
        return ResponseEntity.ok().body(personDto);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>>GetAll (){
        List<PersonDto> people = this.personService.ListPeople().stream()
                .map(person -> this.modelMapper.map(person,PersonDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(people);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto>GetById (@PathVariable(name = "id")Long id){
        Person person = this.personService.GetById(id).get();
        if(person.equals(null))
            return new ResponseEntity<PersonDto>(HttpStatus.NOT_FOUND);
        PersonDto personDto = this.modelMapper.map(person, PersonDto.class);
        return ResponseEntity.ok().body(personDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonDto>Update (@RequestBody PersonDto personDto, @PathVariable(name = "id")Long id){
        Person person = this.modelMapper.map(personDto,Person.class);
        if(person.equals(null))
            return new ResponseEntity<PersonDto>(HttpStatus.NOT_FOUND);
        this.personService.Update(person,id);
        return ResponseEntity.ok().body(personDto);
    }

    @DeleteMapping("/delete/{id}")
    public void Delete (@PathVariable(name = "id")Long id){
        this.personService.DeletePerson(id);
    }

    @GetMapping("/listByMotoId/{id}")
    public ResponseEntity<List<MotorCycleDto>> ListByMotorcycleId (@PathVariable(name = "id")Long id){
        List<MotorCycleDto> motorCycleDtos = this.personService.ListMotorCyclesByUserId(id).get()
                .stream().map(moto -> this.modelMapper.map(moto,MotorCycleDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(motorCycleDtos);
    }
}
