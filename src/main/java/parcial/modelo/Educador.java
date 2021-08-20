package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public abstract class Educador {
    private Long id;
    private String nombre;
    private List<Curso> cursos;
    private BandejaNotificaciones bandejaNotificaciones;

    public Educador() {
        this.cursos = new ArrayList<>();
    }

    public abstract String getNombresDeAyudantes();

}
