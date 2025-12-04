package com.produtoapi.controller;

import com.produtoapi.model.ProdutoFA;
import com.produtoapi.service.ProdutoFAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/futebol-americano")
@CrossOrigin(origins = "*")
public class ProdutoFAController {

    @Autowired
    private ProdutoFAService service;

    @GetMapping
    public List<ProdutoFA> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoFA> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProdutoFA criar(@RequestBody ProdutoFA produto) {
        return service.salvar(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoFA> atualizar(@PathVariable Long id, @RequestBody ProdutoFA produto) {
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