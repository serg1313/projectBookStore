package myBookStore.ui.builder;

import myBookStore.ui.action.ActionEnum;
import myBookStore.ui.controller.Menu;
import myBookStore.ui.controller.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuOrdersAction {
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuOrdersAction(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        List<MenuItem> menuItems = new ArrayList<>();
        currentMenu = new Menu("Действия", menuItems);
        menuItems.add(new MenuItem("1: Создать заказ на книгу", ActionEnum.CREATE_ORDER.getAction(), null));
        menuItems.add(new MenuItem("2: Перевести заказ в статус <ЗАКРЫТ>", ActionEnum.CANCEL_ORDER.getAction(), null));
        menuItems.add((new MenuItem("3: Изменить статус заказа", ActionEnum.CHANGE_ORDER.getAction(), null)));
        menuItems.add(new MenuItem("4: Выход в предыдущее меню", null, rootMenu));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }
}
