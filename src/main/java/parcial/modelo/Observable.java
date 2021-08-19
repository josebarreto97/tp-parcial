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

    public void notificar() {
        this.observers.forEach(observer -> {
            observer.update(this, this );
        });
    }

    public void suscribirse(Observer observer) {
        this.observers.add(observer);
    }

    public void desuscribir(Observer observer) {
        this.observers.remove(observer);
    }
}
