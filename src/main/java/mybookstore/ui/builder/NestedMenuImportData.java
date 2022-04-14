package mybookstore.ui.builder;

import mybookstore.ui.action.ActionEnum;
import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuImportData {
    private static final Logger log = LogManager.getLogger(NestedMenuImportData.class.getName());
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuImportData(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        log.info(NestedMenuImportData.class.getSimpleName() + " " + "calling a method buildMenu");
        List<MenuItem> list = new ArrayList<>();
        currentMenu = new Menu("Загрузка из базы данных", list);
        list.add(new MenuItem("1: Загрузка списка книг из базы данных", ActionEnum.READ_FILE_CSV_BOOK.getAction(), null));
        list.add(new MenuItem("2: Загрузка списка заказов из базы данных", ActionEnum.READ_FILE_CSV_ORDER.getAction(), null));
        list.add(new MenuItem("3: Загрузка списка запросов на книги из базы данных", ActionEnum.READ_FILE_CSV_REQUEST.getAction(), null));
        list.add(new MenuItem("4: Загрузка списка покупателей из базы данных", null, null));
        list.add(new MenuItem("5: Выход в предыдущее меню", null, rootMenu));
        log.info(NestedMenuImportData.class.getSimpleName() + " " + "calling a method buildMenu");
    }

    public Menu getRootMenu() {
        log.info(NestedMenuImportData.class.getSimpleName() + " " + "calling a method getRootMenu");
        log.info(NestedMenuImportData.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }


    public Menu getCurrentMenu() {
        log.info(NestedMenuImportData.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        log.info(NestedMenuImportData.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }
}
