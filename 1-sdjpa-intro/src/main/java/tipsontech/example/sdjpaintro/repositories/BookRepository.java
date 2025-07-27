package tipsontech.example.sdjpaintro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaintro.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
