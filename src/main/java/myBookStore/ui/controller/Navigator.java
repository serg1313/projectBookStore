package myBookStore.ui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Navigator {
    private static final Logger log = LogManager.getLogger(Navigator.class.getName());
    private Menu currentMenu;

    public Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        printHeader();
        if (currentMenu != null) {
            currentMenu.getMenuItem().forEach(System.out::println);
        }

    }


    public void navigate(int index) {
        if (index < 0) {
            return;
        }
        MenuItem menuItem = currentMenu.getMenuItem().get(index - 1);
        if (menuItem.getAction() != null) {
            menuItem.doAction();
        }
        if (menuItem.getNextMenu() != null) {
            currentMenu = menuItem.getNextMenu();
        }
    }

    public Menu getCurrentMenu() {

        return currentMenu;
    }

    public void printHeader() {
        System.out.println("**************************************************************************");
        System.out.println("\t\t\t" + currentMenu.getName().toUpperCase());
        System.out.println("**************************************************************************");

    }
}
