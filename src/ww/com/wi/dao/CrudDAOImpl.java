package ww.com.wi.dao;


import ww.com.wi.dao.CrudDAO;
import ww.com.wi.entity.SuperEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T, ID> {

    @PersistenceContext
    private EntityManager em;
    private Class<T> entity;

    public CrudDAOImpl(){
        entity = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public void save(T entity) throws Exception {
        em.persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        em.merge(entity);
    }

    @Override
    public void delete(ID key) throws Exception {
        em.remove(em.getReference(entity,key));
    }

    @Override
    public Optional<T> find(ID key) throws Exception {
        return Optional.ofNullable(em.find(entity,key));
    }

    @Override
    public Optional<List<T>> findAll() throws Exception {
        return Optional.ofNullable(em.createQuery("SELECT alias FROM " + entity.getName() + " alias").getResultList());
    }

}
