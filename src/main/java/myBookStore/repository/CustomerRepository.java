package myBookStore.repository;

import myBookStore.model.Customer;

import java.util.List;

public interface CustomerRepository {

    /**
     * возвращает список покупателей
     *
     * @return
     */
    List<Customer> getCustomers();

    /**
     * получить покупателя по id
     * @param Id
     * @return
     */
    Customer getCustomerById(long Id);
}