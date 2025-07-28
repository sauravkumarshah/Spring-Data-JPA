package tipsontech.example.sdjpajdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpajdbc.domain.Author;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource datasource;

    @Autowired
    public AuthorDaoImpl(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Author getById(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement("SELECT * FROM Author where id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Author author = new Author();
                author.setId(id);
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));

                return author;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement("SELECT * FROM Author where first_name = ? and last_name = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            rs = ps.executeQuery();

            if (rs.next()) {
                Author author = new Author();
                author.setId(rs.getLong("id"));
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                return author;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
