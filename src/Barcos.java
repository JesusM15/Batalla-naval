import java.util.ArrayList;

public class Barcos {
    ArrayList<Barco> barcos;

    public Barcos() {
        this.barcos = new ArrayList<>();
        generarBarcos();
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }

    public void generarBarcos() {
        if(!barcos.isEmpty()) return;
        int n = 5;
        Barco barco;
        String id;
        // 5 2
        int[] tamanos = { 2, 3, 3, 4, 5};

        for(int i = 0; i<n; i++){
            id = "b" + (i + 1);

            barco = new Barco(id, tamanos[i]);
            barcos.add(barco);
        }
    }


}
