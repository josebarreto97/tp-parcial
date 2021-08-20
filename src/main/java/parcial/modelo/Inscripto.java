package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Inscripto implements Estado {
    public String nombre = "Inscripto";
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
