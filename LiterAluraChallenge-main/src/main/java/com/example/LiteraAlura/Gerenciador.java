package com.example.LiteraAlura;

import com.example.LiteraAlura.model.Autor;
import com.example.LiteraAlura.model.EnumIdiomas;
import com.example.LiteraAlura.model.Livro;
import com.example.LiteraAlura.repository.AutorRepository;
import com.example.LiteraAlura.repository.LivroRepository;
import com.example.LiteraAlura.service.ConsultaApiLivros;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

@Component
public class Gerenciador {

    private Scanner leitura = new Scanner(System.in);
    private ConsultaApiLivros apiLivros = new ConsultaApiLivros();

    @Autowired
    private final AutorRepository autorRepository;

    @Autowired
    private final LivroRepository livroRepository;

    public Gerenciador(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    public void Buscarlivro(){

        System.out.println("Digite o nome do livro que você deseja procurar\n");
        var nomeLivro = leitura.nextLine();

        try {

            Livro livro = apiLivros.pesquisarLivro(nomeLivro);

            if (livro != null) {
                Autor autor = livro.getAutor();
                if (autor != null) {

                    Autor autorExistente = autorRepository.findByNome(autor.getNome());
                    if (autorExistente == null) {

                        autorRepository.save(autor);
                    } else {
                        // Se o autor já existir, atualiza o objeto autor com o autor existente no banco
                        autor = autorExistente;
                    }
                    // Associa o autor ao livro
                    livro.setAutor(autor);
                }

                livroRepository.save(livro);
                System.out.println("Livro encontrado e salvo no banco de dados.");


            } else {
                System.out.println("Nenhum livro encontrado com o nome fornecido.");
            }

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Transactional
    public void Listarlivros() {
        List<Livro> livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }

    @Transactional
    public void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    @Transactional
    public void AutoresVivosEm() {
        System.out.println("Digite o ano para Busca:\n");
        var ano = leitura.nextInt();
        List<Autor> autores = autorRepository.findAutoresVivosNoAno(ano);
        System.out.println("Autores vivos no ano de " + ano + "\n");
        autores.forEach(System.out::println);
    }

    @Transactional
    public void ListarlivrosPorIdioma() {
        System.out.println("Qual o Idioma?\n");
        String idiomaEscolhido;
        idiomaEscolhido = leitura.nextLine();


        try {
            EnumIdiomas idioma = EnumIdiomas.valueOf(idiomaEscolhido);

            List<Livro> livros = livroRepository.findByIdioma(idioma);
            if (livros.isEmpty()) {
                System.out.println("Nenhum livro encontrado no idioma: " + idioma);
            } else {
                System.out.println("Livros no idioma " + idioma + ":");
                livros.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Idioma inválido. Tente novamente.");
        }
    }
}
