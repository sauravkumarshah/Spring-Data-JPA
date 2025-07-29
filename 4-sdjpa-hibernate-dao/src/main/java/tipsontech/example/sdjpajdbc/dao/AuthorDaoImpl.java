package tipsontech.example.sdjpajdbc.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpajdbc.domain.Author;

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
        TypedQuery<Author> query = getEntityManager().createQuery("select a from Author a where a.firstName = :firstName and a.lastName = :lastName", Author.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getSingleResult();
    }

    @Override
    public Author save(Author author) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin(); // To start the transaction
        entityManager.persist(author);
        entityManager.getTransaction().commit(); // To commit the transaction
        return author;
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
