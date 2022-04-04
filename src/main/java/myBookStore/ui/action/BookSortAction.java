package myBookStore.ui.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookSortAction implements IAction {

    @Override
    public void execuit() {
        System.out.println("Сортировка");
    }
}
