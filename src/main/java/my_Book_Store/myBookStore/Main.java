package Task_6.myBookStore;

import Task_6.myBookStore.repository.*;
import Task_6.myBookStore.service.*;
import Task_6.myBookStore.ui.controller.MenuController;

public class Main {
    public static BookRepository bookRepository = new BookRepositoryImpl();
    public static OrderRepository orderRepository = new OrderRepositoryImpl();
    public static CustomerRepository customerRepository = new CustomerRepositoryImpl();
    public static OrderService orderService = new OrderServiceImpl(orderRepository, bookRepository, customerRepository);
    public static RequestRepository requestRepository = new RequestRepositoryImpl();
    public static RequestService requestService = new RequestServiceImpl(requestRepository, bookRepository);
    public static BookService bookService = new BookServiceImpl(bookRepository, requestService);

    public static void main(String[] args) {

        MenuController menuController = MenuController.getInstance();
        menuController.run();
        menuController.close();

    }
}
