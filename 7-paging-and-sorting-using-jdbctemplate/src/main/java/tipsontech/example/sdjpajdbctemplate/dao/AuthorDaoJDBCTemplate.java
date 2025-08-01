package tipsontech.example.sdjpajdbctemplate.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpajdbctemplate.domain.Author;

import java.util.List;

@Component
public class AuthorDaoJDBCTemplate implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> findAuthorByLastName(String lastName, Pageable pageable) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * FROM author WHERE last_name = ? ");

        if(pageable.getSort().getOrderFor("firstname") != null) {
            sb.append("ORDER BY first_name ").append(pageable.getSort().getOrderFor("firstname").getDirection().name());
        }

        sb.append(" LIMIT ? OFFSET ?");

        return this.jdbcTemplate.query(sb.toString(), getRowMapper(), lastName, pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public List<Author> findAuthorByLastName(String lastName, int pageSize, int offset) {
        return this.jdbcTemplate.query("SELECT * FROM author WHERE last_name = ? ORDER BY first_name LIMIT ? OFFSET ?", getRowMapper(), lastName, pageSize, offset);
    }

    @Override
    public List<Author> findAuthorByLastName(String lastName) {
        return this.jdbcTemplate.query("SELECT * FROM author WHERE last_name = ? ORDER BY first_name", getRowMapper(), lastName);
    }

    @Override
    public Author getById(Long id) {
        String sql = "select author.id, author.first_name, author.last_name, book.id as book_id, book.title, book.isbn, book.publisher " +
                "from author " +
                "left join book on author.id = book.author_id " +
                "where author.id = ?";

        return this.jdbcTemplate.query(sql, new AuthorExtractor(), id);
//        return this.jdbcTemplate.queryForObject("SELECT * FROM author WHERE id = ?", getRowMapper(), id);
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