import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Juego game = new Juego(true);
        int opc;

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Iniciar nuevo juego");
            System.out.println("2. Cargar juego guardado");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opc = sc.nextInt();
            sc.nextLine();

            String filename;
            switch (opc) {
                case 1:
                    File archivo;
                    do {
                        System.out.print("Ingrese el nombre del archivo con el que se guardara la partida: ");
                        filename = sc.nextLine();
                        archivo = new File(filename);
                        if (archivo.exists()) {
                            System.out.println("El archivo ya existe");
                        }
                    }while(archivo.exists());
                    game.jugar(filename);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del archivo a cargar: ");
                    filename = sc.nextLine();

                    game.cargarJuego(filename);
                    game.jugar(filename);
                    break;

                case 3:
                    System.out.println("Saliendo del juego.");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opc != 3);

        sc.close();
    }
}
