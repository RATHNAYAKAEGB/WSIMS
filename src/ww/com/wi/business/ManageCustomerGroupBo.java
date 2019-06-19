package ww.com.wi.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ww.com.wi.dao.custom.CustomerGroupDAO;
import ww.com.wi.dao.custom.SubCommityDAO;
import ww.com.wi.entity.CustomerGroup;
import ww.com.wi.entity.SubCommity;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class ManageCustomerGroupBo {

    private CustomerGroupDAO customerGroupDAO;
    @Autowired
    public ManageCustomerGroupBo(CustomerGroupDAO customerGroupDAO) {
        this.customerGroupDAO = customerGroupDAO;
    }

    public void createCustomerGroup(CustomerGroup group) throws Exception {
        customerGroupDAO.save(group);
    }

    public CustomerGroup findCustomerGroup(String id) throws Exception {
        return customerGroupDAO.find(id).get();
    }
    public List<CustomerGroup> findAll() throws Exception {
        return customerGroupDAO.findAll().get();
    }

    public void updateCustomerGroup(CustomerGroup customerGroup) throws Exception {
        customerGroupDAO.update(customerGroup);
    }
    public void deleteCustomerGroup( String id) throws Exception {
        customerGroupDAO.delete(id);
    }

}
