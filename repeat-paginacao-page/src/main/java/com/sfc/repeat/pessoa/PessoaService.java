package com.sfc.repeat.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PessoaService {
    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoa salvar(Pessoa pessoa) {
        pessoa.setDataInclusao(LocalDateTime.now());
        return repository.save(pessoa);
    }

    public Page<Pessoa> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Pessoa buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }


}

