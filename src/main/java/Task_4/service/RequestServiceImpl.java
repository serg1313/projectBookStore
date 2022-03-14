package Task_4.service;



import Task_4.model.Book;
import Task_4.model.Request;
import Task_4.model.RequestStatus;
import Task_4.repository.BookRepository;
import Task_4.repository.RequestRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        Request request;
        Book current = bookRepository.getBookById(idBook);
        if (current != null) {
            request = new Request(current, RequestStatus.NEW);
            requestRepository.getRequest().add(request);
            current.setRequests(request);
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
    public void sortRequestBookByName() {
        bookRepository.getBooks().stream().filter(book -> book.getRequests().size() > 0)
                .sorted((o1, o2) -> o1.getNameBook().compareTo(o2.getNameBook()))
                .forEach(book -> System.out.println("На книгу " + book.getNameBook() + " всего " + book.getRequests().size() + " запросов."));

    }

    @Override
    public void sortRequestByCount() {
        bookRepository.getBooks().stream().filter(book -> book.getRequests().size() > 0)
                .sorted((o1, o2) -> o2.getRequests().size() - o1.getRequests().size())
                .forEach(book -> System.out.println("На книгу " + book.getNameBook() + " всего " + book.getRequests().size() + " запросов."));
    }

    @Override
    public int sortRequestBookById(long idBook) {
        int count = 0;
        for (Request request : requestRepository.getRequest()) {
            if (request.getBook().getId() == idBook) {
                count++;
            }
        }
        return count;
    }
}


