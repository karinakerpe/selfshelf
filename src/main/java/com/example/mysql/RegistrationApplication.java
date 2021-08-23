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

		 /*Book book1 = new Book("Alice's Adventures in Wonderland", "Lewis Carroll", 1998, 200, "019283374X", BookStatus.AVAILABLE);
		 bookRepository.save(book1);
		 Book book2 = new Book("The Emperor's New Mind", "Roger Penrose", 1989, 480, "0192861980", BookStatus.AVAILABLE);
		 bookRepository.save(book2);
		 Book book3 = new Book("The Little Mermaid", "Hans Christian Andersen", 2019, 64, "1782692509", BookStatus.AVAILABLE);
		 bookRepository.save(book3);
		 Book book4 = new Book("Ficciones", "Jorge Luis Borges", 1962, 174, "0802130305", BookStatus.AVAILABLE);
		 bookRepository.save(book4);
		 Book book5 = new Book("The Little Prince", "Antoine de Saint-Exupéry", 2000, 96, "0547761406", BookStatus.AVAILABLE);
		 bookRepository.save(book5);
		 Book book6 = new Book("Planning Extreme Programming", "Mike Hendrickson, Kent Beck, Martin Fowler", 2001, 139, "0201710919", BookStatus.AVAILABLE);
		 bookRepository.save(book6);
		 Book book7 = new Book("Augmented Reality", "Dieter Schmalstieg, Tobias Hollerer", 2016, 528, "0133153207", BookStatus.AVAILABLE);
		 bookRepository.save(book7);
		 Book book8 = new Book("Java Threads", "Scott Oaks, Henry Wong", 2004, 362, "144936666X", BookStatus.AVAILABLE);
		 bookRepository.save(book8);
		 Book book9 = new Book("Clockwork Game Design", "Keith Burgun", 2015, 136, "1317630394", BookStatus.AVAILABLE);
		 bookRepository.save(book9);
		 Book book10 = new Book("Java Precisely", "Peter Sestoft", 2016, 216, "0262529076", BookStatus.AVAILABLE);
		 bookRepository.save(book10);
		 Book book11 = new Book("Killer Game Programming in Java", "Andrew Davison", 2005, 998, "0596552904", BookStatus.AVAILABLE);
		 bookRepository.save(book11);
		 Book book12 = new Book("Growing Object-Oriented Software, Guided by Tests", "Steve Freeman, Nat Pryce", 2009, 384, "0321699769", BookStatus.AVAILABLE);
		 bookRepository.save(book12);
		 Book book13 = new Book("The Algorithm Design Manual: Text", "Steven S. Skiena", 1998, 486, "0387948600", BookStatus.AVAILABLE);
		 bookRepository.save(book13);
		 Book book14 = new Book("Principles of Object-Oriented JavaScript", "Nicholas C. Zakas", 2014, 120, "1593275404", BookStatus.AVAILABLE);
		 bookRepository.save(book14);
		 Book book15 = new Book("Adventures in Coding", "Eva Holland, Chris Minnick", 2016, 320, "1119232686", BookStatus.AVAILABLE);
		 bookRepository.save(book15);
		 Book book16 = new Book("The Rings of Saturn", "W. G. Sebald", 2016, 304, "081122130X", BookStatus.AVAILABLE);
		 bookRepository.save(book16);
		 Book book17 = new Book("The Way to Go", "Ivo Balbaert", 2012, 628, "1469769166", BookStatus.AVAILABLE);
		 bookRepository.save(book17);
		 Book book18 = new Book("Database Systems", "S. K. Singh", 2009, 867, "8177585673", BookStatus.AVAILABLE);
		 bookRepository.save(book18);
		 Book book19 = new Book("Fearless Symmetry", "Avner Ash, Robert Gross", 2008, 312, "0691138710", BookStatus.AVAILABLE);
		 bookRepository.save(book19);
		 Book book20 = new Book("Algorithms Unlocked", "Thomas H. Cormen", 2013, 222, "0262518805", BookStatus.AVAILABLE);
		 bookRepository.save(book20);
		 Book book21 = new Book("Learning Object-Oriented Programming", "Gastón C. Hillar", 2015, 280, "1785289934", BookStatus.AVAILABLE);
		 bookRepository.save(book21);
		 Book book22 = new Book("Into the Wild", "Jon Krakauer", 2018, 376, "1529011906", BookStatus.AVAILABLE);
		 bookRepository.save(book22);
		 Book book23 = new Book("Introducing Time", "Craig Callender", 2014, 176, "1848317727", BookStatus.AVAILABLE);
		 bookRepository.save(book23);
		 Book book24 = new Book("Thinking in Java", "Bruce Eckel", 2003, 1119, "0131002872", BookStatus.AVAILABLE);
		 bookRepository.save(book24);
		 Book book25 = new Book("Situational Game Design", "Brian Upton", 2017, 119, "131539801X", BookStatus.AVAILABLE);
		 bookRepository.save(book25);
		 Book book26 = new Book("Distributed Learning and Virtual Librarianship", "Sharon G. Almquist", 2011, 306, "1591589061", BookStatus.AVAILABLE);
		 bookRepository.save(book26);
		 Book book27 = new Book("Theory of Fun for Game Design", "Raph Koster", 2013, 300, "1449363172", BookStatus.AVAILABLE);
		 bookRepository.save(book27);
		 Book book28 = new Book("Hyperspace", "Michio Kaku", 2018, 128, "0192565001", BookStatus.AVAILABLE);
		 bookRepository.save(book28);
		 Book book29 = new Book("The Art of Travel", "Alain De Botton", 2008, 272, "0307481662", BookStatus.AVAILABLE);
		 bookRepository.save(book29);
		 Book book30 = new Book("A Field Guide to Rocks and Minerals", "Frederick H. Pough", 1996, 396, "039591096X", BookStatus.AVAILABLE);
		 bookRepository.save(book30);*/
	}

}
