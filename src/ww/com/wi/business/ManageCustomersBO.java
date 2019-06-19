package ww.com.wi.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ww.com.wi.dao.custom.CustomerDao;
import ww.com.wi.entity.Customer;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class ManageCustomersBO {

    private CustomerDao customerDao;

    @Autowired
    public ManageCustomersBO(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    public void createCustomer(Customer customer) throws Exception {
        customerDao.save(customer);
    }

    public Customer findCustomer(String id) throws Exception {
        return customerDao.find(id).get();
    }
    public List<Customer> findAll() throws Exception {
        return customerDao.findAll().get();
    }

    public void updateCustomerGroup(Customer customer) throws Exception {
        customerDao.update(customer);
    }
    public void deleteCustomerGroup( String id) throws Exception {
        customerDao.delete(id);
    }

}
