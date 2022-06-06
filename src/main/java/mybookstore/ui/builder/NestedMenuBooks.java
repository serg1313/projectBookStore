package mybookstore.ui.builder;

import mybookstore.ui.action.ActionEnum;
import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для проведения действия над книгами из меню.
 */
public class NestedMenuBooks {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(NestedMenuBooks.class.getName());
    /**
     * поле главного меню над провеением действий над книгами.
     */
    private Menu rootMenu;
    /**
     * поле вложенного меню.
     */
    private Menu current;

    public NestedMenuBooks(final Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    /**
     * Строит меню книг. Позволяет перейти в предыдущее меню или во вложенное при
     * нажатии определенной цифры.
     */
    public void buildMenu() {
        LOG.info(NestedMenuBooks.class.getSimpleName() + " " + "calling a method buildMenu");
        List<MenuItem> menuItems = new ArrayList<>();
        current = new Menu("Меню книг", menuItems);
        menuItems.add(new MenuItem("1: Список всех книг", ActionEnum.GET_LIST_BOOKS.getAction(), null));
        NestedMenuBookSorting nestedMenuBookSorting = new NestedMenuBookSorting(current);
        nestedMenuBookSorting.buildMenu();
        menuItems.add(new MenuItem("2: Меню сортировки книг", ActionEnum.BOOK_SORT_ACTION.getAction(), nestedMenuBookSorting.getCurrentMenu()));
        NestedMenuBookAction nestedMenuBookAction = new NestedMenuBookAction(current);
        nestedMenuBookAction.buildMenu();
        menuItems.add(new MenuItem("3: Меню действий над книгами", ActionEnum.BOOK_MENU_ACTION.getAction(), nestedMenuBookAction.getCurrentMenu()));
        menuItems.add(new MenuItem("4: Книги которые не проданы больше чем 6 мес.", ActionEnum.SORT_BOOKS_NOT_SOLD.getAction(), null));
        menuItems.add(new MenuItem("5: Посмотреть описание книги", ActionEnum.GET_DISCRIPTION_BOOK.getAction(), null));
        menuItems.add(new MenuItem("6: Выход в главное меню", null, rootMenu));
        LOG.info("method buildMenu completed");
    }

    /**
     * Метод помогает вернуться из вложенного меню в главное меню.
     * @return главное меню
     */
    public Menu getRootMenu() {
        LOG.info(NestedMenuBooks.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(NestedMenuBooks.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }

    /**
     * Возвращает вложенное меню.
     * @return вложенное меню
     */
    public Menu getCurrent() {
        LOG.info(NestedMenuBooks.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(NestedMenuBooks.class.getSimpleName() + " " + "method getRootMenu completed");
        return current;
    }
}
