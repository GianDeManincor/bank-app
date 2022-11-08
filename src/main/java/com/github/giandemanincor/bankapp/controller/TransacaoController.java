package com.github.giandemanincor.bankapp.controller;

import com.github.giandemanincor.bankapp.exception.ContaNotFoundException;
import com.github.giandemanincor.bankapp.exception.DefaultException;
import com.github.giandemanincor.bankapp.model.Transacao;
import com.github.giandemanincor.bankapp.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping("/efetuar")
    private ResponseEntity transferir(@RequestBody Transacao transacao) throws DefaultException {
        service.transacionar(transacao);
        return ResponseEntity.ok().build();
    }
}
