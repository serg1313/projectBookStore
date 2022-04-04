package myBookStore.ui.builder;

import myBookStore.ui.action.ActionEnum;
import myBookStore.ui.controller.Menu;
import myBookStore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuBookAction {
    private static final Logger log = LogManager.getLogger(NestedMenuBookAction.class.getName());
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuBookAction(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public Menu getRootMenu() {
        log.info("calling a method getRootMenu");
        log.info("method getRootMenu completed");
        return rootMenu;
    }

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

    public Menu getCurrentMenu() {
        log.info("calling a method getCurrentMenu");
        log.info("method getCurrentMenu completed");
        return currentMenu;
    }


}
