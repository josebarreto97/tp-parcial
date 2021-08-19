package parcial.exec;

import parcial.modelo.Alumno;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlumnoApi extends Command{

    private List<Alumno> alumnos = new ArrayList<>();

    @Override
    public void execute(Scanner scanner) {
        String token = scanner.next();

        if(token.equals("crear")) {
            String nombre = scanner.next();
            String apellido = scanner.next();

            Alumno alumno = new Alumno();
            alumno.setNombre(nombre);
            alumno.setApellido(apellido);

            this.alumnos.add(alumno);

            System.out.println(ANSI_CYAN + "Creado nuevo alumno: " +
                    "id -> " + (this.alumnos.size() - 1) + " | " +
                    "nombre -> " + alumno.getNombre() + " | " +
                    "apellido -> " + alumno.getApellido() + ANSI_RESET);

        }
        else if(token.equals("ver"))
        {
            System.out.println(ANSI_CYAN + "Listado de Alumnos, total -> " + this.alumnos.size() + ANSI_RESET);
            int i = 0;
            for (Alumno alumno : this.alumnos) {
                System.out.println(ANSI_CYAN +
                        "id -> " + i + " | " +
                        "nombre -> " + alumno.getNombre() + " | " +
                        "apellido -> " + alumno.getApellido() + ANSI_RESET);
                i++;
            }
        }
        else if(token.equals("actualizar"))
        {
            int id = Integer.parseInt(scanner.next());
            String nuevoNombre = scanner.next();
            String nuevoApellido = scanner.next();

            boolean inBounds = id >=0 && id < this.alumnos.size();

            if(!inBounds) {
                System.out.println(ANSI_YELLOW + "No se encuentra el alumno con id " + id + ANSI_RESET);
            }
            else {
                Alumno alumno = this.alumnos.get(id);
                alumno.setNombre(nuevoNombre);
                alumno.setApellido(nuevoApellido);

                System.out.println(ANSI_CYAN + "Actualizado el alumno: " +
                        "id -> " + id + " | " +
                        "nombre -> " + alumno.getNombre() + " | " +
                        "apellido -> " + alumno.getApellido() + ANSI_RESET);
            }
        }
    }
}
