package proyecto_so2;

import javax.swing.*;
import java.awt.*;

public class MesaPanel extends JPanel {
    private Tenedores[] tenedores;
    private Filosofos[] filosofos;
    private int maestros;

    public MesaPanel(int maestros) {
        this.maestros = maestros;
        setPreferredSize(new Dimension(400, 400));
    }

    public void setTenedores(Tenedores[] tenedores) {
        this.tenedores = tenedores;
    }

    public void setFilosofos(Filosofos[] filosofos) {
        this.filosofos = filosofos;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (tenedores == null || filosofos == null) {
            return;
        }

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 3;

        // Draw table
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        // Draw philosophers and forks
        for (int i = 0; i < maestros; i++) {
            double angle = 2 * Math.PI * i / maestros;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));

            // Draw philosopher
            g.setColor(Color.BLUE);
            g.drawString(Proyecto_SO2.nombresFilosofos[i], x - 10, y - 10);

            // Draw fork
            int forkX = (int) (centerX + (radius / 1.5) * Math.cos(angle + Math.PI / maestros));
            int forkY = (int) (centerY + (radius / 1.5) * Math.sin(angle + Math.PI / maestros));
            g.setColor(Color.BLACK);
            g.fillRect(forkX - 5, forkY - 5, 10, 10);
        }
    }
}
