package mybookstore.ui.builder;

import mybookstore.ui.action.ActionEnum;
import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для получения информации о заказах при определенном выборе в меню.
 */
public class NestedMenuOrderInfo {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(NestedMenuOrderInfo.class.getName());
    /**
     * поле главного меню.
     */
    private Menu rootMenu;
    /**
     * поле вложенного меню.
     */
    private Menu currentMenu;

    public NestedMenuOrderInfo(final Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    /**
     * Строит меню информации о заказах. Позволяет перейти в предыдущее меню или во вложенное при
     * нажатии определенной цифры.
     */
    public void buildMenu() {
        LOG.info(NestedMenuOrderInfo.class.getSimpleName() + " " + "calling a method buildMenu");
        List<MenuItem> menuItems = new ArrayList<>();
        currentMenu = new Menu("Информация о заказах", menuItems);
        menuItems.add(new MenuItem("1: Получить список всех заказов", ActionEnum.GET_LIST_ORDERS.getAction(), null));
        menuItems.add(new MenuItem("2: Получить список книг в конкретном заказе", ActionEnum.GET_BOOK_BY_ORDER.getAction(), null));
        menuItems.add(new MenuItem("3: Получить информацию из заказа о покупателе, книгах и сумме заказа", ActionEnum.GET_INFO_ORDER.getAction(), null));
        menuItems.add(new MenuItem("4: Получить сумму заработанных средств за период времени", ActionEnum.GET_ORDER_SUMM_BY_PERIOD.getAction(), null));
        menuItems.add(new MenuItem("5: Получить количество выполненных заказов с указанной даты по настоящее время", ActionEnum.GET_COUNT_COMPLETED_ORDER_BY_PERIOD.getAction(), null));
        menuItems.add(new MenuItem("6: Получить общую стоимость указанного заказа", ActionEnum.GET_PRICE_OF_SOLD_BOOK_BY_ORDER.getAction(), null));
        menuItems.add(new MenuItem("7: Получить список заказов по указанному статусу", ActionEnum.GET_LIST_ORDER_BY_STATUS.getAction(), null));
        menuItems.add(new MenuItem("8: Выход в предыдущее меню", null, rootMenu));
        LOG.info(NestedMenuOrderInfo.class.getSimpleName() + " " + "method buildMenu completed");
    }

    /**
     * Позволяет перейти в меню на уровень выше.
     * @return меню уровнем выше
     */
    public Menu getRootMenu() {
        LOG.info(NestedMenuOrderInfo.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(NestedMenuOrderInfo.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }

    /**
     * Позволяет перейти во вложенное меню.
     * @return вложенное меню
     */
    public Menu getCurrentMenu() {
        LOG.info(NestedMenuOrderInfo.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        LOG.info(NestedMenuOrderInfo.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }
}
