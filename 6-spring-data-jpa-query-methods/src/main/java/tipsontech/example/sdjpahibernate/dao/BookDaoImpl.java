package tipsontech.example.sdjpahibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpahibernate.domain.Book;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    private final EntityManagerFactory entityManagerFactory;

    public BookDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public List<Book> findAll() {
        EntityManager entityManager = getEntityManager();
        try{
            TypedQuery<Book> query = entityManager.createNamedQuery("find_all_books", Book.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Book findByISBN(String isbn) {
        EntityManager entityManager = getEntityManager();
        try{
            TypedQuery<Book> query = entityManager.createQuery("select b from Book b where b.isbn = :isbn", Book.class);
            query.setParameter("isbn", isbn);
            return query.getSingleResult();
        } finally {
            entityManager.close();
        }
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
        try{
            TypedQuery<Book> query = getEntityManager().createNamedQuery("find_book_by_title", Book.class);
            query.setParameter("title", title);
            return query.getSingleResult();
        }finally {
            entityManager.close();
        }

    }

    @Override
    public Book findBookByTitleCriteria(String springInAction) {
        EntityManager entityManager = getEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);

            ParameterExpression<String> titleParam = criteriaBuilder.parameter(String.class, "springInAction");

            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("title"), titleParam));

            Predicate titlePredicate = criteriaBuilder.equal(root.get("title"), titleParam);

            criteriaQuery.select(root).where(titlePredicate);
            TypedQuery<Book> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setParameter(titleParam, springInAction);
            return typedQuery.getSingleResult();

        }finally {
            entityManager.close();
        }
    }

    @Override
    public Book findBookByTitleNative(String title) {
        EntityManager entityManager = getEntityManager();
        try{
//            Query query = entityManager.createNativeQuery("select * from book where title = ?", Book.class);
//            query.setParameter(1, title);

            Query query = entityManager.createNativeQuery("select * from book where title = :title", Book.class);
            query.setParameter("title", title);
            return (Book) query.getSingleResult();
        }finally {
            entityManager.close();
        }
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
