package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{


    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    public void initData() {

        //Publisher
        Publisher pub = new Publisher("Publisher 1", "5th Avenue, NY");
        publisherRepository.save(pub);


        //Rick
        Author rick = new Author("Rick", "Grimmes");
        Book book1 = new Book("Domain Driven Design", "1234", pub);
        rick.getBooks().add(book1);
        book1.getAuthors().add(rick);

        authorRepository.save(rick);
        bookRepository.save(book1);

        //Morty
        Author morty = new Author("Morty", "Grimmes");
        Book book2 = new Book("J2EE Development without EJB", "23444", pub);
        morty.getBooks().add(book2);

        authorRepository.save(morty);
        bookRepository.save(book2);
    }
}
