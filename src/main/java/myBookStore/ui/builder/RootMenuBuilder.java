package myBookStore.ui.builder;

import myBookStore.ui.action.ActionEnum;
import myBookStore.ui.controller.Menu;
import myBookStore.ui.controller.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class RootMenuBuilder {
    private Menu currentMenu;

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void buildMenu() {
        List<MenuItem> menuItems = new ArrayList<>();
        currentMenu = new Menu("Главное меню \nВыберите нужный пункт меню нажав цифру ", menuItems);
        NestedMenuOrders nestedMenuBuilder = new NestedMenuOrders(currentMenu);
        nestedMenuBuilder.buildMenu();
        menuItems.add(new MenuItem("1: Заказы", ActionEnum.ORDER_ACTION.getAction(), nestedMenuBuilder.getCurrentMenu()));
        NestedMenuBooks nestedMenuBooks = new NestedMenuBooks(currentMenu);
        nestedMenuBooks.buildMenu();
        menuItems.add(new MenuItem("2. Книги", ActionEnum.BOOK_ACTION.getAction(), nestedMenuBooks.getCurrent()));
        NestedMenuRequest nestedMenuRequest = new NestedMenuRequest(currentMenu);
        nestedMenuRequest.buildMenu();
        menuItems.add(new MenuItem("3: Запросы", ActionEnum.REQUEST_ACTION.getAction(), nestedMenuRequest.getCurrentMenu()));
        NestedMenuImportData nestedMenuImportData = new NestedMenuImportData(currentMenu);
        nestedMenuImportData.buildMenu();
        menuItems.add(new MenuItem("4. Загрузка базы данных", ActionEnum.IMPORT_EXPORT_DATA.getAction(), nestedMenuImportData.getCurrentMenu()));
        menuItems.add(new MenuItem("5: Выход из меню", ActionEnum.EXIST_ACTION.getAction(), null));
    }

}
