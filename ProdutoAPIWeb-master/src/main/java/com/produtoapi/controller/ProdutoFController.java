package com.produtoapi.controller;

import com.produtoapi.model.ProdutoF;
import com.produtoapi.service.ProdutoFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/futebol")
@CrossOrigin(origins = "*")
public class ProdutoFController {

    @Autowired
    private ProdutoFService service;

    @GetMapping
    public List<ProdutoF> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoF> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProdutoF criar(@RequestBody ProdutoF produto) {
        return service.salvar(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoF> atualizar(@PathVariable Long id, @RequestBody ProdutoF produto) {
        return service.buscarPorId(id)
                .map(existente -> {
                    existente.setNome(produto.getNome());
                    // Se o HTML não enviar descrição, mantém a antiga ou fica null
                    if(produto.getDescricao() != null) existente.setDescricao(produto.getDescricao());

                    existente.setPreco(produto.getPreco());

                    // ATUALIZAÇÃO: Campos novos
                    existente.setQuantidade(produto.getQuantidade());
                    existente.setStatus(produto.getStatus());

                    return ResponseEntity.ok(service.salvar(existente));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}