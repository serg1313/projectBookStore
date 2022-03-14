package Task_6.myBookStore.ui.builder;

import Task_6.myBookStore.ui.controller.Menu;
import Task_6.myBookStore.ui.controller.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuConsumer {
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuConsumer(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        List<MenuItem> itemList = new ArrayList<>();
        currentMenu = new Menu("Меню покупателей", itemList);
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }
}
