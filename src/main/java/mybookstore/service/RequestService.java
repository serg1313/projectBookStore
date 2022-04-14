package mybookstore.service;

import mybookstore.model.Request;

public interface RequestService {

    void createRequestBook(String nameBook, String authorBook);

    void createRequestBookById(long iD);

    Request getRequestByBookId(long bookId);

    void canceledRequestByBookId(long id);

    int getCountRequestBookById(long idBook);

    void sortRequestBookByName();

    void sortRequestByCount();

    void writeFileCsvRequest();

    void readFileCsvRequest();
}
