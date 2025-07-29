package tipsontech.example.sdjpahibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpahibernate.domain.Author;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Author> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Author> query = entityManager.createNamedQuery("author_find_all", Author.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Author> listAuthorByLastNameLike(String lastName) {
        EntityManager entityManager = getEntityManager();
        try {
            Query query = entityManager.createQuery("select a from Author a where a.lastName like :lastName", Author.class);
            query.setParameter("lastName", lastName);
            List<Author> authors = query.getResultList();

            return authors;
        } finally {
            entityManager.close();
        }
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

        TypedQuery<Author> query = entityManager.createNamedQuery("find_by_name", Author.class);

        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        Author author = query.getSingleResult();
        entityManager.close();
        return author;
    }

    @Override
    public Author save(Author author) {
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

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {
        EntityManager entityManager = getEntityManager();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
            Root<Author> root = criteriaQuery.from(Author.class);

            ParameterExpression<String> firstNameParam = criteriaBuilder.parameter(String.class, "firstName");
            ParameterExpression<String> lastNameParam = criteriaBuilder.parameter(String.class, "lastName");

            Predicate firstNamePredicate = criteriaBuilder.equal(root.get("firstName"), firstNameParam);
            Predicate lastNamePredicate = criteriaBuilder.equal(root.get("lastName"), lastNameParam);

            criteriaQuery.select(root).where(criteriaBuilder.and(firstNamePredicate, lastNamePredicate));

            TypedQuery<Author> typedQuery = entityManager.createQuery(criteriaQuery);
            typedQuery.setParameter(firstNameParam, firstName);
            typedQuery.setParameter(lastNameParam, lastName);
            return typedQuery.getSingleResult();
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Author findAuthorByNameNative(String firstName, String lastName) {
        EntityManager entityManager = getEntityManager();
        try{
            Query query = entityManager.createNativeQuery("select * from author where first_name = ? and last_name = ?", Author.class);
            query.setParameter(1, firstName);
            query.setParameter(2, lastName);
            return (Author) query.getSingleResult();
        }finally {
            entityManager.close();
        }
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
