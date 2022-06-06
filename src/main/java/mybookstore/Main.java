package mybookstore;

import mybookstore.repository.BookRepository;
import mybookstore.repository.BookRepositoryImpl;
import mybookstore.repository.CustomerRepository;
import mybookstore.repository.CustomerRepositoryImpl;
import mybookstore.repository.OrderRepository;
import mybookstore.repository.OrderRepositoryImpl;
import mybookstore.repository.RequestRepository;
import mybookstore.repository.RequestRepositoryImpl;
import mybookstore.service.BookService;
import mybookstore.service.BookServiceImpl;
import mybookstore.service.OrderService;
import mybookstore.service.OrderServiceImpl;
import mybookstore.service.RequestService;
import mybookstore.service.RequestServiceImpl;
import mybookstore.ui.controller.MenuController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * класс для запуска приложения.
 */
@SuppressWarnings({"checkstyle:VisibilityModifier"})
public class Main {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(Main.class.getName());
    /**
     * поле для инициализации.
     */
    @SuppressWarnings("checkstyle:VisibilityModifier")
    private static BookRepository bookRepository = BookRepositoryImpl.getBookRepository();
    /**
     * поле для инициализации.
     */
    private static OrderRepository orderRepository = OrderRepositoryImpl.getOrderRepository();
    /**
     * поле для инициализации.
     */
    private static CustomerRepository customerRepository = new CustomerRepositoryImpl();
    /**
     * поле для инициализации.
     */
    private static OrderService orderService = new OrderServiceImpl(orderRepository, bookRepository, customerRepository);
    /**
     * поле для инициализации.
     */
    private static RequestRepository requestRepository = RequestRepositoryImpl.getRequestRepository();
    /**
     * поле для инициализации.
     */
    private static RequestService requestService = new RequestServiceImpl(requestRepository, bookRepository);
    /**
     * поле для инициализации.
     */
    private static BookService bookService = new BookServiceImpl(bookRepository, requestService);

    /**
     * конструктор.
     */
    protected Main() {
    }

    /**
     * главный метод для запуска приложения.
     * @param args аргументы
     */
    @SuppressWarnings("checkstyle:WhitespaceAround")
    public static void main(final String[] args) {
        MenuController menuController = MenuController.getInstance();
            menuController.run();
            menuController.close();
    }

    /**
     * возвращает значение поля bookRepository.
     * @return bookRepository
     */
    public static BookRepository getBookRepository() {
        return bookRepository;
    }

    /**
     * возвращает значение поля orderRepository.
     * @return orderRepository
     */
    public static OrderRepository getOrderRepository() {
        return orderRepository;
    }

    /**
     * возвращает значение поля customerRepository.
     * @return customerRepository
     */
    public static CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    /**
     * возвращает значение поля orderService.
     * @return orderService
     */
    public static OrderService getOrderService() {
        return orderService;
    }

    /**
     * возвращает значение поля requestRepository.
     * @return requestRepository
     */
    public static RequestRepository getRequestRepository() {
        return requestRepository;
    }

    /**
     * возвращает значение поля requestService.
     * @return requestService
     */
    public static RequestService getRequestService() {
        return requestService;
    }

    /**
     * возвращает значение поля bookService.
     * @return bookService
     */
    public static BookService getBookService() {
        return bookService;
    }
}



