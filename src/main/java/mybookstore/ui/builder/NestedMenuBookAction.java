package mybookstore.ui.builder;

import mybookstore.ui.action.ActionEnum;
import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuBookAction {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(NestedMenuBookAction.class.getName());
    /**
     * поле главного меню для операций над книгами.
     */
    private Menu rootMenu;
    /**
     * поле вложенного меню для операций над книгами.
     */
    private Menu currentMenu;

    public NestedMenuBookAction(final Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    @SuppressWarnings("checkstyle:DesignForExtension")
    public Menu getRootMenu() {
        LOG.info(NestedMenuBookAction.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(NestedMenuBookAction.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }

    /**
     * Составляет главное меню действий над книгами.
     */
    public void buildMenu() {
        List<MenuItem> menuItemList = new ArrayList<>();
        currentMenu = new Menu("Действия", menuItemList);
        menuItemList.add(new MenuItem("1: Списать книгу со склада и перевести в статус отсутствует", ActionEnum.WRITE_OFF_BOOK.getAction(), null));
        menuItemList.add(new MenuItem("2: Добавить книгу на склад", ActionEnum.ADD_BOOK.getAction(), null));
        menuItemList.add(new MenuItem("3: Узнать наличие книги на складе", ActionEnum.GET_STATUS_BOOK.getAction(), null));
        menuItemList.add(new MenuItem("4: Узнать информацию о книге по ее id", ActionEnum.GET_BOOK_BY_ID.getAction(), null));
        menuItemList.add(new MenuItem("5: Получить общее количество книг на складе", ActionEnum.GET_COUNT_BOOK_BY_REPOSITIRY.getAction(), null));
        menuItemList.add(new MenuItem("6: Выход в главное меню", null, rootMenu));
    }

    /**
     * Возвращает вложенное меню.
     * @return вложенное меню
     */
    public Menu getCurrentMenu() {
        LOG.info(NestedMenuBookAction.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        LOG.info(NestedMenuBookAction.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }
}
