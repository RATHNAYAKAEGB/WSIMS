package ww.com.wi.dao.custom.impl;

import org.springframework.stereotype.Repository;
import ww.com.wi.dao.CrudDAO;
import ww.com.wi.dao.CrudDAOImpl;
import ww.com.wi.dao.custom.SubCommityDAO;
import ww.com.wi.entity.SubCommity;

@Repository
public class SubCommityDAOImpl extends CrudDAOImpl<SubCommity,String> implements SubCommityDAO  {
}
