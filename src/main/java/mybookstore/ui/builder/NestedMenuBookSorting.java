package mybookstore.ui.builder;

import mybookstore.ui.action.ActionEnum;
import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для построения вложенного меню и для проведения сортировки книг.
 */
public class NestedMenuBookSorting {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(NestedMenuBookSorting.class.getName());
    /**
     * поле главного меню.
     */
    private Menu rootMenu;
    /**
     * поле вложенного меню.
     */
    private Menu currentMenu;

    public NestedMenuBookSorting(final Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    /**
     * Строит меню сортировки книг по выбранным критериям. Позволяет перейти в предыдущее меню при
     * нажатии определенной цифры.
     */
    public void buildMenu() {
        LOG.info(NestedMenuBookSorting.class.getSimpleName() + " " + "calling a method buildMenu");
        List<MenuItem> itemList = new ArrayList<>();
        currentMenu = new Menu("Меню сортировки книг", itemList);
        itemList.add(new MenuItem("1: Cортировка книг по названию", ActionEnum.SORT_BOOK_BY_NAME.getAction(), null));
        itemList.add(new MenuItem("2: Cортировка книг по дате издания", ActionEnum.SORT_BOOK_BY_DATE_PUBLIC.getAction(), null));
        itemList.add(new MenuItem("3: Cортировка книг по стоимости", ActionEnum.SORT_BOOK_BY_PRICE.getAction(), null));
        itemList.add(new MenuItem("4: Cортировка книг по наличию на складе", ActionEnum.SORT_BOOK_BY_STOCK_AVAILABILITY.getAction(), null));
        itemList.add(new MenuItem("5: Cортировка книг по  id", ActionEnum.SORT_BOOK_BY_ID.getAction(), null));
        itemList.add(new MenuItem("6: Cортировка книг по дате поступления", ActionEnum.SORT_BOOK_BY_DATE_DELIVERY.getAction(), null));
        itemList.add(new MenuItem("7: Выход в предыдущее меню", null, rootMenu));
        LOG.info("method buildMenu completed");
    }

    /**
     * Метод помогает вернуться из вложенного меню в меню уровнем выше.
     * @return меню на уровне выше
     */
    public Menu getRootMenu() {
        LOG.info(NestedMenuBookSorting.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(NestedMenuBookSorting.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }

    /**
     * Возвращает вложенное меню.
     * @return вложенное меню.
     */
    public Menu getCurrentMenu() {
        LOG.info(NestedMenuBookSorting.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        LOG.info(NestedMenuBookSorting.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }
}
