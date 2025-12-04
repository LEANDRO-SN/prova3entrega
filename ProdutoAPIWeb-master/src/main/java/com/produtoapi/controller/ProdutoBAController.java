package com.produtoapi.controller;

import com.produtoapi.model.ProdutoBA;

import com.produtoapi.service.ProdutoBAService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/basquetebol")
@CrossOrigin(origins = "*")
public class ProdutoBAController {

    @Autowired
    private ProdutoBAService service;

    @GetMapping
    public List<ProdutoBA> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoBA> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProdutoBA criar(@RequestBody ProdutoBA produto) {
        return service.salvar(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoBA> atualizar(@PathVariable Long id, @RequestBody ProdutoBA produto) {
        return service.buscarPorId(id)
                .map(existente -> {
                    existente.setNome(produto.getNome());

                    if(produto.getDescricao() != null) existente.setDescricao(produto.getDescricao());

                    existente.setPreco(produto.getPreco());


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