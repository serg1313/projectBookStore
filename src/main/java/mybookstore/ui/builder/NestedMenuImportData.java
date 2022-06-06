package mybookstore.ui.builder;

import mybookstore.ui.action.ActionEnum;
import mybookstore.ui.controller.Menu;
import mybookstore.ui.controller.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для загрузки информации о книгах, заказах, запросов, покупателей из базы данных.
 * Информация сохраняет в списках данных классов.
 */
public class NestedMenuImportData {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(NestedMenuImportData.class.getName());
    /**
     * поле главного меню.
     */
    private Menu rootMenu;
    /**
     * поле вложенного меню.
     */
    private Menu currentMenu;

    public NestedMenuImportData(final Menu rootMenu) {
        this.rootMenu = rootMenu;
    }

    /**
     * Считывает информацию из базы данных и сохраняет выгруженную информацию в списках.
     * Позволяет перейти в предыдущее меню при нажатии определенной цифры.
     */
    public void buildMenu() {
        LOG.info(NestedMenuImportData.class.getSimpleName() + " " + "calling a method buildMenu");
        List<MenuItem> list = new ArrayList<>();
        currentMenu = new Menu("Загрузка из базы данных", list);
        list.add(new MenuItem("1: Загрузка списка книг из базы данных", ActionEnum.READ_FILE_CSV_BOOK.getAction(), null));
        list.add(new MenuItem("2: Загрузка списка заказов из базы данных", ActionEnum.READ_FILE_CSV_ORDER.getAction(), null));
        list.add(new MenuItem("3: Загрузка списка запросов на книги из базы данных", ActionEnum.READ_FILE_CSV_REQUEST.getAction(), null));
        list.add(new MenuItem("4: Загрузка списка покупателей из базы данных", null, null));
        list.add(new MenuItem("5: Выход в предыдущее меню", null, rootMenu));
        LOG.info(NestedMenuImportData.class.getSimpleName() + " " + "calling a method buildMenu");
    }

    /**
     * Помогает вернуться в меню на уровень выше.
     * @return меню на уровень выше
     */
    public Menu getRootMenu() {
        LOG.info(NestedMenuImportData.class.getSimpleName() + " " + "calling a method getRootMenu");
        LOG.info(NestedMenuImportData.class.getSimpleName() + " " + "method getRootMenu completed");
        return rootMenu;
    }

    /**
     * Возвращает вложенное меню.
     * @return вложенное меню
     */
    public Menu getCurrentMenu() {
        LOG.info(NestedMenuImportData.class.getSimpleName() + " " + "calling a method getCurrentMenu");
        LOG.info(NestedMenuImportData.class.getSimpleName() + " " + "method getCurrentMenu completed");
        return currentMenu;
    }
}
