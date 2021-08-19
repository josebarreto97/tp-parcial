package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Maestro extends Educador{
    private String nombre;
    private List<Curso> cursos;
    private List<Educador> ayudantes;
    private BandejaNotificaciones bandejaNotificaciones;

    public Maestro() {
        this.cursos = new ArrayList<>();
        this.ayudantes = new ArrayList<>();
    }

    @Override
    public String getNombresDeAyudantes() {
        StringBuilder nombresAyudantes = new StringBuilder();

        for (Educador ayudante : this.ayudantes) {
            nombresAyudantes.append(ayudante.getNombresDeAyudantes()).append(" ");
        }

        return nombre + " " + nombresAyudantes;
    }
}
