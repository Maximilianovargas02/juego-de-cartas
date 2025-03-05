import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrmJuego extends JFrame {

    JPanel pnlJugador1, pnlJugador2;
    JTabbedPane tpJugadores;

    public FrmJuego() {
        setSize(700, 250);
        setTitle("Juguemos al apuntado!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        getContentPane().add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        getContentPane().add(btnVerificar);

        JButton btnPuntaje = new JButton("Mostrar Puntaje");
        btnPuntaje.setBounds(230, 10, 150, 25);
        getContentPane().add(btnPuntaje);

        tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10, 40, 650, 150);
        getContentPane().add(tpJugadores);

        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(16, 139, 37));
        pnlJugador1.setLayout(null);

        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raúl Vidal", pnlJugador2);

        btnRepartir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repartirCartas();
            }
        });

        btnVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarJugador();
            }
        });

        btnPuntaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pestañaSeleccionada = tpJugadores.getSelectedIndex();
                switch (pestañaSeleccionada) {
                    case 0:
                        int puntajeJugador1 = jugador1.calcularPuntaje();
                        JOptionPane.showMessageDialog(null, "Puntaje de Martín Estrada Contreras: " + puntajeJugador1);
                        break;
                    case 1:
                        int puntajeJugador2 = jugador2.calcularPuntaje();
                        JOptionPane.showMessageDialog(null, "Puntaje de Raúl Vidal: " + puntajeJugador2);
                        break;
                }
            }
        });
    }

    Jugador jugador1 = new Jugador();
    Jugador jugador2 = new Jugador();

    private void repartirCartas() {
        jugador1.repartir();
        jugador1.mostrar(pnlJugador1);
        jugador2.repartir();
        jugador2.mostrar(pnlJugador2);
    }

    private void verificarJugador() {
        int pestañaSeleccionada = tpJugadores.getSelectedIndex();
        switch (pestañaSeleccionada) {
            case 0:
                JOptionPane.showMessageDialog(null, jugador1.getGrupos());
                break;
            case 1:
                JOptionPane.showMessageDialog(null, jugador2.getGrupos());
                break;
        }
    }

}
