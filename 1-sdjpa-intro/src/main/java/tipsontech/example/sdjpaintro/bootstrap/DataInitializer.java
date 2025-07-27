package tipsontech.example.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpaintro.domain.AuthorUuid;
import tipsontech.example.sdjpaintro.domain.Book;
import tipsontech.example.sdjpaintro.domain.BookUuid;
import tipsontech.example.sdjpaintro.repositories.AuthorUuidRepository;
import tipsontech.example.sdjpaintro.repositories.BookRepository;
import tipsontech.example.sdjpaintro.repositories.BookUuidRepository;

@Profile({"local","default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorUuidRepository authorUuidRepository;
    private final BookUuidRepository bookUuidRepository;

    public DataInitializer(BookRepository bookRepository, AuthorUuidRepository authorUuidRepository, BookUuidRepository bookUuidRepository) {
        this.bookRepository = bookRepository;
        this.authorUuidRepository = authorUuidRepository;
        this.bookUuidRepository = bookUuidRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();

        Book bookSBIA = new Book("Spring Boot in Action", "978-1484249693", "Manning", null);
        Book savedSBIA = bookRepository.save(bookSBIA);

        Book bookSIA = new Book("Spring in Action", "978-1484250213", "Manning", null);
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("book Id : " + book.getId());
            System.out.println("book title : " + book.getTitle());
        });

        AuthorUuid authorUuid = new AuthorUuid();
        authorUuid.setFirstName("John");
        authorUuid.setLastName("Doe");
        AuthorUuid savedAuthor = authorUuidRepository.save(authorUuid);
        System.out.println("Saved Author id : " + savedAuthor.getId());

        BookUuid bookUuid = new BookUuid();
        bookUuid.setTitle("All about UUIDs");
        bookUuid.setIsbn("978-1484250213");
        bookUuid.setPublisher("Manning");
        BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
        System.out.println("Saved BookUuid id : " + savedBookUuid.getId());
    }
}
