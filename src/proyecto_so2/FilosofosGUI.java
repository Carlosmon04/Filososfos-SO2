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
    private JButton btnStop;
    private Filosofos[] filosofos;
    private MesaPanel mesaPanel;
    private int maestros;

    public FilosofosGUI() {
        setTitle("Problema de los Filósofos Comensales");
        setSize(800, 600);
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

        btnStop = new JButton("STOP");
        panelInput.add(btnStop);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOutput);

        mesaPanel = new MesaPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mesaPanel, scrollPane);
        splitPane.setResizeWeight(0.5);

        add(panelInput, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSimulacion();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detenerSimulacion();
            }
        });
    }

    private void iniciarSimulacion() {
        try {
            maestros = Integer.parseInt(txtFilosofos.getText());
            int segundos = Integer.parseInt(txtSegundos.getText()) * 1000;
            Proyecto_SO2.segundos = segundos;

            if (maestros < 1 || maestros > 10) {
                JOptionPane.showMessageDialog(this, "La cantidad de filósofos debe estar entre 1 y 10.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            txtOutput.setText("");
            Silla s = new Silla();
            Semaphore tenedorSemaforo = new Semaphore(maestros - 1);
            Tenedores[] cubiertos = new Tenedores[maestros];
            filosofos = new Filosofos[maestros];

            for (int i = 0; i < cubiertos.length; i++) {
                String nombreR = "Derecho";
                String nombreL = "Izquierdo";
                String fName = (i % 2 == 0) ? nombreR : nombreL;
                cubiertos[i] = new Tenedores(i, fName);
            }

            for (int k = 0; k < filosofos.length; k++) {
                String nombre = Proyecto_SO2.nombresFilosofos[k];
                filosofos[k] = new Filosofos(k, cubiertos[k], cubiertos[(k + 1) % maestros], s, nombre, tenedorSemaforo, maestros, txtOutput);
            }

            mesaPanel.setMaestros(maestros);
            mesaPanel.setTenedores(cubiertos);
            mesaPanel.setFilosofos(filosofos);
            mesaPanel.repaint();

            for (int j = 0; j < filosofos.length; j++) {
                filosofos[j].start();
                if ((j + 1) % 4 == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void detenerSimulacion() {
        if (filosofos != null) {
            for (Filosofos filosofo : filosofos) {
                if (filosofo != null) {
                    filosofo.detener();
                }
            }
        }
    }
}
