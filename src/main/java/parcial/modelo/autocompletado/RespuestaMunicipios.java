package parcial.modelo.autocompletado;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RespuestaMunicipios {
    private int cantidad;
    private int inicio;
    private int total;
    private List<Municipio> municipios;
}
