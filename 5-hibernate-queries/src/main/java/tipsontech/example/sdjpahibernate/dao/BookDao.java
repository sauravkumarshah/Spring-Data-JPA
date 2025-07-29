package tipsontech.example.sdjpahibernate.dao;

import tipsontech.example.sdjpahibernate.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAll();
    Book findByISBN(String isbn);
    Book save(Book book);
    Book update(Book book);
    void delete(Long id);
    Book getById(Long id);
    Book getByTitle(String title);
}
