package com.andrey.gerenciador_estoque.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.andrey.gerenciador_estoque.domain.Produto;
import com.andrey.gerenciador_estoque.repository.ProdutoRepository;


import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ProdutoService {

private final ProdutoRepository repository;
    public List<Produto> listarProdutos(){
        return repository.findAll();
    }
    public Produto adcionarNovoProduto(Produto produto){
        boolean existe = repository.existsByCodigoBarras(produto.getCodigoBarras());
        if(existe){
            throw new IllegalArgumentException("Produto já existe");
        }
        return repository.save(produto);
    }

    public void deletarProdutoPeloID(Long id){
        repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Produto não existe no estoque"));
        repository.deleteById(id);
    }

}
