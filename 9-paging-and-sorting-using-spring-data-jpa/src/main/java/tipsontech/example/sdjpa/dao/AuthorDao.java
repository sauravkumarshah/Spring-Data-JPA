package tipsontech.example.sdjpa.dao;

import org.springframework.data.domain.Pageable;
import tipsontech.example.sdjpa.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> findAll();

    List<Author> findAllAuthors(Pageable pageable);

    List<Author> findAuthorByLastName(String lastName, Pageable pageable);

    List<Author> findAuthorByLastName(String lastName, int pageSize, int offset);

    List<Author> findAuthorByLastName(String lastName);

    Author getById(Long id);
    Author getByName(String firstName, String lastName);
    Author save(Author author);
    Author update(Author author);
    void delete(Long id);
}
