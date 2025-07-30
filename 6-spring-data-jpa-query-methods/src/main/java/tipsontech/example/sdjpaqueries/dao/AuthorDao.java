package tipsontech.example.sdjpaqueries.dao;

import tipsontech.example.sdjpaqueries.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> findAll();
    Author getById(Long id);
    Author getByName(String firstName, String lastName);
    Author save(Author author);
    Author update(Author author);
    void delete(Long id);
}
