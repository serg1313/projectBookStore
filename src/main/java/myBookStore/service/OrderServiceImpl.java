package myBookStore.service;

import myBookStore.model.Book;
import myBookStore.model.Customer;
import myBookStore.model.Order;
import myBookStore.model.OrderStatus;
import myBookStore.repository.*;
import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class.getName());
    OrderRepository orderRepository;
    BookRepository bookRepository;
    CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, BookRepository bookRepository, CustomerRepository customerRepository) {
        log.info("the constructor is called OrderServiceImpl");
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
    public void createOrder(LocalDate localDate, long idCustomer, long... books) {
        log.info("calling a method createOrder");
        List<Order> orderCopy = orderRepository.getOrders();
        Order newOrder = new Order(localDate, idCustomer, books);
        orderCopy.add(newOrder);
        System.out.println("заказ № " + newOrder.getId() + " создан");
        log.info("method createOrder completed");
    }

    /**
     * переводит заказ в статус закрыт
     *
     * @param orderId id заказа
     */
    @Override
    public void cancelOrder(long orderId) {
        log.info("calling a method cancelOrder with parametr = " + orderId);
        for (Order o : orderRepository.getOrders()) {
            if (o.getId() == orderId && (o.getOrderStatus().equals(OrderStatus.NEW) || o.getOrderStatus().equals(OrderStatus.COMPLETED))) {
                o.setOrderStatus(OrderStatus.CANCELLED);
                System.out.println("статус заказа № " + orderId + " закрыт.");
                break;
            }
        }
        log.info("method cancelOrder completed");
    }

    /**
     * изменить статус заказа на статус который в параметре
     *
     * @param orderId     id заказа
     * @param orderStatus на какой статус изменить заказ
     */
    @Override
    public void changeOrder(long orderId, OrderStatus orderStatus, LocalDate localDate) {
        log.info("calling a method changeOrder");
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
        log.info("method changeOrder completed");
    }

    @Override
    public double getPriceOfSoldBooksByOrder(Order order) {
        log.info("calling a method getPriceOfSoldBooksByOrder");
        double summ = 0;
        for (long books : order.getBookId()) {
            Book book;
            book = bookRepository.getBookById(books);
            if (book != null) {
                summ += book.getPrice();
            }
        }
        log.info("method getPriceOfSoldBooksByOrder completed");
        return summ;
    }

    @Override
    public double getPriceOfSoldBooksByOrderId(long idOrder) {
        log.info("calling a method getPriceOfSoldBooksByOrderId");
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
            log.info("данного заказа нет в наличии");
        }
        log.info("method getPriceOfSoldBooksByOrderId completed");
        return summ;
    }

    @Override
    public List<Order> sortOrderByPrice(List<Order> orders) {
        log.info("calling a method sortOrderByPrice");
        orders.sort((o1, o2) -> (int) (getPriceOfSoldBooksByOrder(o1) - getPriceOfSoldBooksByOrder(o2)));
        for (Order order : orders) {
            System.out.println("Заказ № " + order.getId() + " сумма заказа: " + getPriceOfSoldBooksByOrder(order));
        }
        log.info("method sortOrderByPrice completed");
        return orders;
    }

    @Override
    public void getInfoOrder(long id) {
        log.info("calling a method getInfoOrder");
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
        log.info("method getInfoOrder completed");
    }

    @Override
    public List<Order> getOrderListByPeriod(LocalDate dateStart, LocalDate dateEnd) {
        log.info("calling a method getOrderListByPeriod");
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        result.sort(Comparator.comparing(order -> order.getCompleteDate()));
        log.info("method getOrderListByPeriod completed");
        return result;
    }

    @Override
    public List<Order> getOrderListCompletedByPrice(LocalDate dateStart, LocalDate dateEnd) {
        log.info("calling a method getOrderListCompletedByPrice");
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        result.sort((o1, o2) -> (int) (getPriceOfSoldBooksByOrderId(o1.getId()) - getPriceOfSoldBooksByOrderId(o2.getId())));
        log.info("method getOrderListCompletedByPrice completed");
        return result;
    }

    @Override
    public double getSummEarnedMoneyByPeriod(LocalDate dateStart, LocalDate dateEnd) {
        log.info("calling a method getSummEarnedMoneyByPeriod");
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());

        double count = 0;
        for (Order order : result) {
            count += getPriceOfSoldBooksByOrder(order);
        }
        log.info("method getSummEarnedMoneyByPeriod completed");
        return count;
    }

    @Override
    public List<Book> getBookByOrder(long idOrder) {
        log.info("calling a method getBookByOrder");
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
        log.info("method getBookByOrder completed");
        return null;
    }

    @Override
    public List<Order> getOrderByStatus(OrderStatus orderStatus) {
        log.info("calling a method getOrderByStatus");
        List<Order> ord = new ArrayList<>();
        for (Order order : orderRepository.getOrders())
            if (order.getOrderStatus() == orderStatus) {
                ord.add(order);
            }
        log.info("method getOrderByStatus completed");
        return ord;
    }

    @Override
    public List<Order> getListOrder() {
        log.info("calling a method getListOrder");
        List<Order> list = new ArrayList<>();
        for (Order order : orderRepository.getOrders()) {
            list.add(order);
        }
        log.info("method getListOrder completed");
        return list;
    }

    @Override
    public List<Order> sortOrderByStatus(List<Order> orders) {
        log.info("calling a method sortOrderByStatus");
        orders.sort((o1, o2) -> o1.getOrderStatus().compareTo(o2.getOrderStatus()));
        log.info("method sortOrderByStatus completed");
        return orders;
    }

    @Override
    public List<Order> sortOrderByDate(List<Order> orders) {
        log.info("calling a method sortOrderByDate");
        orders.sort(Comparator.comparing(Order::getCompleteDate));
        log.info("method sortOrderByDate completed");
        return orders;
    }

    @Override
    public int getCountComletedOrdersByPeriod(LocalDate dateStart, LocalDate dateEnd) {
        log.info("calling a method getCountComletedOrdersByPeriod");
        int count = 0;
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        count = result.size();
        log.info("method getCountComletedOrdersByPeriod completed");
        return count;
    }

    @Override
    public void writeFileCsvOrder() {
        log.info("calling a method writeFileCsvOrder");
        String csvFile = "orderRepository.csv";
        Path path = Paths.get(csvFile);
        File file = new File(csvFile);
        char separator = ';';
        List<Order> orderList = orderRepository.getOrders();
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile),
                separator,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {
            for (Order b : orderList) {
                String[] csvNewFile = new String[7];
                csvNewFile[0] = String.valueOf(b.getId());
                csvNewFile[1] = Arrays.toString(b.getBookId());
                csvNewFile[2] = String.valueOf(b.getOrderStatus());
                csvNewFile[3] = String.valueOf(b.getIdCustomer());
                csvNewFile[4] = String.valueOf(b.getOrderDate());
                csvNewFile[5] = String.valueOf(b.getCompleteDate());
                writer.writeNext(csvNewFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("method writeFileCsvOrder completed");
    }

    @Override
    public void readFileCsvOrder() {
        log.info("calling a method readFileCsvOrder");
        OrderRepository orderRepository = OrderRepositoryImpl.getOrderRepository();
        List<Order> orders = orderRepository.getOrders();
        if (orders.size() == 0) {
            String csv = "orderRepository.csv";
            try (BufferedReader reader = new BufferedReader(new FileReader(csv));) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    line.replaceAll("\\s+", "");
                    String[] items = line.split(";");
                    try {
                        Long id = Long.parseLong(items[0]);
                        ;
                        Long[] longs = Arrays.stream(items[1].replace("[", "").replace("]", "").split(","))
                                .map(String::trim).map(Long::valueOf).toArray(Long[]::new);
                        long[] primLong = new long[longs.length];
                        for (int index = 0; index < longs.length; index++) {
                            primLong[index] = longs[index];
                        }
                        OrderStatus orderStatus = OrderStatus.valueOf(items[2]);
                        long idCustomer = Long.parseLong(items[3]);
                        LocalDate orderDate = LocalDate.parse(items[4]);
                        if (items[5].equals("null")) {
                            LocalDate comleteDate = null;
                        } else {
                            LocalDate completeDate = LocalDate.parse(items[5]);
                        }
                        Order order = new Order(orderDate, idCustomer, primLong);
                        orders.add(order);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                        System.out.println("не сработал");
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("method readFileCsvOrder completed");
    }
}








