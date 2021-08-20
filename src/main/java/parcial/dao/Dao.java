package parcial.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    T get(long id);

    void save(T t);

    void update(T t, String[] params);
}
