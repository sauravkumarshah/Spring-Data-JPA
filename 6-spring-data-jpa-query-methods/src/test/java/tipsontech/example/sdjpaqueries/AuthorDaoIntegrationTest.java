package tipsontech.example.sdjpaqueries;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.sdjpaqueries.dao.AuthorDao;
import tipsontech.example.sdjpaqueries.dao.AuthorDaoImpl;
import tipsontech.example.sdjpaqueries.domain.Author;

import java.util.List;

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
    public void testFindAllAuthors(){
        List<Author> authors = authorDao.findAll();

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

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
    public void testGetAuthorByNameNotFound(){
        assertThrows(EntityNotFoundException.class, () -> {
            Author author = authorDao.getByName("Jonny", "Gaddar");
        });
    }

    @Test
    public void testSaveAuthor(){
        Author author = new Author();
        author.setFirstName("Johny");
        author.setLastName("Thompson");
        author = authorDao.save(author);
        assertThat(author.getId()).isNotNull();
    }

    @Test
    public void testUpdateAuthor(){
        Author author = new Author();
        author.setFirstName("Steve");
        author.setLastName("Smth");
        Author savedAuthor = authorDao.save(author);

        savedAuthor.setLastName("Smith");
        Author  updatedAuthor = authorDao.update(savedAuthor);
        assertThat(updatedAuthor.getLastName()).isEqualTo("Smith");
    }

    @Test
    public void testDeleteAuthor(){
        Author author = new Author();
        author.setFirstName("Steve");
        author.setLastName("Harvey");
        Author savedAuthor = authorDao.save(author);
        authorDao.delete(savedAuthor.getId());
        assertThat(authorDao.getById(savedAuthor.getId())).isNull();
    }
}
