package myBookStore.repository;

import myBookStore.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private static final Logger log = LogManager.getLogger(CustomerRepositoryImpl.class.getName());
    private static CustomerRepository customerRepository;
    private List<Customer> customers = new ArrayList<>();

    public static CustomerRepository getCustomerRepository() {
        if (customerRepository == null) {
            customerRepository = new CustomerRepositoryImpl();
        }
        log.info("method CustomerRepository completed");
        return customerRepository;
    }

    public CustomerRepositoryImpl() {
        log.info("the constructor is called CustomerRepositoryImpl");
        initCustomers();
    }


    private void initCustomers() {
        log.info("calling a method initCustomers");
        customers.add(new Customer("покупатель1", 25));
        customers.add(new Customer("покупатель2", 35));
        customers.add(new Customer("покупатель3", 45));
        customers.add(new Customer("покупатель4", 55));
        customers.add(new Customer("покупатель5", 34));
        customers.add(new Customer("покупатель6", 27));
        customers.add(new Customer("покупатель7", 84));
        customers.add(new Customer("покупатель8", 29));
        log.info("method initCustomers completed");
    }

    /**
     * возвращает список покупателей
     *
     * @return
     */
    @Override
    public List<Customer> getCustomers() {
        log.info("calling a method getCustomers");
        log.info("method getCustomers completed");
        return customers;
    }

    @Override
    public Customer getCustomerById(long id) {
        log.info("calling a method getCustomerById");
        for (Customer customer : getCustomers()) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        log.info("method getCustomerById completed");
        return null;
    }
}
