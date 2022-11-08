package com.github.giandemanincor.bankapp.controller;

import com.github.giandemanincor.bankapp.exception.ContaExistException;
import com.github.giandemanincor.bankapp.exception.DefaultException;
import com.github.giandemanincor.bankapp.model.Conta;
import com.github.giandemanincor.bankapp.model.Pessoa;
import com.github.giandemanincor.bankapp.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Conta> criarConta(@RequestBody Pessoa pessoa) throws DefaultException {
        return ResponseEntity.ok(contaService.criarConta(pessoa));
    }
}
