package com.andrey.gerenciador_estoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrey.gerenciador_estoque.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByCodigoBarras(String codigoBarras);

    boolean existsByCodigoBarras(String codigoBarras);

}
