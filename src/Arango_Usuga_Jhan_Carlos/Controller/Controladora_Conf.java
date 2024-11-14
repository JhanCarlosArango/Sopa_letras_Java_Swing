package Arango_Usuga_Jhan_Carlos.Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import Arango_Usuga_Jhan_Carlos.Model.BD_imagenes;
import Arango_Usuga_Jhan_Carlos.Model.Validacion;
import Arango_Usuga_Jhan_Carlos.View.Conf_Ventana;
import Arango_Usuga_Jhan_Carlos.View.Letra_Ventana;
import Arango_Usuga_Jhan_Carlos.View.Nivel2_Ventana;

public class Controladora_Conf implements ActionListener, KeyListener {

    Conf_Ventana i_c;
    Letra_Ventana view_letras;
    Nivel2_Ventana view_nivel2;
    BD_imagenes setImagenes, setImagenes_nivel2;
    Validacion vali;
   public static Color color_acierots, color_erroes;
    int velocidad = 0;

    public Controladora_Conf(Conf_Ventana i_c) {
        this.i_c = i_c;
        vali = new Validacion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == i_c.btnColorChooserA) {
            color_acierots = JColorChooser.showDialog(null, "Aciertos", i_c.btnColorChooserA.getBackground());
            if (color_acierots != null) {
                i_c.btnColorChooserA.setBackground(color_acierots);
            }
        }

        if (e.getSource() == i_c.btnColorChooserE) {
            color_erroes = JColorChooser.showDialog(null, "Errores", i_c.btnColorChooserE.getBackground());
            if (color_erroes != null) {
                i_c.btnColorChooserE.setBackground(color_erroes);
            }
        }
        if (i_c.l.isSelected()) {
            System.out.println("Lento");
            velocidad = 2000;
        } else if (i_c.n.isSelected()) {
            System.out.println("normal");
            velocidad = 1000;
        } else if (i_c.r.isSelected()) {
            System.out.println("Rapido");
            velocidad = 100;
        }

        if (e.getSource() == i_c.bto_guarda) {
            if (vali.ValidarColor(color_acierots, color_erroes) == false) {
                System.out.println("--------");
                if (velocidad != 0) {
                    if ((vali.VerificarCampos(Conf_Ventana.txt_tiempo) != true)) {
                        if ((vali.menor60(Conf_Ventana.txt_tiempo) == true)) {
                            if (i_c.nivel.getSelectedIndex() == 1) {
                                view_letras = new Letra_Ventana();

                                setImagenes = new BD_imagenes(view_letras.bto_letras, velocidad);
                                setImagenes.start();
                            } else if (i_c.nivel.getSelectedIndex() == 2) {
                                view_nivel2 = new Nivel2_Ventana();
                                setImagenes_nivel2 = new BD_imagenes(view_nivel2.bto_letras, velocidad);
                                setImagenes_nivel2.start();
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione Velocidad");
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        vali.validarsolonumero(ke, Conf_Ventana.txt_tiempo);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
