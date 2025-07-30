package tipsontech.example.sdjpajdbctemplate.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.sdjpajdbctemplate.domain.Author;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"tipsontech.example.sdjpajdbctemplate.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoJDBCTemplateTest {


    private AuthorDao authorDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp(){
        authorDao = new AuthorDaoJDBCTemplate(jdbcTemplate);
    }

    @Test
    public void findAllAuthorsByLastNameFirstName(){
        List<Author> authors = authorDao.findAuthorByLastName("Smith", PageRequest.of(0, 10));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
    }

    @Test
    public void findAllAuthorsByLastNameSortFirstNameDesc(){
        List<Author> authors = authorDao.findAuthorByLastName("Smith", PageRequest.of(0, 10, Sort.by(Sort.Order.desc("firstname"))));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        assertThat(authors.get(0).getFirstName()).isEqualTo("William");
    }

    @Test
    public void findAllAuthorsByLastNameSortFirstNameAsc(){
        List<Author> authors = authorDao.findAuthorByLastName("Smith", PageRequest.of(0, 10, Sort.by(Sort.Order.asc("firstname"))));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(10);
        assertThat(authors.get(0).getFirstName()).isEqualTo("Andrew");
    }

    @Test
    public void findAllAuthorsByLastNameSortByFirstName(){
        List<Author> authors = authorDao.findAuthorByLastName("Smith", PageRequest.of(0, 100));
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(40);
    }


    @Test
    public void testFindAuthorsByLastNameSortByFirstName1(){
        List<Author> authors = authorDao.findAuthorByLastName("Smith",1, 1);
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isEqualTo(1);
    }
    @Test
    public void testFindAuthorsByLastNameSortByFirstName2(){
        List<Author> authors = authorDao.findAuthorByLastName("Smith",2, 2);
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(1);
    }

    @Test
    public void testFindAuthorByLastNameSortByFirstName(){
        List<Author> authors = authorDao.findAuthorByLastName("Smith");
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

        assertThat(authorDao.getById(savedAuthor.getId())).isNull();
    }
}
