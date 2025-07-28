package tipsontech.example.sdjpajdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.sdjpajdbc.dao.AuthorDao;
import tipsontech.example.sdjpajdbc.dao.AuthorDaoImpl;
import tipsontech.example.sdjpajdbc.domain.Author;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ActiveProfiles("local")
@DataJpaTest
//@ComponentScan(basePackages = {"tipsontech.example.sdjpajdbc.dao"})
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AurthorDaoIntegrationTest {


    @Autowired
    private AuthorDao authorDao;
    @Test
    public void testGetAuthor(){
        Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();
    }

    @Test
    public void testGetAuthorByName(){
        Author author = authorDao.getByName("John", "Doe");
        assertThat(author).isNotNull();
        assertThat(author.getFirstName()).isEqualTo("John");
        assertThat(author.getLastName()).isEqualTo("Doe");
    }
}
