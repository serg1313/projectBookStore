package mybookstore.repository;

import mybookstore.model.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getCustomers();

    Customer getCustomerById(long id);
}
