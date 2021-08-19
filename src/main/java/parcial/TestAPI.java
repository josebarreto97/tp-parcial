package parcial;

import parcial.modelo.autocompletado.BuscadorGeoRef;
import parcial.modelo.autocompletado.Provincia;

import java.io.IOException;
import java.util.List;

public class TestAPI {
    public static void main(String[] args) throws IOException {
        BuscadorGeoRef buscadorGeoRef = BuscadorGeoRef.getInstancia();

        List<Provincia> provincias = buscadorGeoRef.buscarProvincias();

        System.out.println("Cantidad de provincias -> "+provincias.size());

        provincias.forEach(provincia -> {
            System.out.println("id -> " + provincia.getId() + " | nombre -> " + provincia.getNombre());
        });

        int input = System.in.read();
        System.out.println(input);
    }
}
