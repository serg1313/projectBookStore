package mybookstore.ui.action;

import mybookstore.model.Book;
import mybookstore.model.Order;
import mybookstore.model.OrderStatus;
import mybookstore.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static mybookstore.Main.getBookRepository;
import static mybookstore.Main.getBookService;
import static mybookstore.Main.getOrderRepository;
import static mybookstore.Main.getOrderService;
import static mybookstore.Main.getRequestRepository;
import static mybookstore.Main.getRequestService;

@jdk.nashorn.internal.runtime.logging.Logger
@SuppressWarnings("checkstyle:JavadocVariable")
public enum ActionEnum {

    EXIST_ACTION(() -> {
        getBookService().writeFileCsvBook();
        getOrderService().writeFileCsvOrder();
        getRequestService().writeFileCsvRequest();
        System.exit(0);
    }),
    ORDER_ACTION(() -> System.out.println("Заказ")),
    ORDER_MENU_INFO(() -> System.out.println("Информция по заказам")),
    ORDER_SORT_ACTION(() -> System.out.println("Сортировка")),
    BOOK_SORT_ACTION(() -> System.out.println("Сортировка книг")),
    BOOK_MENU_ACTION(() -> System.out.println("Действия над книгой")),
    REQUEST_ACTION(() -> System.out.println("Запросы")),
    BOOK_ACTION(() -> System.out.println("Книги")),
    IMPORT_EXPORT_DATA(() -> System.out.println("Импорт/экспорт данных")),
    CUSTOMER_ACTION(() -> System.out.println("Покупатели")),
    CREATE_ORDER(() -> {
        System.out.println("Заказ");
        Scanner scanner = new Scanner(System.in);
        LocalDate localDate = LocalDate.now();
        System.out.println("Введите ваш id");
        long id = (long) scanner.nextInt();
        System.out.println("Введите количество книг");
        int size = scanner.nextInt();
        long[] array = new long[size];
        System.out.println("Введите номера книг");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt(); // Заполняем массив элементами, введёнными с клавиатуры
        }
        Order order = new Order(localDate, id, array);
        getOrderRepository().addNewOrder(order);
    }),
    CREATE_REQUEST_BOOK(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для создания запроса введите название книги");
        String book = scanner.nextLine();
        System.out.println("Введите имя и фамилию автора");
        String author = scanner.nextLine();
        getRequestService().createRequestBook(book, author);
    }),
    CREATE_REQUEST_BOOK_ID(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для создания запроса введите id книги");
        long id = scanner.nextLong();
        getRequestService().createRequestBookById(id);
    }),
    CANCELED_REQUEST_BY_BOOK_ID(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id книги");
        long id = scanner.nextLong();
        getRequestService().canceledRequestByBookId(id);
    }),
    GET_LIST_REQUEST(() -> {
        for (Request request : getRequestRepository().getRequest()) {
            System.out.println(request);
        }
    }),
    CANCEL_ORDER(() -> {
        System.out.println("Введите номер заказа для отмены");
        Scanner scanner = new Scanner(System.in);
        getOrderService().cancelOrder(scanner.nextLong());
    }),
    @SuppressWarnings("checkstyle:MagicNumber") CHANGE_ORDER(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер заказа");
        long id2 = scanner.nextLong();
        System.out.println("Введите новый статус заказа, выбрав необходимый статус:"
                + "\n1: NEW"
                + "\n2: COMPLETED"
                + "\n3: CANCELLED");
        int command = scanner.nextInt();
        OrderStatus orderStatus = null;
        switch (command) {
            case 1:
                orderStatus = OrderStatus.NEW;
                break;
            case 2:
                orderStatus = OrderStatus.COMPLETED;
                break;
            case 3:
                orderStatus = OrderStatus.CANCELLED;
                break;
            default:
                System.out.println("Не верный статус");
        }
        LocalDate localDate1 = LocalDate.now();
        getOrderService().changeOrder(id2, orderStatus, localDate1);
    }),
    GET_LIST_ORDERS(() -> {
        for (Order order : getOrderRepository().getOrders()) {
            System.out.println(order);
        }
    }),
    GET_ORDER_SUM(() -> {
        System.out.println("Введите номер заказа");
        Scanner scanner = new Scanner(System.in);
        long summ = scanner.nextLong();
        System.out.println(getOrderService().getPriceOfSoldBooksByOrderId(summ));
    }),
    GET_BOOK_BY_ORDER(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер заказа");
        long result = scanner.nextLong();
        for (Book book : getOrderService().getBookByOrder(result)) {
            System.out.println(book);
        }
    }),
    GET_INFO_ORDER(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер заказа");
        long result = scanner.nextLong();
        getOrderService().getInfoOrder(result);
    }),
    GET_ORDER_SUMM_BY_PERIOD(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для сортировки введите начало периода");
        System.out.println("Введите год в формате числа");
        int date = scanner.nextInt();
        System.out.println("Введите месяц в формате числа");
        int date1 = scanner.nextInt();
        System.out.println("Введите день в формате числа");
        int date2 = scanner.nextInt();
        System.out.println("Введите дату окончания периода");
        System.out.println("Введите год в формате числа");
        int date3 = scanner.nextInt();
        System.out.println("Введите месяц в формате числа");
        int date4 = scanner.nextInt();
        System.out.println("Введите день в формате числа");
        int date5 = scanner.nextInt();
        System.out.println(getOrderService().getSummEarnedMoneyByPeriod(LocalDate.of(date, date1, date2), LocalDate.of(date3, date4, date5)));
    }),
    GET_PRICE_OF_SOLD_BOOK_BY_ORDER(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер заказа");
        long id = scanner.nextLong();
        System.out.println(getOrderService().getPriceOfSoldBooksByOrderId(id));
    }),
    @SuppressWarnings("checkstyle:MagicNumber") GET_LIST_ORDER_BY_STATUS(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите по какому статусу желаете получить список заказов"
                + "\n1: NEW"
                + "\n2: COMPLETED"
                + "\n3: CANCELLED");
        int result = scanner.nextInt();
        if (result == 1) {
            for (Order order : getOrderService().getOrderByStatus(OrderStatus.NEW)) {
                System.out.println(order);
            }
        } else if (result == 2) {
            for (Order order : getOrderService().getOrderByStatus(OrderStatus.COMPLETED)) {
                System.out.println(order);
            }
        } else if (result == 3) {
            for (Order order : getOrderService().getOrderByStatus(OrderStatus.CANCELLED)) {
                System.out.println(order);
            }
        } else {
            System.out.println("Не верный статус");
        }
    }),
    SORT_ORDER_BY_DATA(() -> {
        List<Order> list = getOrderRepository().getOrders();
        getOrderService().sortOrderByDate(list);
        for (Order order : list) {
            System.out.println(order);
        }
    }),
    SORT_ORDER_BY_STATUS(() -> {
        for (Order order : getOrderService().sortOrderByStatus(getOrderRepository().getOrders())) {
            System.out.println(order);
        }
    }),
    SORT_ORDER_BY_PRICE(() -> {
        for (Order order : getOrderService().sortOrderByPrice(getOrderRepository().getOrders())) {
            System.out.println(order + " Сумма заказа = " + getOrderService().getPriceOfSoldBooksByOrderId(order.getId()));
        }
    }),
    GET_ORDER_BY_PERIOD(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для сортировки введите дату");
        System.out.println("Введите год в формате числа");
        int date = scanner.nextInt();
        System.out.println("Введите месяц в формате числа");
        int date1 = scanner.nextInt();
        System.out.println("Введите день в формате числа");
        int date2 = scanner.nextInt();
        System.out.println(getOrderService().getOrderListByPeriod(LocalDate.of(date, date1, date2), LocalDate.now()));
    }),
    GET_ORDER_BY_SUMM_PERIOD(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для сортировки введите дату");
        System.out.println("Введите год в формате числа");
        int dat = scanner.nextInt();
        System.out.println("Введите месяц в формате числа");
        int dat1 = scanner.nextInt();
        System.out.println("Введите день в формате числа");
        int dat2 = scanner.nextInt();
        for (Order order : getOrderService().getOrderListCompletedByPrice(LocalDate.of(dat, dat1, dat2), LocalDate.now())) {
            System.out.println(order);
        }
    }),
    GET_COUNT_COMPLETED_ORDER_BY_PERIOD(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для сортировки введите дату");
        System.out.println("Введите год в формате числа");
        int d = scanner.nextInt();
        System.out.println("Введите месяц в формате числа");
        int d1 = scanner.nextInt();
        System.out.println("Введите день в формате числа");
        int d2 = scanner.nextInt();
        System.out.println(getOrderService().getCountComletedOrdersByPeriod(LocalDate.of(d, d1, d2), LocalDate.now()));
    }),
    GET_LIST_BOOKS(() -> {
        for (Book book : getBookRepository().getBooks()) {
            System.out.println(book);
        }
    }),
    SORT_BOOK_BY_DATE_PUBLIC(() -> {
        getBookService().sortingBookByYearOfPublic(getBookRepository().getBooks());
    }),
    SORT_BOOK_BY_PRICE(() -> {
        getBookService().sortingBookByPrice(getBookRepository().getBooks());
    }),
    SORT_BOOK_BY_STOCK_AVAILABILITY(() -> {
        Scanner scanner = new Scanner(System.in);
        boolean a = true;
        boolean b = false;
        System.out.println("Укажите по по какому критерию сортировать"
                + "\n1 - по наличию книг"
                + "\n2 - по отсутствию книг");
        int result = scanner.nextInt();
        if (result == 1) {
            getBookService().sortBooksByStockAvailability(true);
        } else if (result == 2) {
            getBookService().sortBooksByStockAvailability(false);
        } else {
            System.out.println("Введено не верное значение");
        }
    }),
    @SuppressWarnings("checkstyle:MagicNumber") SORT_BOOKS_NOT_SOLD(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Список книг которые не проданы более 6 месяцев"
                + "\nУкажите по какому критерию сортировать книги"
                + "\n1 - по дате поступления"
                + "\n2 - по цене"
                + "\n3 - по названию книги");
        int result = scanner.nextInt();
        if (result == 1) {
            for (Book book : getBookService().sortByDateDeliveryOfStaleBooksNotSold(getBookRepository().getBooks())) {
                System.out.println(book);
            }
        } else if (result == 2) {
            for (Book book : getBookService().sortByPriceListOfStaleBooksNotSold(getBookRepository().getBooks())) {
                System.out.println(book);
            }
        } else if (result == 3) {
            for (Book book : getBookService().sortByNameListOfStaleBooksNotSold(getBookRepository().getBooks())) {
                System.out.println(book);
            }
        } else {
            System.out.println("Введено не верное занчение");
        }
    }),
    SORT_BOOK_BY_NAME(() -> {
        getBookService().sortingBookByName(getBookRepository().getBooks());
    }),
    GET_DISCRIPTION_BOOK(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для просмотра информации введите id книги");
        int result = scanner.nextInt();
        getBookService().getDescriptionBook(result);
    }),
    SORT_REQUEST_BY_COUNT(() -> {
        getRequestService().sortRequestByCount();
    }),
    SORT_REQUEST_BY_NAME_BOOK(() -> {
        getRequestService().sortRequestBookByName();
    }),
    GET_REQUEST_BY_BOOK_ID(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для сортировки введите id книги");
        long id = scanner.nextLong();
        int count = getRequestService().getCountRequestBookById(id);
        System.out.println(count);
    }),
    SORT_BOOK_BY_ID(() -> {
        getBookService().sortingBookById(getBookRepository().getBooks());
    }),
    SORT_BOOK_BY_DATE_DELIVERY(() -> {
        getBookService().sortBookByDateDelivery(getBookRepository().getBooks());
    }),
    WRITE_OFF_BOOK(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id книги в виде числа");
        long result = scanner.nextLong();
        getBookService().writeOffBook(result);
    }),
    ADD_BOOK(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название книги");
        String name = scanner.nextLine();
        System.out.println("Введите автора книги");
        String author = scanner.nextLine();
        System.out.println("Введите год публикации книги");
        int yearOfPublic = scanner.nextInt();
        System.out.println("Введите стоимость книги");
        double price = scanner.nextDouble();
        boolean statusBook = true;
        LocalDate dateDelivery = LocalDate.now();
        Book book = new Book(name, author, yearOfPublic, price, statusBook, dateDelivery);
        getBookRepository().addNewBook(book);
    }),
    GET_STATUS_BOOK(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id книги в виде числа");
        long result = scanner.nextLong();
        getBookService().getStatusBook(result);
    }),
    GET_BOOK_BY_ID(() -> {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id книги в виде числа");
        long result = scanner.nextLong();
        System.out.println(getBookService().getBookById(result));
    }),
    READ_FILE_CSV_BOOK(() -> {
        getBookService().readCsvBook();
    }),
    READ_FILE_CSV_ORDER(() -> {
        getOrderService().readFileCsvOrder();
    }),
    READ_FILE_CSV_REQUEST(() -> getRequestService().readFileCsvRequest()),
    GET_COUNT_BOOK_BY_REPOSITIRY(() -> {
        System.out.println(getBookService().getCountBookByRepository());
    });

    private final IAction action;
    private static final Logger LOG = LogManager.getLogger(OrderStatus.class.getName());

    ActionEnum(final IAction action) {
        this.action = action;
    }

    public IAction getAction() {
        return action;
    }
}
