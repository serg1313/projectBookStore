package mybookstore.repository;

import mybookstore.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания списка заказов.
 */
public class OrderRepositoryImpl implements OrderRepository {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(OrderRepositoryImpl.class.getName());
    /**
     * статичное поле OrderRepository.
     */
    private static OrderRepository orderRepository;
    /**
     * поле список заказов.
     */
    private List<Order> orders = new ArrayList<>();

    /**
     * метод проверяет поле OrderRepository. Если поле равно null, то создает
     * новый объект типа данных OrderRepository. Если не null то возвращает объект.
     * @return значение поля orderRepository
     */
    public static OrderRepository getOrderRepository() {
        LOG.info(OrderRepositoryImpl.class.getName() + " " + "calling a method getOrderRepository");
        if (orderRepository == null) {
            orderRepository = new OrderRepositoryImpl();
        }
        LOG.info(OrderRepositoryImpl.class.getSimpleName() + " method getOrderRepository completed");
        return orderRepository;
    }

    public OrderRepositoryImpl() {
        LOG.info(OrderRepositoryImpl.class.getSimpleName() + " the constructor is called OrderRepositoryImpl");
        initOrders();
    }

    private void initOrders() {
        LOG.info(OrderRepositoryImpl.class.getSimpleName() + " calling a method initOrders");
        LOG.info(OrderRepositoryImpl.class.getSimpleName() + " method initOrders completed");
    }

    /**
     * вернуть список заказов.
     * @return
     */
    @Override
    public List<Order> getOrders() {
        LOG.info(OrderRepositoryImpl.class.getSimpleName() + " calling a method getOrders");
        LOG.info(OrderRepositoryImpl.class.getSimpleName() + " method getOrders completed");
        return orders;
    }

    /**
     * поиск заказа по его id переданному в параметре.
     * @param id заказа
     * @return
     */
    @Override
    public Order getOrderById(final long id) {
        LOG.info(OrderRepositoryImpl.class.getSimpleName() + " calling a method getOrderById");
        Order order = null;
        for (Order order1 : getOrders()) {
            if (order1.getId() == id) {
                order = order1;
                break;
            }
        }
        LOG.info(OrderRepositoryImpl.class.getSimpleName() + " method getOrderById completed");
        return order;
    }

    /**
     * Добавляет заказ в список заказов.
     * @param order
     */
    @Override
    public void addNewOrder(final Order order) {
        LOG.info(OrderRepositoryImpl.class.getSimpleName() + " calling a method addNewOrder");
        orders.add(order);
        System.out.println("заказ № " + order.getId() + " создан");
        LOG.info(OrderRepositoryImpl.class.getSimpleName() + " method addNewOrder completed");
    }

    @SuppressWarnings("checkstyle:DesignForExtension")
    @Override
    public String toString() {
        LOG.info(OrderRepositoryImpl.class.getSimpleName() + " calling a method toString");
        return "OrderRepositoryImpl{"
                + "orders=" + orders
                + '}';
    }
}
