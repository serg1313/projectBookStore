package mybookstore.ui.builder;

import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings({"checkstyle:JavadocVariable", "checkstyle:RegexpSingleline"})
public class NestedMenuConsumer {
    private static final Logger LOG = LogManager.getLogger(NestedMenuConsumer.class.getName());
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuConsumer(final Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    @SuppressWarnings("checkstyle:DesignForExtension")
    public void buildMenu() {
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "calling a method buildMenu");
        List<MenuItem> itemList = new ArrayList<>();
        currentMenu = new Menu("Меню покупателей", itemList);
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "method buildMenu completed");
    }

    @SuppressWarnings("checkstyle:DesignForExtension")
    public Menu getRootMenu() {
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }

    /**
     *
     * @return
     */
    public Menu getCurrentMenu() {
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }
}
