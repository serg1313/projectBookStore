package mybookstore.service;

import mybookstore.model.Book;
import mybookstore.model.Order;
import mybookstore.model.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    void createOrder(LocalDate localDate, long idCustomer, long... books);

    void cancelOrder(long orderId);

    void changeOrder(long orderId, OrderStatus orderStatus, LocalDate localDate);

    double getPriceOfSoldBooksByOrder(Order order);

    double getPriceOfSoldBooksByOrderId(long idOrder);

    List<Order> sortOrderByDate(List<Order> orders);

    List<Order> getOrderByStatus(OrderStatus orderStatus);

    List<Order> getListOrder();

    List<Order> sortOrderByStatus(List<Order> orders);

    List<Order> sortOrderByPrice(List<Order> orders);

    List<Book> getBookByOrder(long idOrder);

    void getInfoOrder(long id);

    List<Order> getOrderListByPeriod(LocalDate dateStart, LocalDate dateEnd);

    List<Order> getOrderListCompletedByPrice(LocalDate dateStart, LocalDate dateEnd);

    double getSummEarnedMoneyByPeriod(LocalDate dateStart, LocalDate dateEnd);

    int getCountComletedOrdersByPeriod(LocalDate dateStart, LocalDate dateEnd);

    void writeFileCsvOrder();

    void readFileCsvOrder();
}

