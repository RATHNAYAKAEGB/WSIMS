package ww.com.wi.dao.custom.impl;

import org.springframework.stereotype.Repository;
import ww.com.wi.dao.CrudDAOImpl;
import ww.com.wi.dao.custom.UserDAO;
import ww.com.wi.entity.Users;

@Repository
public class UserDAOImpl extends CrudDAOImpl<Users,String> implements UserDAO {

}
