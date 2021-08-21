package parcial.test;

import parcial.dao.AlumnoDao;
import parcial.dao.EstablecimientoDao;
import parcial.modelo.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

public class ObserverTest {
    public static void main(String[] args) throws SQLException {
        EstablecimientoDao establecimientoDao = new EstablecimientoDao();
        Establecimiento establecimiento = establecimientoDao.get(1);

        Curso curso1A = establecimiento.getCursos().get(0);
        Curso curso6A = establecimiento.getCursos().get(3);

        Alumno lautaro = curso6A.getAlumnos().get(0);
        Alumno jose = curso1A.getAlumnos().get(0);

        System.out.println(establecimiento.getNombre());

        AlumnoDao alumnoDao = new AlumnoDao();

        jose.promociona();
        jose.repite();
        jose.sale();

        Alumno tomi = new Alumno();
        tomi.setNombre("Tomi");
        tomi.setApellido("Gabutti");
        tomi.setNacimiento(new Date());
        tomi.setEstado(new Inscripto());
        tomi.setCurso(curso1A);

        AlumnoRepitenteObserver observer2 = new AlumnoRepitenteObserver();
        AlumnoCambioDeCursoObserver observer1 = new AlumnoCambioDeCursoObserver();
        AlumnoSalidoObserver observer3 = new AlumnoSalidoObserver();

        observer1.setBandeja(curso1A.getEducador().getBandejaNotificaciones());
        observer2.setBandeja(curso1A.getEducador().getBandejaNotificaciones());
        observer3.setBandeja(curso1A.getEducador().getBandejaNotificaciones());

        tomi.setObservers(Arrays.asList(observer1, observer2, observer3));

        alumnoDao.update(jose);
        alumnoDao.save(tomi);

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
