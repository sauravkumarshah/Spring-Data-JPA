package tipsontech.example.sdjpajdbctemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.sdjpajdbctemplate.dao.AuthorDao;
import tipsontech.example.sdjpajdbctemplate.dao.AuthorDaoImpl;
import tipsontech.example.sdjpajdbctemplate.domain.Author;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ActiveProfiles("local")
@DataJpaTest
//@ComponentScan(basePackages = {"tipsontech.example.sdjpajdbc.dao"})
@Import(AuthorDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {


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

    @Test
    public void testSaveAuthor(){
        Author author = new Author();
        author.setFirstName("Johny");
        author.setLastName("Thompson");
        author = authorDao.save(author);

        System.out.println("New Id is: " + author.getId());

        assertThat(author.getId()).isNotNull();
    }

    @Test
    public void testUpdateAuthor(){
        Author author = new Author();
        author.setFirstName("John1");
        author.setLastName("Th2");
        Author savedAuthor = authorDao.save(author);

        savedAuthor.setLastName("Thompson");
        Author  updatedAuthor = authorDao.update(savedAuthor);
        assertThat(updatedAuthor.getLastName()).isEqualTo("Thompson");
    }

    @Test
    public void testDeleteAuthor(){
        Author author = new Author();
        author.setFirstName("Steve");
        author.setLastName("Harvey");

        Author savedAuthor = authorDao.save(author);

        authorDao.delete(savedAuthor.getId());

        assertThrows(TransientDataAccessResourceException.class, () -> {
                authorDao.getById(savedAuthor.getId());
        });
    }
}
