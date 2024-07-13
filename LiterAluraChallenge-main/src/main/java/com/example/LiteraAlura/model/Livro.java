package com.example.LiteraAlura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private EnumIdiomas idioma;

    private int dowloads;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public EnumIdiomas getIdioma() {
        return idioma;
    }

    public void setIdioma(EnumIdiomas idioma) {
        this.idioma = idioma;
    }

    public int getDowloads() {
        return dowloads;
    }

    public void setDowloads(int dowloads) {
        this.dowloads = dowloads;
    }


    @Override
    public String toString() {
        return   "*********** LIVRO *************"+ '\n' +
                "Titulo: " + titulo + '\n' +
                "Autor: " + autor.getNome() + '\n' +
                "Idioma= " + idioma +  '\n' +
                "Número de dowloads= " + dowloads + '\n'+
                "**********************************\n\n";

    }
}
