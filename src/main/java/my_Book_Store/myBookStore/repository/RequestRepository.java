package my_Book_Store.myBookStore.repository;

import my_Book_Store.myBookStore.model.Request;

import java.util.List;

public interface RequestRepository {

    /**
     * вернуть список запросов
     *
     * @return
     */
    List<Request> getRequest();

    void addNewRequest(long idBook);

}
