package tipsontech.example.sdjpa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpa.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
    List<Author> findByLastName(String lastName);

    Page<Author> findAllAuthorByLastName(String lastName, Pageable pageable);
}
