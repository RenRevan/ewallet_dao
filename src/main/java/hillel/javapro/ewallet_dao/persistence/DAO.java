package hillel.javapro.ewallet_dao.persistence;

import java.util.List;

public interface DAO<T> {

    Integer create(T t);

    void update(T t);

    void delete(T t);

    T get(int id);

    List<T> getAll();

}
