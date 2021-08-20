package parcial.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static DB instancia;
    private Connection connection;

    private DB() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mariadb://tp.conlatoso.com:3306/tp?user=tp&password=tp123456789");
        }
        catch (SQLException exception) {
            System.out.println("Hubo un error al conectarse a la DB");
            System.out.println(exception.getMessage());
        }
    }

    public static DB getInstancia() {
        if(instancia == null) {
            instancia = new DB();
        }
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }
}
