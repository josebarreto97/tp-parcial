package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Egresado implements Estado {
    public String nombre = "Egresado";
    @Override
    public void promociona(Alumno alumno) {
        // Se mantiene en este estado
    }

    @Override
    public void repite(Alumno alumno) {
        // Se mantiene en este estado
    }

    @Override
    public void sale(Alumno alumno) {
        // Se mantiene en este estado
    }
}
