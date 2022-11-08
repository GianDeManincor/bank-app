package com.github.giandemanincor.bankapp.repository;

import com.github.giandemanincor.bankapp.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
}
