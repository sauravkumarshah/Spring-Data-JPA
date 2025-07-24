package tipsontech.example.sdjpaintro;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import tipsontech.example.sdjpaintro.domain.Book;
import tipsontech.example.sdjpaintro.repositories.BookRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class SpringBootJpaTestSlice {
    @Autowired
    BookRepository bookRepository;

//    @Rollback(value = false)
    @Commit
    @Order(1)
    @Test
    public void testJpaTestSplice() {
        long countBefore = bookRepository.count();

        assertThat(countBefore).isEqualTo(0);

        bookRepository.save(new Book("My Book", "1235555", "Self"));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    public void testJpaTestSpliceTransaction() {
        long countBefore = bookRepository.count();

        assertThat(countBefore).isEqualTo(1);


    }
}
