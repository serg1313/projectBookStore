package myBookStore.repository;

import myBookStore.model.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestRepositoryImpl implements RequestRepository {
    private static RequestRepository requestRepository;
    private List<Request> requests = new ArrayList<>();

    public static RequestRepository getRequestRepository() {
        if (requestRepository == null) {
            requestRepository = new RequestRepositoryImpl();
        }
        return requestRepository;
    }

    public RequestRepositoryImpl() {
        initRequest();
    }

    private void initRequest() {
    }

    /**
     * вернуть список запросов
     *
     * @return
     */
    @Override
    public List<Request> getRequest() {
        return requests;
    }

    @Override
    public void addNewRequest(long idBook) {

    }

}






