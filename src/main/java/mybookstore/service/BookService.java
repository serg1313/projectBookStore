package mybookstore.service;

import mybookstore.model.Book;

import java.util.List;

public interface BookService {

    void writeOffBook(long id);

    void addBook(long id);

    boolean getStatusBook(long idBook);

    void sortingBookByName(List<Book> bookList);

    void sortingBookByYearOfPublic(List<Book> bookList);

    void sortingBookByPrice(List<Book> bookList);

    void sortBooksByStockAvailability(boolean b);

    Book getBookById(long b);

    int getCountBookByRepository();

    void sortingBookById(List<Book> bookList);

    void sortBookByDateDelivery(List<Book> bookList);

    List<Book> sortByNameListOfStaleBooksNotSold(List<Book> bookList);

    List<Book> sortByPriceListOfStaleBooksNotSold(List<Book> bookList);

    List<Book> sortByDateDeliveryOfStaleBooksNotSold(List<Book> books);

    void getDescriptionBook(long id);

    void writeFileCsvBook();

    void addNewBookToRepository(Book book);

    void readCsvBook();
}
