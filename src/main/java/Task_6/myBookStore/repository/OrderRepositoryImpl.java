package Task_6.myBookStore.repository;

import Task_6.myBookStore.model.Book;
import Task_6.myBookStore.model.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    public List<Order> orders = new ArrayList<>();

    public OrderRepositoryImpl() {
        initOrders();
    }

    public void initOrders() {
        orders.add(new Order(LocalDate.of(2021, 1, 2), 1, new long[]{2, 3, 5}));
        orders.add(new Order(LocalDate.of(2021, 2, 12), 2, new long[]{5, 7, 8, 9, 10}));
        orders.add(new Order(LocalDate.of(2021, 3, 22), 3, new long[]{5, 11, 13, 14, 15}));
        orders.add(new Order(LocalDate.of(2021, 4, 2), 4, new long[]{8, 4, 3, 1, 7}));
        orders.add(new Order(LocalDate.of(2021, 5, 21), 5, new long[]{10, 11, 12, 18, 17}));
        orders.add(new Order(LocalDate.of(2021, 6, 23), 6, new long[]{15, 17, 19, 20}));
        orders.add(new Order(LocalDate.of(2021, 7, 6), 7, new long[]{20, 1, 2, 5, 9}));
        orders.add(new Order(LocalDate.of(2021, 10, 8), 8, new long[]{6, 9, 11, 14, 13, 15}));
        orders.add(new Order(LocalDate.of(2021, 12, 30), 9, new long[]{9, 8, 7, 6, 5, 4, 3, 2}));
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
    public String toString() {
        return "OrderRepositoryImpl{" +
                "orders=" + orders +
                '}';
    }
}
