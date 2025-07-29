package tipsontech.example.sdjpahibernate;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.sdjpahibernate.dao.AuthorDao;
import tipsontech.example.sdjpahibernate.dao.AuthorDaoImpl;
import tipsontech.example.sdjpahibernate.dao.BookDao;
import tipsontech.example.sdjpahibernate.dao.BookDaoImpl;
import tipsontech.example.sdjpahibernate.domain.Author;
import tipsontech.example.sdjpahibernate.domain.Book;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ActiveProfiles("local")
@DataJpaTest
//@ComponentScan(basePackages = {"tipsontech.example.sdjpajdbc.dao"})
@Import({BookDaoImpl.class, AuthorDaoImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoIntegrationTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testFindAllAuthors(){
        List<Author> authors = authorDao.findAll();

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    public void testFindBookByISBN(){
        Book book = new Book();
        book.setIsbn("1234" + RandomString.make());
        book.setTitle("The ISBN test book");
        book.setPublisher("Penguin");

        Book savedBook = bookDao.save(book);

        Book foundBook = bookDao.findByISBN(savedBook.getIsbn());
        assertThat(foundBook).isNotNull();
    }

    @Test
    public void testGetBook(){
        Book author = bookDao.getById(1L);
        assertThat(author).isNotNull();
    }

    @Test
    public void testGetBookByTitle(){
        Book author = bookDao.getByTitle("Spring In Action");
        assertThat(author).isNotNull();
        assertThat(author.getTitle()).isEqualTo("Spring In Action");
    }

    @Test
    public void testSaveBook(){
        Book book = new Book();
        book.setTitle("The Lord of the Rings");
        book.setIsbn("123456789");
        book.setPublisher("Penguin");
        Book savedBook = bookDao.save(book);
        assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    public void testUpdateBook() {
        Author author = authorDao.getById(1L); // Use an ID that exists in your database
        assertThat(author).isNotNull();

        Book book = new Book();
        book.setTitle("The Lord of the Rongs");
        book.setIsbn("123456789");
        book.setPublisher("Penguin");
        book.setAuthor(author);

        Book savedBook = bookDao.save(book);
        assertThat(savedBook.getId()).isNotNull();

        savedBook.setTitle("The Lord of the Rings");
        Book updatedBook = bookDao.update(savedBook);
        assertThat(updatedBook.getTitle()).isEqualTo("The Lord of the Rings");
    }

    @Test
    public void testDeleteBook(){
        Book book = new Book();
        book.setTitle("The Alchemist");
        book.setIsbn("123456789");
        book.setPublisher("Penguin");
        Book savedBook = bookDao.save(book);
        bookDao.delete(savedBook.getId());
        assertThat(bookDao.getById(savedBook.getId())).isNull();
    }
}
