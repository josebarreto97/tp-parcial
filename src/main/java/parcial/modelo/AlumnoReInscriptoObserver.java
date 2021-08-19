package parcial.modelo;

public class AlumnoReInscriptoObserver implements Observer{
    private BandejaNotificaciones bandeja;

    @Override
    public void update(Observable observable, Object oldValue, Object newValue) {
        //Si el alumno es reinscripto, crear notificacion
        Alumno alumno = (Alumno) observable;

        if(alumno.getEstado().equals(newValue) && newValue instanceof ReInscripto) {

            this.bandeja.crearNotificacion(
                    "Reinscripcion del alumno " + alumno.getNombre() + " " + alumno.getApellido(),
                    "El alumno " + alumno.getNombre() + " " + alumno.getApellido() +
                            " fue reinscripto en el establecimiento."
            );
        }
    }
}
