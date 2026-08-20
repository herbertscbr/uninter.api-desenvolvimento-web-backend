package com.uninter.ads.back_end.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uninter.ads.back_end.model.entity.Pedido;
import com.uninter.ads.back_end.model.service.PedidoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "PedidoController", description = "Endpoints para gerenciamento de Pedidos")
@RequestMapping(value={"/pedidos" /*, "/pedidos/"*/})
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping
	public List<Pedido> findAll() {
		return service.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return service.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Pedido item) {
		Pedido itemCriado = service.create(item);
		return ResponseEntity.ok(itemCriado);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok(Map.of("message", "Pedido "+ id + " deletado com sucesso"));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Pedido item) {
		Pedido updated = service.update(id, item);
		return ResponseEntity.ok(updated);		
	}

}
