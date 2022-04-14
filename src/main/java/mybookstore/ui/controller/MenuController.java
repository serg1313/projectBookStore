package mybookstore.ui.controller;

import mybookstore.repository.*;
import mybookstore.service.*;
import mybookstore.ui.builder.Builder;
import mybookstore.ui.builder.RootMenuBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class MenuController {
    private static final Logger LOG = LogManager.getLogger(MenuController.class.getName());
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

    private int getInput(int maxChoice) {
        LOG.info(MenuController.class.getSimpleName() + " " + "calling a method getInput");
        int menuChois = sc.nextInt();
        if (menuChois < 1 || menuChois > maxChoice) {
            LOG.info("Не верный выбор");
            return -1;
        }
        LOG.info(MenuController.class.getSimpleName() + " " + "method getInput completed");
        return menuChois;
    }

    public void close() throws IOException {
        LOG.info(MenuController.class.getSimpleName() + " " + "calling a method close");
        bookService.writeFileCsvBook();
        orderService.writeFileCsvOrder();
        requestService.writeFileCsvRequest();
        sc.close();
        LOG.info(MenuController.class.getSimpleName() + " " + "method close completed");
    }
}
