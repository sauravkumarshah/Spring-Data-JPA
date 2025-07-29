package tipsontech.example.sdjpahibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpahibernate.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
