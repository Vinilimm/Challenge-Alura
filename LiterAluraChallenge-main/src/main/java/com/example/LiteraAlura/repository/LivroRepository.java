package com.example.LiteraAlura.repository;

import com.example.LiteraAlura.model.EnumIdiomas;
import com.example.LiteraAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {


    List<Livro> findByIdioma(EnumIdiomas Idioma);
}
