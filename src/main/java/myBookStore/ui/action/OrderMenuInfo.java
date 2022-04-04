package myBookStore.ui.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderMenuInfo implements IAction {
    @Override
    public void execuit() {
        System.out.println("Информация о заказах");
    }
}
