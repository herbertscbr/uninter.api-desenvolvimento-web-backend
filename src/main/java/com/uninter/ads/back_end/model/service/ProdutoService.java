package com.uninter.ads.back_end.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.uninter.ads.back_end.model.entity.Produto;


@Service
public class ProdutoService extends GenericService<Produto, Long> {
	
	@Autowired
	public ProdutoService(JpaRepository<Produto, Long> repository) {
		super(repository);// Passa o repository para o pai		
	}



	public Produto update(Long id, Produto itemAtualizado) {
		Produto itemFind = findById(id) //USANDO MÉTODO DO PAI. Antes era repository.findById
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	        
	    itemFind.setNome(itemAtualizado.getNome());
	    itemFind.setPreco(itemAtualizado.getPreco());
	    itemFind.setEstoque(itemAtualizado.getEstoque());
	        
	    return create(itemFind); //USANDO MÉTODO DO PAI. Antes era: repository.save
	}
	

	
	
	
	//RETIRADOS: estão todos na classe abstrata "GenericService"
	/*public List<Produto> findAll() {
		return repository.findAll();
	}*
	
	/*public Optional<Produto> findById(Long id) {
		return repository.findById(id);
	}*/	
	
	/*public Produto create(Produto item) {
		return repository.save(item);
	}*/	
	
	/*public void delete( Long id) {
		if (!repository.existsById(id)) {
	        throw new RuntimeException("Produto não encontrado");
	    }
	    repository.deleteById(id);		
	}*/

}
