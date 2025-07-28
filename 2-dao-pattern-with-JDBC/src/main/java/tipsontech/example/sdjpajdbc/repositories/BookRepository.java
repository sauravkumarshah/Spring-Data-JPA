package tipsontech.example.sdjpajdbc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpajdbc.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
