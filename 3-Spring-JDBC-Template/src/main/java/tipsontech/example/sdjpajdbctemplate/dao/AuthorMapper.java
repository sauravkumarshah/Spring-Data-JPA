package tipsontech.example.sdjpajdbctemplate.dao;

import org.springframework.jdbc.core.RowMapper;
import tipsontech.example.sdjpajdbctemplate.domain.Author;
import tipsontech.example.sdjpajdbctemplate.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        // First, check if this is a simple query (no book columns)
        try {
            // Try to access a book column to see if this is a join query
            rs.findColumn("book_id");

            // If we get here, it's a join query
            return mapJoinQuery(rs);
        } catch (SQLException e) {
            // If we get here, it's a simple query
            return mapSimpleQuery(rs);
        }
    }

    private Author mapSimpleQuery(ResultSet rs) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setBooks(new ArrayList<>()); // Empty books list for simple queries
        return author;
    }

    private Author mapJoinQuery(ResultSet rs) throws SQLException {
        // Check if we have any results
        if (!rs.isBeforeFirst()) {
            return null;
        }

        // Get the first row
        rs.next();

        // Create author from first row
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));

        List<Book> books = new ArrayList<>();
        author.setBooks(books);

        // Process first book
        if (rs.getObject("book_id") != null) {
            do {
                Book book = new Book();
                book.setId(rs.getLong("book_id"));
                book.setTitle(rs.getString("title"));
                book.setIsbn(rs.getString("isbn"));
                book.setPublisher(rs.getString("publisher"));
                book.setAuthor(author);  // Set the author object instead of authorId
                books.add(book);
            } while (rs.next() && rs.getLong("id") == author.getId());

            // Move cursor back if there are more authors
            if (!rs.isAfterLast() && rs.getLong("id") != author.getId()) {
                rs.previous();
            }
        }

        return author;
    }
}