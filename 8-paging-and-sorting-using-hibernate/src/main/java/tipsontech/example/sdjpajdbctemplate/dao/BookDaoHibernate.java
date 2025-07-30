package tipsontech.example.sdjpajdbctemplate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpajdbctemplate.domain.Book;

import java.util.List;

@Component
public class BookDaoHibernate implements BookDao {

    private final EntityManagerFactory entityManagerFactory;

    public BookDaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {
        EntityManager entityManager = getEntityManager();
        try {
            String sql = "SELECT b FROM Book b ORDER BY b.title " + pageable.getSort().getOrderFor("title").getDirection().name();
            TypedQuery<Book> query = entityManager.createQuery(sql, Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
            query.setFirstResult(offset);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> findAllBooks() {
        return this.getEntityManager().createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public Book save(Book book) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();
        return book;
    }

    @Override
    public Book update(Book book) {
        try (EntityManager entityManager = getEntityManager()) {
            entityManager.getTransaction().begin();
            Book mergedBook = entityManager.merge(book);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return mergedBook;
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Book getById(Long id) {
        EntityManager entityManager = getEntityManager();
        Book book = getEntityManager().find(Book.class, id);
        entityManager.close();
        return book;
    }

    @Override
    public Book getByTitle(String title) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Book> query = getEntityManager().createQuery("select b from Book b where b.title = :title", Book.class);
        query.setParameter("title", title);
        Book book = query.getSingleResult();
        entityManager.close();
        return book;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}