package tipsontech.example.sdjpajdbctemplate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpajdbctemplate.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
