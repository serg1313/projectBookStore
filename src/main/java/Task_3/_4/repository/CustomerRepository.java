package Task_3._4.repository;

import Task_3._4.model.Customer;

import java.util.List;

public interface CustomerRepository {

    /**
     * возвращает список покупателей
     *
     * @return
     */
    List<Customer> getCustomers();

    Customer getCustomerById(long Id);
}
