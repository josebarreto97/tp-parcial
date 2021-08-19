package parcial;

import parcial.modelo.autocompletado.BuscadorGeoRef;
import parcial.modelo.autocompletado.Localidad;
import parcial.modelo.autocompletado.Municipio;
import parcial.modelo.autocompletado.Provincia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class TestAPI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\\s");
        menu();
    }

    private static void error(String expected, String actual) {
        System.out.println(ANSI_YELLOW + "Error sintáctico: se esperaba " + ANSI_RESET  + expected + ANSI_YELLOW + " pero se envio " + ANSI_RESET + actual);
    }

    private static void menu() {
        System.out.println(" --- MENU --- ");
        System.out.println("api provincias <= 'Listado de provincias'");
        System.out.println("api municipios [provincia_id] <= 'Listado de municipios'");
        System.out.println("api localidades [municipio_id] <= 'Listado de localidades'");

        while(scanner.hasNext()) {
            String token = scanner.next();

            if(token.equals("api"))
            {
                api();
            }
            else
            {
                error("api", token);
            }
        }
    }

    private static void api() {
        String token = scanner.next();

        if(token.equals("provincias")) {
            BuscadorGeoRef buscadorGeoRef = BuscadorGeoRef.getInstancia();

            List<Provincia> provincias = buscadorGeoRef.buscarProvincias();

            System.out.println(ANSI_CYAN + "Total provincias -> " + provincias.size() + ANSI_RESET);

            for (Provincia provincia : provincias) {
                System.out.println(ANSI_CYAN + "id -> " + provincia.getId() + " | nombre -> " + provincia.getNombre() + ANSI_RESET);
            }

        }
        else if(token.equals("municipios")) {
            token = scanner.next();

            BuscadorGeoRef buscadorGeoRef = BuscadorGeoRef.getInstancia();

            List<Municipio> municipios = buscadorGeoRef.buscarMunicipios(token);

            System.out.println(ANSI_CYAN + "Total municipios -> " + municipios.size() + ANSI_RESET);

            for (Municipio municipio : municipios) {
                System.out.println(ANSI_CYAN + "id -> " + municipio.getId() + " | nombre -> " + municipio.getNombre() + ANSI_RESET);
            }
        }
        else if(token.equals("localidades")) {
            token = scanner.next();

            BuscadorGeoRef buscadorGeoRef = BuscadorGeoRef.getInstancia();

            List<Localidad> localidades = buscadorGeoRef.buscarLocalidades(token);

            System.out.println(ANSI_CYAN + "Total localidades -> " + localidades.size() + ANSI_RESET);

            for (Localidad localidad : localidades) {
                System.out.println(ANSI_CYAN + "id -> " + localidad.getId() + " | nombre -> " + localidad.getNombre() + ANSI_RESET);
            }
        }
        else {
            error("provincias | municipios | localidades", token);
        }
    }
}
