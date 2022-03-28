package my_Book_Store.myBookStore.ui.builder;

import my_Book_Store.myBookStore.ui.controller.Menu;
import my_Book_Store.myBookStore.ui.controller.MenuItem;

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
