package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public abstract class Educador {
    private Long id;
    private String nombre;
    private Curso curso;
    private BandejaNotificaciones bandejaNotificaciones;

    public abstract String getNombresDeAyudantes();

}
