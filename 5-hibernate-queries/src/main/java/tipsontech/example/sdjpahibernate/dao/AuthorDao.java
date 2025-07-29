package tipsontech.example.sdjpahibernate.dao;

import tipsontech.example.sdjpahibernate.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> findAll();
    List<Author> listAuthorByLastNameLike(String lastName);
    Author getById(Long id);
    Author getByName(String firstName, String lastName);
    Author save(Author author);
    Author update(Author author);
    void delete(Long id);

    Author findAuthorByNameCriteria(String firstName, String lastName);
}
