package com.sfc.repeat.pessoa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public String formAddPessoa(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "form-save-pessoa";
    }

    @GetMapping("/list")
    public String formPessoas(Model model) {
        model.addAttribute("pessoas", service.listarTodos());
        return "form-pessoa";
    }



    @PostMapping
    public String salvarPessoa(@ModelAttribute Pessoa pessoa) {
        service.salvar(pessoa);
        return "redirect:/pessoa/list?sucesso";
    }

    @GetMapping("/repetir/{id}")
    public String repetirCadastro(@PathVariable Long id) {
        Pessoa pessoaOriginal = service.buscarPorId(id);
        Pessoa copia = new Pessoa();
        copia.setNome(pessoaOriginal.getNome());
        copia.setIdade(pessoaOriginal.getIdade());
        copia.setGenero(pessoaOriginal.getGenero());
        service.salvar(copia);
        return "redirect:/pessoa/list?repetido";
    }

}
