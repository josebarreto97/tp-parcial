package parcial.demo;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class Menu extends Command{

    @Override
    public void execute(Scanner scanner) {
        System.out.println(ANSI_GREEN+" --------- MENU --------- "+ANSI_RESET);
        System.out.println("Comandos disponibles:");
        System.out.println(ANSI_YELLOW+"api provincias"+ ANSI_GREEN +" -- Busca provincias"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"api municipios [provincia_id]"+ ANSI_GREEN +" -- Busca municipios dada un id de provincia"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"api localidades [municipio_id]"+ ANSI_GREEN +" -- Busca localidades dada un id de municipio"+ANSI_RESET);
        System.out.println("");
        System.out.println(ANSI_YELLOW+"db get establecimiento [establecimiento_id]"+ ANSI_GREEN +" -- Obtener un establecimiento de la base de datos"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"db get alumno [alumno_id]"+ ANSI_GREEN +" -- Obtener un alumno de la base de datos"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"db get notificaciones [curso_id]"+ ANSI_GREEN +" -- Obtener las notificaciones del educador de un curso"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"db insert alumno [nombre] [apellido] [estado] [curso_id]"+ ANSI_GREEN +" -- Inserta un nuevo alumno en la base de datos"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"db promover alumno [alumno_id]"+ ANSI_GREEN +" -- Promover alumno"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"db repetir alumno [alumno_id]"+ ANSI_GREEN +" -- Hacer que un alumno repita"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"db sacar alumno [alumno_id]"+ ANSI_GREEN +" -- Hacer que un alumno salga"+ANSI_RESET);

        while(scanner.hasNext()) {
            String token = scanner.next();

            if(token.equals("api"))
            {
                new GeoRefApi().execute(scanner);
            }
            else if(token.equals("db"))
            {
                new DataBase().execute(scanner);
            }
            else
            {
                error(scanner,"api", token);
            }

            System.out.println(ANSI_GREEN+" --------- MENU --------- "+ANSI_RESET);
            System.out.println("Comandos disponibles:");
            System.out.println(ANSI_YELLOW+"api provincias"+ ANSI_GREEN +" -- Busca provincias"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"api municipios [provincia_id]"+ ANSI_GREEN +" -- Busca municipios dada un id de provincia"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"api localidades [municipio_id]"+ ANSI_GREEN +" -- Busca localidades dada un id de municipio"+ANSI_RESET);
            System.out.println("");
            System.out.println(ANSI_YELLOW+"db get establecimiento [establecimiento_id]"+ ANSI_GREEN +" -- Obtener un establecimiento de la base de datos"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"db get alumno [alumno_id]"+ ANSI_GREEN +" -- Obtener un alumno de la base de datos"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"db get notificaciones [curso_id]"+ ANSI_GREEN +" -- Obtener las notificaciones del educador de un curso"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"db insert alumno [nombre] [apellido] [estado] [curso_id]"+ ANSI_GREEN +" -- Inserta un nuevo alumno en la base de datos"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"db promover alumno [alumno_id]"+ ANSI_GREEN +" -- Promover alumno"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"db repetir alumno [alumno_id]"+ ANSI_GREEN +" -- Hacer que un alumno repita"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"db sacar alumno [alumno_id]"+ ANSI_GREEN +" -- Hacer que un alumno salga"+ANSI_RESET);
        }
    }
}
