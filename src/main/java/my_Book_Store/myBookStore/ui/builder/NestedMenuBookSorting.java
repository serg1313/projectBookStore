package my_Book_Store.myBookStore.ui.builder;

import my_Book_Store.myBookStore.ui.action.ActionEnum;
import my_Book_Store.myBookStore.ui.controller.Menu;
import my_Book_Store.myBookStore.ui.controller.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuBookSorting {
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuBookSorting(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        List<MenuItem> itemList = new ArrayList<>();
        currentMenu = new Menu("Меню сортировки книг", itemList);
        itemList.add(new MenuItem("1: Cортировка книг по названию", ActionEnum.SORT_BOOK_BY_NAME.getAction(), null));
        itemList.add(new MenuItem("2: Cортировка книг по дате издания", ActionEnum.SORT_BOOK_BY_DATE_PUBLIC.getAction(), null));
        itemList.add(new MenuItem("3: Cортировка книг по стоимости", ActionEnum.SORT_BOOK_BY_PRICE.getAction(), null));
        itemList.add(new MenuItem("4: Cортировка книг по наличию на складе", ActionEnum.SORT_BOOK_BY_STOCK_AVAILABILITY.getAction(), null));
        itemList.add(new MenuItem("5: Cортировка книг по  id", ActionEnum.SORT_BOOK_BY_ID.getAction(), null));
        itemList.add(new MenuItem("6: Cортировка книг по дате поступления", ActionEnum.SORT_BOOK_BY_DATE_DELIVERY.getAction(), null));
        itemList.add(new MenuItem("7: Выход в предыдущее меню", null, rootMenu));
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }
}
