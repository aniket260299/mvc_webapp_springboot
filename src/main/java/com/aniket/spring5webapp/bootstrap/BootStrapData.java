package com.aniket.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.aniket.spring5webapp.model.Author;
import com.aniket.spring5webapp.model.Book;
import com.aniket.spring5webapp.model.Publisher;
import com.aniket.spring5webapp.repositories.AuthorRepository;
import com.aniket.spring5webapp.repositories.BookRepository;
import com.aniket.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started in BootStrap");

		Publisher publisher = new Publisher("xyz", "Book Bazar", "Delhi", "Delhi", 100000);
		publisherRepository.save(publisher);

		Author rsAgarwal = new Author("RS", "Agarwal");
		Book maths = new Book("Maths Practice Book", "123123");
		rsAgarwal.getBooks().add(maths);
		maths.getAuthors().add(rsAgarwal);

		maths.setPublisher(publisher);
		publisher.getBooks().add(maths);

		authorRepository.save(rsAgarwal);
		bookRepository.save(maths);
		publisherRepository.save(publisher);

		Author hcVerma = new Author("HC", "Verma");
		Book physics = new Book("Physics Practice Book", "123456");
		hcVerma.getBooks().add(physics);
		physics.getAuthors().add(hcVerma);

		physics.setPublisher(publisher);
		publisher.getBooks().add(physics);

		authorRepository.save(hcVerma);
		bookRepository.save(physics);
		publisherRepository.save(publisher);

		System.out.println("Number of books = " + bookRepository.count());
		System.out.println("Number of authors = " + authorRepository.count());
		System.out.println("Number of publishers = " + publisherRepository.count());
	}

}
