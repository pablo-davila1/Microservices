package com.crud.JuanApp.repositories;

import com.crud.JuanApp.entities.MotorCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorCycleRepository extends JpaRepository<MotorCycle, Long> {
}
