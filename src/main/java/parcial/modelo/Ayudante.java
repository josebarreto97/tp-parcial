package parcial.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Ayudante extends Educador{

    @Override
    public String getNombresDeAyudantes() {
        return this.getNombre();
    }
}
