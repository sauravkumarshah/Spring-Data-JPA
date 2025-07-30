package tipsontech.example.sdjpa.dao;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpa.domain.Author;
import tipsontech.example.sdjpa.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoJPA implements AuthorDao {

    private final AuthorRepository authorRepository;

    public AuthorDaoJPA(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Author> findAuthorByLastName(String lastName, Pageable pageable) {
        return authorRepository.findAllAuthorByLastName(lastName, pageable).getContent();
    }

    @Override
    public List<Author> findAuthorByLastName(String lastName, int pageSize, int offset) {
        Pageable pageable = PageRequest.of(offset, pageSize);
        if(offset > 0){
            pageable = pageable.withPage(offset / pageSize);
        }else {
            pageable = pageable.withPage(0);
        }

        return this.findAllAuthors(pageable);
    }

    @Override
    public List<Author> findAuthorByLastName(String lastName) {
        return authorRepository.findByLastName(lastName);
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        Optional<Author> byFirstNameAndLastName = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        return byFirstNameAndLastName.orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author update(Author author) {
        Author foundAuthor = authorRepository.findById(author.getId()).orElse(null);
        foundAuthor.setFirstName(author.getFirstName());
        foundAuthor.setLastName(author.getLastName());
        return authorRepository.save(foundAuthor);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
