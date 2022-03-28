package my_Book_Store.myBookStore.ui.builder;

import my_Book_Store.myBookStore.ui.action.ActionEnum;
import my_Book_Store.myBookStore.ui.controller.Menu;
import my_Book_Store.myBookStore.ui.controller.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuBooks {
    private Menu rootMenu;
    private Menu current;

    public NestedMenuBooks(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
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
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public Menu getCurrent() {
        return current;
    }
}
