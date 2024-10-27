import java.util.Random;
import java.util.Scanner;

public class Bot extends Jugador{

    public Bot() {
        super("", 2);
    }

    public void acomodarBarcosAleatorio(){
        Random rand = new Random();
        int x, y, voltear;
        Barco barco;
        while(!barcos.getBarcos().isEmpty()){
            barco = barcos.getBarcos().getFirst();

            voltear = rand.nextInt(2);
            if(voltear == 0){
                barco.voltear();
            }
            x = rand.nextInt(10); // 0 - 15
            y = rand.nextInt(10);

            barco.setXCord(x);
            barco.setYCord(y);

            if(oceano.colocarBarco(barco, x, y)){
                barcos.getBarcos().removeFirst();
            };
        }
    }
    public void disparar(Oceano oc) {
        int x = 0,y=0;
        Random rand = new Random();
        String[][] r = new String[10][10];
        r = oc.getOceano();
        do{
            x = rand.nextInt(10);
            y = rand.nextInt(10);
        }while(r[x][y].equals("1") || r[x][y].equals("X"));
        if(r[x][y].equals("b1")||r[x][y].equals("b2")||r[x][y].equals("b3")||r[x][y].equals("b4")||r[x][y].equals("b5")){
            r[x][y] = "X";
        }else{
            System.out.println("el bot fallo el tiro");
            r[x][y] = "1";
        }
    }
}
