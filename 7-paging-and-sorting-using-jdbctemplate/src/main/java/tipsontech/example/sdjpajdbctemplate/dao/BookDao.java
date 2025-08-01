package tipsontech.example.sdjpajdbctemplate.dao;

import org.springframework.data.domain.Pageable;
import tipsontech.example.sdjpajdbctemplate.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAllBooksSortByTitle(Pageable pageable);

    List<Book> findAllBooks(Pageable pageable);

    List<Book> findAllBooks(int pageSize, int offset);

    List<Book> findAllBooks();

    Book save(Book book);
    Book update(Book book);
    void delete(Long id);
    Book getById(Long id);
    Book getByTitle(String title);
}
