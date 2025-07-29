package tipsontech.example.sdjpahibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpahibernate.domain.Book;

@Component
public class BookDaoImpl implements BookDao {

    private final EntityManagerFactory entityManagerFactory;

    public BookDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public Book save(Book book) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        return book;
    }

    @Override
    public Book update(Book book) {
        EntityManager entityManager = getEntityManager();
        entityManager.joinTransaction();
        entityManager.merge(book);
        entityManager.flush();
        entityManager.clear();
        return book;
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
        entityManager.getTransaction().commit();
    }

    @Override
    public Book getById(Long id) {
        return getEntityManager().find(Book.class, id);
    }

    @Override
    public Book getByTitle(String title) {
        TypedQuery<Book> query = getEntityManager().createQuery("select b from Book b where b.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
