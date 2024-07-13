package com.example.LiteraAlura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String nome;

    private int anoDeNascimento;

    private int anoDeFalecimento;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Livro> livrosEscritos;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(int anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public int getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    public void setAnoDeFalecimento(int anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
    }

    public List<Livro> getLivrosEscritos() {
        return livrosEscritos;
    }

    public void setLivrosEscritos(List<Livro> livrosEscritos) {
        this.livrosEscritos = livrosEscritos;
    }

    @Override
    public String toString() {

        String livrosTitulos = livrosEscritos != null ? livrosEscritos.stream()
                .map(Livro::getTitulo)
                .collect(Collectors.joining(", ")) : "N/A";

        return  "**************Autor**************" + '\n' +
                "Autor: '" + nome + '\n' +
                "Ano de nascimento: " + anoDeNascimento + '\n' +
                "Ano de falecimento :" + anoDeFalecimento + '\n' +
                "Livros= [" + livrosTitulos + "]" + '\n' +
                "*********************************" + '\n';
    }
}


