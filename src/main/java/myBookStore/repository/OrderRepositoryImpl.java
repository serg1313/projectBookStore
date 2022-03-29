package myBookStore.repository;

import myBookStore.model.Book;
import myBookStore.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private static OrderRepository orderRepository;
    public List<Order> orders = new ArrayList<>();

    public static OrderRepository getOrderRepository() {
        if (orderRepository == null) {
            orderRepository = new OrderRepositoryImpl();
        }
        return orderRepository;
    }

    public OrderRepositoryImpl() {
        initOrders();
    }

    public void initOrders() {
    }

    /**
     * вернуть список заказов
     *
     * @return
     */
    @Override
    public List<Order> getOrders() {
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
        Order order = null;
        for (Order order1 : getOrders()) {
            if (order1.getId() == id) {
                order = order1;
                break;
            }
        }
        return order;
    }

    @Override
    public List<Book> getBookByOrder(long id) {
        List<Book> books = new ArrayList<>();
        return books;
    }

    @Override
    public void addNewOrder(Order order) {
        orders.add(order);
        System.out.println("заказ № " + order.getId() + " создан");
    }


    @Override
    public String toString() {
        return "OrderRepositoryImpl{" +
                "orders=" + orders +
                '}';
    }
}
