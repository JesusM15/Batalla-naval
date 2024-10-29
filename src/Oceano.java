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

    public String showPrivateOcean(){
        StringBuilder sb = new StringBuilder().append("   ");
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        for(int i = 0; i < MAX; i++) {
            sb.append(String.format(("%2s "), letters[i]));
        }
        sb.append('\n');

        for(int row = 0; row<MAX; row++){
            sb.append(String.format("%2d ", (row+1)));
            for(int col = 0; col<MAX; col++){
                if(!impactoEnBarco(oceano[row][col])){
                    sb.append(String.format("%2s ", oceano[row][col]));
                }else {
                    sb.append(String.format("%2s ", "0"));
                }
            }
            sb.append("\n");
        }

        return sb.toString();
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

    public boolean manejarDisparo(int x, int y){
        String target = this.oceano[x][y];
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.println(String.format("DISPARO EN (%2d , %2c)", (x+1), letters[y]));
        if (impactoEnBarco(target)){
            this.oceano[x][y] = "X";
            return true;
        }else {
            this.oceano[x][y] = "1";
            return false;
        }
    }

    private boolean impactoEnBarco(String target){
        return target.equals("b1")|| target.equals("b2")|| target.equals("b3")|| target.equals("b4")|| target.equals("b5");
    }

    private String[][] generarCopiaOceano(){
        String[][] copiaOceano = new String[oceano.length][];

        for (int i = 0; i < oceano.length; i++) {
            copiaOceano[i] = oceano[i].clone(); // Clonar cada sub-arreglo
        }
        return copiaOceano;
    }

    public boolean buscarRemanentesDelBarco(String target){
        boolean ocurrencia = false;
        String[][] copiaOceano = generarCopiaOceano(); // copia profunda.

        for(int i = 0; i < copiaOceano.length; i++){

            Arrays.sort(copiaOceano[i]);

            int index = Arrays.binarySearch(copiaOceano[i], target);

            if(index >= 0){
                ocurrencia = true;
                break;
            }
        }

        if(!ocurrencia){
            System.out.println("El barco " + target + " Ha sido destruido!");
        }
        return ocurrencia;

    }

    public void derribarBarco(Barco barco) {
        // Obtener coordenadas inicial y final del barco
        int xInicio = barco.getXCord();
        int yInicio = barco.getyCord();
        int[] coordFinal = barco.obtenerCoordFinal();
        int xFin = coordFinal[0];
        int yFin = coordFinal[1];

        // Reemplazar "X" con espacios en blanco en las posiciones del barco
        if (barco.isEsVertical()) {
            for (int i = yInicio; i < yFin; i++) {
                if (oceano[xInicio][i].equals("X")) {
                    oceano[xInicio][i] = " "; // Borrar posición del barco
                }
            }
        } else {
            for (int i = xInicio; i < xFin; i++) {
                if (oceano[i][yInicio].equals("X")) {
                    oceano[i][yInicio] = " "; // Borrar posición del barco
                }
            }
        }
    }

    public void setOceano(String[][] oceano) {
        this.oceano = oceano;
    }
}
