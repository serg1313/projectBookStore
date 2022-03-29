package myBookStore.ui.builder;

import myBookStore.ui.controller.Menu;

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
