package com.example.LiteraAlura.principal;

import com.example.LiteraAlura.Gerenciador;
import com.example.LiteraAlura.Menu;
import com.example.LiteraAlura.repository.AutorRepository;
import com.example.LiteraAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    Scanner input = new Scanner(System.in);
    Menu menu = new Menu();

    @Autowired
    Gerenciador gerenciador;


    public void run(){
        int escolha = -1;


        while(escolha!=0){
            menu.print();
            escolha = input.nextInt();

            switch (escolha){
                case 1:
                    gerenciador.Buscarlivro();
                    break;
                case 2:
                    gerenciador.Listarlivros();
                    break;
                case 3:
                    gerenciador.listarAutores();
                    break;
                case 4:
                    gerenciador.AutoresVivosEm();
                    break;
                case 5:
                    gerenciador.ListarlivrosPorIdioma();
                    break;
            }
        }
    }


}
