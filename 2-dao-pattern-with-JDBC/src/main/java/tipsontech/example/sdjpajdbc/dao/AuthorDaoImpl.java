package tipsontech.example.sdjpajdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpajdbc.domain.Author;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource datasource;

    @Autowired
    public AuthorDaoImpl(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Author getById(Long id) {
        Connection conn;
        Statement stmt;
        ResultSet rs;

        try {
            conn = datasource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Author where id = " + id);

            if (rs.next()) {
                Author author = new Author();
                author.setId(id);
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));

                return author;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
