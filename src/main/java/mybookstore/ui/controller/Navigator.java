package mybookstore.ui.controller;

import mybookstore.ui.builder.RootMenuBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Navigator {
    private static final Logger LOG = LogManager.getLogger(Navigator.class.getName());
    private Menu currentMenu;

    public Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        LOG.info(Navigator.class.getSimpleName() + " " + "calling a method printMenu");
        printHeader();
        if (currentMenu != null) {
            currentMenu.getMenuItem().forEach(System.out::println);
        }
        LOG.info(Navigator.class.getSimpleName() + " " + "method printMenu completed");
    }

    public void navigate(int index) {
        LOG.info(Navigator.class.getSimpleName() + " " + "calling a method navigate");
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
        LOG.info(Navigator.class.getSimpleName() + " " + "method navigate completed");
    }

    public Menu getCurrentMenu() {
        LOG.info(Navigator.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        LOG.info(Navigator.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }

    public void printHeader() {
        LOG.info(Navigator.class.getSimpleName() + " " + "calling a method printHeader");
        System.out.println("**************************************************************************");
        System.out.println("\t\t\t" + currentMenu.getName().toUpperCase());
        System.out.println("**************************************************************************");
        LOG.info(Navigator.class.getSimpleName() + " " + "method printHeader completed");
    }
}
