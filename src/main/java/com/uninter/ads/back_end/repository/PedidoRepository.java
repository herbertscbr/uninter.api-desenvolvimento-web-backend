package com.uninter.ads.back_end.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uninter.ads.back_end.model.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
    @EntityGraph(attributePaths = {"cliente", "produto"})//boa prática que garante que os relacionamentos sempre venham carregados 
    Optional<Pedido> findById(Long id);

}
