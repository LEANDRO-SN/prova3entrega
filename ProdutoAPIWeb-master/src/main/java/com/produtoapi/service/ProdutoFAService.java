package com.produtoapi.service;



 // Usa o Passo 2
import com.produtoapi.model.ProdutoFA;
import com.produtoapi.repository.ProdutoFARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoFAService {

    @Autowired
    private ProdutoFARepository repository; // Injeta a ferramenta do banco

    public List<ProdutoFA> listarTodos() {
        return repository.findAll();
    }

    public Optional<ProdutoFA> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public ProdutoFA salvar(ProdutoFA produto) {
        return repository.save(produto);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
