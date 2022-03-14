package Task_6.myBookStore.ui.builder;

import Task_6.myBookStore.ui.action.ActionEnum;
import Task_6.myBookStore.ui.controller.Menu;
import Task_6.myBookStore.ui.controller.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuOrderInfo {
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuOrderInfo(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        List<MenuItem> menuItems = new ArrayList<>();
        currentMenu = new Menu("ИНформация о заказах", menuItems);
        menuItems.add(new MenuItem("1: Получить список книг в конкретном заказе", ActionEnum.GET_BOOK_BY_ORDER.getAction(), null));
        menuItems.add(new MenuItem("2: Получить информацию из заказа о покупателе, книгах и сумме заказа", ActionEnum.GET_INFO_ORDER.getAction(), null));
        menuItems.add(new MenuItem("3: Получить сумму заработанных средств за период времени", ActionEnum.GET_ORDER_SUMM_BY_PERIOD.getAction(), null));
        menuItems.add(new MenuItem("4: Получить количество выполненных заказов с указанной даты по настоящее время", ActionEnum.GET_COUNT_COMPLETED_ORDER_BY_PERIOD.getAction(), null));
        menuItems.add(new MenuItem("5: Получить общую стоимость указанного заказа", ActionEnum.GET_PRICE_OF_SOLD_BOOK_BY_ORDER.getAction(), null));
        menuItems.add(new MenuItem("6: Получить список заказов по указанному статусу", ActionEnum.GET_LIST_ORDER_BY_STATUS.getAction(), null));
        menuItems.add(new MenuItem("7: ", null, rootMenu));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }
}
