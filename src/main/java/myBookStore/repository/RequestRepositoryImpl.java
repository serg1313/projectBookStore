package myBookStore.repository;

import myBookStore.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RequestRepositoryImpl implements RequestRepository {
    private static final Logger log = LogManager.getLogger(RequestRepositoryImpl.class.getName());
    private static RequestRepository requestRepository;
    private List<Request> requests = new ArrayList<>();

    public static RequestRepository getRequestRepository() {
        log.info("calling a method getRequestRepository");
        if (requestRepository == null) {
            requestRepository = new RequestRepositoryImpl();
        }
        log.info("method getRequestRepository completed");
        return requestRepository;
    }

    public RequestRepositoryImpl() {
        log.info("the constructor is called RequestRepositoryImpl");
        initRequest();
    }

    private void initRequest() {
        log.info("calling a method initRequest");
        log.info("method initRequest completed");
    }

    /**
     * вернуть список запросов
     *
     * @return
     */
    @Override
    public List<Request> getRequest() {
        log.info("calling a method getRequest");
        log.info("method getRequest completed");
        return requests;
    }

    @Override
    public void addNewRequest(long idBook) {
        log.info("calling a method addNewRequest");
        log.info("method addNewRequest completed");
    }

}






