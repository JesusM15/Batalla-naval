import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Juego {
    private Jugador jugador;
    private Bot bot;
    private boolean consoleMode;
    private boolean playerTurn = false;
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
        Oceano tableroBot = new Oceano();
        tableroBot.iniciarMatriz();
        pedirConfigurarBarcos();
        mostrarOceanos();
        obtenerTurno();
        do{
            // desarrollar el juego pidiendo disparos en un metodo y lanzandolos revisar si impacta.
            System.out.println("iniciando fase de batalla...");
            if(playerTurn){
                System.out.println("turno jugador");
                System.out.println(jugador.getOceano());
                System.out.println(tableroBot);
                jugador.disparar(bot.getOceano(),tableroBot);
                System.out.println(jugador.getOceano());
                System.out.println(tableroBot);
            }else {
                System.out.println("turno bot");
                bot.disparar(jugador.getOceano());
            }
            obtenerTurno();
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
    public void obtenerTurno(){
        this.playerTurn = !this.playerTurn;
    }
}
