package com.uninter.ads.back_end.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
/*import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@Data// "Data "não funcionou
@AllArgsConstructor
@NoArgsConstructor*/

@Entity
@JsonIgnoreProperties({"pedidos"})  // Ignora a lista de pedidos - evita loop infinito ao obter a lista em Pedidos
public class Produto extends AbstractEntity<Long> {	

	private static final long serialVersionUID = 1638499891737714492L;
	
	@NotBlank(message="Nome da Produto é obrigatório")
	@Size(min=6, max=100, message="O nome deve conter no minimo {min} caractere e no máximo {max}")
	@Column( nullable=false, unique=true, length=100)
	private String nome;
	
	
	@NotNull(message = "O campo Preço não pode ser nulo")
	@DecimalMin(value = "0.01", message= "valor mínimo não pode ser 0 ou negativo") 
	@DecimalMax(value = "9999.99", message= "valor máximo é de R$ 9.999,99")
	@Column( nullable=false, precision = 6, scale = 2)// 6 digitos, e duas casas decimais
	private BigDecimal preco;
	
	
	@NotNull(message="Estoque da Produto é obrigatório")
	private Boolean estoque;
	
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL) //  "produto" é o nome do campo em "Pedido" que faz a referência
	private List<Pedido> pedidos = new ArrayList<>();
	
	
	
	
	
	// ###############################################################################################
	
	@Override
	public String toString() {
		return "Produto [id=" + getId() + ", nome=" + nome + ", preco=" + preco + ", estoque=" + estoque + ", pedidos=" + pedidos + "]";
	}

	// Geters e Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public Boolean getEstoque() {
		return estoque;
	}
	public void setEstoque(Boolean estoque) {
		this.estoque = estoque;
	}


	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setItens(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	
}
