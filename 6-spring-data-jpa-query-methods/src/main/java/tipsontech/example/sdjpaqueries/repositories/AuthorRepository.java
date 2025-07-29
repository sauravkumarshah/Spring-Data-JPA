package tipsontech.example.sdjpaqueries.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaqueries.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByFirstNameAndLastName(String firstName, String lastName);
}
