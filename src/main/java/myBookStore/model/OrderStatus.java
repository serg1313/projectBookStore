package myBookStore.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum OrderStatus {

    NEW,
    COMPLETED,
    CANCELLED;

    private static final Logger log = LogManager.getLogger(OrderStatus.class.getName());
}
