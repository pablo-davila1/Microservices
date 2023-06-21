package com.crud.JuanApp.controllers;

import com.crud.JuanApp.dto.motorCycleDtos.MotorCycleDto;
import com.crud.JuanApp.entities.MotorCycle;
import com.crud.JuanApp.services.MotorCycleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/motorcycle")
public class MotorCycleController {
    @Autowired
    private MotorCycleService motorCycleService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<MotorCycleDto> CreateMotorcycle (@RequestBody MotorCycleDto motorCycleDto){
        MotorCycle motorCycle = modelMapper.map(motorCycleDto, MotorCycle.class);
        MotorCycle motorCycleCreated = this.motorCycleService.CreateMotorcycle(motorCycle).get();
        if ( motorCycleCreated.equals(null) )
            return new ResponseEntity<MotorCycleDto>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(motorCycleDto);
    }
    @GetMapping
    public ResponseEntity<List<MotorCycleDto>> List (){
        List<MotorCycleDto> motorCycleDtos = this.motorCycleService.ListMotrorcycles()
                .stream().map(motorCycles -> modelMapper.map(motorCycles, MotorCycleDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(motorCycleDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MotorCycleDto> GetById (@PathVariable(name = "id")Long id){
        MotorCycle motorCycle = this.motorCycleService.GetById(id).get();
        if(motorCycle.equals(null))
            return new ResponseEntity<MotorCycleDto>(HttpStatus.NOT_FOUND);
        MotorCycleDto motorCycleDto = this.modelMapper.map(motorCycle, MotorCycleDto.class);
        return ResponseEntity.ok().body(motorCycleDto);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MotorCycleDto> Update (@RequestBody MotorCycleDto motorCycleDto, @PathVariable(name = "id")Long id){
        MotorCycle updatedMotorcycle = modelMapper.map(motorCycleDto,MotorCycle.class);
        if(updatedMotorcycle.equals(null))
            return new ResponseEntity<MotorCycleDto>(HttpStatus.NOT_FOUND);
        this.motorCycleService.Update(updatedMotorcycle,id);
        return ResponseEntity.ok().body(motorCycleDto);
    }

}
