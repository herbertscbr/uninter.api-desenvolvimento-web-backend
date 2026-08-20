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

import com.uninter.ads.back_end.model.entity.Produto;
import com.uninter.ads.back_end.model.service.ProdutoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

//import org.springframework.http.MediaType;

@RestController
@Tag(name = "ProdutoController", description = "Endpoints para gerenciamento de Produtos")
@RequestMapping(value={ "/produtos"/*, "/produtos/"*/ })
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	
	@GetMapping
	public List<Produto> findAll() {
		return service.findAll(); //return repository.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return service.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());// build -> sem corpo
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Produto item) {
		Produto itemCriado = service.create(item); //return repository.save(linha);
		return ResponseEntity.ok(itemCriado);
		
		//ou com location:
		/*ResponseEntity
        .status(HttpStatus.CREATED)
        .location(ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(itemCriado.getId())
            .toUri())
        .body(itemCriado);*/
	}
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);	// Se der erro, lança exception
		//return ResponseEntity.ok().build(); // build -> sem corpo
		return ResponseEntity.ok(Map.of("message", "Produto deletado com sucesso"));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Produto item) {
		Produto updated = service.update(id, item);
		return ResponseEntity.ok(updated); // atalho para ResponseEntity.ok().body(updated)		
	}
	
}









/*como era o retorno do findById, ligado diretamente ao repositorio: 
return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
		.orElse(ResponseEntity.notFound().build());*/

/*
 /*como era o retorno do delete, ligado diretamente ao repositorio: 
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());  
  */


/*como era o retorno do update, ligado diretamente ao repositorio:
/*return repository.findById(id).map(record -> {
	record.setNome(linha.getNome());
	record.setPreco(linha.getPreco());
	record.setEstoque(linha.getEstoque());
	Produto updated = repository.save(record);			
	return ResponseEntity.ok().body(updated);
	
}).orElse(ResponseEntity.notFound().build());*/
