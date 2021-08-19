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

    public void setNombre(String nombre) {
        this.notificar(this.nombre, this.nombre = nombre);
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
