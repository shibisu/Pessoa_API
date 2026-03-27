package br.com.senac.pessoa_api.Controller;

import br.com.senac.pessoa_api.dtos.PessoasResquestDTO;
import br.com.senac.pessoa_api.entidades.Pessoas;
import br.com.senac.pessoa_api.repositores.PessoasRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoas")
@CrossOrigin
public class PossoasController {

    private PessoasRepository pessoasRepository;

    public  PossoasController(PessoasRepository pessoasRepository){
        this.pessoasRepository = pessoasRepository;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoas>> listar(){
        List<Pessoas> pessoasList = pessoasRepository.findAll();

        if (pessoasList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }
        return ResponseEntity.ok(pessoasList);
    }

    @GetMapping("/criar")
    public  ResponseEntity<Pessoas> criar
            (@RequestBody PessoasResquestDTO pessoa){

        Pessoas pessoaPesist = new Pessoas();

        pessoaPesist.setNome(pessoa.getNome());
        pessoaPesist.setSobrenome(pessoa.getSobrenome());

        Pessoas retorno = pessoasRepository.save(pessoaPesist);
        return ResponseEntity.status(201).body(retorno);
    }

    @PutMapping("/atualizar/{id}")
    public  ResponseEntity<Pessoas> atualizar(
            @RequestBody PessoasResquestDTO pessoa,
            @PathVariable long id
    ){
        if (pessoasRepository.existsById(id)){
            Pessoas pessoaPesist = new Pessoas();
            pessoaPesist.setNome(pessoa.getNome());
            pessoaPesist.setSobrenome(pessoa.getSobrenome());
            pessoaPesist.setId(id);
            Pessoas retorno = pessoasRepository.save(pessoaPesist);

            return ResponseEntity.ok(retorno);


        }
        return  ResponseEntity.badRequest().body(null);

    }
    @DeleteMapping("/deletar/{id}")
    public  ResponseEntity<Void> deletar(
            @PathVariable long id){
        if (pessoasRepository.existsById(id)){
            pessoasRepository.deleteById(id);

            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
