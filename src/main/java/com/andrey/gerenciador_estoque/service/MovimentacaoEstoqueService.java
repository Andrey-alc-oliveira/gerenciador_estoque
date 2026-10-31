package com.andrey.gerenciador_estoque.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.andrey.gerenciador_estoque.domain.MovimentacaoEstoque;
import com.andrey.gerenciador_estoque.enums.TipoMovimentacao;
import com.andrey.gerenciador_estoque.repository.MovimentacaoEstoqueRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MovimentacaoEstoqueService {
private final MovimentacaoEstoqueRepository repository;

   

    public List<MovimentacaoEstoque> listarPelaData(LocalDate data){
        LocalDateTime horaInicio = data.atStartOfDay();
        LocalDateTime horaFinal = data.atTime(LocalTime.MAX);

        boolean existe = repository.existsByDataHoraBetween(horaInicio, horaFinal);
      if(!existe){
        throw new IllegalArgumentException("Nenhuma movimentação feita nesse dia.");
      }
        return repository.findByDataHoraBetween(horaInicio,horaFinal);

    }

    public List<MovimentacaoEstoque> listarMovimentacaoPelaSemana(LocalDate data, TipoMovimentacao tMovimentacao){
      
      LocalDateTime diaInicial = data.minusDays(7).atStartOfDay();
      LocalDateTime diaAtual = LocalDate.now().atTime(LocalTime.MAX);
      boolean existe;
      if(tMovimentacao == null){
        existe = repository.existsByDataHoraBetween(diaInicial ,diaAtual );
      }else{
         existe = repository.existsByDataHoraBetweenAndTipoMovimentacao(diaInicial, diaAtual, tMovimentacao);
    }
      if(!existe){
        throw new IllegalArgumentException("Nenhuma movimentação na semana.");
      }
      return tMovimentacao == null 
      ? repository.findByDataHoraBetween(diaInicial, diaAtual)
      : repository.findByDataHoraBetweenAndTipoMovimentacao(diaInicial, diaAtual, tMovimentacao) ;
      
    }
    
}
