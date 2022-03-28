package my_Book_Store.myBookStore.service;

import my_Book_Store.myBookStore.model.Request;

import java.io.IOException;

public interface RequestService {

    /**
     * создать запрос на книгу по автору и названию книги
     *
     * @param nameBook
     * @param authorBook
     */
    void createRequestBook(String nameBook, String authorBook);

    /**
     * создать запрос на книгу по ее id
     *
     * @param iD
     */
    void createRequestBookById(long iD);

    /**
     * вернуть запрос по id книги
     *
     * @param bookId
     * @return
     */
    Request getRequestByBookId(long bookId);

    /**
     * перевести статус запроса на статус закрыт по id книги
     *
     * @param id
     */
    void canceledRequestByBookId(long id);

    /**
     * вернуть количество запросов на книгу по ее id
     *
     * @param idBook
     * @return
     */
    int getCountRequestBookById(long idBook);

    /**
     * сортировать запросы по имени книги
     *
     * @param
     * @return
     */
    void sortRequestBookByName();

    /**
     * сортировать по количеству запросов на книгу
     *
     * @param
     */
    void sortRequestByCount();

    void writeFileCsvRequest() throws IOException;

    void readFileCsvRequest();
    void readFileCsvRequest2();


}
