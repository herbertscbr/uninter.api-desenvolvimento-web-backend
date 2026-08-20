package com.uninter.ads.back_end.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uninter.ads.back_end.model.entity.Cliente;
import com.uninter.ads.back_end.model.entity.Pedido;
import com.uninter.ads.back_end.model.entity.Produto;
import com.uninter.ads.back_end.repository.ClienteRepository;
import com.uninter.ads.back_end.repository.PedidoRepository;
import com.uninter.ads.back_end.repository.ProdutoRepository;


@Service
@Transactional
public class PedidoService extends GenericService<Pedido, Long> {
	
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;
	
    @Autowired
    private PedidoRepository pedidoRepository;
    
	@Autowired
	public PedidoService(JpaRepository<Pedido, Long> repository) {
		super(repository);// Passa o repository para o Pai		
	}

	
	
    // Sobrescreve o findById para usar o EntityGraph
    @Override
    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);  // ← Usa o repository com EntityGraph
    }
    
    @Override
    public Pedido create(Pedido pedido) {    	
        // Busca o cliente e produto completos antes de salvar
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        Produto produto = produtoRepository.findById(pedido.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        
        // Substitui os objetos parciais pelos completos
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        
        // Salva
        Pedido salvo = super.create(pedido);
        
        // Retorna com os dados carregados
        return findById(salvo.getId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
	
    
    
    
	/**
	 * método "update"
	 * fiz alguns testes,somente para treino, sem pretensões
	 *   JSON de atualzação enviará os objetos "cliente" e "produto", além dos campos "normais", como "quantidade":
			 {
			    "quantidade": 2,
			    "cliente": {
			        "id": 1
			    },
			    "produto": {
			        "id": 1
			    }
			}
	 * @param id
	 * @param itemAtualizado
	 * @return
	 */
	public Pedido update(Long id, Pedido itemAtualizado) {
		Pedido itemFind = findById(id) //USANDO MÉTODO DO PAI
	            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
	        
	    
		
        // Busca o cliente e produto do banco
        Cliente cliente = clienteRepository.findById(itemAtualizado.getCliente().getId())
        		.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        Produto produto = produtoRepository.findById(itemAtualizado.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        itemFind.setCliente(cliente);
        itemFind.setProduto(produto);
        itemFind.setQuantidade(itemAtualizado.getQuantidade());
        
        //OUTRAS maneiras de fazer:         
         //outra forma seria usar "getReferenceById" que NÃO CONSULTA O BANCO, somente cria uma referência 'proxy'.
           //Cliente cliente = clienteRepository.getReferenceById(itemAtualizado.getCliente().getId());
           //Produto produto = produtoRepository.getReferenceById(itemAtualizado.getProduto().getId());
     
        // SUBSTITUI os objetos inteiros, MAS não consulta no banco:
          //itemFind.setCliente(itemAtualizado.getCliente());
          //itemFind.setProduto(itemAtualizado.getProduto());
	    
	        
	    return create(itemFind); //USANDO MÉTODO DO PAI
	}
	
	
	
	// .. DEMAIS métodos estão na classe abstrata "GenericService"
		

}



