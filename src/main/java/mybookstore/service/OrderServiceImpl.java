package mybookstore.service;

import com.opencsv.CSVWriter;
import mybookstore.model.Book;
import mybookstore.model.Customer;
import mybookstore.model.Order;
import mybookstore.model.OrderStatus;
import mybookstore.repository.BookRepository;
import mybookstore.repository.CustomerRepository;
import mybookstore.repository.OrderRepository;
import mybookstore.repository.OrderRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для проведения операций над объектами класса OrderRepository.
 */
public class OrderServiceImpl implements OrderService {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(OrderServiceImpl.class.getName());
    /**
     * поле класса OrderRepository для операций над объектами данного класса.
     */
    private OrderRepository orderRepository;
    /**
     * поле класса BookRepository для доступа к объектам данного класса с целью проведения операций над ними.
     */
    private BookRepository bookRepository;
    /**
     * поле класса BookRepository для доступа к объектам данного класса с целью проведения операций над ними.
     */
    private CustomerRepository customerRepository;

    public OrderServiceImpl(final OrderRepository orderRepository, final BookRepository bookRepository, final CustomerRepository customerRepository) {
        LOG.info("the constructor is called OrderServiceImpl");
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Принимает дату, id покупателя, массив id книг. Создает новый объект Order.
     * Добавляет новый заказ в список заказов.
     * @param localDate
     * @param idCustomer
     * @param books
     */
    @Override
    public void createOrder(final LocalDate localDate, final long idCustomer, final long... books) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method createOrder");
        List<Order> orderCopy = orderRepository.getOrders();
        Order newOrder = new Order(localDate, idCustomer, books);
        orderCopy.add(newOrder);
        System.out.println("заказ № " + newOrder.getId() + " создан");
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method createOrder completed");
    }

    /**
     * Принимает id заказа. Получает все заказы из списка заказов и сверяет полученный id заказа с
     * каждым полученным id из списка. Если указанный заказ имеет статус NEW или COMPLETED,
     * то переводит заказ в статус CANCELED.
     * @param orderId id заказа
     */
    @Override
    public void cancelOrder(final long orderId) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method cancelOrder with parametr = " + orderId);
        for (Order o : orderRepository.getOrders()) {
            if (o.getId() == orderId && (o.getOrderStatus().equals(OrderStatus.NEW) || o.getOrderStatus().equals(OrderStatus.COMPLETED))) {
                o.setOrderStatus(OrderStatus.CANCELLED);
                System.out.println("статус заказа № " + orderId + " закрыт.");
                break;
            }
        }
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method cancelOrder completed");
    }

    /**
     * Принимает id, статус заказа и дату.
     * Сравнивает полученный id заказа с id из списка заказов
     * если заказ с указанным id имеется, то меняет статус заказа на статус указанный в параметре метода.
     * Если в параметре указан статус COMPLETE, то в данном заказе указывается дата изменения заказа.
     * @param orderId
     * @param orderStatus
     * @param localDate
     */
    @Override
    public void changeOrder(final long orderId, final OrderStatus orderStatus, final LocalDate localDate) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method changeOrder");
        for (Order o : orderRepository.getOrders()) {
            if (o.getId() == orderId) {
                o.setOrderStatus(orderStatus);
                if (o.getOrderStatus().equals(OrderStatus.COMPLETED)) {
                    o.setCompleteDate(localDate);
                }
                System.out.println("Статус заказа № " + orderId + " изменен на " + orderStatus);
                break;
            }
        }
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method changeOrder completed");
    }

    /**
     * Принимает заказ. Получает список заказов.
     * Считает сумму стоимости всех книг из заказа с указанным id.
     * @param order
     * @return
     */
    @Override
    public double getPriceOfSoldBooksByOrder(final Order order) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method getPriceOfSoldBooksByOrder");
        double summ = 0;
        for (long books : order.getBookId()) {
            Book book;
            book = bookRepository.getBookById(books);
            if (book != null) {
                summ += book.getPrice();
            }
        }
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method getPriceOfSoldBooksByOrder completed");
        return summ;
    }

    /**
     * Принимает id заказа. Получает заказ из списка заказов с полученным id заказа.
     * Если заказ имеется, то получает список книг из указанного заказа, считает сумму стоимости каждой
     * книги в данном заказе.
     * Если заказа с указанным id нет, выводит в консоль, что указанного заказа нет.
     * @param idOrder
     * @return
     */
    @Override
    public double getPriceOfSoldBooksByOrderId(final long idOrder) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method getPriceOfSoldBooksByOrderId");
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
            LOG.info(OrderServiceImpl.class.getSimpleName() + " данного заказа нет в наличии");
        }
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method getPriceOfSoldBooksByOrderId completed");
        return summ;
    }

    /**
     * Принимает список заказов. Производит сортировку списка заказов по сумме заказа.
     * @param orders
     * @return
     */
    @Override
    public List<Order> sortOrderByPrice(final List<Order> orders) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method sortOrderByPrice");
        orders.sort((o1, o2) -> (int) (getPriceOfSoldBooksByOrder(o1) - getPriceOfSoldBooksByOrder(o2)));
        for (Order order : orders) {
            System.out.println("Заказ № " + order.getId() + " сумма заказа: "
                    + getPriceOfSoldBooksByOrder(order));
        }
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method sortOrderByPrice completed");
        return orders;
    }

    /**
     * Получает id заказа. Если заказ с указанным id имеется в спике заказов - возвращает информацию
     * о книгах в заказе и информацию о покупателе.
     * @param id
     */
    @Override
    public void getInfoOrder(final long id) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method getInfoOrder");
        List<Book> list = getBookByOrder(id);
        if (list != null) {
            List<String> info = list.stream()
                    .map(book -> " Название книги - "
                            + book.getNameBook() + ", автор книги - "
                            + book.getAuthorBook() + ", цена книги - "
                            + book.getPrice())
                    .collect(Collectors.toList());
            String nameCustomer = "null";
            int ageCustomer = 0;
            double summ = 0;
            Order order = orderRepository.getOrderById(id);
            Customer customer = customerRepository.getCustomerById(order.getIdCustomer());
            nameCustomer = customer.getName();
            ageCustomer = customer.getAge();
            summ = getPriceOfSoldBooksByOrderId(id);
            System.out.println("Имя покупателя - " + nameCustomer
                    + "\nВозраст покупателя - " + ageCustomer
                    + "\nприобретенные книги -" + info
                    + "\nОбщая сумму покупки - " + summ);
        }
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method getInfoOrder completed");
    }

    /**
     * Принимает дату начала периода и дату окончания периода поиска заказов.
     * Проводит поиск заказов со статусом COMLETED в указанных границах дат поискаим возвращает список заказов
     * отсортированных по дате исполнения заказа.
     * @param dateStart
     * @param dateEnd
     * @return
     */
    @Override
    public List<Order> getOrderListByPeriod(final LocalDate dateStart, final LocalDate dateEnd) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method getOrderListByPeriod");
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        result.sort(Comparator.comparing(order -> order.getCompleteDate()));
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method getOrderListByPeriod completed");
        return result;
    }

    /**
     * Принимает дату начала периода и дату окончания периода поиска заказов.
     * Проводит поиск заказов со статусом COMLETED в указанных границах дат поискаим возвращает список заказов
     * отсортированных по сумме заказа.
     * @param dateStart
     * @param dateEnd
     * @return
     */
    @Override
    public List<Order> getOrderListCompletedByPrice(final LocalDate dateStart, final LocalDate dateEnd) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method getOrderListCompletedByPrice");
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        result.sort((o1, o2) -> (int) (getPriceOfSoldBooksByOrderId(o1.getId()) - getPriceOfSoldBooksByOrderId(o2.getId())));
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method getOrderListCompletedByPrice completed");
        return result;
    }

    /**
     * Принимает дату начала периода и дату окончания периода поиска заказов.
     * Получает список всех заказов за указанный период со статусом завершен.
     * Производит расчет общей суммы всех заказов за указанный период.
     * @param dateStart
     * @param dateEnd
     * @return
     */
    @Override
    public double getSummEarnedMoneyByPeriod(final LocalDate dateStart, final LocalDate dateEnd) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method getSummEarnedMoneyByPeriod");
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());

        double count = 0;
        for (Order order : result) {
            count += getPriceOfSoldBooksByOrder(order);
        }
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method getSummEarnedMoneyByPeriod completed");
        return count;
    }

    /**
     * Принимает id заказа. Получает заказ из списка заказов с указанным id.
     * Если заказ с указанным id имеется, возвращает список книг из данного заказа.
     * @param idOrder
     * @return
     */
    @Override
    public List<Book> getBookByOrder(final long idOrder) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method getBookByOrder");
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
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method getBookByOrder completed");
        return null;
    }

    /**
     * Принимает статус заказа.
     * Возвращает все заказы из списка с указанным статусом.
     * @param orderStatus
     * @return
     */
    @Override
    public List<Order> getOrderByStatus(final OrderStatus orderStatus) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method getOrderByStatus");
        List<Order> ord = new ArrayList<>();
        for (Order order : orderRepository.getOrders()) {
            if (order.getOrderStatus() == orderStatus) {
                ord.add(order);
            }
        }
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method getOrderByStatus completed");
        return ord;
    }

    /**
     * Возвращает весь список заказов.
     * @return
     */
    @Override
    public List<Order> getListOrder() {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method getListOrder");
        List<Order> list = new ArrayList<>();
        for (Order order : orderRepository.getOrders()) {
            list.add(order);
        }
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method getListOrder completed");
        return list;
    }

    /**
     * Принимает список заказов и сортирует их по статусу в алфавитном порядке.
     * @param orders
     * @return
     */
    @Override
    public List<Order> sortOrderByStatus(final List<Order> orders) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method sortOrderByStatus");
        orders.sort((o1, o2) -> o1.getOrderStatus().compareTo(o2.getOrderStatus()));
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method sortOrderByStatus completed");
        return orders;
    }

    /**
     * Принимает список заказов и сортирует их дате исполнения от старых к новым.
     * @param orders
     * @return
     */
    @Override
    public List<Order> sortOrderByDate(final List<Order> orders) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method sortOrderByDate");
        orders.sort(Comparator.comparing(Order::getCompleteDate));
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method sortOrderByDate completed");
        return orders;
    }

    /**
     * Принимает дату начала периода и дату окончания периода поиска заказов.
     * Возвращает количестов исполненных заказов за указанный период.
     * @param dateStart
     * @param dateEnd
     * @return
     */
    @Override
    public int getCountComletedOrdersByPeriod(final LocalDate dateStart, final LocalDate dateEnd) {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method getCountComletedOrdersByPeriod");
        int count = 0;
        List<Order> result = orderRepository.getOrders().stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.COMPLETED))
                .filter(order -> order.getCompleteDate().isAfter(dateStart))
                .filter(order -> order.getCompleteDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        count = result.size();
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method getCountComletedOrdersByPeriod completed");
        return count;
    }

    /**
     * создает csv файл и заполняет его заказами из списка заказов.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public void writeFileCsvOrder() {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method writeFileCsvOrder");
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
            LOG.error(OrderServiceImpl.class.getSimpleName() + " " + "Error messege ", e);
        }
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method writeFileCsvOrder completed");
    }

    /**
     * Проверяет на заполненность список заказов. Если список книг пустой, то считывает CSV файл и вносит
     * считанную информацию о заказах в список.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public void readFileCsvOrder() {
        LOG.info(OrderServiceImpl.class.getSimpleName() + " calling a method readFileCsvOrder");
        OrderRepository orderRepository1 = OrderRepositoryImpl.getOrderRepository();
        List<Order> orders = orderRepository1.getOrders();
        if (orders.size() == 0) {
            String csv = "orderRepository.csv";
            try (BufferedReader reader = new BufferedReader(new FileReader(csv));) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    line.replaceAll("\\s+", "");
                    String[] items = line.split(";");
                    try {
                        Long id = Long.parseLong(items[0]);
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
                        LOG.error(OrderServiceImpl.class.getSimpleName() + " " + "Error messege ", e);
                    }
                }
            } catch (FileNotFoundException e) {
                LOG.error(OrderServiceImpl.class.getSimpleName() + " " + "Error messege ", e);
            } catch (IOException e) {
                LOG.error(OrderServiceImpl.class.getSimpleName() + " " + "Error messege ", e);
            }
        }
        LOG.info(OrderServiceImpl.class.getSimpleName() + " method readFileCsvOrder completed");
    }
}
