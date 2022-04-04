package myBookStore.service;

import myBookStore.model.Book;

import java.io.IOException;
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

    /**
     * получить общее количество книг на складе
     *
     * @return
     */
    int getCountBookByRepository();

    /**
     * сортировка книг по id
     *
     * @param bookList
     */
    void sortingBookById(List<Book> bookList);

    /**
     * сортировка книг по дате поступления
     *
     * @param bookList
     */
    void sortBookByDateDelivery(List<Book> bookList);

    /**
     * получить список не проданных книг более 6 мес отсортированных по названию книги
     *
     * @param bookList
     * @return
     */
    List<Book> sortByNameListOfStaleBooksNotSold(List<Book> bookList);

    /**
     * получить список не проданных книг более 6 мес отсортированных по цене
     *
     * @param bookList
     * @return
     */
    List<Book> sortByPriceListOfStaleBooksNotSold(List<Book> bookList);

    /**
     * получить список не проданных книг более 6 месяцев отсортированных по дате поступления
     *
     * @param books
     * @return
     */
    List<Book> sortByDateDeliveryOfStaleBooksNotSold(List<Book> books);

    void getDescriptionBook(long id);

    /**
     * Создает новый файл и заполняет его книгами из репозитория
     */
    void writeFileCsvBook();

    /**
     * провеярет существует файл или нет. Если нет, создает его и добавляет новую книгу в репозиторий и в файл
     *
     * @param book
     */
    void addNewBookToRepository(Book book);

    /**
     * читает csv файл и сохраняет считанные данные в List Book
     */
    void readCsvBook();

}
