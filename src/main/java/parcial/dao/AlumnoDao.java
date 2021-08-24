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
                Curso curso = cursoDao.get(resultSet.getLong("curso"));
                alumno.setCurso(curso);

                if(curso.getEducador() != null) {
                    AlumnoCambioDeCursoObserver observer1 = new AlumnoCambioDeCursoObserver();
                    AlumnoRepitenteObserver observer2 = new AlumnoRepitenteObserver();
                    AlumnoSalidoObserver observer3 = new AlumnoSalidoObserver();

                    observer1.setBandeja(curso.getEducador().getBandejaNotificaciones());
                    observer2.setBandeja(curso.getEducador().getBandejaNotificaciones());
                    observer3.setBandeja(curso.getEducador().getBandejaNotificaciones());

                    alumno.setObservers(Arrays.asList(observer1, observer2, observer3));
                }

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

                if(curso.getEducador() != null) {
                    AlumnoCambioDeCursoObserver observer1 = new AlumnoCambioDeCursoObserver();
                    AlumnoRepitenteObserver observer2 = new AlumnoRepitenteObserver();
                    AlumnoSalidoObserver observer3 = new AlumnoSalidoObserver();

                    observer1.setBandeja(curso.getEducador().getBandejaNotificaciones());
                    observer2.setBandeja(curso.getEducador().getBandejaNotificaciones());
                    observer3.setBandeja(curso.getEducador().getBandejaNotificaciones());

                    alumno.setObservers(Arrays.asList(observer1, observer2, observer3));
                }

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
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Alumnos" +
                    "(nombre, apellido, dni, nacimiento, provincia, municipio, localidad, direccion, estado, curso)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)");

            statement.setString(1, alumno.getNombre());
            statement.setString(2, alumno.getApellido());
            statement.setString(3, alumno.getDni());
            if(alumno.getNacimiento() != null)
                statement.setDate(4, new java.sql.Date(alumno.getNacimiento().getTime()));
            else
                statement.setDate(4, null);
            statement.setString(5, alumno.getProvincia());
            statement.setString(6, alumno.getMunicipio());
            statement.setString(7, alumno.getLocalidad());
            statement.setString(8, alumno.getDireccion());
            statement.setString(9, alumno.getEstado().getNombre());
            statement.setLong(10, alumno.getCurso().getId());

            statement.executeQuery();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Alumno alumno) {
        Connection connection = DB.getInstancia().getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Alumnos SET nombre = ?, apellido = ?, dni = ?, estado = ?, " +
                        "direccion = ?, localidad = ?, municipio = ?, provincia = ?, nacimiento = ?, curso = ? " +
                        "WHERE id = ?");

            statement.setString(1, alumno.getNombre());
            statement.setString(2, alumno.getApellido());
            statement.setString(3, alumno.getDni());
            statement.setString(4, alumno.getEstado().getNombre());
            statement.setString(5, alumno.getDireccion());
            statement.setString(7, alumno.getLocalidad());
            statement.setString(6, alumno.getMunicipio());
            statement.setString(8, alumno.getProvincia());
            if(alumno.getNacimiento() != null)
                statement.setDate(9, new java.sql.Date(alumno.getNacimiento().getTime()));
            else
                statement.setDate(9, null);
            statement.setLong(10, alumno.getCurso().getId());
            statement.setLong(11, alumno.getId());

            statement.executeQuery();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
