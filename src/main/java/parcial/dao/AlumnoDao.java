package parcial.dao;

import parcial.modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AlumnoDao implements Dao<Alumno>{

    private List<Estado> estados = Arrays.asList(
            new Inscripto(), new ReInscripto(), new Promovido(),
            new Repitente(), new Egresado(), new Salido()
    );

    @Override
    public Optional<Alumno> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Alumno> getAll() {
        Connection connection = DB.getInstancia().getConnection();
        List<Alumno> alumnos = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Alumnos");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Alumno alumno = new Alumno();
                alumno.setNombre(resultSet.getString("nombre"));
                alumno.setApellido(resultSet.getString("apellido"));
                alumno.setDni(resultSet.getString("dni"));
                alumno.setDireccion(resultSet.getString("direccion"));
                alumno.setLocalidad(resultSet.getString("municipio"));
                alumno.setMunicipio(resultSet.getString("localidad"));
                alumno.setProvincia(resultSet.getString("provincia"));
                alumno.setNacimiento(resultSet.getDate("nacimiento"));

                for (Estado estado : this.estados) {
                    String estadoNombre = estado.getNombre();
                    String estadoDb = resultSet.getString("estado");
                    if(estadoNombre.equals(estadoDb)) {
                        alumno.setEstado(estado);
                    }
                }

                alumnos.add(alumno);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public void save(Alumno alumno) {

    }

    @Override
    public void update(Alumno alumno, String[] params) {

    }
}
