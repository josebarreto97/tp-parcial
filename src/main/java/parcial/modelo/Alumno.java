package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Alumno extends Observable{
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date nacimiento;
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
        this.estado.sale(this);
    }

    public void setNombre(String nombre) {
        this.notificar(this.nombre, this.nombre = nombre);
    }

    public void setApellido(String apellido) {
        this.notificar(this.apellido, this.apellido = apellido);
    }

    public void setEstado(Estado estado) {
        this.notificar(this.estado, this.estado = estado);
    }

    public void setCurso(Curso curso) {
        this.notificar(this.curso, this.curso = curso);
    }
}
