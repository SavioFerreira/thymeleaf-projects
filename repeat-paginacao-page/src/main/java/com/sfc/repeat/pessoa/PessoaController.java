package com.sfc.repeat.pessoa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pessoa")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @GetMapping("/add")
    public String formAddPessoa(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "form-save-pessoa";
    }

    @GetMapping("/list")
    public String formPessoas(Model model,
                              @PageableDefault(size = 10) Pageable pageable) {
        Pageable ordenadoPorData = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "dataInclusao")
        );

        Page<Pessoa> pessoas = service.listarTodos(ordenadoPorData);
        model.addAttribute("pessoas", pessoas);
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
