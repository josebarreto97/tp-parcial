package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Repitente implements Estado {
    public String nombre = "Repitente";
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
