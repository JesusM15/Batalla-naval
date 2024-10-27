import java.util.Arrays;

public class Oceano {
    final int MAX = 10;
    private String[][] oceano;

    public Oceano() {
        oceano = new String[MAX][MAX];
        iniciarMatriz();
    }

    public String[][] getOceano() {
        return oceano;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("   ");
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        for(int i = 0; i < MAX; i++) {
            sb.append(String.format(("%2s "), letters[i]));
        }
        sb.append('\n');

        for(int row = 0; row<MAX; row++){
            sb.append(String.format("%2d ", (row+1)));
            for(int col = 0; col<MAX; col++){
                sb.append(String.format("%2s ", oceano[row][col]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void iniciarMatriz (){
        for(int i = 0; i < MAX; i++){
            Arrays.fill(oceano[i], "0");
        }
    }

    public boolean colocarBarco(Barco barco, int row, int col){
        int[] lastCoords = barco.obtenerCoordFinal();
        if(verificarColocacion(barco, row, col)){
            if(barco.isEsVertical()){
                for(int i = col; i<lastCoords[1]; i++){
                    oceano[row][i] = barco.getId();
                }
            }else {
                for (int i = row; i < lastCoords[0]; i++) {
                    oceano[i][col] = barco.getId();
                }
            }
            return true;
        }
        return false;
    }

    private boolean verificarColocacion(Barco barco, int row, int col) {
        int[] lastCoords = barco.obtenerCoordFinal();
        if(lastCoords[0]> MAX || lastCoords[1] > MAX) return false;

        // recorre en direccion dependiendo si esta vertical u horizontal y ve si es que no hay nada colocado en ninguna de las posiciones
        // que ocupa el barco.
        if(barco.isEsVertical()){for(int i = col; i<lastCoords[1]; i++) if(!oceano[row][i].equals("0")) return false;}
        else {for(int i = row; i<lastCoords[0]; i++) if(!oceano[i][col].equals("0")) return false;}
        return true;
    }
}
