package ww.com.wi.dao.custom.impl;

import org.springframework.stereotype.Repository;
import ww.com.wi.dao.CrudDAO;
import ww.com.wi.dao.CrudDAOImpl;
import ww.com.wi.dao.custom.CustomerGroupDAO;
import ww.com.wi.entity.CustomerGroup;

@Repository
public class CustomerGroupDAOImpl extends CrudDAOImpl<CustomerGroup,String>implements CustomerGroupDAO {
}
