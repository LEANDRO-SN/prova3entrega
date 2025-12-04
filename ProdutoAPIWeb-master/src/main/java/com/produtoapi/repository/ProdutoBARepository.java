package com.produtoapi.repository;

import com.produtoapi.model.ProdutoBA;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProdutoBARepository extends JpaRepository<ProdutoBA, Long> {
}
