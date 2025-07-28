package tipsontech.example.sdjpajdbc.dao;

import tipsontech.example.sdjpajdbc.domain.Author;

public interface AuthorDao {
    Author getById(Long id);
    Author getByName(String firstName, String lastName);
    Author save(Author author);
    Author update(Author author);
}
