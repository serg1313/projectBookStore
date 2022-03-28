package my_Book_Store.myBookStore;

import my_Book_Store.myBookStore.repository.*;
import my_Book_Store.myBookStore.service.*;
import my_Book_Store.myBookStore.ui.controller.MenuController;

import java.io.IOException;

public class Main {

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



