package mybookstore;


import mybookstore.model.Book;
import mybookstore.model.Order;
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

import java.io.IOException;
import java.time.LocalDate;

/**
 * класс для запуска приложения.
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class.getName());

    public static BookRepository bookRepository = BookRepositoryImpl.getBookRepository();
    public static OrderRepository orderRepository = OrderRepositoryImpl.getOrderRepository();
    public static CustomerRepository customerRepository = new CustomerRepositoryImpl();
    public static OrderService orderService = new OrderServiceImpl(orderRepository, bookRepository, customerRepository);
    public static RequestRepository requestRepository = RequestRepositoryImpl.getRequestRepository();
    public static RequestService requestService = new RequestServiceImpl(requestRepository, bookRepository);
    public static BookService bookService = new BookServiceImpl(bookRepository, requestService);


    @SuppressWarnings("checkstyle:WhitespaceAfter")
    public static void main(String[] args) throws IOException {

        MenuController menuController = MenuController.getInstance();
        menuController.run();
        menuController.close();

//        for(Book order : bookRepository.getBooks()) {
//            System.out.println(order);
//        }
    }
}



