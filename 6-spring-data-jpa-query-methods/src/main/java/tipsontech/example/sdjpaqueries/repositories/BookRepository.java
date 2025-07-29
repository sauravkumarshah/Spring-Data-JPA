package tipsontech.example.sdjpaqueries.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaqueries.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
