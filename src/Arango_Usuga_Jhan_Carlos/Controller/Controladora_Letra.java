package Arango_Usuga_Jhan_Carlos.Controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Arango_Usuga_Jhan_Carlos.Model.BD_imagenes;
import Arango_Usuga_Jhan_Carlos.Model.Cronometro;
import Arango_Usuga_Jhan_Carlos.Model.Validacion;
import Arango_Usuga_Jhan_Carlos.View.Conf_Ventana;
import Arango_Usuga_Jhan_Carlos.View.Letra_Ventana;

public class Controladora_Letra extends Thread implements ActionListener {

    Letra_Ventana l_v;
    Validacion suport = new Validacion();
    BD_imagenes BD = new BD_imagenes();
    int cont = 0, cont_error = 0;
    char letra_random;
    ImageIcon _IMG_buscar;
    Boolean play; // para no tener que desabilitar los botones de las letras
    Boolean esta_Detenido = true;
    Cronometro time;
    int second, num_aciertos;
    Thread h1, h2, h3;
    int repe = 1;

    public Controladora_Letra(Letra_Ventana l_v) {
        start();
        this.l_v = l_v;
        play = false;
        second = Integer.parseInt(Conf_Ventana.txt_tiempo.getText()); // recibo el tiempo en segundos de la primera
        time = new Cronometro(second, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ((l_v.bto_play == e.getSource())) {
            Reiniciar_Todo();
            if (BD.isDetenido() == true) { // valido que todas las imagenes este llenas

                for (int i = 0; i < l_v.bto_letras.length; i++) {

                    l_v.bto_letras[i].setEnabled(true);
                }

                // letra_random = suport.letraAleatoria();

                letra_random = BD.encontrarLetraObjetivo(l_v.bto_letras); // obtengo la letra objetivo para la busqueda

                _IMG_buscar = new ImageIcon("src/IMG/" + letra_random + ".png");

                l_v.jlabel_IMG_buscar
                        .setIcon(new ImageIcon(_IMG_buscar.getImage().getScaledInstance(140, 160, Image.SCALE_FAST)));

                play = true; // aqui los botones se habilitan

                time.imprimirCROn(l_v.jlabel_tiempo);

                h1 = new Thread(time);

                h1.start(); /// lanzo de nuevo el hilo para volver a usar el cronometro

                l_v.bto_play.setEnabled(false);
            }
        }

        for (int i = 0; i < l_v.bto_letras.length; i++) {
            if (l_v.bto_letras[i] == e.getSource()) {

                char letra = suport.String_to_char(l_v.bto_letras[i].getName());

                if ((letra == letra_random)) { /// Comparo si puedo usar los botonoes de la sopa /// de letras

                    l_v.bto_letras[i].setEnabled(false);

                    l_v.bto_letras[i].setBackground(Color.GREEN);

                    cont++;

                    l_v.jlabel_aciertos.setText("" + cont);

                    num_aciertos = Integer.parseInt(l_v.jlabel_aciertos.getText());

                } else if ((letra != letra_random) && play == true) { /// Comparo si puedo usar los botonoes de la sopa

                    l_v.bto_letras[i].setBackground(Color.RED);

                    cont_error++;

                    l_v.jlabel_errores.setText("" + cont_error);
                }
            }
        }

        if (l_v.bto_update == e.getSource()) {
            l_v.bto_update.setEnabled(false);

            for (int i = 0; i < l_v.bto_letras.length; i++) {

                l_v.bto_letras[i].setEnabled(true);
                l_v.bto_letras[i].setBackground(new Color(255, 255, 255));
            }

            esta_Detenido = true;

            h2 = new Thread(this);
            h2.start(); // vuelvo a llamar el metodo run para volver a ingresar al while

            time.reiniciarcron(0); // restablesco los valores del cronometro

            h1 = new Thread(time);
            h3 = new Thread(BD);
            h3.start();
            h1.start();
            l_v.jlabel_errores.setText("0");
            l_v.jlabel_aciertos.setText("0");
            Cronometro.reanudar();
        }

        if (l_v.boton_terminar == e.getSource()) {

            play = false;
            esta_Detenido = false; // detengo el hilo
            Cronometro.detener();
        }
        if (l_v.boton_close == e.getSource()) {
            Reiniciar_Todo();
            play = false;
            BD_imagenes.setFin(false); // hago esto para que no se pueda juagar despues de cerrar la ventana
            l_v.dispose();
        }

    }

    @Override
    public void run() {
        System.out.println("Run letra");
        do {
            System.out.println("Verificando");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            if ((time.isDetenido() == true)) { // el isDetenido cambia su esrtado el su hilo propio es decir Hilo

                l_v.info.append(
                        "Juego N°" + repe + "\n" + "Tiempo Utilizado : " + l_v.jlabel_tiempo.getText() + " Seg" + "\n"
                                + "Errores : " + l_v.jlabel_errores.getText() + "\n"
                                + "Aciertos : " + l_v.jlabel_aciertos.getText() + "\n"
                                + " :( No lo graste terminar el Juego" + "\n"
                                + "____________________________" + "\n");

                for (int i = 0; i < l_v.bto_letras.length; i++) {

                    l_v.bto_letras[i].setEnabled(false);
                }
                esta_Detenido = false;
                num_aciertos = 0;
                cont = 0;
                cont_error = 0;
                repe++;
                l_v.bto_update.setEnabled(true);
                repe++;
            }

            if (num_aciertos == 5) {

                l_v.info.append(
                        "Juego N°" + repe + "\n" + "Tiempo Utilizado : " + l_v.jlabel_tiempo.getText() + " Seg" + "\n"
                                + "Errores : " + l_v.jlabel_errores.getText() + "\n"
                                + "Aciertos : " + l_v.jlabel_aciertos.getText() + "\n"
                                + "haz logrado terminar el juego" + "\n"
                                + "_____________________________" + "\n");

                esta_Detenido = false; // detengo el hilo
                Cronometro.detener();
                JOptionPane.showMessageDialog(null, "Ganaste");
                l_v.bto_update.setEnabled(true);

                for (int i = 0; i < l_v.bto_letras.length; i++) {

                    l_v.bto_letras[i].setEnabled(false);
                }

                num_aciertos = 0;
                cont = 0;
                cont_error = 0;
                repe++;
            }

        } while (esta_Detenido != false);

    }

    public void Reiniciar_Todo() {
        time.reiniciarcron(0);
        l_v.jlabel_errores.setText("0");
        l_v.jlabel_aciertos.setText("0");
        esta_Detenido = true;
        h3 = new Thread(this);
        Cronometro.setDetenido(false);
        h3.start();
    }
}
