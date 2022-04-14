package mybookstore.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * класс для создания объекта покупатель.
 */
public class Customer extends BaseEntity {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(Customer.class.getName());
    /**
     * поле id объекта customer.
     */
    private static long id = 1;
    /**
     * поле имя покупателя.
     */
    private String name;
    /**
     * поле возраст покупателя.
     */
    private int age;

    public Customer(final String name, final int age) {
        super(id++);
        this.name = name;
        this.age = age;
    }

    public static long getIdCustomer() {
        return id;
    }

    /**
     * вернуть имя покупателя.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * внести имя покупателя.
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * вернуть возраст покупателя.
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * установить возраст покупателя.
     *
     * @param age
     */
    public void setAge(final int age) {
        this.age = age;
    }
}
