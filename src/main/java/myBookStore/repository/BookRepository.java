package myBookStore.repository;

import lombok.extern.log4j.Log4j2;
import myBookStore.model.Book;

import java.util.List;

public interface BookRepository {

    /**
     * возвращает список книг на складе
     *
     * @return
     */
    List<Book> getBooks();

    /**
     * поиск книги по ее id
     *
     * @param id
     * @return
     */
    Book getBookById(long id);

    /**
     * добавляет книгу на склад
     *
     * @param book
     */
    void addBook(Book book);

    /**
     * Добавляет новую книгу на склад
     *
     * @param book
     */
    void addNewBookInRepository(Book book);

    /**
     * Добавляет книгу если книги с таким названием и автором нет в базе
     *
     * @param book
     */
    void addNewBook(Book book);
}
