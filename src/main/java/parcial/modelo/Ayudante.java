package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Ayudante extends Educador{
    private String nombre;
    private List<Curso> cursos;
    private BandejaNotificaciones bandejaNotificaciones;

    public Ayudante() {
        this.cursos = new ArrayList<>();
    }

    @Override
    public String getNombresDeAyudantes() {
        return nombre;
    }
}
