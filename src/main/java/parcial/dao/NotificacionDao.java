package parcial.dao;

import parcial.modelo.BandejaNotificaciones;
import parcial.modelo.Notificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificacionDao implements Dao<Notificacion>{
    @Override
    public Notificacion get(long id) {
        return null;
    }

    public List<Notificacion> getFromBandejaNotificaciones(BandejaNotificaciones bandejaNotificaciones) {
        Connection connection = DB.getInstancia().getConnection();

        List<Notificacion> notificaciones = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Notificaciones WHERE bandejaDeNotificaciones = ?");
            statement.setLong(1, bandejaNotificaciones.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Notificacion notificacion = new Notificacion();
                notificacion.setId(resultSet.getLong("id"));
                notificacion.setCuerpo(resultSet.getString("cuerpo"));
                notificacion.setTitulo(resultSet.getString("titulo"));
                notificaciones.add(notificacion);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return notificaciones;
    }

    @Override
    public void save(Notificacion notificacion) {

    }

    public void saveInto(Notificacion notificacion, BandejaNotificaciones bandejaNotificaciones) {
        Connection connection = DB.getInstancia().getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Notificaciones(titulo, cuerpo, bandejaDeNotificaciones) VALUES(?,?,?)");
            statement.setString(1, notificacion.getTitulo());
            statement.setString(2, notificacion.getCuerpo());
            statement.setLong(3, bandejaNotificaciones.getId());
            statement.executeQuery();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void update(Notificacion notificacion) {

    }
}
