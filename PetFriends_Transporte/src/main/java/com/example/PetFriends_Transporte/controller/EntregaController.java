package com.example.PetFriends_Transporte.controller;

import com.example.PetFriends_Transporte.domain.Entrega;
import com.example.PetFriends_Transporte.infra.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/entrega")
public class EntregaController {
    @Autowired
    EntregaService entregaService;

    @GetMapping("/{id}")
    public Entrega obterPorId(@PathVariable(value = "id") long id){
        return entregaService.obterPorId(id);
    }

    @PatchMapping("/finalizar/{id}")
    public Entrega finalizarEntrega(@PathVariable(value = "id") long id){
        return entregaService.finalizar(id);
    }
}
