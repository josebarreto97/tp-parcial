package parcial.test;

import parcial.modelo.*;

import java.util.Arrays;

public class ObserverTest {
    public static void main(String[] args) {

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
