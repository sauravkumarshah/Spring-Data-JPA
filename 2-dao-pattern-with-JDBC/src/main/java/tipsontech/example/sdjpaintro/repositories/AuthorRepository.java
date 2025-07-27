package tipsontech.example.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaintro.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
