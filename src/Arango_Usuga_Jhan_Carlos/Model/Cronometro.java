package Arango_Usuga_Jhan_Carlos.Model;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Arango_Usuga_Jhan_Carlos.Controller.Controladora_Nivel2;
import Arango_Usuga_Jhan_Carlos.View.Lienzo;
import Arango_Usuga_Jhan_Carlos.View.Lienzo_arriba;

public class Cronometro extends Thread {

    private JLabel Recibe;

    int cambioVelo = 0;
    int hasta = 0;
    int i = 0;
    public static boolean pausar = false;

    @SuppressWarnings("unused")
    private JButton[] bto_imagen;
    static boolean detenido = false;

    public Cronometro(int hasta, int i) {

        // this.Recibe = new JLabel();
        // this.Recibe = Recibe;
        this.hasta = hasta;
        this.i = i;

    }

    public Cronometro() {

    }

    public void desactivar_botones(JButton[] bto_imagen) {
        bto_imagen = new JButton[100];
        for (byte j = 0; j < bto_imagen.length; j++) {

            bto_imagen[i].setEnabled(false);
        }
    }

    public void imprimirCROn(JLabel Recibe) {
        this.Recibe = new JLabel();
        this.Recibe = Recibe;
    }

    // Método para detener el hilo
    public static void detener() {
        detenido = true;
    }

    public void reiniciarcron(int i) {
        this.i = i;
    }

    public boolean isDetenido() {
        return detenido; // Método para obtener el estado de la bandera
    }

    public static void setDetenido(boolean detenido) {
        Cronometro.detenido = detenido;
    }

    public static void reanudar() {
        detenido = false;
    }

    public void IniciarCronometro() {

        do {
            while (pausar == true) {
                System.out.println("...");
                if (Controladora_Nivel2.pausado == 1) {
                    pausar = false;
                }

            }
            System.out.println("i");

            this.Recibe.setText("" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Manejar la interrupción del hilo
                try {
                    Thread.currentThread().wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            if (i == 0) {

            }

            if (hasta == i) {
                System.out.println("fin");
                detenido = true;
                JOptionPane.showMessageDialog(null, "Se acabo el tiempo");
            }

            i++;
        } while (detenido != true || i == hasta);
    }

    @Override
    public void run() {
        while (Lienzo_arriba.terminar != true && Lienzo.terminar != true) {
            System.out.println("esperando");
        }
        IniciarCronometro();
    }

}
