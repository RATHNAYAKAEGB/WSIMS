package ww.com.wi.dao.custom.impl;

import org.springframework.stereotype.Repository;
import ww.com.wi.dao.CrudDAO;
import ww.com.wi.dao.CrudDAOImpl;
import ww.com.wi.dao.custom.CustomerDao;
import ww.com.wi.entity.Customer;

@Repository
public class CustomerDaoImpl extends CrudDAOImpl<Customer,String> implements CustomerDao {
}
