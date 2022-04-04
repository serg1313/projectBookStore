package myBookStore.ui.controller;

import myBookStore.ui.action.IAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MenuItem {
    private static final Logger log = LogManager.getLogger(MenuItem.class.getName());
    private String title;
    private IAction action;
    private Menu nextMenu;

    public MenuItem(String title, IAction action, Menu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }

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
