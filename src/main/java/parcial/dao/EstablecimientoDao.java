package parcial.dao;

import parcial.modelo.Establecimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstablecimientoDao implements Dao<Establecimiento>{
    @Override
    public Establecimiento get(long id) {
        Connection connection = DB.getInstancia().getConnection();

        Establecimiento establecimiento = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Establecimientos WHERE id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            CursoDao cursoDao = new CursoDao();

            while(result.next()) {
                establecimiento = new Establecimiento();
                establecimiento.setId(result.getLong("id"));
                establecimiento.setNombre(result.getString("nombre"));
                establecimiento.setDireccion(result.getString("direccion"));
                establecimiento.setProvincia(result.getString("provincia"));
                establecimiento.setMunicipio(result.getString("municipio"));
                establecimiento.setLocalidad(result.getString("localidad"));
                establecimiento.setTelefono(result.getString("telefono"));
                establecimiento.setEmail(result.getString("email"));

                establecimiento.setCursos(cursoDao.getFromEstablecimiento(establecimiento.getId()));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return establecimiento;
    }

    @Override
    public void save(Establecimiento establecimiento) {

    }

    @Override
    public void update(Establecimiento establecimiento) {

    }
}
