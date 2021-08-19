package parcial.modelo;

public class Repitente implements Estado {
    @Override
    public void promociona(Alumno alumno) {
        if(alumno.getCurso().isEgresados()) {
            alumno.setEstado(new Egresado());
        } else {
            alumno.setEstado(new Promovido());
        }
    }

    @Override
    public void repite(Alumno alumno) {
        // se mantiene el estado
    }

    @Override
    public void sale(Alumno alumno) {
        alumno.setEstado(new Salido());
    }
}
