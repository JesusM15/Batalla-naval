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
    public void disparar(Oceano oc,Oceano muestra){
        int x = 0,y=0;
        String[][] s = new String[10][10];
        String[][] muestras = new String[10][10];
        s = oc.getOceano();
        muestras = muestra.getOceano();
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
        if(s[x][y].equals("b1")||s[x][y].equals("b2")||s[x][y].equals("b3")||s[x][y].equals("b4")||s[x][y].equals("b5")){
            muestras[x][y] = "X";
        }else{
            System.out.println("fallaste el tiro");
            muestras[x][y] = "1";
        }
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
    }
    public void acomodarBarcos(){
        int x = 0, y = -1, voltear = 0;
        Barco barco;
        Scanner sc = new Scanner(System.in);

        while(!barcos.getBarcos().isEmpty()){
            barco = barcos.getBarcos().getFirst();
            System.out.print(oceano);
            if(barco.isEsVertical()){
                System.out.println(String.format("Barco %dx%d", barco.getWidth(), barco.getHeight()));

                System.out.println(barco);
            }else{
                System.out.println(String.format("Barco %dx%d", barco.getHeight(), barco.getWidth()));
                System.out.println(barco);
            }

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
                System.out.println("y:" +  y);

            }while(x > 9 || x < 0 || y > 9 || y < 0);

            do {
                System.out.print("1)Girar\n2)Conservar\nSeleccione: ");
                voltear = sc.nextInt() - 1;
            }while(voltear != 0 && voltear != 1);

            barco.setXCord(x);
            barco.setYCord(y);

            if(voltear == 0){
                barco.voltear();
            }

            if(oceano.colocarBarco(barco, x, y)){
                barcos.getBarcos().removeFirst();
            }else{
                System.out.println("No se pudo colocar el barco");
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
