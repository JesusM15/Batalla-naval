import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Juego {
    private Jugador jugador;
    private Bot bot;
    private boolean consoleMode;
    private boolean playerTurn = false;
    private String winner;
    private Tablero tablero;
    private boolean juegoCargado = false;

    public Juego(boolean consoleMode){
        this.jugador = new Jugador("", 1);
        this.bot = new Bot();
        this.consoleMode = consoleMode;
        this.playerTurn = true;
        this.winner = null;
        this.tablero = new Tablero(jugador.getOceano(), bot.getOceano());
        this.juegoCargado = false;
    }

    public void jugar(String filename){
        if(!juegoCargado){
            crearJugador();
        }
        if(this.consoleMode){
            if(juegoCargado && gameOver()){
                mostrarOceanos();
                System.out.println("El juego Cargado a concluido");
                reiniciarJuego();
                return;
            }

            jugarEnConsola(filename);
            reiniciarJuego();
        }

    }

    public void reiniciarJuego(){
        jugador.getOceano().iniciarMatriz();
        bot.getOceano().iniciarMatriz();
        juegoCargado = false;
        jugador.borrarBarcos();
        bot.borrarBarcos();
        jugador.crearBarcos();
        bot.crearBarcos();
    }

    public void jugarEnConsola(String filename){
        if(!juegoCargado){
            pedirConfigurarBarcos();
            obtenerTurno();
        }

        mostrarOceanos();
        boolean acerto = false;
        System.out.println("iniciando fase de batalla...\n");

        do{
            // desarrollar el juego pidiendo disparos en un metodo y lanzandolos revisar si impacta.
            if(playerTurn){
                System.out.println("TU TURNO\n");
                mostrarOceanos();
                acerto = jugador.disparar(bot);
//              mostrarOceanos();
            }else {
                System.out.println("TURNO DEL BOT");
                bot.disparar(jugador);
            }
            guardarJuego(filename);
            obtenerTurno();
        }while(!gameOver());
        guardarJuego(filename);

        System.out.println("EL JUEGO HA TERMINADO EL GANADOR ES: " + winner);
    }

    public boolean gameOver(){
        boolean jugadorDerribado = jugador.getBarcos().getBarcos().stream().filter(Barco::isDerribado
        ).count() == 5;

        boolean botDerribado = bot.getBarcos().getBarcos().stream().filter(Barco::isDerribado
        ).count() == 5;

        System.out.println(bot.getBarcos().getBarcos().size());

        if (jugadorDerribado) {
            winner = "BOT";
        } else if (botDerribado) {
            winner = jugador.getNombre();
        }

        return jugadorDerribado || botDerribado;
    }

    public void pedirConfigurarBarcos(){
        jugador.acomodarBarcos(); // ESTE SERA EL VALIDO
//        jugador.acomodarBarcosAleatorio();

        this.playerTurn = false;
        bot.acomodarBarcosAleatorio();
    }

    public void mostrarOceanos(){
        //System.out.println("BOT");
        //System.out.print(bot.getOceano().showPrivateOcean()); // el que se va mostrar

        System.out.println(tablero);

        //System.out.println(bot.getOceano());
       // System.out.println(jugador.getNombre());
        //System.out.println(jugador.getOceano());
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

    public void setPlayerTurn(boolean playerTurn){
        this.playerTurn = playerTurn;
    }

    public void guardarJuego(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("NombreJugador:" + jugador.getNombre());
            writer.newLine();

            writer.write("Turno:" + (playerTurn ? "Jugador" : "Bot"));
            writer.newLine();

            // Guardar la matriz del jugador
            writer.write("Jugador\n");
            for (String[] fila : jugador.getOceano().getOceano()) {
                writer.write(String.join(",", fila));
                writer.newLine();
            }

            // Guardar la matriz del bot
            writer.write("Bot\n");
            for (String[] fila : bot.getOceano().getOceano()) {
                writer.write(String.join(",", fila));
                writer.newLine();
            }

            // Guardar los barcos del jugador
            writer.write("BarcosJugador\n");
            for (Barco barco : jugador.getBarcos().getBarcos()) {
                writer.write(barco.getId() + "," + barco.getXCord() + "," + barco.getyCord() + "," +
                        (barco.isEsVertical() ? "Vertical" : "Horizontal") + "," +
                        (barco.isDerribado() ? "Derribado" : "NoDerribado") + "," +
                        barco.getWidth() + "," + barco.getHeight());
                writer.newLine();
            }

            // Guardar los barcos del bot
            writer.write("BarcosBot\n");
            for (Barco barco : bot.getBarcos().getBarcos()) {
                writer.write(barco.getId() + "," + barco.getXCord() + "," + barco.getyCord() + "," +
                        (barco.isEsVertical() ? "Vertical" : "Horizontal") + "," +
                        (barco.isDerribado() ? "Derribado" : "NoDerribado") + "," +
                        barco.getWidth() + "," + barco.getHeight());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar el juego: " + e.getMessage());
        }
    }


    public void cargarJuego(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String linea;
            String[][] oceanoJugador = new String[10][10];
            String[][] oceanoBot = new String[10][10];
            boolean esJugador = true;
            boolean esBarcoJugador = false;
            boolean esBarcoBot = false;
            int fila = 0;
            jugador.getBarcos().generarBarcos();
            bot.getBarcos().generarBarcos();

            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("NombreJugador:")) {
                    jugador.setNombre(linea.split(":")[1]); // obtener nombre jugador
                }
                else if (linea.startsWith("Turno:")) {
                    playerTurn = linea.split(":")[1].equals("Jugador");
                }
                else if (linea.equals("Jugador")) {
                    esJugador = true;
                    esBarcoJugador = false;
                    esBarcoBot = false;
                    fila = 0;
                }
                else if (linea.equals("Bot")) {
                    esJugador = false;
                    esBarcoJugador = false;
                    esBarcoBot = false;
                    fila = 0;
                }
                else if (linea.equals("BarcosJugador")) {
                    esBarcoJugador = true;
                    esBarcoBot = false;
                }
                else if (linea.equals("BarcosBot")) {
                    esBarcoJugador = false;
                    esBarcoBot = true;
                }
                else if (esJugador || (!esBarcoJugador && !esBarcoBot)) {
                    // Leer las filas de las matrices
                    String[] valores = linea.split(",");
                    if (esJugador) {
                        oceanoJugador[fila] = valores;
                    } else {
                        oceanoBot[fila] = valores;
                    }
                    fila++;
                }
                // Cargar datos de los barcos
                else if (esBarcoJugador || esBarcoBot) {
                    String[] datosBarco = linea.split(",");
                    String id = datosBarco[0];
                    int xCord = Integer.parseInt(datosBarco[1]);
                    int yCord = Integer.parseInt(datosBarco[2]);
                    boolean esVertical = datosBarco[3].equals("Vertical");
                    boolean derribado = datosBarco[4].equals("Derribado");
                    int width = Integer.parseInt(datosBarco[5]);
                    int height = Integer.parseInt(datosBarco[6]);

                    // Crear y asignar barco al jugador o al bot según corresponda

                    if(esBarcoJugador){
                        for(Barco barco: jugador.getBarcos().getBarcos()){
                            if(barco.getId().equals(id)){
                                barco.setEsVertical(esVertical);
                                barco.setXCord(xCord);
                                barco.setYCord(yCord);
                                barco.setDerribado(derribado);
                            }
                        }
                    }else if(esBarcoBot){
                        for(Barco barco: bot.getBarcos().getBarcos()){
                            if(barco.getId().equals(id)){
                                barco.setEsVertical(esVertical);
                                barco.setXCord(xCord);
                                barco.setYCord(yCord);
                                barco.setDerribado(derribado);
                            }
                        }
                    }

                }
            }

            jugador.getOceano().setOceano(oceanoJugador);
            bot.getOceano().setOceano(oceanoBot);
            juegoCargado = true;

            System.out.println("Juego cargado exitosamente desde el archivo " + fileName);
        } catch (IOException e) {
            System.out.println("Error al cargar el juego: " + e.getMessage());
        }
    }

}
