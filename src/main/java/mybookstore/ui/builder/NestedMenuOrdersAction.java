package mybookstore.ui.builder;

import mybookstore.ui.action.ActionEnum;
import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuOrdersAction {
    private static final Logger LOG = LogManager.getLogger(NestedMenuOrdersAction.class.getName());
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuOrdersAction(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        LOG.info(NestedMenuOrdersAction.class.getSimpleName() + " " + "calling a method buildMenu");
        List<MenuItem> menuItems = new ArrayList<>();
        currentMenu = new Menu("Действия", menuItems);
        menuItems.add(new MenuItem("1: Создать заказ на книгу", ActionEnum.CREATE_ORDER.getAction(), null));
        menuItems.add(new MenuItem("2: Перевести заказ в статус <ЗАКРЫТ>", ActionEnum.CANCEL_ORDER.getAction(), null));
        menuItems.add(new MenuItem("3: Изменить статус заказа", ActionEnum.CHANGE_ORDER.getAction(), null));
        menuItems.add(new MenuItem("4: Выход в предыдущее меню", null, rootMenu));
        LOG.info(NestedMenuOrdersAction.class.getSimpleName() + " " + "calling a method buildMenu");
    }

    public Menu getRootMenu() {
        LOG.info(NestedMenuOrdersAction.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(NestedMenuOrdersAction.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }

    public Menu getCurrentMenu() {
        LOG.info(NestedMenuOrdersAction.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        LOG.info(NestedMenuOrdersAction.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }
}
