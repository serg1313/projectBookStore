package mybookstore.ui.builder;

import mybookstore.ui.action.ActionEnum;
import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NestedMenuRequest {
    private static final Logger LOG = LogManager.getLogger(NestedMenuRequest.class.getName());
    private Menu rootMenu;
    private Menu currentMenu;

    public NestedMenuRequest(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    public void buildMenu() {
        LOG.info(NestedMenuRequest.class.getSimpleName() + " " + "calling a method buildMenu");
        List<MenuItem> itemList = new ArrayList<>();
        currentMenu = new Menu("Меню запросов", itemList);
        itemList.add(new MenuItem("1: Посмотреть список запросов", ActionEnum.GET_LIST_REQUEST.getAction(), null));
        itemList.add(new MenuItem("2: Создать запрос на книгу по ее id", ActionEnum.CREATE_REQUEST_BOOK_ID.getAction(), null));
        itemList.add(new MenuItem("3: Перевести статус запроса на статус закрыт по id книги", ActionEnum.CANCELED_REQUEST_BY_BOOK_ID.getAction(), null));
        itemList.add(new MenuItem("4: Сортировать книги по количеству запросов на них", ActionEnum.SORT_REQUEST_BY_COUNT.getAction(), null));
        itemList.add(new MenuItem("5: Сортировать запросы по имени книги", ActionEnum.SORT_REQUEST_BY_NAME_BOOK.getAction(), null));
        itemList.add(new MenuItem("6: Количество запросов на книгу по ее id", ActionEnum.GET_REQUEST_BY_BOOK_ID.getAction(), null));
        itemList.add(new MenuItem("7: Выход в предыдущее меню", null, rootMenu));
        LOG.info(NestedMenuRequest.class.getSimpleName() + " " + "method buildMenu completed");
    }

    public Menu getRootMenu() {
        LOG.info(NestedMenuRequest.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(NestedMenuRequest.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }

    public Menu getCurrentMenu() {
        LOG.info(NestedMenuRequest.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        LOG.info(NestedMenuRequest.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }
}
