package myBookStore.ui.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderAction implements IAction {
    @Override
    public void execuit() {
        System.out.println("Заказы");
    }
}
