package tipsontech.example.sdjpajdbc.dao;

import org.springframework.stereotype.Component;
import tipsontech.example.sdjpajdbc.domain.Book;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class BookDaoImpl implements BookDao {


    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public Book getByTitle(String title) {
        return null;
    }
}
