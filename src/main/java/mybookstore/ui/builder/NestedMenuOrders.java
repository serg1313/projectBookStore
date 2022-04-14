package mybookstore.ui.builder;

import mybookstore.ui.action.ActionEnum;
import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuOrders {
    private static final Logger log = LogManager.getLogger(NestedMenuOrders.class.getName());
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuOrders(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        log.info(NestedMenuOrders.class.getSimpleName() + " " + "calling a method buildMenu");
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
        log.info("method buildMenu completed");
    }

    public Menu getCurrentMenu() {
        log.info(NestedMenuOrders.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        log.info(NestedMenuOrders.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }

    public Menu getRootMenu() {
        log.info(NestedMenuOrders.class.getSimpleName() + " " + "calling a method getRootMenu");
        log.info(NestedMenuOrders.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }
}
