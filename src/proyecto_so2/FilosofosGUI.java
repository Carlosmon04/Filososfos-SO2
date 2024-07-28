package proyecto_so2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

public class FilosofosGUI extends JFrame {
    private JTextField txtFilosofos;
    private JTextField txtSegundos;
    private JTextArea txtOutput;
    private JButton btnIniciar;

    public FilosofosGUI() {
        setTitle("Problema de los Filósofos Comensales");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(3, 2));

        panelInput.add(new JLabel("Cantidad de Filósofos:"));
        txtFilosofos = new JTextField();
        panelInput.add(txtFilosofos);

        panelInput.add(new JLabel("Segundos por Acción:"));
        txtSegundos = new JTextField();
        panelInput.add(txtSegundos);

        btnIniciar = new JButton("Iniciar");
        panelInput.add(btnIniciar);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOutput);

        add(panelInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSimulacion();
            }
        });
    }

    private void iniciarSimulacion() {
        int maestros;
        int segundos;
        try {
            maestros = Integer.parseInt(txtFilosofos.getText());
            segundos = Integer.parseInt(txtSegundos.getText()) * 1000;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (maestros < 1 || maestros > 10) {
            JOptionPane.showMessageDialog(this, "Solo pueden participar de 1 a 10 filósofos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Proyecto_SO2.segundos = segundos;
        Proyecto_SO2.maestros = maestros;

        txtOutput.setText("");
        Silla s = new Silla();
        Semaphore tenedorSemaforo = new Semaphore(maestros - 1);
        Tenedores[] cubiertos = new Tenedores[maestros];
        Filosofos[] f = new Filosofos[maestros];

        for (int i = 0; i < cubiertos.length; i++) {
            String nombreR = "Derecho";
            String nombreL = "Izquierdo";
            String fName = (i % 2 == 0) ? nombreR : nombreL;
            cubiertos[i] = new Tenedores(i, fName);
        }

        for (int k = 0; k < f.length; k++) {
            String nombre = Proyecto_SO2.nombresFilosofos[k];
            f[k] = new Filosofos(k, cubiertos[k], cubiertos[(k + 1) % maestros], s, nombre, tenedorSemaforo, maestros, txtOutput);
        }

        for (int j = 0; j < f.length; j++) {
            f[j].start();
            if ((j + 1) % 4 == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FilosofosGUI().setVisible(true);
            }
        });
    }
}
