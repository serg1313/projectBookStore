package myBookStore.ui.builder;

import myBookStore.ui.controller.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Builder {
    private static final Logger log = LogManager.getLogger(Builder.class.getName());
    private Menu rootMenu;

    public void buildMenu() {
        log.info("calling a method buildMenu");
        RootMenuBuilder rootMenuBuilder = new RootMenuBuilder();
        rootMenuBuilder.buildMenu();
        rootMenu = rootMenuBuilder.getCurrentMenu();
        log.info("method buildMenu completed");
    }

    public Menu getRootMenu() {
        log.info("calling a method getRootMenu");
        log.info("method getRootMenu completed");
        return rootMenu;
    }
}
