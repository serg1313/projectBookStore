package Task_4.repository;



import Task_4.model.Book;

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
     * @param book
     */
    void addNewBookInRepository(Book book);


}
