package ww.com.wi.dao;

import ww.com.wi.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity,ID extends Serializable> {

    Optional<T> find(ID key) throws Exception;

    Optional<List<T>> findAll() throws Exception;

    void save(T entity) throws Exception;

    void update(T entity) throws Exception;

    void delete(ID key) throws Exception;


}
