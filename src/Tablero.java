 public class Tablero {
    Oceano[] tablero = new Oceano[2];

    public Tablero(Oceano oceanoPlayer, Oceano oceanoBot) {
        tablero = new Oceano[]{ oceanoPlayer, oceanoBot};
    }
    public void reiniciarTableros() {
        String [][] ocean1 = tablero[0].getOceano();
        String [][] ocean2 = tablero[1].getOceano();
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                ocean1[i][j] = "0";
                ocean2[i][j] = "0";
            }
        }
        tablero[0].setOceano(ocean1);
        tablero[1].setOceano(ocean2);
    }
     @Override
     public String toString() {
         StringBuilder builder = new StringBuilder().append(tablero[1]);// tablero bot
         for(int i = 0; i<tablero[0].getOceano().length; i++){
             builder.append("---");
         }
         builder.append("---\n").append(tablero[0]);
         return builder.toString();
     }
 }
