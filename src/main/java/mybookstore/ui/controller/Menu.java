package mybookstore.ui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для составления пунктов меню.
 */
public class Menu {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(Menu.class.getName());
    /**
     * поле имени меню.
     * @return
     */
    private String name;

    /**
     * Возвращает имя поля name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     */
    private List<MenuItem> menuItemList = new ArrayList<>();

    public Menu(final String name, final List<MenuItem> menuItemList) {
        this.name = name;
        this.menuItemList = menuItemList;
    }

    @SuppressWarnings("checkstyle:DesignForExtension")
    public void setName(final String name) {
        this.name = name;
    }

    @SuppressWarnings("checkstyle:DesignForExtension")
    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    @SuppressWarnings("checkstyle:DesignForExtension")
    public void setMenuItemList(final List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    @SuppressWarnings("checkstyle:DesignForExtension")
    public List<MenuItem> getMenuItem() {
        return menuItemList;
    }

    @SuppressWarnings("checkstyle:DesignForExtension")
    public void addItem(final MenuItem menuItem) {
        menuItemList.add(menuItem);
    }
}
