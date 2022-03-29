package myBookStore.ui.builder;

import myBookStore.ui.action.ActionEnum;
import myBookStore.ui.controller.Menu;
import myBookStore.ui.controller.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuImportData {
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuImportData(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        List<MenuItem> list = new ArrayList<>();
        currentMenu = new Menu("Загрузка из базы данных", list);
        list.add(new MenuItem("1: Загрузка списка книг из базы данных", ActionEnum.READ_FILE_CSV_BOOK.getAction(), null));
        list.add(new MenuItem("2: Загрузка списка заказов из базы данных", ActionEnum.READ_FILE_CSV_ORDER.getAction(), null));
        list.add(new MenuItem("3: Загрузка списка запросов на книги из базы данных", ActionEnum.READ_FILE_CSV_REQUEST.getAction(), null));
        list.add(new MenuItem("4: Загрузка списка покупателей из базы данных", null, null));
        list.add(new MenuItem("5: Выход в предыдущее меню", null, rootMenu));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }


    public Menu getCurrentMenu() {
        return currentMenu;
    }

}
