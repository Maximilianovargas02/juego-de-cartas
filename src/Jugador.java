import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jugador {

    private int DISTANCIA = 40;
    private int MARGEN = 10;
    private int TOTAL_CARTAS = 10;
    private String MENSAJE_PREDETERMINADO = "No se encontraron grupos\n";
    private String ENCABEZADO_MENSAJE = "Se encontraron los siguientes grupos:\n";
    private int MINIMA_CANTIDAD_GRUPO = 2;
    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int x = MARGEN + (TOTAL_CARTAS - 1) * DISTANCIA;
        for (Carta carta : cartas) {
            carta.mostrar(pnl, x, MARGEN);
            x -= DISTANCIA;
        }
        pnl.repaint();
    }

    public String getGrupos() {
        String mensaje = MENSAJE_PREDETERMINADO;

        int[] contadores = new int[NombreCarta.values().length];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }

        List<String> escaleraMensaje = buscarEscalera();
        if (!escaleraMensaje.isEmpty()) {
            mensaje = "Se encontraron las siguientes escaleras:\n";
            for (String escalera : escaleraMensaje) {
                mensaje += escalera + "\n";
            }
        }

        boolean hayGrupos = false;
        for (int contador : contadores) {
            if (contador >= MINIMA_CANTIDAD_GRUPO) {
                hayGrupos = true;
                break;
            }
        }

        if (hayGrupos) {
            mensaje += ENCABEZADO_MENSAJE;
            int posicion = 0;
            for (int contador : contadores) {
                if (contador >= MINIMA_CANTIDAD_GRUPO) {
                    mensaje += Grupo.values()[contador] + " de " + NombreCarta.values()[posicion] + "\n";
                }
                posicion++;
            }
        }

        return mensaje;
    }

    public List<String> buscarEscalera() {
        List<String> escaleraMensaje = new ArrayList<>();

        List<Carta> trebol = new ArrayList<>();
        List<Carta> pica = new ArrayList<>();
        List<Carta> corazon = new ArrayList<>();
        List<Carta> diamante = new ArrayList<>();

        for (Carta carta : cartas) {
            switch (carta.getPinta()) {
                case TREBOL:
                    trebol.add(carta);
                    break;
                case PICA:
                    pica.add(carta);
                    break;
                case CORAZON:
                    corazon.add(carta);
                    break;
                case DIAMANTE:
                    diamante.add(carta);
                    break;
            }
        }

        escaleraMensaje.addAll(verificarEscaleraEnPinta(trebol, "Trébol"));
        escaleraMensaje.addAll(verificarEscaleraEnPinta(pica, "Pica"));
        escaleraMensaje.addAll(verificarEscaleraEnPinta(corazon, "Corazón"));
        escaleraMensaje.addAll(verificarEscaleraEnPinta(diamante, "Diamante"));

        return escaleraMensaje;
    }

    private List<String> verificarEscaleraEnPinta(List<Carta> cartasDePinta, String pinta) {
        List<String> escaleraDetectada = new ArrayList<>();

        cartasDePinta.sort((c1, c2) -> c1.getNombre().ordinal() - c2.getNombre().ordinal());

        System.out.println("Cartas ordenadas de " + pinta + ":");
        for (Carta c : cartasDePinta) {
            System.out.println(c.getNombre() + " de " + c.getPinta());
        }

        for (int i = 0; i < cartasDePinta.size() - 3; i++) {
            List<Carta> subLista = cartasDePinta.subList(i, i + 4);
            if (esSecuenciaConsecutiva(subLista)) {
                escaleraDetectada.add(pinta + ": " + subLista.get(0).getNombre() + " a " + subLista.get(3).getNombre());
            }
        }

        if (escaleraDetectada.isEmpty()) {
            System.out.println("No se detectaron escaleras en " + pinta);
        } else {
            for (String s : escaleraDetectada) {
                System.out.println("Escalera detectada en " + pinta + ": " + s);
            }
        }

        return escaleraDetectada;
    }

    private boolean esSecuenciaConsecutiva(List<Carta> cartas) {
        for (int i = 0; i < cartas.size() - 1; i++) {
            if (cartas.get(i).getNombre().ordinal() + 1 != cartas.get(i + 1).getNombre().ordinal()) {
                return false;
            }
        }
        return true;
    }

    public int calcularPuntaje() {
        int puntaje = 0;
        List<Carta> cartasExcluidas = new ArrayList<>();

        for (String escalera : buscarEscalera()) {
            for (Carta carta : cartas) {
                if (esParteDeEscalera(carta)) {
                    cartasExcluidas.add(carta);
                }
            }
        }

        for (Carta carta : cartas) {
            if (esParteDeGrupo(carta)) {
                cartasExcluidas.add(carta);
            }
        }

        for (Carta carta : cartas) {
            if (!cartasExcluidas.contains(carta)) {
                puntaje += calcularValorCarta(carta);
            }
        }

        return puntaje;
    }

    private boolean esParteDeGrupo(Carta carta) {
        int contador = 0;
        for (Carta c : cartas) {
            if (c.getNombre() == carta.getNombre()) {
                contador++;
            }
        }
        return contador >= MINIMA_CANTIDAD_GRUPO;
    }

    private boolean esParteDeEscalera(Carta carta) {
        for (String escalera : buscarEscalera()) {
            if (escalera.contains(carta.getNombre().toString())) {
                return true;
            }
        }
        return false;
    }

    private int calcularValorCarta(Carta carta) {
        switch (carta.getNombre()) {
            case AS:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            default:
                return carta.getNombre().ordinal() + 1;
        }
    }
}
