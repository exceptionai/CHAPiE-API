package br.com.exception.chapie.repository;

import br.com.exception.chapie.model.Execucao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecucaoRepository extends JpaRepository<Execucao, Integer> {
}
