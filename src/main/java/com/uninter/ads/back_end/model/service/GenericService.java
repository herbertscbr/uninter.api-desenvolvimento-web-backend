package com.uninter.ads.back_end.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * GenericService
 * para métodos comuns: findAll, create, delete
 * 
 * @author herbert
 *
 * @param <T>
 * @param <ID>
 */
public class GenericService<T, ID>  {	
	
	protected JpaRepository<T, ID> repository; // será "injetado" pelo Connstrutor
	
	
	/**
	 * Construtor para receber o repository da classe filha 
	 * @param repository
	 */
	public GenericService(JpaRepository<T, ID> repository) {		
		this.repository = repository;
	}

	public List<T> findAll() {
		return repository.findAll();
	}	
	
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}	
	
	public T create(T item) {
		return repository.save(item);
	}	
	
	public void delete( ID id) {
		if (!repository.existsById(id)) {
            throw new RuntimeException("Entidade não encontrada com ID: " + id);
        }
        repository.deleteById(id);		
	}

}
