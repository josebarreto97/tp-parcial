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
    public Alumno get(long id) {
        Connection connection = DB.getInstancia().getConnection();
        Alumno alumno = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Alumnos WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            CursoDao cursoDao = new CursoDao();

            while(resultSet.next()) {
                alumno = new Alumno();
                getAlumnoFromResult(resultSet, alumno);
                alumno.setCurso(cursoDao.get(resultSet.getLong("curso")));

                for (Estado estado : this.estados) {
                    String estadoNombre = estado.getNombre();
                    String estadoDb = resultSet.getString("estado");
                    if(estadoNombre.equals(estadoDb)) {
                        alumno.setEstado(estado);
                    }
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return alumno;
    }

    public List<Alumno> getFromCurso(Curso curso) {
        Connection connection = DB.getInstancia().getConnection();
        List<Alumno> alumnos = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Alumnos WHERE curso = ?");
            statement.setLong(1, curso.getId());
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Alumno alumno = new Alumno();
                getAlumnoFromResult(resultSet, alumno);
                alumno.setCurso(curso);

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

    private void getAlumnoFromResult(ResultSet resultSet, Alumno alumno) throws SQLException {
        alumno.setId(resultSet.getLong("id"));
        alumno.setNombre(resultSet.getString("nombre"));
        alumno.setApellido(resultSet.getString("apellido"));
        alumno.setDni(resultSet.getString("dni"));
        alumno.setDireccion(resultSet.getString("direccion"));
        alumno.setLocalidad(resultSet.getString("municipio"));
        alumno.setMunicipio(resultSet.getString("localidad"));
        alumno.setProvincia(resultSet.getString("provincia"));
        alumno.setNacimiento(resultSet.getDate("nacimiento"));
    }

    @Override
    public void save(Alumno alumno) {
        try {
            Connection connection = DB.getInstancia().getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Alumnos");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Alumno alumno, String[] params) {

    }
}
