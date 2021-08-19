package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public abstract class Observable {
    private List<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<>();
    }

    // TODO
    public void notificar() {

    }

    public void suscribir() {

    }

    public void desuscribir() {

    }

}
