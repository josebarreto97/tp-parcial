package parcial.dao;

import parcial.modelo.Ayudante;
import parcial.modelo.Maestro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AyudanteDao implements Dao<Ayudante>{
    @Override
    public Ayudante get(long id) {
        Connection connection = DB.getInstancia().getConnection();

        Ayudante ayudante = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Ayudantes WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            BandejaNotificacionesDao bandejaNotificacionesDao = new BandejaNotificacionesDao();
            CursoDao cursoDao = new CursoDao();

            while (resultSet.next()) {
                ayudante = new Ayudante();
                ayudante.setId(resultSet.getLong("id"));
                ayudante.setNombre(resultSet.getString("nombre"));
                ayudante.setBandejaNotificaciones(bandejaNotificacionesDao.get(resultSet.getLong("bandejaDeNotificaciones")));
                ayudante.setCurso(cursoDao.get(resultSet.getLong("curso")));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return ayudante;
    }

    public List<Ayudante> getAyudanteFromMaestro(Maestro maestro) {
        Connection connection = DB.getInstancia().getConnection();

        List<Ayudante> ayudantes = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Ayudantes WHERE maestro = ?");
            statement.setLong(1, maestro.getId());
            ResultSet resultSet = statement.executeQuery();

            BandejaNotificacionesDao bandejaNotificacionesDao = new BandejaNotificacionesDao();
            CursoDao cursoDao = new CursoDao();

            while (resultSet.next()) {
                Ayudante ayudante = new Ayudante();
                ayudante.setId(resultSet.getLong("id"));
                ayudante.setNombre(resultSet.getString("nombre"));
                ayudante.setBandejaNotificaciones(bandejaNotificacionesDao.get(resultSet.getLong("bandejaDeNotificaciones")));
                ayudante.setCurso(cursoDao.get(resultSet.getLong("curso")));

                ayudantes.add(ayudante);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return ayudantes;
    }

    @Override
    public void save(Ayudante ayudante) {

    }

    @Override
    public void update(Ayudante ayudante) {

    }
}
