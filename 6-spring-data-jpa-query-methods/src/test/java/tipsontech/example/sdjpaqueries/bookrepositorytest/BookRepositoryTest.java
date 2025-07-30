package tipsontech.example.sdjpaqueries.bookrepositorytest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.sdjpaqueries.domain.Book;
import tipsontech.example.sdjpaqueries.repositories.BookRepository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"tipsontech.example.sdjpajdbc.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindBookByTitleWithQueryNamed() {
        Book book = bookRepository.findBookByTitleWithQueryNamed("Spring In Action");
        assertNotNull(book);
    }
    @Test
    public void testFindBookByTitle() {
        Book book = bookRepository.findBookByTitleWithQuery("Spring In Action");
        assertNotNull(book);
    }

    @Test
    public void testFindBookByTitleAsync() throws ExecutionException, InterruptedException {
        Future<Book> bookFuture = bookRepository.queryByTitle("Spring In Action");

        Book book = bookFuture.get();

        assertNotNull(book);
    }

    @Test
    public void testBookStream(){
        AtomicInteger count = new AtomicInteger(0);
        bookRepository.findAllByTitleNotNull().forEach(book -> {
            count.incrementAndGet();
        });
        assertThat(count.get()).isGreaterThan(5);
    }

    @Test
    public void testEmptyResultException(){
       assertThat(bookRepository.readByTitle("Some title")).isNull();
    }

    @Test
    public void testNullParam(){
        assertNull(bookRepository.getByTitle(null));
    }

    @Test
    public void testGetNoException(){
        assertNull(bookRepository.getByTitle("foo"));
    }
}
