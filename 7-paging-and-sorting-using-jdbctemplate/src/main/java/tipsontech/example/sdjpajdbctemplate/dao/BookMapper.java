package tipsontech.example.sdjpajdbctemplate.dao;

import org.springframework.jdbc.core.RowMapper;
import tipsontech.example.sdjpajdbctemplate.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
       Book book = new Book();
       book.setId(rs.getLong("id"));
       book.setTitle(rs.getString("title"));
       book.setIsbn(rs.getString("isbn"));
       book.setPublisher(rs.getString("publisher"));

       return book;
    }
}
