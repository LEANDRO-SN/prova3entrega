package com.produtoapi.repository;


import com.produtoapi.model.ProdutoF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoFRepository extends JpaRepository<ProdutoF, Long> {

}
