package br.com.cleverson.springmysql.controller;

import br.com.cleverson.springmysql.controller.dto.PessoaRQ;
import br.com.cleverson.springmysql.controller.dto.PessoaRs;
import br.com.cleverson.springmysql.model.Pessoa;
import br.com.cleverson.springmysql.repository.PessoaCustomRepository;
import br.com.cleverson.springmysql.repository.PessoaRepository;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    private final PessoaCustomRepository pessoaCustomRepository;

    public PessoaController(PessoaRepository pessoaRepository, PessoaCustomRepository pessoaCustomRepository) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaCustomRepository = pessoaCustomRepository;
    }

    @GetMapping("/pessoas")
    public List<PessoaRs> findAll(){
        var pessoas = pessoaRepository.findAll();
        return pessoas
                .stream()
                .map(PessoaRs::converter)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PessoaRs findById(@PathVariable("id") Long id){
        var pessoa =  pessoaRepository.getById(id);
        return PessoaRs.converter(pessoa);
    }

    @PostMapping("/")
    public void savePessoa(@RequestBody PessoaRQ pessoaRQ){
        var p = new Pessoa();
        p.setNome(pessoaRQ.getNome());
        p.setSobrenome(pessoaRQ.getSobrenome());
        pessoaRepository.save(p);
    }

    @PutMapping("/{id}")
    public void updatePessoa(@PathVariable("id") Long id, @RequestBody PessoaRQ pessoaRQ) throws Exception {
        var p = pessoaRepository.findById(id);
        if(p.isPresent()){
            var pessoaSave= p.get();
            pessoaSave.setNome(pessoaRQ.getNome());
            pessoaSave.setSobrenome(pessoaRQ.getSobrenome());
            pessoaRepository.save(pessoaSave);
        }else {
            throw new Exception("Pessoa não encontrada");
        }
    }

    @GetMapping("/filter")
    public List<PessoaRs> findPersonByName(@RequestParam("name") String name){
        return this.pessoaRepository.findByNomeContains(name)
                .stream()
                .map(PessoaRs::converter)
                .collect(Collectors.toList());


    }

    @GetMapping("/filter/custom")
    public List<PessoaRs> findPersonByCustom(
            @RequestParam(value = "id",required = false) Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "sobrenome", required = false) String sobrenome
            ){
        return this.pessoaCustomRepository.find(id,name,sobrenome)
                .stream()
                .map(PessoaRs::converter)
                .collect(Collectors.toList());


    }

}
