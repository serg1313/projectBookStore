package mybookstore.ui.builder;

import mybookstore.ui.controller.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * класс для постройки меню.
 */
public class Builder {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(Builder.class.getName());
    /**
     * поле корн.
     */
    private Menu rootMenu;

    /**
     * создать меню.
     */
    public void buildMenu() {
        LOG.info(Builder.class.getSimpleName() + " " + "calling a method buildMenu");
        RootMenuBuilder rootMenuBuilder = new RootMenuBuilder();
        rootMenuBuilder.buildMenu();
        rootMenu = rootMenuBuilder.getCurrentMenu();
        LOG.info(Builder.class.getSimpleName() + " " + "method buildMenu completed");
    }

    /**
     * получить корневое меню.
     * @return root menu
     */
    public Menu getRootMenu() {
        LOG.info(Builder.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(Builder.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }
}

