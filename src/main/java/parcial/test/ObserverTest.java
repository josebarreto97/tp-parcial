package parcial.test;

import parcial.dao.AlumnoDao;
import parcial.dao.CursoDao;
import parcial.dao.EstablecimientoDao;
import parcial.modelo.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ObserverTest {
    public static void main(String[] args) throws SQLException {
        EstablecimientoDao establecimientoDao = new EstablecimientoDao();
        Establecimiento establecimiento = establecimientoDao.get(1);

        Alumno lautaro = establecimiento.getCursos().get(3).getAlumnos().get(0);
        Alumno jose = establecimiento.getCursos().get(0).getAlumnos().get(0);

        System.out.println(establecimiento.getNombre());

        AlumnoDao alumnoDao = new AlumnoDao();

        jose.promociona();
        jose.repite();
        jose.sale();

        alumnoDao.update(jose, new String[]{});

        System.out.println("Listo");
    }

    public static void crearAlumno() {
        Alumno alumno = new Alumno();
        BandejaNotificaciones bandeja = new BandejaNotificaciones();

        AlumnoCambioDeCursoObserver cursoObserver = new AlumnoCambioDeCursoObserver();
        cursoObserver.setBandeja(bandeja);

        alumno.setObservers(Arrays.asList(cursoObserver));

        Curso primeroA = new Curso();
        primeroA.setGrado("1");
        primeroA.setDivision("A");

        Curso primeroB = new Curso();
        primeroB.setGrado("1");
        primeroB.setDivision("B");

        alumno.setNombre("Lautaro");
        alumno.setApellido("Robles");

        alumno.setCurso(primeroA);
        alumno.setCurso(primeroB);
        alumno.setCurso(primeroA);

        System.out.println("Notificaciones, total " + bandeja.getNotificaciones().size());
        System.out.println("----------------------------------------");
        for (Notificacion notificacion : bandeja.getNotificaciones()) {
            System.out.println("---[ " + notificacion.getTitulo() + " ]---");
            System.out.println(notificacion.getCuerpo());
        }
    }
}
