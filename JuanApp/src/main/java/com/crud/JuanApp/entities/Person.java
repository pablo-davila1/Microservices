package com.crud.JuanApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private  String lastName;
    private Integer age;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<MotorCycle> motorCycles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<MotorCycle> getMotorCycles() {
        return motorCycles;
    }

    public void setMotorCycles(List<MotorCycle> motorCycles) {
        this.motorCycles = motorCycles;
    }

}
