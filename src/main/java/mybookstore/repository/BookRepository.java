package mybookstore.repository;

import mybookstore.model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> getBooks();

    Book getBookById(long id);

    void addBook(Book book);

    void addNewBookInRepository(Book book);

    void addNewBook(Book book);
}
