package myBookStore.repository;

import myBookStore.model.Book;
import myBookStore.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private static final Logger log = LogManager.getLogger(OrderRepositoryImpl.class.getName());

    private static OrderRepository orderRepository;
    public List<Order> orders = new ArrayList<>();

    public static OrderRepository getOrderRepository() {
        log.info("calling a method getOrderRepository");
        if (orderRepository == null) {
            orderRepository = new OrderRepositoryImpl();
        }
        log.info("method getOrderRepository completed");
        return orderRepository;
    }

    public OrderRepositoryImpl() {
        log.info("the constructor is called OrderRepositoryImpl");
        initOrders();
    }

    public void initOrders() {
        log.info("calling a method initOrders");
        log.info("method initOrders completed");
    }

    /**
     * вернуть список заказов
     *
     * @return
     */
    @Override
    public List<Order> getOrders() {
        log.info("calling a method getOrders");
        log.info("method getOrders completed");
        return orders;
    }

    /**
     * поиск заказа по его id
     *
     * @param id
     * @return
     */
    @Override
    public Order getOrderById(long id) {
        log.info("calling a method getOrderById");
        Order order = null;
        for (Order order1 : getOrders()) {
            if (order1.getId() == id) {
                order = order1;
                break;
            }
        }
        log.info("method getOrderById completed");
        return order;
    }

    @Override
    public List<Book> getBookByOrder(long id) {
        log.info("calling a method getBookByOrder");
        List<Book> books = new ArrayList<>();
        log.info("method getBookByOrder completed");
        return books;
    }

    @Override
    public void addNewOrder(Order order) {
        log.info("calling a method addNewOrder");
        orders.add(order);
        System.out.println("заказ № " + order.getId() + " создан");
        log.info("method addNewOrder completed");
    }


    @Override
    public String toString() {
        log.info("calling a method ");
        return "OrderRepositoryImpl{" +
                "orders=" + orders +
                '}';
    }
}
