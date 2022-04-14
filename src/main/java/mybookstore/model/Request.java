package mybookstore.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для создания объекта request.
 */
public class Request extends BaseEntity {
    /**
     * поле для logger.
     */
    private static final Logger LOG = LogManager.getLogger(Request.class.getName());
    /**
     * поле id объекта request.
     */
    private static long id = 1;
    /**
     * поле id книги в запросе.
     */
    private long idBook;
    /**
     * Поле статуса запроса.
     */
    private RequestStatus requestStatus;

    public Request(final long idBook, final RequestStatus requestStatus) {
        super(id++);
        this.idBook = idBook;
        this.requestStatus = requestStatus;
    }
    /**
     * метод возвращает id книги в запросе.
     * @return idBook
     */
    public long getIdBook() {
        return idBook;
    }
    /**
     * метод вносит id книги в запрос.
     * @param idBook
     */
    public void setIdBook(final long idBook) {
        this.idBook = idBook;
    }
    /**
     * метод возвращает статус запроса.
     * @return requestStatus
     */
    public RequestStatus getRequestStatus() {
        return requestStatus;
    }
    /**
     * метод устанавливает статус запроса.
     * @param requestStatus
     */
    public void setRequestStatus(final RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    /**
     * переопределенный метод toString для класса Request.
     * @return
     */
    @Override
    public String toString() {
        return "Request{"
                + " id = " + getId()
                + " idBook = " + idBook
                + ", requestStatus = " + requestStatus
                + '}';
    }
}


