package com.example.LiteraAlura.repository;

import com.example.LiteraAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByNome(String Nome);

    @Query("SELECT a FROM Autor a WHERE a.anoDeNascimento <= :ano AND (a.anoDeFalecimento >= :ano OR a.anoDeFalecimento IS NULL)")
    List<Autor> findAutoresVivosNoAno(@Param("ano") int ano);
}
