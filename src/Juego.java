import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Juego {
    private Jugador jugador;
    private Bot bot;
    private boolean consoleMode;
    private boolean playerTurn;

    public Juego(boolean consoleMode){
        this.jugador = new Jugador("", 1);
        this.bot = new Bot();
        this.consoleMode = consoleMode;
        this.playerTurn = true;
    }

    public void jugar(){
        crearJugador();
        if(this.consoleMode){
            jugarEnConsola();
        }

    }

    public void jugarEnConsola(){

        pedirConfigurarBarcos();
        mostrarOceanos();

        do{
            // desarrollar el juego pidiendo disparos en un metodo y lanzandolos revisar si impacta.

        }while(!gameOver());
    }

    public boolean gameOver(){
        int filas = 10;
        int columnas = 10;
        int counterJugador = 0;
        int counterBot = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if(jugador.getOceano().getOceano()[i][j].equals("-1")){
                    counterJugador++;
                }

                if (bot.getOceano().getOceano()[i][j].equals("-1")) {
                    counterBot++;
                }
            }
        }


        return (counterJugador == 17 || counterBot == 17);
    }

    public void pedirConfigurarBarcos(){
        jugador.acomodarBarcos();

        this.playerTurn = false;
        bot.acomodarBarcosAleatorio();
    }

    public void mostrarOceanos(){
        System.out.println("BOT");
        System.out.print(bot.getOceano());
        System.out.println(jugador.getNombre());
        System.out.println(jugador.getOceano());
    }

    public void crearJugador(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserte su nombre:");
        String nombre = sc.nextLine();
        jugador.setNombre(nombre);
    }

}
