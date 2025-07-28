package tipsontech.example.sdjpajdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpajdbc.domain.Author;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM author WHERE id = ?", getRowMapper(), id);
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM author WHERE first_name = ? AND last_name = ?", getRowMapper(), firstName, lastName);
    }

    @Override
    public Author save(Author author) {
       this.jdbcTemplate.update("INSERT INTO author (first_name, last_name) VALUES (?, ?)", author.getFirstName(), author.getLastName());
       Long createdId = this.jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
       return this.getById(createdId);
    }

    @Override
    public Author update(Author author) {
        this.jdbcTemplate.update("UPDATE author SET first_name = ?, last_name = ? WHERE id = ?", author.getFirstName(), author.getLastName(), author.getId());
        return this.getById(author.getId());
    }

    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update("DELETE FROM author WHERE id = ?", id);
    }

    private RowMapper<Author> getRowMapper() {
        return new AuthorMapper();
    }
}
