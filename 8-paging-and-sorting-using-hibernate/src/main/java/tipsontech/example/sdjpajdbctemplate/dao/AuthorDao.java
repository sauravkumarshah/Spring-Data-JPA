package tipsontech.example.sdjpajdbctemplate.dao;

import org.springframework.data.domain.Pageable;
import tipsontech.example.sdjpajdbctemplate.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> findAuthorByLastName(String lastName, Pageable pageable);

    List<Author> findAuthorByLastName(String lastName, int pageSize, int offset);

    List<Author> findAuthorByLastName(String lastName);

    Author getById(Long id);
    Author getByName(String firstName, String lastName);
    Author save(Author author);
    Author update(Author author);
    void delete(Long id);
}
