package myBookStore;

import myBookStore.repository.*;
import myBookStore.service.*;
import myBookStore.ui.controller.MenuController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class.getName());

    public static BookRepository bookRepository = BookRepositoryImpl.getBookRepository();
    public static OrderRepository orderRepository = OrderRepositoryImpl.getOrderRepository();
    public static CustomerRepository customerRepository = new CustomerRepositoryImpl();
    public static OrderService orderService = new OrderServiceImpl(orderRepository, bookRepository, customerRepository);
    public static RequestRepository requestRepository = RequestRepositoryImpl.getRequestRepository();
    public static RequestService requestService = new RequestServiceImpl(requestRepository, bookRepository);
    public static BookService bookService = new BookServiceImpl(bookRepository, requestService);


    public static void main(String[] args) throws IOException {

        MenuController menuController = MenuController.getInstance();
        menuController.run();
        menuController.close();



    }

}



