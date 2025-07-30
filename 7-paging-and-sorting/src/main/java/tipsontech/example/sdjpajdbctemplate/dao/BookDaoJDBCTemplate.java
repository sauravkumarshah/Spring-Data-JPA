package tipsontech.example.sdjpajdbctemplate.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpajdbctemplate.domain.Book;

import java.util.List;

@Component
public class BookDaoJDBCTemplate implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoJDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {
        String sql = "SELECT * FROM book ORDER BY title " + pageable.getSort().getOrderFor("title").getDirection().name() + " LIMIT ? OFFSET ?";
        return this.jdbcTemplate.query(sql, getRowMapper(), pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        return this.jdbcTemplate.query("SELECT * FROM book LIMIT ? OFFSET ?", getRowMapper(), pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        return this.jdbcTemplate.query("SELECT * FROM book LIMIT ? OFFSET ?", getRowMapper(), pageSize, offset);
    }

    @Override
    public List<Book> findAllBooks() {
        return this.jdbcTemplate.query("SELECT * FROM book", getRowMapper());
    }

    @Override
    public Book save(Book book) {
        this.jdbcTemplate.update("INSERT INTO book (title, isbn, publisher) VALUES (?, ?, ?)", book.getTitle(), book.getIsbn(), book.getPublisher());
        Long createdId = this.jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return this.getById(createdId);
    }

    @Override
    public Book update(Book book) {
        this.jdbcTemplate.update("UPDATE book SET title = ?, isbn = ?, publisher = ? WHERE id = ?", book.getTitle(), book.getIsbn(), book.getPublisher(), book.getId());
        return this.getById(book.getId());
    }

    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update("DELETE FROM book WHERE id = ?", id);
    }

    @Override
    public Book getById(Long id) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM book WHERE id = ?", getRowMapper(), id);
    }

    @Override
    public Book getByTitle(String title) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM book WHERE title = ?", getRowMapper(), title);
    }

    private RowMapper<Book> getRowMapper() {
        return new BookMapper();
    }
}
