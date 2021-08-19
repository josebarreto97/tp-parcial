package parcial.modelo;

public class Inscripto implements Estado{
    @Override
    public void promociona(Alumno alumno) {
        alumno.setEstado(new Promovido());
    }

    @Override
    public void repite(Alumno alumno) {
        alumno.setEstado(new Promovido());
    }

    @Override
    public void sale(Alumno alumno) {
        alumno.setEstado(new Salido());
    }
}
