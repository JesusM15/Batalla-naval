 public class Tablero {
    Oceano[] tablero = new Oceano[2];

    public Tablero(Oceano oceanoPlayer, Oceano oceanoBot) {
        tablero = new Oceano[]{ oceanoPlayer, oceanoBot };
    }

     @Override
     public String toString() {
         StringBuilder builder = new StringBuilder().append(tablero[1]); // tablero bot
         for(int i = 0; i<tablero[0].getOceano().length; i++){
             builder.append("---");
         }
         builder.append("---\n").append(tablero[0]);
         return builder.toString();
     }
 }
