package parcial.dao;

import parcial.modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class MaestroDao implements Dao<Maestro>{

    @Override
    public Maestro get(long id) {
        return null;
    }

    public Maestro getFromCurso(Curso curso) {

        Connection connection = DB.getInstancia().getConnection();

        Maestro maestro = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Maestros WHERE curso = ?");
            statement.setLong(1, curso.getId());
            ResultSet resultSet = statement.executeQuery();

            AyudanteDao ayudanteDao = new AyudanteDao();

            BandejaNotificacionesDao bandejaNotificacionesDao = new BandejaNotificacionesDao();

            while (resultSet.next()) {
                maestro = new Maestro();
                maestro.setId(resultSet.getLong("id"));
                maestro.setNombre(resultSet.getString("nombre"));
                maestro.setAyudantes(ayudanteDao
                        .getAyudanteFromMaestro(maestro)
                        .stream()
                        .map(ayudante -> (Educador) ayudante)
                        .collect(Collectors.toList())
                );
                maestro.setBandejaNotificaciones(bandejaNotificacionesDao.get(resultSet.getLong("bandejaDeNotificaciones")));
                maestro.setCurso(curso);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return maestro;
    }

    @Override
    public void save(Maestro maestro) {

    }

    @Override
    public void update(Maestro maestro) {

    }
}
