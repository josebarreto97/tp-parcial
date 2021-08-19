package parcial.modelo;

public interface Observer {
    void update(Observable observable, Object oldValue, Object newValue);
}
