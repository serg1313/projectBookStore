package myBookStore.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer extends BaseEntity {
    private static final Logger log = LogManager.getLogger(Customer.class.getName());
    private static long id = 1;
    private String name;
    private int age;

    public Customer(String name, int age) {
        super(id++);
        this.name = name;
        this.age = age;
    }

    public static long getIdCustomer() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
