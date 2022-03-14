package Task_4.repository;



import Task_4.model.Customer;

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
