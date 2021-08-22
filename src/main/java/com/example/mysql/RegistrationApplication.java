package com.example.mysql;

import com.example.mysql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan
@Configuration
@EnableAutoConfiguration
public class RegistrationApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(RegistrationApplication.class, args);
	}

	@Autowired
	private BookRepository bookRepository;

	@Override
	public void run(String... args) throws Exception {



//		 Book book1 = new Book("Alice's Adventures in Wonderland", "Lewis Carroll", 1998, 200, "019283374X", BookStatus.AVAILABLE);
//		 bookRepository.save(book1);
//		 Book book2 = new Book("The Emperor's New Mind", "Roger Penrose", 1989, 480, "0192861980", BookStatus.AVAILABLE);
//		 bookRepository.save(book2);
//		 Book book4 = new Book("Labyrinths", "Jorge Luis Borges", 2007, 240, "0811216993", BookStatus.AVAILABLE);
//		 bookRepository.save(book4);
	}

}
