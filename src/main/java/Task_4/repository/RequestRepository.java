package Task_4.repository;



import Task_4.model.Request;

import java.util.List;

public interface RequestRepository {

    /**
     * вернуть список запросов
     *
     * @return
     */
    List<Request> getRequest();

}
