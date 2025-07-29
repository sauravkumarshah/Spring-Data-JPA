package tipsontech.example.sdjpahibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpahibernate.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
