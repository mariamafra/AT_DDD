package com.example.PetFriends_Almoxarifado.infra.repository;

import com.example.PetFriends_Almoxarifado.domain.OrdemDeServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico, Long> {
}



