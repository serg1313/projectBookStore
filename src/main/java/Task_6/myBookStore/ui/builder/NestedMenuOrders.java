package Task_6.myBookStore.ui.builder;

import Task_6.myBookStore.ui.action.ActionEnum;
import Task_6.myBookStore.ui.controller.Menu;
import Task_6.myBookStore.ui.controller.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuOrders {
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuOrders(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        List<MenuItem> menuList = new ArrayList<>();
        currentMenu = new Menu("Меню заказов", menuList);
        NestedMenuOrdersAction nestedMenuOrdersAction = new NestedMenuOrdersAction(currentMenu);
        nestedMenuOrdersAction.buildMenu();
        menuList.add(new MenuItem("1: Меню действий над заказом", ActionEnum.ORDER_ACTION.getAction(), nestedMenuOrdersAction.getCurrentMenu()));
        NestedMenuOrdersSorting nestedMenuOrdersSorting = new NestedMenuOrdersSorting(currentMenu);
        nestedMenuOrdersSorting.buildMenu();
        menuList.add(new MenuItem("2: Меню сортировки заказов", ActionEnum.ORDER_SORT_ACTION.getAction(), nestedMenuOrdersSorting.getCurrentMenu()));
        NestedMenuOrderInfo nestedMenuOrderInfo = new NestedMenuOrderInfo(currentMenu);
        nestedMenuOrderInfo.buildMenu();
        menuList.add(new MenuItem("3: Меню информации о заказах", ActionEnum.ORDER_MENU_INFO.getAction(), nestedMenuOrderInfo.getCurrentMenu()));
        menuList.add(new MenuItem("4: Выход в главное меню", null, rootMenu));
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}
