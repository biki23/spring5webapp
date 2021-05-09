package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher p1 = new Publisher("p1", "abc", "mm", "adad", "l1m2n3c");
        p1 = publisherRepository.save(p1);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(p1);
        p1.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        p1 = publisherRepository.save(p1);


        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE development without EJB", "1534634");

        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);
        noEjb.setPublisher(p1);
        p1.getBooks().add(noEjb);
        authorRepository.save(rod);
        bookRepository.save(noEjb);

        System.out.println("Started inb bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());



        publisherRepository.save(p1);
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + p1.getBooks().size());
    }
}
