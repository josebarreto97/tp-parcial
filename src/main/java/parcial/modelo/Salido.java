package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Salido implements Estado {
    public String nombre = "Salido";
    @Override
    public void promociona(Alumno alumno) {
        // nada
    }

    @Override
    public void repite(Alumno alumno) {
        // nada
    }

    @Override
    public void sale(Alumno alumno) {
        // nada
    }
}
