package tipsontech.example.sdjpajdbctemplate.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import tipsontech.example.sdjpajdbctemplate.domain.Author;
import tipsontech.example.sdjpajdbctemplate.domain.Book;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"tipsontech.example.sdjpajdbctemplate.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoJDBCTemplateTest {
    @Autowired
    @Qualifier("bookDaoJDBCTemplate")
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testFindAllBooksPage1(){
        List<Book> books = bookDao.findAllBooks(10,0);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }
    @Test
    public void testFindAllBooksPage2(){
        List<Book> books = bookDao.findAllBooks(10,10);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }
    @Test
    public void testFindAllBooksPage10(){
        List<Book> books = bookDao.findAllBooks(10,100);

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    public void testFindAllBooks(){
        List<Book> books = bookDao.findAllBooks();

        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
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

        assertThrows(EmptyResultDataAccessException.class, () -> {
            bookDao.getById(savedBook.getId());
        });
    }
}
