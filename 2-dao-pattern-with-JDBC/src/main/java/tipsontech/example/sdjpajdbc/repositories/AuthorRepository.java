package tipsontech.example.sdjpajdbc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpajdbc.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
