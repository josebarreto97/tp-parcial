package parcial.modelo;

public class AlumnoReInscriptoObserver implements Observer{
    private BandejaNotificaciones bandeja;

    // TODO
    @Override
    public void update(Observable observable, Object oldValue, Object newValue) {
        //Si el alumno es reinscripto, crear notificacion
        Alumno alumno = (Alumno) observable;

        if(alumno.getEstado().equals(newValue)){
            Estado oldSale = (Estado) oldValue;
            Estado newSale = (Estado) newValue;
        }

        this.bandeja.crearNotificacion(
                "Reinscripcion del alumno " + alumno.getNombre() + " " + alumno.getApellido(),
                "El alumno " + alumno.getNombre() + " " + alumno.getApellido() +
                        " fue reinscripto " + oldSale.getEstado() +
                        " a " + newSale.getEstado()
        );

    }
}
