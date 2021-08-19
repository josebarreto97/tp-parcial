package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Alumno extends Observable{
    private String nombre;
    private String apellido;
    private String dni;
    private String nacimiento;
    private String provincia;
    private String municipio;
    private String localidad;
    private String direccion;
    private Estado estado;
    private Curso curso;

    public void promociona() {
        this.estado.promociona(this);
    }

    public void repite() {
        this.estado.repite(this);
    }

    public void sale() {
        this.estado.repite(this);
    }
}
