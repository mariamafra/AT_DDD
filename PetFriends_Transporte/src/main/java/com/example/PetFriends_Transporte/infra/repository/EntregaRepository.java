package com.example.PetFriends_Transporte.infra.repository;

import com.example.PetFriends_Transporte.domain.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
