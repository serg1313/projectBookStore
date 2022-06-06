package mybookstore.ui.builder;

import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для построения меню покупателей и проведения различных действий над объектом покупателей.
 */
public class NestedMenuConsumer {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(NestedMenuConsumer.class.getName());
    /**
     * поле главного меню.
     */
    private Menu rootMenu;
    /**
     * поле вложенного меню.
     */
    private Menu currentMenu;

    public NestedMenuConsumer(final Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    /**
     * Строит меню покупателей. Позволяет перейти в предыдущее меню или во вложенное при
     * нажатии определенной цифры.
     */
    public void buildMenu() {
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "calling a method buildMenu");
        List<MenuItem> itemList = new ArrayList<>();
        currentMenu = new Menu("Меню покупателей", itemList);
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "method buildMenu completed");
    }

    /**
     * Метод возвращает в меню на уровень выше.
     * @return меню на уровень выше
     */
    public Menu getRootMenu() {
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }

    /**
     *Возвращает вложенное меню.
     * @return вложенное меню
     */
    public Menu getCurrentMenu() {
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        LOG.info(NestedMenuConsumer.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }
}
