package Task_4.model;

import myBookStore.model.BaseEntity;

public class Customer extends BaseEntity {

    private static long id = 0;
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
