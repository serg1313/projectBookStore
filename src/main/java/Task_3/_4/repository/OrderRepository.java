package Task_3._4.repository;


import Task_3._4.model.Book;
import Task_3._4.model.Order;


import java.util.List;

public interface OrderRepository {
    /**
     * вернуть список заказов
     *
     * @return
     */
    List<Order> getOrders();

    /**
     * вернуть заказ по его id
     *
     * @param id
     * @return
     */
    Order getOrderById(long id);

    List<Book> getBookByOrder(long id);


}
