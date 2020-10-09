package com.ludmylla.spring.loja.resource;

import java.util.ArrayList;
import java.util.List;

import com.ludmylla.spring.loja.dto.CategoriaDto;
import com.ludmylla.spring.loja.dto.CategoriaDto2;
import com.ludmylla.spring.loja.dto.ProdutoDto;
import com.ludmylla.spring.loja.dto.ProdutoDto2;
import com.ludmylla.spring.loja.dto.ProdutoGetDto;
import com.ludmylla.spring.loja.dto.ProdutoPutDto;
import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.model.Produto;
import com.ludmylla.spring.loja.model.ProdutoCategoriaLista;
import com.ludmylla.spring.loja.service.CategoriaService;
import com.ludmylla.spring.loja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoResoruce {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;


    @PostMapping(path = "/produto")
    public ResponseEntity<ProdutoDto> salvar(@RequestBody ProdutoDto produtoDto) {
        //converter o DTO para Entity
        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        // Entity
        // busca categoria
        Categoria categoria = categoriaService.findById(produtoDto.getCategoriaDto().getId());
        produto.setCategoria(categoria);
        //salvar
        produtoService.salvar(produto);

        return ResponseEntity.ok(produtoDto);
    }

    
    @PostMapping(path = "/produto2")
    public ResponseEntity<Long> salvar2(@RequestBody ProdutoDto2 produtoDto2) {
        //converter o DTO para Entity
        ProdutoCategoriaLista produtoCategoriaLista = new ProdutoCategoriaLista();
        produtoCategoriaLista.setNome(produtoDto2.getNome());
        
        // Entity
        // busca lista categoria
        List listCategorias = new ArrayList<>();
        for ( CategoriaDto2 item : produtoDto2.getListCategoriaDto2()) {
            Categoria categoria = categoriaService.findByNameCat(item.getNome());
            listCategorias.add(categoria);
        }

        produtoCategoriaLista.setCategoria(listCategorias);

        Long id = produtoService.salvar(produtoCategoriaLista);

        return ResponseEntity.ok(id);
    }
    
    @GetMapping(path = "/produto2")
    public ResponseEntity<List<ProdutoGetDto>> listarProdutos(){
    	List<ProdutoCategoriaLista> produtos = produtoService.listar();
    	List<ProdutoGetDto> list = new ArrayList<>();
    	for (int i = 0; i < produtos.size(); i++) {
			ProdutoGetDto produtoGetDto = new ProdutoGetDto();
			produtoGetDto.setNome(produtos.get(i).getNome());		
			list.add(produtoGetDto);
		}
		return ResponseEntity.ok(list);
    }
    
    
    @PutMapping(path = "/produto2")
    public ResponseEntity<String> atualizar(ProdutoPutDto produtoPutDto){
    	
    	ProdutoCategoriaLista produtocategoriaLista = new ProdutoCategoriaLista();
    	produtocategoriaLista.setId(produtoPutDto.getId());
    	produtocategoriaLista.setNome(produtoPutDto.getNome());
    	
    	List listaCategorias = new ArrayList<>();
    	for (CategoriaDto2 item : produtoPutDto.getListCategoriaDto2()) {
 			Categoria categoria = categoriaService.findByNameCat(item.getNome());
			listaCategorias.add(categoria);
		}
	
    	produtocategoriaLista.setCategoria(listaCategorias);
    	
    	produtoService.atualizar(produtocategoriaLista);
    	return ResponseEntity.ok("ok");
    }
    
    @DeleteMapping(path = "/produto2")
    public ResponseEntity<String> delete(Long id){
    	produtoService.deletar(id);;
    	return ResponseEntity.ok("ok");
    }
    

    /*
    @PutMapping(path = "/path/{idCategoria}")
    public ResponseEntity<String> atuliza(@RequestBody String nome, @PathVariable("idCategoria") Integer id) {


        return ResponseEntity.ok("ok" + nome + " " + id);
    }

    @GetMapping(path = "/query")
    public ResponseEntity<String> get(@RequestParam(name = "nome") String nome, @RequestParam(name = "id") String id) {

        return ResponseEntity.ok("ok" + nome + " " + id);
    }*/
}
