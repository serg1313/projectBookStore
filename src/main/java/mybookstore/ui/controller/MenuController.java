package mybookstore.ui.controller;

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
import mybookstore.ui.builder.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Класс для постройки меню и возвращения построенного меню.
 */
@SuppressWarnings("checkstyle:FinalClass")
public class MenuController {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(MenuController.class.getName());
    /**
     * поле для возможности инициализации класса RequestService и BookService.
     */
    private BookRepository bookRepository = new BookRepositoryImpl();
    /**
     * поле для возможности инициализации класса OrderService.
     */
    private OrderRepository orderRepository = OrderRepositoryImpl.getOrderRepository();
    /**
     * поле для возможности инициализации класса OrderService.
     */
    private RequestRepository requestRepository = new RequestRepositoryImpl();
    /**
     * поле для возможности инициализации класса OrderService.
     */
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    /**
     * инициализация объекта RequestService.
     */
    private RequestService requestService = new RequestServiceImpl(requestRepository, bookRepository);
    /**
     * инициализация объекта BookService.
     */
    private BookService bookService = new BookServiceImpl(bookRepository, requestService);
    /**
     * инициализация объекта OrderService.
     */
    private OrderService orderService = new OrderServiceImpl(orderRepository, bookRepository, customerRepository);
    /**
     * поле экземпляра класса MenuController.
     */
    private static MenuController instance;
    /**
     * поле класса Builder.
     */
    private Builder builder;
    /**
     * поле класса Navigator.
     */
    private Navigator navigator;
    /**
     * поле класса Scanner.
     */
    private final Scanner sc = new Scanner(System.in);

    private MenuController() {
    }

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }

    public void run() {
        LOG.info(MenuController.class.getSimpleName() + " " + "calling a method run");
        bookService.readCsvBook();
        orderService.readFileCsvOrder();
        requestService.readFileCsvRequest();
        builder = new Builder();
        builder.buildMenu();
        navigator = new Navigator(builder.getRootMenu());
        int index = 0;
        while (index != -1) {
            navigator.printMenu();
            index = getInput(navigator.getCurrentMenu().getMenuItem().size());
            navigator.navigate(index);
        }
        LOG.info(MenuController.class.getSimpleName() + " " + "method run completed");
    }

    private int getInput(final int maxChoice) {
        LOG.info(MenuController.class.getSimpleName() + " " + "calling a method getInput");
        int menuChois = sc.nextInt();
        if (menuChois < 1 || menuChois > maxChoice) {
            LOG.info("Не верный выбор");
            return -1;
        }
        LOG.info(MenuController.class.getSimpleName() + " " + "method getInput completed");
        return menuChois;
    }

    public void close() {
        try {
            LOG.info(MenuController.class.getSimpleName() + " " + "calling a method close");
            bookService.writeFileCsvBook();
            orderService.writeFileCsvOrder();
            requestService.writeFileCsvRequest();
        } catch (Exception e) {
            LOG.error(MenuController.class.getSimpleName() + " " + "Error message ", e);
        } finally {
            sc.close();
        }
        LOG.info(MenuController.class.getSimpleName() + " " + "method close completed");
    }
}
