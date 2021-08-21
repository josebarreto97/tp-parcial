package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoSalidoObserver implements Observer{
    private BandejaNotificaciones bandeja;

    @Override
    public void update(Observable observable, Object oldValue, Object newValue) {
        //Si el alumno salio, crear notificacion
        Alumno alumno = (Alumno) observable;

        if(oldValue != null && alumno.getEstado().equals(newValue) && newValue instanceof Salido){

            this.bandeja.crearNotificacion(
                    "Salida del " + alumno.getNombre() + " " + alumno.getApellido(),
                    "El alumno " + alumno.getNombre() + " " + alumno.getApellido() +
                            " salió del establecimiento"
            );
        }



    }
}
