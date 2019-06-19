package ww.com.wi.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ww.com.wi.dao.custom.UserDAO;
import ww.com.wi.entity.Users;

import javax.transaction.Transactional;

@Component
@Transactional
public class ManageUserBo {

    private UserDAO userDAO;

    @Autowired
    public ManageUserBo(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void saveUser(Users u) throws Exception {
        userDAO.save(u);
    }

    public Users findUser(String userName) throws Exception {
     return   userDAO.find(userName).get();
    }



}
