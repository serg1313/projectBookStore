package Task_4.repository;



import Task_4.model.Book;
import Task_4.model.Order;

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

    /**
     * Вернуть список книг в заказе по id заказа
     * @param id
     * @return
     */
    List<Book> getBookByOrder(long id);


}
