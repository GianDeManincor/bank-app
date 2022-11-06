package com.github.giandemanincor.bankapp.service;

import com.github.giandemanincor.bankapp.exception.ContaExistException;
import com.github.giandemanincor.bankapp.model.Conta;
import com.github.giandemanincor.bankapp.model.Pessoa;
import com.github.giandemanincor.bankapp.repository.ContaRepository;
import com.github.giandemanincor.bankapp.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@Service
public class ContaService {

    private static final Integer AGENCIA_DEFAULT = 3977;

    private ContaRepository contaRepository;

    private PessoaRepository pessoaRepository;

    public ContaService(ContaRepository repository, PessoaRepository pessoaRepository) {
        this.contaRepository = repository;
        this.pessoaRepository = pessoaRepository;
    }

    public Conta criarConta(Pessoa pessoa) throws ContaExistException {
        Optional<Conta> conta = contaRepository.findByPessoa_Cpf(pessoa.getCpf());
        if (conta.isPresent()) {
            throw new ContaExistException(HttpStatus.UNPROCESSABLE_ENTITY.value(), "A pessoa informada já possui uma conta.");
        }
        return gerarConta(pessoa);
    }

    private Conta gerarConta(Pessoa pessoa) {
        Conta conta = new Conta();
        conta.setAgencia(AGENCIA_DEFAULT);
        conta.setConta(new Random().nextInt(1000) + 9999);
        conta.setSaldo(BigDecimal.valueOf(0));
        conta.setPessoa(cadastrarPessoa(pessoa));
        contaRepository.save(conta);
        return conta;
    }

    private Pessoa cadastrarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

}
