package tipsontech.example.sdjpaqueries.dao;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpaqueries.domain.Author;
import tipsontech.example.sdjpaqueries.repositories.AuthorRepository;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
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
