package Arango_Usuga_Jhan_Carlos.Model;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Validacion {

    boolean lleno, mayor;

    public Validacion() {

    }

    public char String_to_char(String letra_boton) {
        char letra = 'A';
        try {
            letra = letra_boton.charAt(0);
        } catch (NullPointerException e) {
            System.out.println("Cargando Imagenes");
        }

        return letra;
    }

    public char letraAleatoria() {

        Random random = new Random();
        char letra = (char) (random.nextInt(26) + 'A');

        return letra;
    }

    public boolean VerificarCampos(JTextField tid) {

        if ((tid.getText().trim().isEmpty())) {
            lleno = true;
            JOptionPane.showMessageDialog(null, "Ingrese los segundo");

        } else {
            lleno = false;

        }
        return lleno;

    }

    public boolean menor60(JTextField tid) {
        int valor = 0;
        try {

            valor = Integer.parseInt(tid.getText());
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Gigite un Numero");
        }
        if (valor > 60) {
            JOptionPane.showMessageDialog(null, "Máximo 60 segundos");
            return false;
        } else {
            return true;
        }
    }

    public void validarsolonumero(KeyEvent ke, JTextField txt) {

        if (ke.getSource() == txt) {
            char tecla = ke.getKeyChar();
            if (Character.isLetter(tecla)) {

                ke.consume();
            }

        }
    }

    public Boolean ValidarColor(Color aciertos, Color errores) {
        Boolean iguales = true;
        if (aciertos != null && errores != null) {
            System.out.println("no null");
            Color darkerColor = aciertos.darker();
            Color darkerKcolor = errores.darker();
            if (!darkerColor.equals(darkerKcolor)) {
                iguales = false;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione Color");
        }
        return iguales;
    }
}
