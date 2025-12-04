package com.produtoapi.service;



 // Usa o Passo 2
import com.produtoapi.model.ProdutoF;
import com.produtoapi.repository.ProdutoFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoFService {

    @Autowired
    private ProdutoFRepository repository; // Injeta a ferramenta do banco

    public List<ProdutoF> listarTodos() {
        return repository.findAll();
    }

    public Optional<ProdutoF> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public ProdutoF salvar(ProdutoF produto) {
        return repository.save(produto);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
