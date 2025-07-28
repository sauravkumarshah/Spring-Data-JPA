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
                return getAuthorFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs, ps, conn);
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
                return getAuthorFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs, ps, conn);
        }
        return null;
    }

    @Override
    public Author save(Author author) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement("INSERT INTO Author (first_name, last_name) VALUES (?, ?)");
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.execute();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()"); // Only works with MySQL
            if (rs.next()) {
                author.setId(rs.getLong(1));
            }
            return author;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(null, ps, conn);
        }
    }

    @Override
    public Author update(Author author) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement("UPDATE Author SET first_name = ?, last_name = ? WHERE id = ?");
            ps.setString(1, author.getFirstName());
            ps.setString(2, author.getLastName());
            ps.setLong(3, author.getId());
            ps.execute();
            return author;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement("DELETE FROM Author where id = ?");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(null, ps, conn);
        }
    }

    private static void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
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

    private static Author getAuthorFromRS(ResultSet rs) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        return author;
    }
}
