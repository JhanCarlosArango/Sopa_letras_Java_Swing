package Arango_Usuga_Jhan_Carlos.Controller;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Arango_Usuga_Jhan_Carlos.Model.BD_imagenes;
import Arango_Usuga_Jhan_Carlos.Model.Validacion;
import Arango_Usuga_Jhan_Carlos.View.Conf_Ventana;
import Arango_Usuga_Jhan_Carlos.View.Lienzo;
import Arango_Usuga_Jhan_Carlos.View.Lienzo_arriba;
import Arango_Usuga_Jhan_Carlos.View.Nivel2_Ventana;
import Arango_Usuga_Jhan_Carlos.Model.Cronometro;

public class Controladora_Nivel2 extends Thread implements ActionListener, ChangeListener {

    Validacion suport = new Validacion();
    BD_imagenes BD = new BD_imagenes();
    BD_imagenes BD_nuevo;
    int cont = 0, cont_error = 0;
    char letra_random;
    String _IMG_buscar;
    Boolean play; // para no tener que desabilitar los botones de las letras
    Boolean esta_Detenido = true;
    Cronometro time;
    int second, num_aciertos;
    Thread h1, h2, h3, mov, mov_arriba, hBD_nuevo;
    int repe = 1;
    public static int pausado = 0, pausado2 = 0;
    Nivel2_Ventana i_n;

    public Controladora_Nivel2(Nivel2_Ventana i_n) {
        this.i_n = i_n;

        play = false;
        second = Integer.parseInt(Conf_Ventana.txt_tiempo.getText()); // recibo el tiempo en segundos de la primera

        time = new Cronometro(second, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < i_n.bto_letras.length; i++) {
            if (i_n.bto_letras[i] == e.getSource()) {

                char letra = suport.String_to_char(i_n.bto_letras[i].getName());

                if ((letra == letra_random && Lienzo_arriba.isTerminar() == true && Lienzo.isTerminar() == true)) { // usar letras

                    i_n.bto_letras[i].setEnabled(false);

                    i_n.bto_letras[i].setBackground(Controladora_Conf.color_acierots);

                    cont++;

                    i_n.jlabel_aciertos.setText("" + cont);

                    num_aciertos = Integer.parseInt(i_n.jlabel_aciertos.getText());

                } else if ((letra != letra_random) && (play == true) && Lienzo_arriba.isTerminar() == true && Lienzo.isTerminar() == true) { // Comparo si puedo usar los botonoes sopa

                    i_n.bto_letras[i].setBackground(Controladora_Conf.color_erroes);

                    cont_error++;

                    i_n.jlabel_errores.setText("" + cont_error);
                }
            }
        }
        if ((i_n.bto_play == e.getSource())) {

            if (BD.isDetenido() == true) { // valido que todas las imagenes este llenas
                Reiniciar_Todo(); // uso con el fin de restablecer valores antes de ejecutar nuevamente el juego
                for (int i = 0; i < i_n.bto_letras.length; i++) {

                    i_n.bto_letras[i].setEnabled(true);
                }

                // letra_random = suport.letraAleatoria();

                letra_random = BD.encontrarLetraObjetivo(i_n.bto_letras); // obtengo la letra objetivo para la busqueda

                _IMG_buscar = "src/IMG/" + letra_random + ".png";

                mov = new Thread(i_n.cv_sout);
                mov_arriba = new Thread(i_n.cv_east);

                i_n.cv_sout.pintar(_IMG_buscar);
                i_n.cv_east.pintar(_IMG_buscar);

                mov.start();
                mov_arriba.start();
                i_n.cv_sout.setBandera(true);

                i_n.cv_east.setBandera(true);
                play = true; // aqui los botones se habilitan

                time.imprimirCROn(i_n.jlabel_tiempo);

                h1 = new Thread(time);

                h1.start(); /// lanzo de nuevo el hilo para volver a usar el cronometro

                i_n.bto_play.setEnabled(false);
            }
        }

        if (i_n.pausar_reanudar == e.getSource()) {
            if (pausado2 == 0) {
                BD_imagenes.pausar = true;
                Lienzo.pausar = true;
                Lienzo_arriba.pausar = true;
                Cronometro.pausar = true;
                pausado = 0;
                pausado2 = 1;
                Nivel2_Ventana.p_grid_letras.setVisible(false);
            } else {
                pausado = 1;
                pausado2 = 0;
                Nivel2_Ventana.p_grid_letras.setVisible(true);
            }

        }

        if (i_n.bto_update == e.getSource()) {
            i_n.bto_update.setEnabled(false);

            for (int i = 0; i < i_n.bto_letras.length; i++) {

                i_n.bto_letras[i].setEnabled(true);
                i_n.bto_letras[i].setIcon(null);
                i_n.bto_letras[i].setBackground(new Color(255, 255, 255));
            }

            BD_nuevo = new BD_imagenes(i_n.bto_letras, BD_imagenes.velocidad);
            hBD_nuevo = new Thread(BD_nuevo);
            hBD_nuevo.start();

            letra_random = BD_nuevo.encontrarLetraObjetivo(i_n.bto_letras); // obtengo la letra objetivo para la busqueda

            _IMG_buscar = "src/IMG/" + letra_random + ".png";

            mov = new Thread(i_n.cv_sout);
            mov_arriba = new Thread(i_n.cv_east);

            i_n.cv_sout.pintar(_IMG_buscar);
            i_n.cv_east.pintar(_IMG_buscar);

            mov.start();
            mov_arriba.start();
            i_n.cv_sout.setBandera(true);

            i_n.cv_east.setBandera(true);

            esta_Detenido = true;
            i_n.cv_sout.setX(0);
            i_n.cv_east.setX(-1315);
            i_n.cv_east.setY(208);
            h2 = new Thread(this);
            h2.start(); // vuelvo a llamar el metodo run para volver a ingresar al while

            time.reiniciarcron(0); // restablesco los valores del cronometro

            h1 = new Thread(time);
            Thread h3 = new Thread(BD);
            h3.start();
            h1.start();
            i_n.jlabel_errores.setText("0");
            i_n.jlabel_aciertos.setText("0");
            Cronometro.reanudar();
        }

        if (i_n.boton_terminar == e.getSource()) {

            play = false;
            esta_Detenido = false; // detengo el hilo
            Cronometro.detener();
        }
        if (i_n.boton_close == e.getSource()) {

            Reiniciar_Todo();
            BD_imagenes.setFin(false); // hago esto para que no se pueda juagar despues de cerrar la ventana
            play = false;
            i_n.dispose();

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

                i_n.info.append(
                        "Juego N°" + repe + "\n" + "Tiempo Utilizado : " + i_n.jlabel_tiempo.getText() + " Seg"
                                + "\n"
                                + "Errores : " + i_n.jlabel_errores.getText() + "\n"
                                + "Aciertos : " + i_n.jlabel_aciertos.getText() + "\n"
                                + " :( No lo graste terminar el Juego" + "\n"
                                + "____________________________" + "\n");

                for (int i = 0; i < i_n.bto_letras.length; i++) {

                    i_n.bto_letras[i].setEnabled(false);
                }
                esta_Detenido = false;
                num_aciertos = 0;
                cont = 0;
                cont_error = 0;
                repe++;
                i_n.bto_update.setEnabled(true);
                repe++;
                play = true;
            }

            if (num_aciertos == 5) {

                i_n.info.append(
                        "Juego N°" + repe + "\n" + "Tiempo Utilizado : " + i_n.jlabel_tiempo.getText() + " Seg"
                                + "\n"
                                + "Errores : " + i_n.jlabel_errores.getText() + "\n"
                                + "Aciertos : " + i_n.jlabel_aciertos.getText() + "\n"
                                + "haz logrado terminar el juego" + "\n"
                                + "_____________________________" + "\n");

                esta_Detenido = false; // detengo el hilo
                Cronometro.detener();
                JOptionPane.showMessageDialog(null, "Ganaste");
                i_n.bto_update.setEnabled(true);

                for (int i = 0; i < i_n.bto_letras.length; i++) {

                    i_n.bto_letras[i].setEnabled(false);
                }

                num_aciertos = 0;
                cont = 0;
                cont_error = 0;
                repe++;
                play = true;
            }

        } while (esta_Detenido != false);

    }

    public void Reiniciar_Todo() {
        time.reiniciarcron(0);
        i_n.jlabel_errores.setText("0");
        i_n.jlabel_aciertos.setText("0");
        esta_Detenido = true;
        h3 = new Thread(this);
        Cronometro.setDetenido(false);
        h3.start();
        BD_imagenes.pausar = false;
        Lienzo.pausar = false;
        Lienzo_arriba.pausar = false;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        BD_imagenes.velocidad = i_n.velocidad.getValue();
    }
}
