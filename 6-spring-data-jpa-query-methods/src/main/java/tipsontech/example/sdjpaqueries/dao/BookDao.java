package tipsontech.example.sdjpaqueries.dao;

import tipsontech.example.sdjpaqueries.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAll();
    Book findByISBN(String isbn);
    Book save(Book book);
    Book update(Book book);
    void delete(Long id);
    Book getById(Long id);
    Book getBookByTitle(String title);
}
