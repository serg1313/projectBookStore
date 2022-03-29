package Task_6.myBookStore.repository;

import Task_6.myBookStore.model.Request;

import java.util.List;

public interface RequestRepository {

    /**
     * вернуть список запросов
     *
     * @return
     */
    List<Request> getRequest();

}
