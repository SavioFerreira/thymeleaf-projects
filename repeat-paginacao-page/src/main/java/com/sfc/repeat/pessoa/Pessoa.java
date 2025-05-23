package com.sfc.repeat.pessoa;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer idade;
    private String genero;
    @Column(name = "data_inclusao")
    private LocalDateTime dataInclusao;

}
