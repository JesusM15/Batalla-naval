import javax.swing.*;
import java.awt.*;

public class VentanaGrafica extends JFrame {
    private PanelTablero panelJugador;
    private PanelTablero panelBot;

    public VentanaGrafica(Oceano oceanoJugador, Oceano oceanoBot) {
        setTitle("Batalla Naval");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2, 0, 0));

        panelJugador = new PanelTablero("Jugador", oceanoJugador);
        panelBot = new PanelTablero("Bot", oceanoBot);

        add(panelJugador);
        add(panelBot);
    }

    public void actualizarTablero() {
        panelJugador.repaint();
        panelBot.repaint();
    }
}
