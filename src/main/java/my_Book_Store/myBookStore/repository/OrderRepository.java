package my_Book_Store.myBookStore.repository;

import my_Book_Store.myBookStore.model.Book;
import my_Book_Store.myBookStore.model.Order;

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
     *
     * @param id
     * @return
     */
    List<Book> getBookByOrder(long id);

    void addNewOrder(Order order);


}
