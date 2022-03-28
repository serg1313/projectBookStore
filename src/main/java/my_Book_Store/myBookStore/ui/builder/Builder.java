package my_Book_Store.myBookStore.ui.builder;

import my_Book_Store.myBookStore.ui.controller.Menu;

public class Builder {
    private Menu rootMenu;

    public void buildMenu() {
        RootMenuBuilder rootMenuBuilder = new RootMenuBuilder();
        rootMenuBuilder.buildMenu();
        rootMenu = rootMenuBuilder.getCurrentMenu();
    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}
