package com.uninter.ads.back_end.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;



@Entity
@JsonIgnoreProperties({"pedidos"})  // Ignora a lista de pedidos - evita loop infinito ao obter a lista em Pedidos
public class Cliente extends AbstractEntity<Long> {
	
	private static final long serialVersionUID = 2760877286232268629L;
	
	@NotBlank(message="Nome da Cliente é obrigatório")
	@Size(min=6, max=100, message="O nome deve conter no minimo {min} caractere e no máximo {max}")
	@Column(nullable=false, unique=true, length=100)
	private String nome;
	
	
	@NotNull(message = "o campo clienteDesde pode ser nulo")
	@PastOrPresent(message = "A data de cadastro não pode ser no futuro")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // -> 2026-08-16
	@Column(nullable = false) //name = "cliente_desde" já ficou certo no banco mysql 
	private LocalDate clienteDesde;
	
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL) //  "cliente" é o nome do campo em "Pedido" que faz a referência
	private List<Pedido> pedidos = new ArrayList<>();
	
	
	
	
	
	// ###############################################################################################
	
	@Override
	public String toString() {
		return "Cliente [id=" + getId() + ", nome=" + nome + ", clienteDesde=" + clienteDesde + ", pedidos=" + pedidos + "]";
	}	
	
	// Geters e Setters
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getClienteDesde() {
		return clienteDesde;
	}
	public void setClienteDesde(LocalDate clienteDesde) {
		this.clienteDesde = clienteDesde;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setItens(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}
