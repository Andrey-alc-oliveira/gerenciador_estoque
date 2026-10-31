package com.andrey.gerenciador_estoque.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import com.andrey.gerenciador_estoque.enums.TipoMovimentacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table
@AllArgsConstructor 
@NoArgsConstructor
@Getter
@Setter 
public class MovimentacaoEstoque {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn( name= "produto_id", nullable= false)
    private Produto produto;

    @Column(nullable= false)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable= false)
    private TipoMovimentacao tipoMovimentacao;
    
    @CreationTimestamp
    @Column(nullable= false, updatable= false) 
    private LocalDateTime dataHora;
}
