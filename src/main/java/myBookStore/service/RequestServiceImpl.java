package myBookStore.service;

import myBookStore.model.Book;
import myBookStore.model.Request;
import myBookStore.model.RequestStatus;
import myBookStore.repository.BookRepository;
import myBookStore.repository.RequestRepository;
import myBookStore.repository.RequestRepositoryImpl;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RequestServiceImpl implements RequestService {
    private static final Logger log = LogManager.getLogger(RequestServiceImpl.class.getName());
    private RequestRepository requestRepository;
    private BookRepository bookRepository;

    public RequestServiceImpl(RequestRepository requestRepository, BookRepository bookRepository) {
        log.info("the constructor is called RequestServiceImpl");
        this.bookRepository = bookRepository;
        this.requestRepository = requestRepository;
    }

    /**
     * создать запрос на книгу по ее id
     *
     * @param idBook
     */
    public void createRequestBookById(long idBook) {
        log.info("calling a method createRequestBookById");
        Request request;
        Book current = bookRepository.getBookById(idBook);
        if (current != null) {
            request = new Request(idBook, RequestStatus.NEW);
            requestRepository.getRequest().add(request);
            current.setRequests(request);
            System.out.println("Запрос на книгу \"" + current.getNameBook() + "\" создан.");
        } else {
            System.out.println("Книги с указанным id - " + idBook + " нет в магазине");
            log.info("Книги с указанным id - " + idBook + " нет в магазине");
        }
        log.info("method createRequestBookById completed");
    }

    /**
     * создатьзапрос на книгу по автору и названию книги
     *
     * @param nameBook
     * @param authorBook
     */
    @Override
    public void createRequestBook(String nameBook, String authorBook) {
        log.info("calling a method createRequestBook");
        Request request = null;
        for (Book book : bookRepository.getBooks()) {
            if (book.getNameBook().equalsIgnoreCase(nameBook) && book.getAuthorBook().equalsIgnoreCase(authorBook)) {
                request = new Request(book.getId(), RequestStatus.NEW);
                System.out.println("Запрос № " + request.getId() + " на книгу " + book.getNameBook() + " создан");
                break;
            }
        }
        System.out.println("Указанной книги c наименованием - " + nameBook +
                " и указанного автора - " + authorBook + " нет в магазине");
        log.info("Указанной книги c наименованием - " + nameBook +
                " и указанного автора - " + authorBook + " нет в магазине");
        if (request != null) {
            requestRepository.getRequest().add(request);
        }
        log.info("method createRequestBook completed");
    }

    /**
     * вернуть запрос по id книги
     *
     * @param bookId книги
     * @return
     */
    @Override
    public Request getRequestByBookId(long bookId) {
        log.info("calling a method getRequestByBookId");
        for (Request request : requestRepository.getRequest()) {
            if (request.getIdBook() == bookId) {
                return request;
            }
        }
        log.info("method getRequestByBookId completed");
        return null;
    }

    /**
     * перевести статус запроса на статус закрыт
     *
     * @param id id книги
     */
    @Override
    public void canceledRequestByBookId(long id) {
        log.info("calling a method canceledRequestByBookId");
        for (Request request : requestRepository.getRequest()) {
            if (request.getIdBook() == id) {
                if (request.getRequestStatus().equals(RequestStatus.NEW)) {
                    request.setRequestStatus(RequestStatus.CANCELLED);
                    System.out.println("Запрос № " + request.getId() + " переведен в статус закрыт");
                }
            }
            System.out.println("Запроса на указанную книгу нет");
            log.info("Запроса на указанную книгу нет");
        }
        log.info("method canceledRequestByBookId completed");
    }

    @Override
    public void sortRequestBookByName() {
        log.info("calling a method sortRequestBookByName");
        bookRepository.getBooks().stream().filter(book -> book.getRequests().size() > 0)
                .sorted((o1, o2) -> o1.getNameBook().compareTo(o2.getNameBook()))
                .forEach(book -> System.out.println("На книгу " + book.getNameBook() + " всего " + book.getRequests().size() + " запросов."));
        log.info("method sortRequestBookByName completed");
    }

    @Override
    public void sortRequestByCount() {
        log.info("calling a method sortRequestByCount");
        bookRepository.getBooks().stream().filter(book -> book.getRequests().size() > 0)
                .sorted((o1, o2) -> o2.getRequests().size() - o1.getRequests().size())
                .forEach(book -> System.out.println("На книгу " + book.getNameBook() + " всего " + book.getRequests().size() + " запросов."));
        log.info("method sortRequestByCount completed");
    }

    @Override
    public void writeFileCsvRequest() {
        log.info("calling a method writeFileCsvRequest");
        RequestRepository requestRepository = RequestRepositoryImpl.getRequestRepository();
        String csvFile = "requestRepository.csv";
        Path path = Paths.get(csvFile);
        File file = new File(csvFile);
        char separator = ';';
        List<Request> requestList = requestRepository.getRequest();
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile),
                separator,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {
            for (Request request : requestList) {
                String[] csvNewFile = new String[3];
                csvNewFile[0] = String.valueOf(request.getId());
                csvNewFile[1] = String.valueOf(request.getIdBook());
                csvNewFile[2] = String.valueOf(request.getRequestStatus());
                writer.writeNext(csvNewFile);
            }
        } catch (IOException e) {
            log.error("Error message ", e);
        }
        log.info("method writeFileCsvRequest completed");
    }

    @Override
    public void readFileCsvRequest() {
        log.info("calling a method readFileCsvRequest");
        RequestRepository requestRepository = RequestRepositoryImpl.getRequestRepository();
        List<Request> requests = requestRepository.getRequest();
        Book book = null;
        if (requests.size() == 0) {
            String csv = "requestRepository.csv";
            try (BufferedReader reader = new BufferedReader(new FileReader(csv));) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] items = line.split(";");
                    try {
                        Long id = Long.parseLong(items[0]);
                        Long idBook = Long.parseLong(items[1]);
                        RequestStatus requestStatus = RequestStatus.valueOf(items[2]);
                        Request request = new Request(idBook, requestStatus);
                        requests.add(request);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                        System.out.println("не сработал");
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                log.error("Error message ", e);
            } catch (IOException e) {
                log.error("Error message ", e);
            }
        }
        log.info("method readFileCsvRequest completed");
    }

    @Override
    public int getCountRequestBookById(long idBook) {
        log.info("calling a method getCountRequestBookById");
        int count = 0;
        for (Request request : requestRepository.getRequest()) {
            if (request.getIdBook() == idBook) {
                count++;
            }
        }
        log.info("method getCountRequestBookById completed");
        return count;
    }

}



