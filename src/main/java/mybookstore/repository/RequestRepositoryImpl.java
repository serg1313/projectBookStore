package mybookstore.repository;

import mybookstore.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания запросов на книгу.
 */
public class RequestRepositoryImpl implements RequestRepository {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(RequestRepositoryImpl.class.getName());
    /**
     * статичное поле типа RequestRepository.
     */
    private static RequestRepository requestRepository;
    /**
     * поле список запросов.
     */
    private List<Request> requests = new ArrayList<>();

    /**
     * метод проверяет поле requestRepository. Если поле равно null, то создает
     * новый объект типа данных requestRepository. Если не null то возвращает объект.
     * @return значение поля requestRepository
     */
    public static RequestRepository getRequestRepository() {
        LOG.info(RequestRepositoryImpl.class.getSimpleName() + " calling a method getRequestRepository");
        if (requestRepository == null) {
            requestRepository = new RequestRepositoryImpl();
        }
        LOG.info(RequestRepositoryImpl.class.getSimpleName() + " method getRequestRepository completed");
        return requestRepository;
    }

    public RequestRepositoryImpl() {
        LOG.info(RequestRepositoryImpl.class.getSimpleName() + " the constructor is called RequestRepositoryImpl");
        initRequest();
    }

    private void initRequest() {
        LOG.info(RequestRepositoryImpl.class.getSimpleName() + " calling a method initRequest");
        LOG.info(RequestRepositoryImpl.class.getSimpleName() + " method initRequest completed");
    }

    /**
     * возвращает список запросов.
     * @return
     */
    @Override
    public List<Request> getRequest() {
        LOG.info(RequestRepositoryImpl.class.getSimpleName() + " calling a method getRequest");
        LOG.info(RequestRepositoryImpl.class.getSimpleName() + " method getRequest completed");
        return requests;
    }
}






