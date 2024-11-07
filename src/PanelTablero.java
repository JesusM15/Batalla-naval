import javax.swing.*;
import java.awt.*;

public class PanelTablero extends JPanel {
    private final int GRID_SIZE = 10; // 10x10
    private final int CELL_SIZE = 30;
    private Oceano oceano;
    private boolean jugador;

    public PanelTablero(String titulo, Oceano oceano) {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), titulo));
        this.oceano = oceano;
        this.jugador = true;
        if(titulo.equals("Bot")){
            this.jugador = false;
        }
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        g.setStroke(new BasicStroke(0.5f));

        String[][] matriz = oceano.getOceano();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                String valor = matriz[i][j];

                g.setColor(Color.BLUE);
                switch (valor) {
                    case "0":
                        g.setColor(Color.BLUE); // Agua no disparada
                        break;
                    case "1":
                        g.setColor(Color.CYAN); // Agua disparada sin barco
                        break;
                    case "X":
                        g.setColor(new Color(139, 69, 19)); // Impacto en barco
                        break;
                    case " ":
                        g.setColor(Color.WHITE); // Parte de barco destruido
                        break;
                    case "b5":
                        if(jugador){
                            g.setColor(Color.GREEN);
                        }
                        break;
                    case "b4":
                        if(jugador){
                            g.setColor(Color.RED);
                        }
                        break;
                    case "b3":
                        if(jugador){
                            g.setColor(Color.YELLOW);
                        }
                        break;
                    case "b2":
                        if(jugador){
                            g.setColor(Color.ORANGE);
                        }
                        break;
                    case "b1":
                        if(jugador){
                            g.setColor(Color.PINK);
                        }
                        break;
                    default:
                        break;
                }

                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.WHITE);
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }
}
