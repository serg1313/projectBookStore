package mybookstore.service;

import com.opencsv.CSVWriter;
import mybookstore.model.Book;
import mybookstore.model.Request;
import mybookstore.model.RequestStatus;
import mybookstore.repository.BookRepository;
import mybookstore.repository.RequestRepository;
import mybookstore.repository.RequestRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Класс для проведения операций над объектами
 * класса RequestRepository (запросами на книгу).
 */
public class RequestServiceImpl implements RequestService {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(RequestServiceImpl
            .class.getName());
    /**
     * поле класса RequestRepository для операций над объектами данного класса.
     */
    private RequestRepository requestRepository;
    /**
     * поле класса BookRepository для операций над объектами данного класса.
     */
    private BookRepository bookRepository;

    public RequestServiceImpl(final RequestRepository requestRepository,
                              final BookRepository bookRepository) {
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "the constructor is called RequestServiceImpl");
        this.bookRepository = bookRepository;
        this.requestRepository = requestRepository;
    }

    /**
     * Принимает id книги. Проверяет наличие книги с указанным id.
     * Если книга имеется, создает новый запрос со статусом NEW и вносит в список запросов.
     * Если книги не имеется, выводит на консоль что указанной книги нет.
     * @param idBook
     */
    public void createRequestBookById(final long idBook) {
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "calling a method createRequestBookById");
        Request request;
        Book current = bookRepository.getBookById(idBook);
        if (current != null) {
            request = new Request(idBook, RequestStatus.NEW);
            requestRepository.getRequest().add(request);
            current.setRequests(request);
            System.out.println("Запрос на книгу \"" + current.getNameBook() + "\" создан.");
        } else {
            System.out.println("Книги с указанным id - " + idBook + " нет в магазине");
            LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "Книги с указанным id - " + idBook + " нет в магазине");
        }
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "method createRequestBookById completed");
    }

    /**
     * Принимает название и автора книги.
     * Сверяет книги из списка книг по указанным параметрам.
     * Если книга с указанными параметрами имеется в списке книг, создает новый запрос и вносит его в список запросов.
     * Если книги не имеется, выводит на консоль что указанной книги нет.
     * @param nameBook
     * @param authorBook
     */
    @Override
    public void createRequestBook(final String nameBook, final String authorBook) {
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "calling a method createRequestBook");
        Request request = null;
        for (Book book : bookRepository.getBooks()) {
            if (book.getNameBook().equalsIgnoreCase(nameBook) && book.getAuthorBook().equalsIgnoreCase(authorBook)) {
                request = new Request(book.getId(), RequestStatus.NEW);
                System.out.println("Запрос № " + request.getId() + " на книгу " + book.getNameBook() + " создан");
                break;
            }
        }
        System.out.println("Указанной книги c наименованием - " + nameBook
                + " и указанного автора - " + authorBook + " нет в магазине");
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "Указанной книги c наименованием - " + nameBook
                + " и указанного автора - " + authorBook + " нет в магазине");
        if (request != null) {
            requestRepository.getRequest().add(request);
        }
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "method createRequestBook completed");
    }

    /**
     * Принимает id книги.
     * Получает все запросы из списка запросов.
     * Проверяет id книг в каждом запросе из списка.
     * Если поиск успешен, возвращает запрос с указаной книгой.
     * @param bookId книги
     * @return
     */
    @Override
    public Request getRequestByBookId(final long bookId) {
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "calling a method getRequestByBookId");
        for (Request request : requestRepository.getRequest()) {
            if (request.getIdBook() == bookId) {
                return request;
            }
        }
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "method getRequestByBookId completed");
        return null;
    }

    /**
     * Переводит статус запроса на статус закрыт.
     * Принимает id книги.
     * Получает список запросов и проводит поиск запроса в котором имеется книга с указанным id.
     * Если статус найденного запроса NEW, происходит изменение статуса на CANCELED.
     * @param id id книги
     */
    @Override
    public void canceledRequestByBookId(final long id) {
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "calling a method canceledRequestByBookId");
        for (Request request : requestRepository.getRequest()) {
            if (request.getIdBook() == id) {
                if (request.getRequestStatus().equals(RequestStatus.NEW)) {
                    request.setRequestStatus(RequestStatus.CANCELLED);
                    System.out.println("Запрос № " + request.getId() + " переведен в статус закрыт");
                }
            }
            System.out.println("Запроса на указанную книгу нет");
            LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "Запроса на указанную книгу нет");
        }
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "method canceledRequestByBookId completed");
    }

    /**
     * Производит сортировку запросов по имени книги в алфавитном порядке.
     */
    @Override
    public void sortRequestBookByName() {
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "calling a method sortRequestBookByName");
        bookRepository.getBooks().stream().filter(book -> book.getRequests().size() > 0)
                .sorted((o1, o2) -> o1.getNameBook().compareTo(o2.getNameBook()))
                .forEach(book -> System.out.println("На книгу " + book.getNameBook() + " всего " + book.getRequests().size() + " запросов."));
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "method sortRequestBookByName completed");
    }

    /**
     * Возвращает список книг отсортированных по количеству запросов.
     */
    @Override
    public void sortRequestByCount() {
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "calling a method sortRequestByCount");
        bookRepository.getBooks().stream().filter(book -> book.getRequests().size() > 0)
                .sorted((o1, o2) -> o2.getRequests().size() - o1.getRequests().size())
                .forEach(book -> System.out.println("На книгу " + book.getNameBook() + " всего " + book.getRequests().size() + " запросов."));
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "method sortRequestByCount completed");
    }

    /**
     * создает csv файл и заполняет его запросами из списка запросов.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public void writeFileCsvRequest() {
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "calling a method writeFileCsvRequest");
        RequestRepository requestRepository1 = RequestRepositoryImpl.getRequestRepository();
        String csvFile = "requestRepository.csv";
        Path path = Paths.get(csvFile);
        File file = new File(csvFile);
        char separator = ';';
        List<Request> requestList = requestRepository1.getRequest();
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
            LOG.error(RequestServiceImpl.class.getSimpleName() + " " + "Error message ", e);
        }
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "method writeFileCsvRequest completed");
    }

    /**
     * Проверяет на заполненность список запросов. Если список запросов пустой,
     * то считывает CSV файл и вносит
     * считанную информацию о запросах в список.
     */
    @Override
    public void readFileCsvRequest() {
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "calling a method readFileCsvRequest");
        RequestRepository requestRepository1 = RequestRepositoryImpl.getRequestRepository();
        List<Request> requests = requestRepository1.getRequest();
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
                        LOG.error(RequestServiceImpl.class.getSimpleName() + " " + "Error message ", e);
                    }
                }
            } catch (FileNotFoundException e) {
                LOG.error(RequestServiceImpl.class.getSimpleName() + " " + "Error message ", e);
            } catch (IOException e) {
                LOG.error(RequestServiceImpl.class.getSimpleName() + " " + "Error message ", e);
            }
        }
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "method readFileCsvRequest completed");
    }

    /**
     * Принимает id книги. Возвращает количество запросов на указанную книгу.
     * @param idBook
     * @return
     */
    @Override
    public int getCountRequestBookById(final long idBook) {
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "calling a method getCountRequestBookById");
        int count = 0;
        for (Request request : requestRepository.getRequest()) {
            if (request.getIdBook() == idBook) {
                count++;
            }
        }
        LOG.info(RequestServiceImpl.class.getSimpleName() + " " + "method getCountRequestBookById completed");
        return count;
    }
}



