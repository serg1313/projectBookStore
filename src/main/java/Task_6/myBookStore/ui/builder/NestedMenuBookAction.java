package Task_6.myBookStore.ui.builder;

import Task_6.myBookStore.ui.action.ActionEnum;
import Task_6.myBookStore.ui.controller.Menu;
import Task_6.myBookStore.ui.controller.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuBookAction {
    private Menu rootMenu;
    private Menu currentMenu;

    public Menu getRootMenu() {
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
        return currentMenu;
    }

    public NestedMenuBookAction(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }
}
