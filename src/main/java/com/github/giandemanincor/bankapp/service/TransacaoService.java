package com.github.giandemanincor.bankapp.service;

import com.github.giandemanincor.bankapp.exception.ContaNotFoundException;
import com.github.giandemanincor.bankapp.exception.DefaultException;
import com.github.giandemanincor.bankapp.exception.SaldoInsuficienteException;
import com.github.giandemanincor.bankapp.exception.TransacaoSuperiorPermitidoException;
import com.github.giandemanincor.bankapp.model.Conta;
import com.github.giandemanincor.bankapp.model.Transacao;
import com.github.giandemanincor.bankapp.repository.ContaRepository;
import com.github.giandemanincor.bankapp.repository.TransacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransacaoService {

    private ContaRepository contaRepository;

    private TransacaoRepository transacaoRepository;

    public TransacaoService(ContaRepository contaRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public void transacionar(Transacao transacao) throws DefaultException {
        switch (transacao.getTipo().getCode()) {
            case 1:
                transferir(transacao);
                break;
            case 2:
                depositar(transacao);
                break;
        }
    }

    private void depositar(Transacao transacao) throws ContaNotFoundException {
        Optional<Conta> destinatario = contaRepository.findById(transacao.getDestinatarioId());

        Conta conta = verificarConta(destinatario);
        conta.setSaldo(conta.getSaldo().add(transacao.getValor()));

        contaRepository.save(conta);
        transacaoRepository.save(transacao);
    }

    private void transferir(Transacao transacao) throws DefaultException {
        Optional<Conta> remetente = contaRepository.findById(transacao.getRemetenteId());
        Optional<Conta> destinatario = contaRepository.findById(transacao.getDestinatarioId());

        Conta remetenteValido = verificarConta(remetente);
        Conta destinatarioValido = verificarConta(destinatario);

        if (transacao.getValor().doubleValue() > 2000) {
            throw new TransacaoSuperiorPermitidoException(HttpStatus.UNPROCESSABLE_ENTITY.value(), "O Limite por transação é de no máximo R$2000,00");
        }

        if (remetenteValido.getSaldo().doubleValue() < transacao.getValor().doubleValue()) {
            throw new SaldoInsuficienteException(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Saldo insuficiente para realizar a transação");
        }

        remetenteValido.setSaldo(remetenteValido.getSaldo().subtract(transacao.getValor()));
        destinatarioValido.setSaldo(destinatarioValido.getSaldo().add(transacao.getValor()));

        contaRepository.save(remetenteValido);
        contaRepository.save(destinatarioValido);
        transacaoRepository.save(transacao);
    }

    private Conta verificarConta(Optional<Conta> conta) throws ContaNotFoundException {
        if (conta.isEmpty()) {
            throw new ContaNotFoundException(HttpStatus.NOT_FOUND.value(), "A conta informada não existe.");
        }
        return conta.get();
    }

}
