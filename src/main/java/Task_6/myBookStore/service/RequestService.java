package Task_6.myBookStore.service;

import Task_6.myBookStore.model.Request;

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
     * @param id
     * @return
     */
    Request getRequestByBookId(long id);

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
    int sortRequestBookById(long idBook);

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


}
