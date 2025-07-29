package tipsontech.example.sdjpajdbc.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpajdbc.domain.Author;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Author getById(Long id) {
        return getEntityManager().find(Author.class, id);
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author save(Author author) {
        return null;
    }

    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
