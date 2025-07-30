package tipsontech.example.sdjpa.dao;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import tipsontech.example.sdjpa.domain.Book;
import tipsontech.example.sdjpa.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Component
public class BookDaoJPA implements BookDao {

    private final BookRepository bookRepository;

    public BookDaoJPA(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {
        Page<Book> bookPage = bookRepository.findAll(pageable);
        return bookPage.getContent();
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        Pageable pageable = PageRequest.of(offset, pageSize);
        if(offset > 0){
            pageable = pageable.withPage(offset / pageSize);
        }else {
            pageable = pageable.withPage(0);
        }

        return this.findAllBooks(pageable);
    }

    @Override
    public List<Book> findAllBooks() {
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
    public Book getByTitle(String title) {
        Optional<Book> byTitle = bookRepository.findByTitle(title);
        return byTitle.orElseThrow(EntityNotFoundException::new);
    }
}
