package parcial.dao;

import parcial.modelo.BandejaNotificaciones;
import parcial.modelo.Educador;
import parcial.modelo.Maestro;
import parcial.modelo.Notificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class BandejaNotificacionesDao implements Dao<BandejaNotificaciones>{
    @Override
    public BandejaNotificaciones get(long id) {
        Connection connection = DB.getInstancia().getConnection();

        BandejaNotificaciones bandejaNotificaciones = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM BandejaDeNotificaciones WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            NotificacionDao notificacionDao = new NotificacionDao();

            while (resultSet.next()) {
                bandejaNotificaciones = new BandejaNotificaciones();
                bandejaNotificaciones.setId(resultSet.getLong("id"));
                bandejaNotificaciones.setNotificaciones(notificacionDao.getFromBandejaNotificaciones(bandejaNotificaciones));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return bandejaNotificaciones;
    }

    @Override
    public void save(BandejaNotificaciones bandejaNotificaciones) {

    }

    @Override
    public void update(BandejaNotificaciones bandejaNotificaciones, String[] params) {

    }
}
