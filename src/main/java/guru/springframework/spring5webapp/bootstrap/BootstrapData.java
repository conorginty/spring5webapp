package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner { // Spring will look for classes of type CommandLineRunner in order to run them (Spring Boot will automatically call the run method of all beans implementing this interface after the application context has been loaded)

    // Dependency injection
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
        // === 1. ===
        // Instantiate Author and Book entities
        Author eric = new Author("Eric", "Evans");
        Book domainDrivenDesign = new Book("Domain Driven Design", "123123");

        // Map them to one another
        eric.getBooks().add(domainDrivenDesign);
        domainDrivenDesign.getAuthors().add(eric);

        // === CHALLENGE ===
        Publisher sfg = new Publisher("SFG Publishing", null, "St Petersburg", "FL", null); // Guru was too lazy to fill the arguments in...
        publisherRepository.save(sfg);
        domainDrivenDesign.setPublisher(sfg);
        sfg.getBooks().add(domainDrivenDesign);

        // Save the JPA Entities to a repo (to an in-memory H2 DB) (we're using the repo methods to do this - and underneath the covers Spring Data JPA is using hibernate to save these to an in-memory H2 DB)
        authorRepository.save(eric);
        bookRepository.save(domainDrivenDesign);

        // === 2. ===
        // Instantiate Author and Book entities
        Author rod = new Author("Rod", "Johnson"); // Founder of the Spring Framework
        Book noEJB = new Book("J2EE Development without EJB", "393945959");

        // Map them to one another
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        // === CHALLENGE ===
        noEJB.setPublisher(sfg);
        sfg.getBooks().add(noEJB);

        // Save the JPA Entities to a repo (to an in-memory H2 DB) (we're using the repo methods to do this - and underneath the covers Spring Data JPA is using hibernate to save these to an in-memory H2 DB)
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(sfg);

        // ----------------------
        System.out.println("Started in Bootstrap...");
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Number of SFG Publisher's Books: " + sfg.getBooks().size());
    }
}
