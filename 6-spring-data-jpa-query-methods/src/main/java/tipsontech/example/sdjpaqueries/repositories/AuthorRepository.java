package tipsontech.example.sdjpaqueries.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaqueries.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

//    Author findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
