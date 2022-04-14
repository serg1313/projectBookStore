package mybookstore.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для перечисления статусов заказа.
 */
public enum OrderStatus {
    /**
     * статус заказа.
     */
    NEW,
    /**
     * статус заказа.
     */
    COMPLETED,
    /**
     * статус заказа.
     */
    CANCELLED;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private static final Logger LOG = LogManager.getLogger(OrderStatus.class.getName());
}
