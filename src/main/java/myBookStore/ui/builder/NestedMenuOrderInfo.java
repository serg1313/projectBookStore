package myBookStore.ui.builder;

import myBookStore.ui.action.ActionEnum;
import myBookStore.ui.controller.Menu;
import myBookStore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuOrderInfo {
    private static final Logger log = LogManager.getLogger(NestedMenuOrderInfo.class.getName());
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuOrderInfo(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        log.info("calling a method buildMenu");
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
        log.info("method buildMenu completed");
    }

    public Menu getRootMenu() {
        log.info("calling a method getRootMenu");
        log.info("method getRootMenu completed");
        return rootMenu;
    }

    public Menu getCurrentMenu() {
        log.info("calling a method getCurrentMenu");
        log.info("method getCurrentMenu completed");
        return currentMenu;
    }
}
