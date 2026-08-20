package com.uninter.ads.back_end.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// ver pdf aula pŕatica 5 para as validações
@Entity
public class Pedido extends AbstractEntity<Long> { // seria como um "itemPedido", contendo cada item de compra, porém, foi seguido exatamente o que foi pedido no trabalho.
	
	private static final long serialVersionUID = 1162681956792327459L;

	@NotNull(message = "O campo Quantidade não pode ser nulo")
	@Min(value = 1, message= "valor mínimo não pode ser 0 ou negativo")
	@Max(value = 1000, message= "valor máximo não pode maior que 1.000")
	@Column( nullable=false)
	private Integer quantidade;
	
	
	// MAPEAMENTOS : O lado "filho" Pedido possui o mapeamento "@ManyToOne" para "cliente" e para "produto"	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;
	
	
	
	
	
	// ###############################################################################################
	
	@Override
	public String toString() {
		return "Pedido [id=" + getId() + ", clientId=" + cliente.getId() + ", ProdutoId=" + produto.getId() + ", quantidade=" + quantidade + ", cliente="
				+ cliente + ", produto=" + produto + "]";
	}

	// Geters e Setters
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
