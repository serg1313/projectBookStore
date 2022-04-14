package mybookstore.repository;

import mybookstore.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания списка покупателей.
 */
public class CustomerRepositoryImpl implements CustomerRepository {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(CustomerRepositoryImpl.class.getName());
    /**
     * статичное поле CustomerRepository.
     */
    private static CustomerRepository customerRepository;
    /**
     * поле список покупателей.
     */
    private List<Customer> customers = new ArrayList<>();

    public static CustomerRepository getCustomerRepository() {
        if (customerRepository == null) {
            customerRepository = new CustomerRepositoryImpl();
        }
        LOG.info(CustomerRepositoryImpl.class.getSimpleName() + " method CustomerRepository completed");
        return customerRepository;
    }

    public CustomerRepositoryImpl() {
        LOG.info(CustomerRepositoryImpl.class.getSimpleName() + " the constructor is called CustomerRepositoryImpl");
        initCustomers();
    }


    @SuppressWarnings("checkstyle:MagicNumber")
    private void initCustomers() {
        LOG.info(CustomerRepositoryImpl.class.getSimpleName() + " calling a method initCustomers");
        customers.add(new Customer("покупатель1", 25));
        customers.add(new Customer("покупатель2", 35));
        customers.add(new Customer("покупатель3", 45));
        customers.add(new Customer("покупатель4", 55));
        customers.add(new Customer("покупатель5", 34));
        customers.add(new Customer("покупатель6", 27));
        customers.add(new Customer("покупатель7", 84));
        customers.add(new Customer("покупатель8", 29));
        LOG.info(CustomerRepositoryImpl.class.getSimpleName() + " method initCustomers completed");
    }

    /**
     * возвращает список покупателей.
     * @return
     */
    @Override
    public List<Customer> getCustomers() {
        LOG.info(CustomerRepositoryImpl.class.getSimpleName() + " calling a method getCustomers");
        LOG.info(CustomerRepositoryImpl.class.getSimpleName() + " method getCustomers completed");
        return customers;
    }

    /**
     * возвращает объект покупателя по id переданному в параметре. Если такого id нет, то возвращает null.
     * @param id покупателя
     * @return
     */
    @Override
    public Customer getCustomerById(final long id) {
        LOG.info(CustomerRepositoryImpl.class.getSimpleName() + " " + "calling a method getCustomerById");
        for (Customer customer : getCustomers()) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        LOG.info(CustomerRepositoryImpl.class.getSimpleName() + " " + "method getCustomerById completed");
        return null;
    }
}
