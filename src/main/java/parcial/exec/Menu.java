package parcial.exec;

import java.util.Scanner;

public class Menu extends Command{

    @Override
    public void execute(Scanner scanner) {
        System.out.println(ANSI_GREEN+" --------- MENU --------- "+ANSI_RESET);
        System.out.println("Comandos disponibles:");
        System.out.println(ANSI_YELLOW+"api provincias"+ ANSI_GREEN +" -- Busca provincias'"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"api municipios [provincia_id]"+ ANSI_GREEN +" -- Busca municipios dada un id de provincia"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"api localidades [municipio_id]"+ ANSI_GREEN +" -- Busca localidades dada un id de municipio"+ANSI_RESET);

        while(scanner.hasNext()) {
            String token = scanner.next();

            if(token.equals("api"))
            {
                new API().execute(scanner);
            }
            else
            {
                error(scanner,"api", token);
            }
        }
    }
}
