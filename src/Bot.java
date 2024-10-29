import java.util.Random;
import java.util.Scanner;

public class Bot extends Jugador{

    public Bot() {
        super("", 2);
    }

    public void acomodarBarcosAleatorio() {
        Random rand = new Random();
        int x, y, voltear;

        for (Barco barco : barcos.getBarcos()) {
            do {
                voltear = rand.nextInt(2);
                if (voltear == 0) {
                    barco.voltear();
                }

                x = rand.nextInt(10);
                y = rand.nextInt(10);

                barco.setXCord(x);
                barco.setYCord(y);

            } while (!oceano.colocarBarco(barco, x, y));
        }
    }

    public boolean disparar(Jugador enemigo) {
        int x = 0,y=0;
        Random rand = new Random();
        String[][] r = new String[10][10];
        Oceano oc = enemigo.getOceano();
        r = oc.getOceano();
        do{
            x = rand.nextInt(10);
            y = rand.nextInt(10);
        }while(r[x][y].equals("1") || r[x][y].equals("X"));
        String target = r[x][y];

        if(oc.manejarDisparo(x, y)){
            System.out.println("El bot acerto a un barco.");

            if(!oc.buscarRemanentesDelBarco(target)){
                for(Barco barcoDerribado : enemigo.getBarcos().barcos){
                    if(barcoDerribado.getId().equals(target)){
                        System.out.println("dentro bot");

                        barcoDerribado.setDerribado(true);
                        oc.derribarBarco(barcoDerribado);
                    }
                }
            }

            return true;
        }

        System.out.println("El bot fallo el disparo.");
        return false;
    }
}
