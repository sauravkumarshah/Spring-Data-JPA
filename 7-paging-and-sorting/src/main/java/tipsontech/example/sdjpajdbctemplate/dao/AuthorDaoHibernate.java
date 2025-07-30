package tipsontech.example.sdjpajdbctemplate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;
import tipsontech.example.sdjpajdbctemplate.domain.Author;

import java.util.List;

public class AuthorDaoHibernate implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Author> findAuthorByLastName(String lastName, Pageable pageable) {
        EntityManager entityManager = getEntityManager();
        try {
            String hql = "select a from Author a where a.lastName = :lastName";

            if(pageable.getSort().getOrderFor("firstname") != null) {
                hql += " order by a.firstName " + pageable.getSort().getOrderFor("firstname").getDirection().name();
            }

            TypedQuery<Author> query = entityManager.createQuery(hql, Author.class);
            query.setParameter("lastName", lastName);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Author> findAuthorByLastName(String lastName, int pageSize, int offset) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Author> query = entityManager.createQuery("select a from Author a where a.lastName = :lastName", Author.class);
            query.setParameter("lastName", lastName);
            query.setFirstResult(offset);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            entityManager.close();
        }

    }

    @Override
    public List<Author> findAuthorByLastName(String lastName) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Author> query = entityManager.createQuery("select a from Author a where a.lastName = :lastName", Author.class);
            query.setParameter("lastName", lastName);
            return query.getResultList();
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
        TypedQuery<Author> query = getEntityManager().createQuery("select a from Author a where a.firstName = :firstName and a.lastName = :lastName", Author.class);
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

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}