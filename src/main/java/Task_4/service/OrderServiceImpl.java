package Task_4.service;



import Task_4.model.Book;
import Task_4.model.Customer;
import Task_4.model.Order;
import Task_4.model.OrderStatus;
import Task_4.repository.BookRepository;
import Task_4.repository.CustomerRepository;
import Task_4.repository.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    BookRepository bookRepository;
    CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, BookRepository bookRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * создать заказ на книгу
     *
     * @param idCustomer id  покупателя
     * @param books      список книг
     */
    @Override
    public void createOrder(LocalDate localDate, long idCustomer, long[] books) {
        List<Order> orderCopy = orderRepository.getOrders();
        Order newOrder = new Order(localDate, idCustomer, books);
        orderCopy.add(newOrder);
        System.out.println("заказ № " + newOrder.getId() + " создан");
    }

    /**
     * переводит заказ в статус закрыт
     *
     * @param orderId id заказа
     */
    @Override
    public void cancelOrder(long orderId) {
        for (Order o : orderRepository.getOrders()) {
            if (o.getId() == orderId && (o.getOrderStatus().equals(OrderStatus.NEW) || o.getOrderStatus().equals(OrderStatus.COMPLETED))) {
                o.setOrderStatus(OrderStatus.CANCELLED);
                System.out.println("статус заказа № " + orderId + " закрыт.");
                break;
            }
        }
    }

    /**
     * изменить статус заказа на статус который в параметре
     *
     * @param orderId     id заказа
     * @param orderStatus на какой статус изменить заказ
     */
    @Override
    public void changeOrder(long orderId, OrderStatus orderStatus, LocalDate localDate) {
        for (Order o : orderRepository.getOrders()) {
            if (o.getId() == orderId) {
                o.setOrderStatus(orderStatus);
                if (o.getOrderStatus() == OrderStatus.COMPLETED) {
                    o.setCompleteDate(localDate);
                }
                System.out.println("Статус заказа № " + orderId + " изменен на " + orderStatus);
                break;
            }
        }
    }

    @Override
    public double getPriceOfSoldBooksByOrder(Order order) {
        double summ = 0;
        for (long books : order.getBookId()) {
            Book book;
            book = bookRepository.getBookById(books);
            if (book != null) {
                summ += book.getPrice();
            }
        }
        return summ;
    }

    @Override
    public double getPriceOfSoldBooksByOrderId(long idOrder) {
        double summ = 0;
        try {
            Order order = orderRepository.getOrderById(idOrder);
            for (long books : order.getBookId()) {
                Book book;
                book = bookRepository.getBookById(books);
                if (book != null) {
                    summ += book.getPrice();
                } else {
                    System.out.println("заказа нет");
                    break;
                }
            }
            return summ;
        } catch (NullPointerException e) {
            System.out.println("данного заказа нет в наличии");
        }
        return summ;
    }

    @Override
    public List<Order> sortOrderByPrice(List<Order> orders) {
        orders.sort((o1, o2) -> (int) (getPriceOfSoldBooksByOrder(o1) - getPriceOfSoldBooksByOrder(o2)));
        for (Order order : orders) {
            System.out.println("Заказ № " + order.getId() + " сумма заказа: " + getPriceOfSoldBooksByOrder(order));
        }
        return orders;
    }

    @Override
    public void getInfoOrder(long id) {
        List<Book> list = getBookByOrder(id);
        if (list != null) {
            List<String> info = list.stream()
                    .map(book -> " Название книги - " +
                            book.getNameBook() + ", автор книги - " +
                            book.getAuthorBook() + ", цена книги - " +
                            book.getPrice())
                    .collect(Collectors.toList());
            String nameCustomer = "null";
            int ageCustomer = 0;
            double summ = 0;
            Order order = orderRepository.getOrderById(id);
            Customer customer = customerRepository.getCustomerById(order.getIdCustomer());
            nameCustomer = customer.getName();
            ageCustomer = customer.getAge();
            summ = getPriceOfSoldBooksByOrderId(id);
            System.out.println("Имя покупателя - " + nameCustomer +
                    "\nВозраст покупателя - " + ageCustomer +
                    "\nприобретенные книги -" + info +
                    "\nОбщая сумму покупки - " + summ);
        }
    }

    @Override
    public List<Order> getOrderListByPeriod(LocalDate dateStart, LocalDate dateEnd) {
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        result.sort(Comparator.comparing(order -> order.getCompleteDate()));
        return result;
    }

    @Override
    public List<Order> getOrderListCompletedByPrice(LocalDate dateStart, LocalDate dateEnd) {
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        result.sort((o1, o2) -> (int) (getPriceOfSoldBooksByOrderId(o1.getId()) - getPriceOfSoldBooksByOrderId(o2.getId())));
        return result;
    }

    @Override
    public double getSummEarnedMoneyByPeriod(LocalDate dateStart, LocalDate dateEnd) {
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());

        double count = 0;
        for (Order order : result) {
            count += getPriceOfSoldBooksByOrder(order);
        }

        return count;
    }

    @Override
    public List<Book> getBookByOrder(long idOrder) {
        List<Book> bookList = new ArrayList<>();
        Order order = orderRepository.getOrderById(idOrder);
        if (order != null) {
            for (long book : order.getBookId()) {
                Book b;
                b = bookRepository.getBookById(book);
                bookList.add(b);
            }
            return bookList;
        }
        System.out.println("данного заказа нет");
        return null;
    }

    @Override
    public List<Order> getOrderByStatus(OrderStatus orderStatus) {
        List<Order> ord = new ArrayList<>();
        for (Order order : orderRepository.getOrders())
            if (order.getOrderStatus() == orderStatus) {
                ord.add(order);
            }
        return ord;
    }

    @Override
    public List<Order> sortOrderByStatus(List<Order> orders) {
        orders.sort((o1, o2) -> o1.getOrderStatus().compareTo(o2.getOrderStatus()));
        return orders;
    }

    @Override
    public List<Order> sortOrderByDate(List<Order> orders) {
        orders.sort(Comparator.comparing(Order::getCompleteDate));
        return orders;
    }

    @Override
    public int getCountComletedOrdersByPeriod(LocalDate dateStart, LocalDate dateEnd) {
        int count = 0;
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        count = result.size();
        return count;
    }

}








