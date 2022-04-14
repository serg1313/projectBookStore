package mybookstore.repository;

import mybookstore.model.Order;

import java.util.List;

public interface OrderRepository {

    List<Order> getOrders();

    Order getOrderById(long id);

    void addNewOrder(Order order);
}
