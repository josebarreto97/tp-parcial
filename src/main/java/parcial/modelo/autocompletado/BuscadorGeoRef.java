package parcial.modelo.autocompletado;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

public class BuscadorGeoRef {
    private static BuscadorGeoRef instancia;
    private WebClient webClient;

    private BuscadorGeoRef() {
        this.webClient = WebClient.builder()
                .baseUrl("https://apis.datos.gob.ar/georef/api")
                .build();
    }

    public static BuscadorGeoRef getInstancia() {
        if(instancia == null) {
            instancia = new BuscadorGeoRef();
        }
        return instancia;
    }

    //TODO buscar en GeoRef
    public List<Provincia> buscarProvincias() {
        RespuestaProvincias respuesta = this.webClient.get()
                .uri("/provincias?campos=id,nombre")
                .retrieve()
                .bodyToMono(RespuestaProvincias.class)
                .block();

        if(respuesta != null && respuesta.getCantidad() > 0) {
            return respuesta.getProvincias();
        }
        else {
            return new ArrayList<>();
        }

    }

    public List<Municipio> buscarMunicipios(String provincia) {
        RespuestaMunicipios respuesta = this.webClient.get()
                .uri("/municipios?campos=id,nombre&provincia="+provincia)
                .retrieve()
                .bodyToMono(RespuestaMunicipios.class)
                .block();

        if(respuesta != null && respuesta.getCantidad() > 0) {
            return respuesta.getMunicipios();
        }
        else {
            return new ArrayList<>();
        }
    }

    public List<Localidad> buscarLocalidades(String municipio) {
        RespuestaLocalidades respuesta = this.webClient.get()
                .uri("/localidades?campos=id,nombre&municipio="+municipio)
                .retrieve()
                .bodyToMono(RespuestaLocalidades.class)
                .block();

        if(respuesta != null && respuesta.getCantidad() > 0) {
            return respuesta.getLocalidades();
        }
        else {
            return new ArrayList<>();
        }
    }
}
