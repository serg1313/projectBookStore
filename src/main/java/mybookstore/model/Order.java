package mybookstore.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * класс для создания объекта - заказ.
 */
public class Order extends BaseEntity {
    /**
     * поле для logger.
     */
    private static final Logger LOG = LogManager.getLogger(Order.class.getName());
    /**
     * поле id объекта.
     */
    private static long id = 1;
    /**
     * поле для хранения всех id книг в заказе.
     */
    private long[] bookId;
    /**
     * поле статус заказа. Хранит enum.
     */
    private OrderStatus orderStatus;
    /**
     * поле id покупателя.
     */
    private long idCustomer;
    /**
     * поле дата заказа.
     */
    private LocalDate orderDate;
    /**
     * поле дата исполнения заказа.
     */
    private LocalDate completeDate;

    public Order(final LocalDate orderDate, final long idCustomer, final long[] bookId) {
        super(id++);
        this.orderDate = orderDate;
        this.bookId = bookId;
        orderStatus = OrderStatus.NEW;
        this.idCustomer = idCustomer;
    }

    /**
     * метод возвращает дату исполнения заказа.
     * @return completeDate
     */
    public LocalDate getCompleteDate() {
        return completeDate;
    }
    /**
     * метод для установки даты исполнения заказа.
     * @param completeDate
     */
    public void setCompleteDate(final LocalDate completeDate) {
        this.completeDate = completeDate;
    }
    /**
     * метод для возврата даты заказа.
     * @return orderDate
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }
    /**
     * метод для установки даты заказа.
     * @param orderDate
     */
    public void setOrderDate(final LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public static long getIdOrder() {
        return id;
    }
    /**
     * метод для установки id заказа.
     * @param id
     */
    public void setId(final long id) {
        Order.id = id;
    }
    /**
     * метод для возвращает массив id книг в заказе.
     * @return bookId
     */
    public long[] getBookId() {
        return bookId;
    }
    /**
     * метод заполняет массив id книг в заказ.
     * @param bookId каждой книги в заказе
     */
    public void setBookId(final long[] bookId) {
        this.bookId = bookId;
    }
    /**
     * метод возвращает статус заказа.
     * @return orderStatus
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    /**
     * установить статус заказа.
     * @param orderStatus
     */
    public void setOrderStatus(final OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    /**
     * вернуть id покупателя.
     * @return idCustomer
     */
    public long getIdCustomer() {
        return idCustomer;
    }
    /**
     * установить id покупателя.
     * @param customer
     */
    public void setIdCustomer(final long customer) {
        this.idCustomer = customer;
    }

    /**
     * переопределенный метод toString для класса Order.
     * @return
     */
    @Override
    public String toString() {
        return "Order " + getId()
                + "{ bookId=" + Arrays.toString(bookId)
                + ", orderStatus=" + orderStatus
                + ", idCustomer=" + idCustomer
                + ", orderDate=" + orderDate
                + ", completeDate=" + completeDate
                + '}';
    }
}
