package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Promovido implements Estado {
    public String nombre = "Promovido";
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
        alumno.setEstado(new Repitente());
    }

    @Override
    public void sale(Alumno alumno) {
        alumno.setEstado(new Salido());
    }
}
