package parcial.modelo;

public class AlumnoCambioDeCursoObserver implements Observer{
    private BandejaNotificaciones bandeja;

    // TODO
    @Override
    public void update(Observable observable, Object oldValue, Object newValue) {
        // Si el alumno cambio de curso, crear una notificacion
        Alumno alumno = (Alumno) observable;

        if(alumno.getCurso().equals(newValue)) {

            Curso oldCurso = (Curso) oldValue;
            Curso newCurso = (Curso) newValue;

            this.bandeja.crearNotificacion(
                    "Cambio de curso de " + alumno.getNombre() + " " + alumno.getApellido(),
                    "El alumno " + alumno.getNombre() + " " + alumno.getApellido() +
                           " cambio del curso " + oldCurso.getGrado() + "°" + oldCurso.getDivision() +
                           " a " + newCurso.getGrado() + "°" + newCurso.getDivision()
            );
        }

    }
}
