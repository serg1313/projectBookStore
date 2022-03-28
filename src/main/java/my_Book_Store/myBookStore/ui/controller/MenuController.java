package my_Book_Store.myBookStore.ui.controller;

import my_Book_Store.myBookStore.repository.*;
import my_Book_Store.myBookStore.service.*;
import my_Book_Store.myBookStore.ui.builder.Builder;

import java.io.IOException;
import java.util.Scanner;

public class MenuController {
    BookRepository bookRepository = new BookRepositoryImpl();
    OrderRepository orderRepository = OrderRepositoryImpl.getOrderRepository();
    RequestRepository requestRepository = new RequestRepositoryImpl();
    CustomerRepository customerRepository = new CustomerRepositoryImpl();
    RequestService requestService = new RequestServiceImpl(requestRepository, bookRepository);
    BookService bookService = new BookServiceImpl(bookRepository, requestService);
    OrderService orderService = new OrderServiceImpl(orderRepository, bookRepository, customerRepository);

    private static MenuController instance;
    private Builder builder;
    private Navigator navigator;
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
    }

    private int getInput(int maxChoice) {
        int menuChois = sc.nextInt();
        if (menuChois < 1 || menuChois > maxChoice) {
            System.out.println("Не верный выбор");
            return -1;
        }
        return menuChois;
    }

    public void close() throws IOException {
        bookService.writeFileCsvBook();
        orderService.writeFileCsvOrder();
        requestService.writeFileCsvRequest();
        sc.close();
    }
}
