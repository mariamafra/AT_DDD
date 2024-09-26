package com.example.PetFriends_Almoxarifado.controller;

import com.example.PetFriends_Almoxarifado.domain.OrdemDeServico;
import com.example.PetFriends_Almoxarifado.infra.service.OrdemDeServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/os")
public class OrdemDeServicoController {
    @Autowired
    private OrdemDeServicoService service;

    @GetMapping("/{id}")
    public OrdemDeServico obterPorId(@PathVariable(value = "id") long id){
        return service.obterPorId(id);
    }

    @PatchMapping("/finalizar/{id}")
    public OrdemDeServico finalizarOrdemServico(@PathVariable(value = "id") long id){
        return service.finalizar(id);
    }
}
