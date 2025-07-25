package tipsontech.example.sdjpaintro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.sdjpaintro.repositories.BookRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = "tipsontech.example.sdjpaintro.bootstrap")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

    @Autowired
    private BookRepository bookRepository;
    @Test
    public void testBookRepository() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }
}
