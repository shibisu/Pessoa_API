package br.com.senac.pessoa_api.Controller;

import br.com.senac.pessoa_api.dtos.PessoasResquestDTO;
import br.com.senac.pessoa_api.entidades.Pessoas;
import br.com.senac.pessoa_api.repositores.PessoasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pessoas")
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
    public  ResponseEntity<Pessoas> criar(@RequestBody PessoasResquestDTO pessoa){

        Pessoas pessoaPesist = new Pessoas();
        pessoaPesist.setNome(pessoa.getNome());
        pessoaPesist.setSobenome(pessoa.getSobrenome());
        pessoasRepository.save(pessoaPesist);
        return ResponseEntity.status(201).body()
    }
}
