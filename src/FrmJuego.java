import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

public class FrmJuego extends JFrame {

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

        JTabbedPane tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10, 40, 650, 150);
        getContentPane().add(tpJugadores);

        JPanel pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(16, 139, 37));
        pnlJugador1.setLayout(null);

        JPanel pnlJugador2 = new JPanel();
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
    }

    private void repartirCartas() {

    }

    private void verificarJugador() {

    }

}
