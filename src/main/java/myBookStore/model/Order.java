package myBookStore.model;

import java.time.LocalDate;
import java.util.Arrays;

public class Order extends BaseEntity {

    public static long id = 1;
    private long[] bookId;
    private OrderStatus orderStatus;
    private long idCustomer;
    private LocalDate orderDate;
    private LocalDate completeDate;

    public Order(LocalDate orderDate, long idCustomer, long[] bookId) {
        super(id++);
        this.orderDate = orderDate;
        this.bookId = bookId;
        orderStatus = OrderStatus.NEW;
        this.idCustomer = idCustomer;
    }

    public LocalDate getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(LocalDate completeDate) {
        this.completeDate = completeDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public static long getIdOrder() {
        return id;
    }

    public void setId(long id) {
        Order.id = id;
    }

    public long[] getBookId() {
        return bookId;
    }

    public void setBookId(long[] bookId) {
        this.bookId = bookId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long customer) {
        this.idCustomer = customer;
    }

    @Override
    public String toString() {
        return "Order " + getId() +
                "{ bookId=" + Arrays.toString(bookId) +
                ", orderStatus=" + orderStatus +
                ", idCustomer=" + idCustomer +
                ", orderDate=" + orderDate +
                ", completeDate=" + completeDate +
                '}';
    }
}