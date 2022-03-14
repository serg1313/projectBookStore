package Task_6.myBookStore.ui.controller;

import Task_6.myBookStore.model.Order;
import Task_6.myBookStore.model.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static Task_6.myBookStore.Main.orderRepository;
import static Task_6.myBookStore.Main.orderService;


public class OrderMenu {

    static Scanner scanner = new Scanner(System.in);
    static int command;

    public static void orderMenu() {
        do {
            System.out.println("\nДобро пожаловать в меню заказов+" +
                    "\nСделайте свой выбор нажав необходимую цифру меню");

            System.out.println("1: Создать заказ");
            System.out.println("2: Отменить заказ");
            System.out.println("3: Изменить статус заказа");
            System.out.println("4: Получить общую стоимость одного заказа");
            System.out.println("5: Отсортировать заказы по дате исполнения");
            System.out.println("6: Отсортировать заказы по статусу");
            System.out.println("7: Отсортировать заказы по цене");
            System.out.println("8: Получить список выполненных заказов за указанный период времени");
            System.out.println("9: Получить список выполненных заказов за указанный период времени отсортированный по сумме");
            System.out.println("10: Получить сумму заработанных средств за указанный период времени");
            System.out.println("11: Количество выполненных заказов за указанный период времени");
            System.out.println("12: Посмотреть детали заказа");
            System.out.println("0: Выход");
            command = scanner.nextInt();
            switch (command) {
                case 1:
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
                    orderService.createOrder(localDate, id, array);
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Введите номер заказа для отмены");
                    orderService.cancelOrder(scanner.nextLong());
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Введите номер заказа");
                    long id2 = scanner.nextLong();
                    System.out.println("Введите новый статус заказа, выбрав необходимый статус:" +
                            "\n1: NEW" +
                            "\n2: COMPLETED" +
                            "\n3: CANCELLED");
                    command = scanner.nextInt();
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
                    orderService.changeOrder(id2, orderStatus, localDate1);
                    break;
                case 4:
                    System.out.println("Введите номер заказа");

                    long summ = scanner.nextLong();
                    System.out.println(orderService.getPriceOfSoldBooksByOrderId(summ));
                    break;

                case 5:

                    try {
                        List<Order> list = orderRepository.getOrders();
                        orderService.sortOrderByDate(list);
                        for (Order order : list) {
                            System.out.println(order);
                        }
                    } catch (Exception e) {
                        System.out.println("Заказов со статусов исполнены - нет");

                    }
                    break;

                case 6:
                    for (Order order : orderService.sortOrderByStatus(orderRepository.getOrders())) {
                        System.out.println(order);
                    }
                    break;
                case 7:
                    for (Order order : orderService.sortOrderByPrice(orderRepository.getOrders())) {
                        System.out.println(order + " Сумма заказа = " + orderService.getPriceOfSoldBooksByOrderId(order.getId()));
                    }
                    break;
                case 8:
                    System.out.println("Для сортировки введите дату");
                    System.out.println("Введите год в формате числа");
                    int date = scanner.nextInt();
                    System.out.println("Введите месяц в формате числа");
                    int date1 = scanner.nextInt();
                    System.out.println("Введите день в формате числа");
                    int date2 = scanner.nextInt();

                    for (Order order : orderService.getOrderListByPeriod(LocalDate.of(date, date1, date2), LocalDate.now())) {
                        System.out.println(order);
                    }
                    break;
                case 9:
                    System.out.println("Для сортировки введите дату");
                    System.out.println("Введите год в формате числа");
                    int dat = scanner.nextInt();
                    System.out.println("Введите месяц в формате числа");
                    int dat1 = scanner.nextInt();
                    System.out.println("Введите день в формате числа");
                    int dat2 = scanner.nextInt();
                    for (Order order : orderService.getOrderListCompletedByPrice(LocalDate.of(dat, dat1, dat2), LocalDate.now())) {
                        System.out.println(order);
                    }
                    break;
                case 10:
                    System.out.println("Для сортировки введите дату");
                    System.out.println("Введите год в формате числа");
                    int dat3 = scanner.nextInt();
                    System.out.println("Введите месяц в формате числа");
                    int dat4 = scanner.nextInt();
                    System.out.println("Введите день в формате числа");
                    int dat5 = scanner.nextInt();
                    System.out.println(orderService.getSummEarnedMoneyByPeriod(LocalDate.of(dat3, dat4, dat5), LocalDate.of(2021, 12, 30)));
                case 11:
                    System.out.println("Для сортировки введите дату");
                    System.out.println("Введите год в формате числа");
                    int d = scanner.nextInt();
                    System.out.println("Введите месяц в формате числа");
                    int d1 = scanner.nextInt();
                    System.out.println("Введите день в формате числа");
                    int d2 = scanner.nextInt();
                    System.out.println(orderService.getCountComletedOrdersByPeriod(LocalDate.of(d, d1, d2), LocalDate.now()));
                    break;
                case 12:
                    System.out.println("Введите номер заказа");
                    int i = scanner.nextInt();
                    orderService.getInfoOrder(i);
                    break;
            }
        }
        while (command != 0);

    }
}







