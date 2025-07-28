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
//        Statement stmt = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = datasource.getConnection();
//            stmt = conn.createStatement();
            ps = conn.prepareStatement("SELECT * FROM Author where id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
//            rs = stmt.executeQuery("SELECT * FROM Author where id = " + id);

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
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return null;
    }
}
