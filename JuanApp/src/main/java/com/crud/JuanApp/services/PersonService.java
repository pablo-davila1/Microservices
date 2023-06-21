package com.crud.JuanApp.services;

import com.crud.JuanApp.entities.MotorCycle;
import com.crud.JuanApp.entities.Person;
import com.crud.JuanApp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService{
    @Autowired
    private PersonRepository personRepository;
    public Optional<Person> CreatePerson (Person person){
        return Optional.of(this.personRepository.save(person));
    }
    public Optional<List<Person>> ListPeople (){
        List<Person> people = this.personRepository.findAll();
        return Optional.of(people);
    }
    public Optional<Person> GetById (Long id){
        return this.personRepository.findById(id);
    }
    public Optional<Person> Update (Person person, Long id){
        Optional<Person> personToUpdate = this.personRepository.findById(id);
        personToUpdate.get().setMotorCycles(person.getMotorCycles());
        if(!personToUpdate.isPresent())
            return null;
        person.setId(personToUpdate.get().getId());
        this.personRepository.save(person);
        return Optional.of(person);
    }
    public void DeletePerson (Long id){
        Optional<Person> person = this.personRepository.findById(id);
        if(!person.isPresent())
            return;
        this.personRepository.delete(person.get());
    }
    public Optional<List<MotorCycle>> ListMotorCyclesByUserId (Long id){
        return Optional.of(this.personRepository.findById(id).get().getMotorCycles());
    }

}
