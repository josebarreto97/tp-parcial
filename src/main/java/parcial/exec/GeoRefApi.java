package parcial.exec;

import parcial.modelo.autocompletado.BuscadorGeoRef;
import parcial.modelo.autocompletado.Localidad;
import parcial.modelo.autocompletado.Municipio;
import parcial.modelo.autocompletado.Provincia;

import java.util.List;
import java.util.Scanner;

public class GeoRefApi extends Command{
    @Override
    public void execute(Scanner scanner) {
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
            error(scanner,"provincias | municipios | localidades", token);
        }
    }
}
