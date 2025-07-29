package tipsontech.example.sdjpaqueries.bookrepositorytest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.sdjpaqueries.repositories.BookRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"tipsontech.example.sdjpajdbc.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

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
