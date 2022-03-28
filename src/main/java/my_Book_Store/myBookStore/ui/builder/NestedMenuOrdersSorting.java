package my_Book_Store.myBookStore.ui.builder;

import my_Book_Store.myBookStore.ui.action.ActionEnum;
import my_Book_Store.myBookStore.ui.controller.Menu;
import my_Book_Store.myBookStore.ui.controller.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuOrdersSorting {
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuOrdersSorting(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        List<MenuItem> menuItems = new ArrayList<>();
        currentMenu = new Menu("Меню сортировки заказов", menuItems);
        menuItems.add(new MenuItem("1: Сортировка всех заказов по дате исполнения", ActionEnum.SORT_ORDER_BY_DATA.getAction(), null));
        menuItems.add(new MenuItem("2: Сортировка всех заказов по статусу", ActionEnum.SORT_ORDER_BY_STATUS.getAction(), null));
        menuItems.add(new MenuItem("3: Сортировка всех заказов по цене", ActionEnum.SORT_ORDER_BY_PRICE.getAction(), null));
        menuItems.add(new MenuItem("4: Сортировка выполненных заказов за указанный период ", ActionEnum.GET_ORDER_BY_PERIOD.getAction(), null));
        menuItems.add(new MenuItem("5: Сортировка выполненных заказов за указанный период по сумме заказа", ActionEnum.GET_ORDER_BY_SUMM_PERIOD.getAction(), null));
        menuItems.add(new MenuItem("6: Выход в предыдущее меню", null, rootMenu));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }
}
