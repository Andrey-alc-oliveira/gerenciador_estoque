package com.andrey.gerenciador_estoque.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "produtos")
@AllArgsConstructor 
@NoArgsConstructor 
@Setter 
@Getter 
public class Produto {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column (nullable = false, unique = true)
    private String codigoBarras;

    @Column (nullable = false)
    private BigDecimal precoFornecedor;

    @Column (nullable = false)
    private BigDecimal precoVenda;

    @Column (nullable = false)
    private int quantidadeEstoque;

    @Column (nullable = false)
    private BigDecimal aliquotaImposto;

    public Produto(String nome, String codigoBarras, BigDecimal precoFornecedor, BigDecimal precoVenda, int quantidadeEstoque, BigDecimal aliquotaImposto) {
        this.nome = nome;
        this.codigoBarras = codigoBarras;
        this.precoFornecedor = precoFornecedor;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.aliquotaImposto = aliquotaImposto;
    }

    public BigDecimal calcularImposto(){
        if(aliquotaImposto == null || precoVenda == null){
            return BigDecimal.ZERO;
        }

        return precoVenda.multiply(aliquotaImposto)
                        .divide(new BigDecimal("100"),2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculoLucroLiquidoUnitario(){
        if(precoVenda == null || precoFornecedor == null){
            return BigDecimal.ZERO;
        }
        BigDecimal imposto = calcularImposto();
        return precoVenda.subtract(precoFornecedor).subtract(imposto);
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }
}
