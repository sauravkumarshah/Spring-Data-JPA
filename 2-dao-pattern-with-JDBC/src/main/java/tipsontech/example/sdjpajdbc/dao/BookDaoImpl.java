package tipsontech.example.sdjpajdbc.dao;

import org.springframework.stereotype.Component;
import tipsontech.example.sdjpajdbc.domain.Book;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class BookDaoImpl implements BookDao {

    private DataSource datasource;
    private AuthorDao authorDao;

    public BookDaoImpl(DataSource datasource, AuthorDao authorDao) {
        this.datasource = datasource;
        this.authorDao = authorDao;
    }

    @Override
    public Book save(Book book) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement("INSERT INTO Book (title, isbn, publisher, author_id) VALUES (?, ?, ?, ?)");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setString(3, book.getPublisher());

            if(book.getAuthor() != null) {
                ps.setLong(4, book.getAuthor().getId());
            } else {
                ps.setNull(4, -5);
            }
            ps.execute();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()"); // Only works with MySQL
            if (rs.next()) {
                book.setId(rs.getLong(1));
            }
            return book;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(null, ps, conn);
        }
    }

    @Override
    public Book update(Book book) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement("UPDATE Book SET title = ?, isbn = ?, publisher = ?, author_id = ? WHERE id = ?");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setString(3, book.getPublisher());

            if(book.getAuthor() != null) {
                ps.setLong(4, book.getAuthor().getId());
            } else {
                ps.setNull(4, -5);
            }
            ps.setLong(5, book.getId());
            ps.execute();
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(null, ps, conn);
        }
    }

    @Override
    public void delete(Long id) {
        Connection  conn = null;
        PreparedStatement ps = null;
        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement("DELETE FROM Book where id = ?");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(null, ps, conn);
        }
    }

    @Override
    public Book getById(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement("SELECT * FROM Book where id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getBookFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs, ps, conn);
        }
        return null;
    }

    @Override
    public Book getByTitle(String title) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement("SELECT * FROM Book where title = ?");
            ps.setString(1, title);
            rs = ps.executeQuery();
            if (rs.next()) {
                return getBookFromRS(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeAll(rs, ps, conn);
        }
        return null;
    }

    private Book getBookFromRS(ResultSet rs) {
        try {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setIsbn(rs.getString("isbn"));
            book.setPublisher(rs.getString("publisher"));
            book.setAuthor(authorDao.getById(rs.getLong(5)));
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}
