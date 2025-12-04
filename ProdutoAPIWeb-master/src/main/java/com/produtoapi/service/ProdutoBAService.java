package com.produtoapi.service;


// Usa o Passo 2
import com.produtoapi.model.ProdutoBA;
import com.produtoapi.model.ProdutoF;
import com.produtoapi.repository.ProdutoBARepository;
import com.produtoapi.repository.ProdutoFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoBAService {
    @Autowired
    private ProdutoBARepository repository; // Injeta a ferramenta do banco

    public List<ProdutoBA> listarTodos() {
        return repository.findAll();
    }

    public Optional<ProdutoBA> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public ProdutoBA salvar(ProdutoBA produto) {
        return repository.save(produto);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
