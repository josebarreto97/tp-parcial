package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Establecimiento {
    private Long id;
    private String nombre;
    private String provincia;
    private String municipio;
    private String localidad;
    private String direccion;
    private String telefono;
    private String email;
    private List<Curso> cursos;

    public Establecimiento() {
        this.cursos = new ArrayList<>();
    }
}
