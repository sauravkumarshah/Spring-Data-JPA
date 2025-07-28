package tipsontech.example.sdjpajdbctemplate.dao;

import org.springframework.jdbc.core.RowMapper;
import tipsontech.example.sdjpajdbctemplate.domain.Author;
import tipsontech.example.sdjpajdbctemplate.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        rs.next();

        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));

        if(rs.getString("isbn") == null){
            author.setBooks(new ArrayList<>());
            author.getBooks().add(mapBook(rs));
        }

        while(rs.next()){
            author.getBooks().add(mapBook(rs));
        }

        return author;
    }

    private Book mapBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong(4));
        book.setTitle(rs.getString(5));
        book.setIsbn(rs.getString(6));
        book.setPublisher(rs.getString(7));
        book.setAuthorId(rs.getLong(1));
        return book;
    }
}
