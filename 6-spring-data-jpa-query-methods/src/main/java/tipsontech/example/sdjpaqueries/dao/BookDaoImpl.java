package tipsontech.example.sdjpaqueries.dao;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpaqueries.domain.Book;
import tipsontech.example.sdjpaqueries.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByISBN(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(Book book) {
        Book foundBook = bookRepository.findById(book.getId()).orElse(null);
        foundBook.setTitle(book.getTitle());
        foundBook.setIsbn(book.getIsbn());
        return bookRepository.save(foundBook);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book getBookByTitle(String title) {
        Optional<Book> byTitle = bookRepository.findByTitle(title);
        return byTitle.orElseThrow(EntityNotFoundException::new);
    }
}
