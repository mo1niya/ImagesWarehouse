package imageswarehouse.model.dao;

import java.util.Collection;

public interface CrudDao<T> {
    Collection<T> find(String name);
    Collection<T> findByObg(T model);
    int save(T model);
    void update(T model);
    void delete(Integer Id);
    Collection<T> findAll();
}
