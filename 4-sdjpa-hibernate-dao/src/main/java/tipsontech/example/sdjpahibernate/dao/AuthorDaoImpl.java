package tipsontech.example.sdjpahibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpahibernate.domain.Author;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Author getById(Long id) {
        EntityManager entityManager = getEntityManager();
        Author author = getEntityManager().find(Author.class, id);
        entityManager.close();
        return author;
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Author> query = getEntityManager().createQuery("select a from Author a where a.firstName = :firstName and a.lastName = :lastName", Author.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        Author author = query.getSingleResult();
        entityManager.close();
        return author;
    }

    @Override
    public Author save(Author author) {
//        EntityManager entityManager = getEntityManager();
//        entityManager.persist(author);
//        return author;

//        EntityManager entityManager = getEntityManager();
//        entityManager.getTransaction().begin(); // To start the transaction
//        entityManager.persist(author);
//        entityManager.getTransaction().commit(); // To commit the transaction
//        return author;

//        EntityManager entityManager = getEntityManager();
//        entityManager.joinTransaction();
//        entityManager.persist(author);
//        entityManager.flush();
//        return author;

        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.joinTransaction();
        entityManager.persist(author);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return author;
    }

    @Override
    public Author update(Author author) {
        try (EntityManager entityManager = getEntityManager()) {
            entityManager.getTransaction().begin();
            Author mergedAuthor = entityManager.merge(author);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return mergedAuthor;
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        entityManager.remove(author);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
