package mybookstore.ui.builder;

import mybookstore.ui.action.ActionEnum;
import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuOrdersSorting {
    private static final Logger LOG = LogManager.getLogger(NestedMenuOrdersSorting.class.getName());
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuOrdersSorting(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        LOG.info(NestedMenuOrdersSorting.class.getSimpleName() + " " + "calling a method buildMenu");
        List<MenuItem> menuItems = new ArrayList<>();
        currentMenu = new Menu("Меню сортировки заказов", menuItems);
        menuItems.add(new MenuItem("1: Сортировка всех заказов по дате исполнения", ActionEnum.SORT_ORDER_BY_DATA.getAction(), null));
        menuItems.add(new MenuItem("2: Сортировка всех заказов по статусу", ActionEnum.SORT_ORDER_BY_STATUS.getAction(), null));
        menuItems.add(new MenuItem("3: Сортировка всех заказов по цене", ActionEnum.SORT_ORDER_BY_PRICE.getAction(), null));
        menuItems.add(new MenuItem("4: Сортировка выполненных заказов за указанный период ", ActionEnum.GET_ORDER_BY_PERIOD.getAction(), null));
        menuItems.add(new MenuItem("5: Сортировка выполненных заказов за указанный период по сумме заказа", ActionEnum.GET_ORDER_BY_SUMM_PERIOD.getAction(), null));
        menuItems.add(new MenuItem("6: Выход в предыдущее меню", null, rootMenu));
        LOG.info(NestedMenuOrdersSorting.class.getSimpleName() + " " + "method buildMenu completed");
    }

    public Menu getRootMenu() {
        LOG.info(NestedMenuOrdersSorting.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(NestedMenuOrdersSorting.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }

    public Menu getCurrentMenu() {
        LOG.info(NestedMenuOrdersSorting.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        LOG.info(NestedMenuOrdersSorting.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }
}
