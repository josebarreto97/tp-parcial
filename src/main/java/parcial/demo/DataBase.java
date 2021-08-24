package parcial.demo;

import parcial.dao.*;
import parcial.modelo.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataBase extends Command{

    EstablecimientoDao establecimientoDao = new EstablecimientoDao();
    CursoDao cursoDao = new CursoDao();
    AlumnoDao alumnoDao = new AlumnoDao();
    MaestroDao maestroDao = new MaestroDao();

    private List<Estado> estados = Arrays.asList(
            new Inscripto(), new ReInscripto(), new Promovido(),
            new Repitente(), new Egresado(), new Salido()
    );

    @Override
    public void execute(Scanner scanner) {
        String token = scanner.next();

        if(token.equals("get")) {
            token = scanner.next();
            if(token.equals("establecimiento")) {
                token = scanner.next();
                // db get establecimiento [id]
                Establecimiento establecimiento = establecimientoDao.get(Long.parseLong(token));

                if(establecimiento != null) {
                    System.out.println(ANSI_CYAN + "Se encontró el establecimiento");
                    System.out.println("------------------------------------------------------");
                    System.out.println("Nombre -> " + establecimiento.getNombre());
                    System.out.println("Direccion -> " + establecimiento.getDireccion());
                    System.out.println("Provincia -> " + establecimiento.getProvincia());
                    System.out.println("Municipio -> " + establecimiento.getMunicipio());
                    System.out.println("Localidad -> " + establecimiento.getLocalidad());
                    System.out.println("Cursos -> Size (" + establecimiento.getCursos().size() + "){");
                    for (Curso curso : establecimiento.getCursos()) {
                        Educador educador = curso.getEducador();
                        System.out.println("{");
                        System.out.println("    Id -> " + curso.getId());
                        System.out.println("    Curso -> " + curso.getGrado() + "°" + curso.getDivision());
                        System.out.println("    Educador -> {");
                        if(educador != null) {
                            System.out.println("        Id -> " + educador.getId());
                            System.out.println("        Nombre -> " + educador.getNombre());
                        }
                        System.out.println("    }");
                        System.out.println("    Alumnos -> Size (" + curso.getAlumnos().size() + ") {");
                        for (Alumno alumno : curso.getAlumnos()) {
                            System.out.println("        {");
                            System.out.println("            Id -> " + alumno.getId());
                            System.out.println("            Nombre -> " + alumno.getNombre());
                            System.out.println("            Apellido -> " + alumno.getApellido());
                            System.out.println("            Dni -> " + alumno.getDni());
                            System.out.println("            Estado -> " + alumno.getEstado().getNombre());
                            System.out.println("        }");
                        }
                        System.out.println("    }");
                        System.out.println("}");
                    }
                } else {
                    System.out.println(ANSI_RED + "No se encontró ningun establecimiento con id " + ANSI_RESET + token);
                }
            }
            else if (token.equals("alumno")) {
                token = scanner.next();

                Alumno alumno = alumnoDao.get(Long.parseLong(token));

                if(alumno != null) {
                    System.out.println(ANSI_CYAN + "Se encontró el alumno");
                    System.out.println("------------------------------------------------------");
                    System.out.println("Nombre -> " + alumno.getNombre());
                    System.out.println("Apellido -> " + alumno.getApellido());
                    System.out.println("Dni -> " + alumno.getDni());
                    System.out.println("Direccion -> " + alumno.getDireccion());
                    System.out.println("Provincia -> " + alumno.getProvincia());
                    System.out.println("Municipio -> " + alumno.getMunicipio());
                    System.out.println("Localidad -> " + alumno.getLocalidad());
                    System.out.println("Estado -> " + alumno.getEstado().getNombre());
                    System.out.println("Curso -> {");
                    System.out.println("    Id -> " + alumno.getCurso().getId());
                    System.out.println("    Curso -> " + alumno.getCurso().getGrado() + "°" + alumno.getCurso().getDivision());
                    System.out.println("    Educador -> {");
                    Educador educador = alumno.getCurso().getEducador();
                    if(educador != null) {
                        System.out.println("        Id -> " + educador.getId());
                        System.out.println("        Nombre -> " + educador.getNombre());
                    }
                    System.out.println("    }");
                    System.out.println("}");
                } else {
                    System.out.println(ANSI_RED + "No se encontró ningun alumno con id " + ANSI_RESET + token);
                }
            }
            else if (token.equals("notificaciones")) {
                // id de curso
                token = scanner.next();
                Curso curso = cursoDao.get(Long.parseLong(token));

                if(curso != null) {
                    Maestro maestro = maestroDao.getFromCurso(curso);
                    if(maestro != null) {
                        BandejaNotificaciones bandejaNotificaciones = maestro.getBandejaNotificaciones();
                        if(bandejaNotificaciones != null) {
                            System.out.println(ANSI_CYAN + "Mostrando notificaciones");
                            for (Notificacion notificacion : bandejaNotificaciones.getNotificaciones()) {
                                System.out.println("{");
                                System.out.println("    Id -> " + notificacion.getId());
                                System.out.println("    Titulo -> " + notificacion.getTitulo());
                                System.out.println("    Cuerpo -> " + notificacion.getCuerpo());
                                System.out.println("}");
                            }
                        } else {
                            System.out.println(ANSI_RED + "No se encontró la bandeja de notificaciones " + ANSI_RESET);
                        }
                    } else {
                        System.out.println(ANSI_RED + "No se encontró ningun educador en el curso " + ANSI_RESET + curso.getGrado() + "°" + curso.getDivision());
                    }
                } else {
                    System.out.println(ANSI_RED + "No se encontró curso con id " + ANSI_RESET + token);
                }

            }
            else {
                error(scanner, "establecimiento | alumno | notificaciones", token);
            }
        }
        else if(token.equals("insert")) {
            token = scanner.next();
            if(token.equals("alumno")) {
                Alumno alumno = new Alumno();
                alumno.setNombre(scanner.next());
                alumno.setApellido(scanner.next());
                String estadoString = scanner.next();
                for (Estado estado : this.estados) {
                    String estadoNombre = estado.getNombre();
                    if(estadoNombre.equals(estadoString)) {
                        alumno.setEstado(estado);
                    }
                }
                Long idCurso = Long.parseLong(scanner.next());
                Curso curso = cursoDao.get(idCurso);
                alumno.setCurso(curso);

                if(curso.getEducador() != null) {
                    AlumnoCambioDeCursoObserver observer1 = new AlumnoCambioDeCursoObserver();
                    AlumnoRepitenteObserver observer2 = new AlumnoRepitenteObserver();
                    AlumnoSalidoObserver observer3 = new AlumnoSalidoObserver();

                    observer1.setBandeja(curso.getEducador().getBandejaNotificaciones());
                    observer2.setBandeja(curso.getEducador().getBandejaNotificaciones());
                    observer3.setBandeja(curso.getEducador().getBandejaNotificaciones());

                    alumno.setObservers(Arrays.asList(observer1, observer2, observer3));
                }

                alumnoDao.save(alumno);
                System.out.println(ANSI_PURPLE + "Alumno insertado correctamente" + ANSI_RESET);
            }
            else {
                error(scanner, "alumno", token);
            }
        }
        else if(token.equals("promover")) {
            token = scanner.next();
            if(token.equals("alumno")) {
                long idAlumno = Long.parseLong(scanner.next());
                Alumno alumno = alumnoDao.get(idAlumno);
                alumno.promociona();
                alumnoDao.update(alumno);
                System.out.println(ANSI_PURPLE + "Alumno ha promovido correctamente" + ANSI_RESET);
            }
            else {
                error(scanner, "alumno", token);
            }
        }
        else if(token.equals("repetir")) {
            token = scanner.next();
            if(token.equals("alumno")) {
                long idAlumno = Long.parseLong(scanner.next());
                Alumno alumno = alumnoDao.get(idAlumno);
                alumno.repite();
                alumnoDao.update(alumno);
                System.out.println(ANSI_PURPLE + "Alumno ha repetido correctamente" + ANSI_RESET);
            }
            else {
                error(scanner, "alumno", token);
            }
        }
        else if(token.equals("sacar")) {
            token = scanner.next();
            if(token.equals("alumno")) {
                long idAlumno = Long.parseLong(scanner.next());
                Alumno alumno = alumnoDao.get(idAlumno);
                alumno.sale();
                alumnoDao.update(alumno);
                System.out.println(ANSI_PURPLE + "Alumno ha salido correctamente" + ANSI_RESET);
            }
            else {
                error(scanner, "alumno", token);
            }
        }
        else {
            error(scanner, "get | insert | promover | repetir | sacar", token);
        }
    }
}
