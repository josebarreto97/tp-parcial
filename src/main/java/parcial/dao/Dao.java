package parcial.dao;

public interface Dao<T> {

    T get(long id);

    void save(T t);

    void update(T t);
}
