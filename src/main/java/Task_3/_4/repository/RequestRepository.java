package Task_3._4.repository;

import Task_3._4.model.Request;


import java.util.List;

public interface RequestRepository {

    /**
     * вернуть список запросов
     *
     * @return
     */
    List<Request> getRequest();

}
