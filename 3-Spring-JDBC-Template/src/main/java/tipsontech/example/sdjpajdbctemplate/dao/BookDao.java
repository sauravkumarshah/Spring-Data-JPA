package tipsontech.example.sdjpajdbctemplate.dao;

import tipsontech.example.sdjpajdbctemplate.domain.Book;

public interface BookDao {
    Book save(Book book);
    Book update(Book book);
    void delete(Long id);
    Book getById(Long id);
    Book getByTitle(String title);
}
