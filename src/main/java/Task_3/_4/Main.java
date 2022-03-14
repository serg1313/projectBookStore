package Task_3._4;

import Task_3._4.model.OrderStatus;
import Task_3._4.repository.*;
import Task_3._4.service.*;

import java.time.LocalDate;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepositoryImpl();
        OrderRepository orderRepository = new OrderRepositoryImpl();
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        OrderService orderService = new OrderServiceImpl(orderRepository, bookRepository, customerRepository);
        RequestRepository requestRepository = new RequestRepositoryImpl();
        RequestService requestService = new RequestServiceImpl(requestRepository, bookRepository);
        BookService bookService = new BookServiceImpl(bookRepository, requestService);

        // Списать книгу со склада (перевести в статус “отсутствует”)
        bookService.writeOffBook(5);

        //Создать заказ
        orderService.createOrder(LocalDate.of(2022,2,10), 4, new long[]{5,4,9});

        // отменить заказ
        orderService.cancelOrder(1);

        //изменить статус заказа
        orderService.changeOrder(1, OrderStatus.COMPLETED);
        orderService.changeOrder(2, OrderStatus.CANCELLED);

        // Добавить книгу на склад (закрывает все запросы книги и меняет ее статус на “вналичии”)
        bookService.addBook(8);

        //Оставить запрос на книгу
        requestService.createRequestBookById(4);
    }
    // write your code here

}
