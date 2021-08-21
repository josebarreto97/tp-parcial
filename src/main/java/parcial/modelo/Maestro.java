package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Maestro extends Educador{
    private List<Educador> ayudantes;

    public Maestro() {
        this.ayudantes = new ArrayList<>();
    }

    @Override
    public String getNombresDeAyudantes() {
        StringBuilder nombresAyudantes = new StringBuilder();

        for (Educador ayudante : this.ayudantes) {
            nombresAyudantes.append(ayudante.getNombresDeAyudantes()).append(" ");
        }

        return this.getNombre() + " " + nombresAyudantes;
    }
}
