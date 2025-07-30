package tipsontech.example.sdjpaqueries.repositories;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import tipsontech.example.sdjpaqueries.domain.Book;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book jpaNamed(@Param("title") String title);
    @Query(value = "SELECT * FROM Book b WHERE b.title = :title", nativeQuery = true)
    Book findBookByTitleNativeQuery(@Param("title") String title);
    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book findBookByTitleWithQueryNamed(@Param("title") String title);
    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Book findBookByTitleWithQuery(String title);
    Book findByIsbn(String isbn);

    Optional<Book> findByTitle(String title);

    Book readByTitle(String title);

    @Nullable
    Book getByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();

    @Async
    Future<Book> queryByTitle(String title);
}
