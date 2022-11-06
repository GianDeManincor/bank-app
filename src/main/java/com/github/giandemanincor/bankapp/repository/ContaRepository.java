package com.github.giandemanincor.bankapp.repository;

import com.github.giandemanincor.bankapp.model.Conta;
import com.github.giandemanincor.bankapp.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    public Optional<Conta> findByPessoa_Cpf(String cpf);
}
