import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jugador {
    protected String nombre;
    protected int id;
    protected Barcos barcos;
    protected Oceano oceano;

    public Jugador(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.barcos = new Barcos();
        this.barcos.generarBarcos();
        this.oceano = new Oceano();
    }
    public void borrarBarcos() {
        barcos.borrarBarcos();
    }
    public void crearBarcos() {
        this.barcos.generarBarcos();
    }
    public boolean disparar(Jugador enemigo){
        int x = 0,y=0;
//        System.out.println("Barco bot: ");
//        System.out.println(oceano.showPrivateOcean());
        Oceano oceano = enemigo.getOceano();
        Scanner sc = new Scanner(System.in);
        do{
            System.out.print("Escriba la coordenada x(1-10): ");
            x = sc.nextInt() - 1;
            System.out.print("Escriba la coordenada y(A-J/a-j): ");
            char c = sc.next().charAt(0);

            if(c > 64 && c < 91){
                y = c - 65;
            }
            if(c > 96 && c < 123){
                y = c - 97;
            }
        }while(x > 9 || x < 0 || y > 9 || y < 0);
        String target = oceano.getOceano()[x][y];
        if(!oceano.manejarDisparo(x, y)){
            System.out.println("Fallas te el tiro...\n");
            return false;
        }

        if(!oceano.buscarRemanentesDelBarco(target)){
            for(Barco barcoDerribado : enemigo.getBarcos().barcos){
                if(barcoDerribado.getId().equals(target)){
                    System.out.println("dentro");
                    barcoDerribado.setDerribado(true);
                    oceano.derribarBarco(barcoDerribado);
                }

            }
        }

        System.out.println("Acertaste a un barco!.\n");
        return true;
    }

    public void acomodarBarcosAleatorio() {
        Random rand = new Random();
        int x, y, voltear;

        for (Barco barco : barcos.getBarcos()) { // Iterar sobre cada barco
            voltear = rand.nextInt(2);
            if (voltear == 0) {
                barco.voltear(); // Voltear el barco aleatoriamente
            }

            do {
                // Asignar coordenadas y probar colocación
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                barco.setXCord(x);
                barco.setYCord(y);
            } while (!oceano.colocarBarco(barco, x, y)); // Repetir hasta que el barco se coloque exitosamente
        }
    }

    public void acomodarBarcos() {
        int x, y, voltear;
        Scanner sc = new Scanner(System.in);

        for (Barco barco : barcos.getBarcos()) { // Iterar sobre cada barco
            System.out.print(oceano); // Mostrar el estado del tablero

            // Mostrar información del barco actual
            if (barco.isEsVertical()) {
                System.out.println(String.format("Barco %dx%d", barco.getWidth(), barco.getHeight()));
                System.out.println(barco);
            } else {
                System.out.println(String.format("Barco %dx%d", barco.getHeight(), barco.getWidth()));
                System.out.println(barco);
            }

            // Obtener coordenadas del usuario
            do {
                System.out.print("Escriba la coordenada x(1-10): ");
                x = sc.nextInt() - 1;
                System.out.print("Escriba la coordenada y(A-J/a-j): ");
                char c = sc.next().charAt(0);
                y = (c > 64 && c < 91) ? c - 65 : c - 97;
                System.out.println("y: " + y);
                barco.setXCord(x);
                barco.setYCord(y); // Asignar coordenadas antes de la colocación
            } while (x > 9 || x < 0 || y > 9 || y < 0);

            // Permitir girar el barco si el usuario lo desea
            do {
                System.out.print("1) Girar\n2) Conservar\nSeleccione: ");
                voltear = sc.nextInt() - 1;
            } while (voltear != 0 && voltear != 1);

            if (voltear == 0) {
                barco.voltear();
            }

            // Intentar colocar el barco en el tablero
            if (!oceano.colocarBarco(barco, x, y)) {
                System.out.println("No se pudo colocar el barco"); // Mensaje si falla la colocación
            }
        }
    }

    public Barcos getBarcos() {
        return barcos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Oceano getOceano() {
        return oceano;
    }

}
