package com.uninter.ads.back_end.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.uninter.ads.back_end.model.entity.Cliente;


@Service
public class ClienteService extends GenericService<Cliente, Long> {
	
	@Autowired
	public ClienteService(JpaRepository<Cliente, Long> repository) {
		super(repository);// Passa o repository para o Pai		
	}

	public Cliente update(Long id, Cliente itemAtualizado) {
		Cliente itemFind = findById(id) //USANDO MÉTODO DO PAI
	            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	        
		itemFind.setNome(itemAtualizado.getNome());
	    itemFind.setClienteDesde(itemAtualizado.getClienteDesde());
	    
	        
	    return create(itemFind); //USANDO MÉTODO DO PAI
	}
	
	
	// .. DEMAIS métodos estão na classe abstrata "GenericService"

}
