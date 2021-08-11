package com.example.mysql;

import com.example.mysql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
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

		/*
		 * Book book1 = new Book("Alice's Adventures in Wonderland", "Lewis Carroll",
		 * 1998, 200); bookRepository.save(book1);
		 *
		 * Book book2 = new
		 * Book("The Emperor's New Mind: Concerning Computers, Minds and The Laws of Physics"
		 * , "Roger Penrose", 1989, 480); bookRepository.save(book2);
		 *
		 * Book book3 = new Book("Diary of a Genius", "Salvador Dali", 2017, 160);
		 * bookRepository.save(book3);
		 */
	}

}
