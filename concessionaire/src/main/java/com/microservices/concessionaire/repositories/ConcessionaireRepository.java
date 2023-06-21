package com.microservices.concessionaire.repositories;

import com.microservices.concessionaire.entities.Concessionaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcessionaireRepository extends JpaRepository <Concessionaire, Long> {
}
