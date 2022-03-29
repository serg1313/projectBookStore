package myBookStore.repository;

import myBookStore.model.Request;

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
