package Task_3._4.service;

import Task_3._4.model.Book;
import Task_3._4.model.Request;
import Task_3._4.model.RequestStatus;
import Task_3._4.repository.BookRepository;
import Task_3._4.repository.RequestRepository;

import java.time.LocalDate;
import java.util.List;

public class RequestServiceImpl implements RequestService {

    private RequestRepository requestRepository;
    private BookRepository bookRepository;

    public RequestServiceImpl(RequestRepository requestRepository, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.requestRepository = requestRepository;
    }

    /**
     * создать запрос на книгу по ее id
     *
     * @param idBook
     */
    public void createRequestBookById(long idBook) {
        Request request = null;
        List<Book> books = null;
        Book current = bookRepository.getBookById(idBook);
        if (current != null) {
            request = new Request(current, RequestStatus.NEW);
            requestRepository.getRequest().add(request);
        }
    }

    /**
     * создатьзапрос на книгу по автору и названию книги
     *
     * @param nameBook
     * @param authorBook
     */
    @Override
    public void createRequestBook(String nameBook, String authorBook) {
        Request request = null;
        for (Request r : requestRepository.getRequest()) {
            if (!r.getBook().getNameBook().equals(nameBook) && !r.getBook().getAuthorBook().equals(authorBook)) {
                request = new Request(
                        new Book("Code", "Isabella Maldonado", 2019, 350, true, LocalDate.now()), RequestStatus.NEW);
                break;
            }
        }
        if (request != null) {
            requestRepository.getRequest().add(request);
        }
    }

    /**
     * вернуть запрос по id книги
     *
     * @param id книги
     * @return
     */
    @Override
    public Request getRequestByBookId(long id) {
        for (Request request : requestRepository.getRequest()) {
            if (request.getBook().getId() == id) {
                return request;
            }
        }
        return null;
    }

    /**
     * перевести статус запроса на статус закрыт
     *
     * @param id id книги
     */
    @Override
    public void canceledRequestByBookId(long id) {
        for (Request request : requestRepository.getRequest()) {
            if (request.getBook().getId() == id) {
                if (request.getRequestStatus().equals(RequestStatus.NEW)) {
                    request.setRequestStatus(RequestStatus.CANCELLED);
                }
            }
        }
    }

    @Override
    public int sortRequestBookByName(String nameBook) {
        int count = 0;
        for (Request request : requestRepository.getRequest()) {
            if (request.getBook().getNameBook().equals(nameBook)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int sortRequestBookById(long idBook) {
        int count = 0;
        for (Request request : requestRepository.getRequest()) {
            if (request.getBook().getId() == idBook) {
                count++;
            }
        }
        System.out.println(count);
        return count;
    }
}


