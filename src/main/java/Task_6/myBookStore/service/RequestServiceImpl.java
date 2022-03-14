package Task_6.myBookStore.service;

import Task_6.myBookStore.model.Book;
import Task_6.myBookStore.model.Request;
import Task_6.myBookStore.model.RequestStatus;
import Task_6.myBookStore.repository.BookRepository;
import Task_6.myBookStore.repository.RequestRepository;

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
            System.out.println("Запрос на книгу "+ current.getNameBook()+" создан.");
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
        for (Book book : bookRepository.getBooks()) {
            if (book.getNameBook().equalsIgnoreCase(nameBook) && book.getAuthorBook().equalsIgnoreCase(authorBook)) {
                request = new Request(book, RequestStatus.NEW);
                System.out.println("Запрос № " + request.getId() + " на книгу " + request.getBook().getNameBook() + " создан");
                break;
            }
        }
                System.out.println("Указанной книги c наименованием - "+nameBook+
                        " и указанного автора - "+authorBook+" нет в магазине");


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
                    System.out.println("Запрос № "+ request.getId()+" переведен в статус закрыт");
                }
            }
            //System.out.println("Запроса на указанную книгу нет");
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


