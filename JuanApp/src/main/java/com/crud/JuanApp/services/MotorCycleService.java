package com.crud.JuanApp.services;

import com.crud.JuanApp.entities.MotorCycle;
import com.crud.JuanApp.entities.Person;
import com.crud.JuanApp.repositories.MotorCycleRepository;
import com.crud.JuanApp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorCycleService {
    @Autowired
    private MotorCycleRepository motorCycleRepository;
    @Autowired
    private PersonRepository personRepository;

    public Optional<MotorCycle> CreateMotorcycle (MotorCycle motorCycle){
        return Optional.of(this.motorCycleRepository.save(motorCycle));
    }
    public Optional<List<MotorCycle>> ListMotrorcycles (){
        return Optional.of(this.motorCycleRepository.findAll());
    }
    public Optional<MotorCycle> GetById (Long id){
        return this.motorCycleRepository.findById(id);
    }
    public Optional<MotorCycle> Update (MotorCycle motorCycle, Long id){
        Optional<Person> person = this.personRepository.findById(motorCycle.getPerson().getId());
        Optional<MotorCycle> motorCycleToUpdate = this.motorCycleRepository.findById(id);
        if( person.isEmpty() || motorCycleToUpdate.isEmpty())
            return null;

        motorCycle.setPerson(person.get());
        motorCycle.setId(motorCycleToUpdate.get().getId());
        this.motorCycleRepository.save(motorCycle);
        return  Optional.of(motorCycle);
    }

}
