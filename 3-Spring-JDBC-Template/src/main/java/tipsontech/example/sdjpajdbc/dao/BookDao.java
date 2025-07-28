package tipsontech.example.sdjpajdbc.dao;

import tipsontech.example.sdjpajdbc.domain.Book;

public interface BookDao {
    Book save(Book book);
    Book update(Book book);
    void delete(Long id);
    Book getById(Long id);
    Book getByTitle(String title);
}
