package tipsontech.example.sdjpaintro.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpaintro.domain.Book;
import tipsontech.example.sdjpaintro.repositories.BookRepository;

@Profile({"local","default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();

        Book bookSBIA = new Book("Spring Boot in Action", "978-1484249693", "Manning");
        Book savedSBIA = bookRepository.save(bookSBIA);

        Book bookSIA = new Book("Spring in Action", "978-1484250213", "Manning");
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("book Id : " + book.getId());
            System.out.println("book title : " + book.getTitle());
        });
    }
}
