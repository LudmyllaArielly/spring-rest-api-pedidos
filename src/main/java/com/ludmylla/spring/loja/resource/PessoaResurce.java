package com.ludmylla.spring.loja.resource;

import java.util.ArrayList;
import java.util.List;

import com.ludmylla.spring.loja.dto.PessoaDto;
import com.ludmylla.spring.loja.dto.PessoaGetDto;
import com.ludmylla.spring.loja.dto.PessoaPUTDto;
import com.ludmylla.spring.loja.mapper.PessoaMapper;
import com.ludmylla.spring.loja.model.Pessoa;
import com.ludmylla.spring.loja.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaResurce {

    @Autowired
    private PessoaService pessoaService;


    @PostMapping(path = "/pessoa")
    public ResponseEntity<Long> cadastrarPessoa(@RequestBody PessoaDto pessoaDto) {

        Pessoa pessoa = PessoaMapper.INSTANCE.dtoToPessoa(pessoaDto);

//        Pessoa pessoa = new Pessoa();
//        pessoa.setCpf(pessoaDto.getCpf());
//        pessoa.setName(pessoaDto.getName());
//        pessoa.setSenha(pessoaDto.getSenha());

        //insert
        Long id = pessoaService.salvar(pessoa);
        return ResponseEntity.ok(id);

    }

    @PostMapping(path = "/cadastroCliente")
    public ResponseEntity<String> cadastrarCliente(@RequestBody PessoaDto pessoaDto) {
        //validaçoes
        //banco verificação
        //salvei
        //retonei
        //exibindo a msg
        return ResponseEntity.ok("cliente cadatro");

    }




    @PutMapping(path = "/pessoa")
    public ResponseEntity<String> atualizar(PessoaPUTDto pessoaPUTDto) {
        //atulizar
        pessoaService.atualizar(pessoaPUTDto.getId(), pessoaPUTDto.getName());

        return ResponseEntity.ok("ok");

    }

    @DeleteMapping(path = "/pessoa")
    public ResponseEntity<String> deletarPessoa(long id) {

        pessoaService.deltar(id);

        return ResponseEntity.ok("ok");

    }

}
