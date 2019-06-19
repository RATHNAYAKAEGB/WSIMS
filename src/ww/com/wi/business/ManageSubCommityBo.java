package ww.com.wi.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ww.com.wi.dao.custom.SubCommityDAO;
import ww.com.wi.entity.SubCommity;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Component
public class ManageSubCommityBo {

    private SubCommityDAO subCommityDAO;

    @Autowired
    public ManageSubCommityBo(SubCommityDAO subCommityDAO) {
        this.subCommityDAO = subCommityDAO;
    }

    public void createSubCommity(SubCommity commity) throws Exception {
        subCommityDAO.save(commity);
    }

    public SubCommity findSubcommity(String id) throws Exception {
       return subCommityDAO.find(id).get();
    }
    public List<SubCommity>findAll() throws Exception {
        return subCommityDAO.findAll().get();
    }

    public void updateSubComity(SubCommity commity) throws Exception {
        subCommityDAO.update(commity);
    }
    public void deleteSubCommity( String id) throws Exception {
        subCommityDAO.delete(id);
    }

}
