package tipsontech.example.sdjpajdbctemplate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpajdbctemplate.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
