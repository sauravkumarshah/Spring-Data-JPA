package tipsontech.example.sdjpajdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.sdjpajdbc.dao.AuthorDao;
import tipsontech.example.sdjpajdbc.dao.AuthorDaoImpl;
import tipsontech.example.sdjpajdbc.dao.BookDao;
import tipsontech.example.sdjpajdbc.dao.BookDaoImpl;
import tipsontech.example.sdjpajdbc.domain.Author;
import tipsontech.example.sdjpajdbc.domain.Book;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ActiveProfiles("local")
@DataJpaTest
//@ComponentScan(basePackages = {"tipsontech.example.sdjpajdbc.dao"})
@Import(BookDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoIntegrationTest {


    @Autowired
    private BookDao bookDao;

    @Test
    public void testGetAuthor(){
        Book author = bookDao.getById(1L);
        assertThat(author).isNotNull();
    }

    @Test
    public void testGetAuthorByName(){
        Book author = bookDao.getByTitle("Spring In Action");
        assertThat(author).isNotNull();
        assertThat(author.getTitle()).isEqualTo("Spring In Action");
    }

    @Test
    public void testSaveAuthor(){
        Book book = new Book();
        book.setTitle("The Lord of the Rings");
        book.setIsbn("123456789");
        book.setPublisher("Penguin");
        book.setAuthorId(1L);
        Book savedBook = bookDao.save(book);
        assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    public void testUpdateAuthor(){
        Book book = new Book();
        book.setTitle("The Lord of the Rongs");
        book.setIsbn("123456789");
        book.setPublisher("Penguin");
        book.setAuthorId(1L);
        Book savedBook = bookDao.save(book);

        savedBook.setTitle("The Lord of the Rings");
        Book updatedBook = bookDao.update(book);
        assertThat(updatedBook.getTitle()).isEqualTo("The Lord of the Rings");
    }

    @Test
    public void testDeleteAuthor(){
        Book book = new Book();
        book.setTitle("The Alchemist");
        book.setIsbn("123456789");
        book.setPublisher("Penguin");
        book.setAuthorId(1L);
        Book savedBook = bookDao.save(book);
        bookDao.delete(savedBook.getId());
        assertThat(bookDao.getById(savedBook.getId())).isNull();
    }
}
