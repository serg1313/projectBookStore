package Task_3._4.service;


import Task_3._4.model.Book;


import java.util.List;

public interface BookService {
    /**
     * списать книгу со склада и перевести в статус отсутствует
     *
     * @param id
     */
    void writeOffBook(long id);

    /**
     * добавить книгу на склад
     *
     * @param id
     */
    void addBook(long id);

    /**
     * узнать наличие книги на складе
     *
     * @param idBook
     * @return
     */
    boolean getStatusBook(long idBook);

    /**
     * сортировка книг по названию
     *
     * @param bookList
     */
    void sortingBookByName(List<Book> bookList);

    /**
     * сортировка книг по дате издания
     *
     * @param bookList
     */
    void sortingBookByYearOfPublic(List<Book> bookList);

    /**
     * Сортировка книг по стоимости
     *
     * @param bookList
     */
    void sortingBookByPrice(List<Book> bookList);

    /**
     * сортировка книг по наличию на складе
     *
     * @param b
     */
    void sortBooksByStockAvailability(boolean b);

    /**
     * Возвращает книгу по ID
     *
     * @param b
     * @return
     */
    Book getBookById(long b);

    void addNewBookInRepository(Book book);

}
