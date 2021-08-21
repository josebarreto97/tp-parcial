package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoRepitenteObserver implements Observer{
    private BandejaNotificaciones bandeja;

    @Override
    public void update(Observable observable, Object oldValue, Object newValue) {
        //Si el alumno es reinscripto, crear notificacion
        Alumno alumno = (Alumno) observable;

        if(oldValue != null && alumno.getEstado().equals(newValue) && newValue instanceof Repitente) {

            this.bandeja.crearNotificacion(
                    "Repitio el alumno " + alumno.getNombre() + " " + alumno.getApellido(),
                    "El alumno " + alumno.getNombre() + " " + alumno.getApellido() +
                            " ha repetido y se mantiene en el " + alumno.getCurso().getGrado() + "°" + alumno.getCurso().getDivision() + "."
            );
        }
    }
}
