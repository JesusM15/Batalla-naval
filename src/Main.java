import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Oceano oceano = new Oceano();
        Barcos barcos = new Barcos();
        System.out.println(oceano);
       // barco.voltear();
        Random rand = new Random();
        int x, y, voltear;
        Barco barco;

        // algoritmo que coloca barcos en posicion aleatoria (para pruebas)
        while(!barcos.getBarcos().isEmpty()){
            barco = barcos.getBarcos().getFirst();

            voltear = rand.nextInt(2);
            if(voltear == 0){
                barco.voltear();
            }
            do{
                x = rand.nextInt(16); // 0 - 15
                y = rand.nextInt(16);
            }while(x > 9 || x < 0 || y > 9 || y < 0); // prueba

            barco.setXCord(x);
            barco.setYCord(y);

            if(oceano.colocarBarco(barco, x, y)){
                barcos.getBarcos().removeFirst();
            };
        }

        System.out.println(oceano);

    }
}