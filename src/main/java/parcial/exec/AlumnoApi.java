package parcial.exec;

import parcial.modelo.Alumno;

import java.util.Scanner;

public class AlumnoApi extends Command{
    @Override
    public void execute(Scanner scanner) {
        String token = scanner.next();

        if(token.equals("crear")) {
            String nombre = scanner.next();
            String apellido = scanner.next();

            Alumno alumno = new Alumno();
            alumno.setNombre(nombre);
            alumno.setApellido(apellido);

            System.out.println("Creado alumno: " + nombre + " " + apellido);
        }
    }
}
