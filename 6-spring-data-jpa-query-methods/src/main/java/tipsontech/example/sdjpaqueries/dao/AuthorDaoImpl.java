package tipsontech.example.sdjpaqueries.dao;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpaqueries.domain.Author;
import tipsontech.example.sdjpaqueries.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
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
