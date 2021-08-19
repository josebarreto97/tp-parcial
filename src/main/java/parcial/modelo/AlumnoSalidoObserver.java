package parcial.modelo;

public class AlumnoSalidoObserver implements Observer{
    private BandejaNotificaciones bandeja;

    @Override
    public void update(Observable observable, Object oldValue, Object newValue) {
        //Si el alumno salio, crear notificacion
        Alumno alumno = (Alumno) observable;

        if(alumno.getEstado().equals(newValue) && newValue.equals()){
            Estado oldSale = (Estado) oldValue;
            Estado newSale = (Estado) newValue;
        }

        this.bandeja.crearNotificacion(
                "Salida del " + alumno.getNombre() + " " + alumno.getApellido(),
                "El alumno " + alumno.getNombre() + " " + alumno.getApellido() +
                        " salio del " + oldSale.getEstado() +
                        " a " + newSale.getEstado()
        );

    }
}
