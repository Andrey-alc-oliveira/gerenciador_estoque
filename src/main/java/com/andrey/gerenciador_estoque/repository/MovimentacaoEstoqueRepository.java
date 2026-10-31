package com.andrey.gerenciador_estoque.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.andrey.gerenciador_estoque.domain.MovimentacaoEstoque;

import java.util.List;
import java.time.LocalDateTime;

import com.andrey.gerenciador_estoque.enums.TipoMovimentacao;


public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {

    boolean existsByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);

    List<MovimentacaoEstoque> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);

    boolean existsByDataHoraBetweenAndTipoMovimentacao(LocalDateTime inicio, LocalDateTime fim, TipoMovimentacao tipoMovimentacao);

    List<MovimentacaoEstoque> findByDataHoraBetweenAndTipoMovimentacao(LocalDateTime inicio, LocalDateTime fim, TipoMovimentacao tipoMovimentacao);


}
