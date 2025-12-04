package com.produtoapi.repository;


import com.produtoapi.model.ProdutoFA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoFARepository extends JpaRepository<ProdutoFA, Long> {

}
