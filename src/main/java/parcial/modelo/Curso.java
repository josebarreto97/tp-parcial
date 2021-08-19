package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Curso {
    private String grado;
    private String division;
    private List<Alumno> alumnos;
    private Educador educador;
    private boolean egresados;

    public Curso() {
        this.alumnos = new ArrayList<>();
    }
}
