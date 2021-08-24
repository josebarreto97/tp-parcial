package parcial.dao;

import parcial.modelo.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDao implements Dao<Curso>{
    @Override
    public Curso get(long id) {
        Connection connection = DB.getInstancia().getConnection();

        Curso curso = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cursos WHERE id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            AlumnoDao alumnoDao = new AlumnoDao();
            MaestroDao maestroDao = new MaestroDao();

            while(result.next()) {
                curso = new Curso();
                curso.setId(result.getLong("id"));
                curso.setGrado(result.getString("grado"));
                curso.setDivision(result.getString("division"));
                curso.setEgresados(result.getBoolean("egresados"));
                curso.setEducador(maestroDao.getFromCurso(curso));
                curso.setAlumnos(alumnoDao.getFromCurso(curso));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return curso;
    }

    public List<Curso> getFromEstablecimiento(long idEstablecimiento) {
        Connection connection = DB.getInstancia().getConnection();

        List<Curso> cursos = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Cursos WHERE establecimiento = ?");
            statement.setLong(1, idEstablecimiento);
            ResultSet result = statement.executeQuery();

            AlumnoDao alumnoDao = new AlumnoDao();
            MaestroDao maestroDao = new MaestroDao();

            while(result.next()) {
                Curso curso = new Curso();
                curso.setId(result.getLong("id"));
                curso.setGrado(result.getString("grado"));
                curso.setDivision(result.getString("division"));
                curso.setEgresados(result.getBoolean("egresados"));
                curso.setEducador(maestroDao.getFromCurso(curso));
                curso.setAlumnos(alumnoDao.getFromCurso(curso));
                cursos.add(curso);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return cursos;
    }

    @Override
    public void save(Curso curso) {

    }

    @Override
    public void update(Curso curso) {

    }
}
