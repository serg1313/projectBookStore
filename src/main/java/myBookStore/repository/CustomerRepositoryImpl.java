package myBookStore.repository;

import myBookStore.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static CustomerRepository customerRepository;
    private List<Customer> customers = new ArrayList<>();

    public static CustomerRepository getCustomerRepository() {
        if (customerRepository == null) {
            customerRepository = new CustomerRepositoryImpl();
        }
        return customerRepository;
    }

    public CustomerRepositoryImpl() {
        initCustomers();
    }

    private void initCustomers() {
//        customers.add(new Customer("покупатель1", 25));
//        customers.add(new Customer("покупатель2", 35));
//        customers.add(new Customer("покупатель3", 45));
//        customers.add(new Customer("покупатель4", 55));
//        customers.add(new Customer("покупатель5", 34));
//        customers.add(new Customer("покупатель6", 27));
//        customers.add(new Customer("покупатель7", 84));
//        customers.add(new Customer("покупатель8", 29));
    }

    /**
     * возвращает список покупателей
     *
     * @return
     */
    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public Customer getCustomerById(long id) {
        for (Customer customer : getCustomers()) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
}
