package com.nicode.challenge_literalura;

import com.nicode.challenge_literalura.dominio.servicios.MenuServicio;
import com.nicode.challenge_literalura.persistencia.repositorios.LibroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

    @Autowired
    private LibroRepo libroRepo;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        MenuServicio menu = new MenuServicio(libroRepo);
        menu.guardarLibro();
    }


}
