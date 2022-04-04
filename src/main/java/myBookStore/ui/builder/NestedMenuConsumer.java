package myBookStore.ui.builder;

import myBookStore.ui.controller.Menu;
import myBookStore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuConsumer {
    private static final Logger log = LogManager.getLogger(NestedMenuConsumer.class.getName());
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuConsumer(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        log.info("calling a method buildMenu");
        List<MenuItem> itemList = new ArrayList<>();
        currentMenu = new Menu("Меню покупателей", itemList);
        log.info("method buildMenu completed");
    }

    public Menu getRootMenu() {
        log.info("calling a method getRootMenu");
        log.info("method getRootMenu completed");
        return rootMenu;
    }

    public Menu getCurrentMenu() {
        log.info("calling a method getCurrentMenu");
        log.info("method getCurrentMenu completed");
        return currentMenu;
    }
}
