package mybookstore.ui.controller;

import mybookstore.ui.action.IAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для создания пунктов меню.
 */
public class MenuItem {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(MenuItem.class.getName());
    /**
     * поле заглавие.
     */
    private String title;
    /**
     * поле действия.
     */
    private IAction action;
    /**
     * поле вложенного меню.
     */
    private Menu nextMenu;

    public MenuItem(final String title, final IAction action, final Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

    /**
     * Позволяет
     */
    public void doAction() {
        action.execuit();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public IAction getAction() {
        return action;
    }

    public void setAction(IAction action) {
        this.action = action;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public void setNextMenu(Menu nextMenu) {
        this.nextMenu = nextMenu;
    }

    @Override
    public String toString() {
        return title;
    }
}
