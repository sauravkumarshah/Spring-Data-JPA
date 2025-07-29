package tipsontech.example.sdjpaqueries.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tipsontech.example.sdjpaqueries.domain.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);

    Optional<Book> findByTitle(String title);
}
