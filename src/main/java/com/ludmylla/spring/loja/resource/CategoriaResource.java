package com.ludmylla.spring.loja.resource;

import java.util.ArrayList;
import java.util.List;

import com.ludmylla.spring.loja.dto.CategoriaDto2;
import com.ludmylla.spring.loja.dto.CategoriaPutDto;
import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping(path = "/categoria")
    public ResponseEntity<String> cadastrar(@RequestBody CategoriaDto2 categoriaDto2) {

        try {
            Categoria categoria = new Categoria();
            categoria.setNome(categoriaDto2.getNome());

            Long id = categoriaService.salvar(categoria);
            return ResponseEntity.ok("Categoria adicionada id: " + id);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Falha: " + ex.getMessage());
        }

    }

    @GetMapping(path = "/categoria")
    public ResponseEntity<List<CategoriaDto2>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listar();
        List<CategoriaDto2> list = new ArrayList<>();

        for (int i = 0; i < categorias.size(); i++) {
            CategoriaDto2 categoriaDto2 = new CategoriaDto2();
            categoriaDto2.setNome(categorias.get(i).getNome());
            list.add(categoriaDto2);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/categoria/{nome}")
    public ResponseEntity<List<CategoriaDto2>> listaPorNome(String nome) {
        List<Categoria> categorias = categoriaService.findByLikeName(nome);
        List<CategoriaDto2> listCatDtos = new ArrayList<>();
        for (int i = 0; i < categorias.size(); i++) {
            CategoriaDto2 categoriaDto2 = new CategoriaDto2();
            categoriaDto2.setNome(categorias.get(i).getNome());
            listCatDtos.add(categoriaDto2);
        }
        return ResponseEntity.ok(listCatDtos);
    }

    @PutMapping(path = "/categoria")
    public ResponseEntity<String> atualizar(CategoriaPutDto categoriaPutDto) {
        categoriaService.atualizar(categoriaPutDto.getId(), categoriaPutDto.getNome());
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping(path = "/categoria")
    public ResponseEntity<String> deletar(Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.ok("ok");
    }
}
