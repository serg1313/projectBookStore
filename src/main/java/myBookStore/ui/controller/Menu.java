package myBookStore.ui.controller;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String name;

    public String getName() {
        return name;
    }

    private List<MenuItem> menuItemList = new ArrayList<>();

    public Menu(String name, List<MenuItem> menuItemList) {
        this.name = name;
        this.menuItemList = menuItemList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public List<MenuItem> getMenuItem() {
        return menuItemList;
    }

    public void addItem(MenuItem menuItem) {
        menuItemList.add(menuItem);
    }
}
