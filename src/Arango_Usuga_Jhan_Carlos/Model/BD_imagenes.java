package Arango_Usuga_Jhan_Carlos.Model;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Arango_Usuga_Jhan_Carlos.Controller.Controladora_Nivel2;

import java.awt.Image;

public class BD_imagenes extends Thread {
    private JButton[] bto_imagen;

    private ImageIcon imagen_generada;
    private byte ale;
    public static boolean fin = false;
    public static boolean pausar = false;
    public static int velocidad;
    private final String[] ruta_imagen = { "A.png", "B.png", "C.png", "D.png", "E.png", "F.png", "G.png", "H.png",
            "I.png", "J.png", "K.png", "L.png", "M.png", "N.png", "O.png", "P.png", "Q.png", "R.png", "S.png", "T.png",
            "U.png", "V.png", "W.png", "X.png", "Y.png", "Z.png" };

    public BD_imagenes(JButton[] bto_imagen, int velocidad) {
        BD_imagenes.velocidad = velocidad;
        this.bto_imagen = bto_imagen;

    }

    public BD_imagenes() {

    }

    public byte generar_imagen_aleatoria() {
        return (byte) (Math.random() * 26);
    }

    public char encontrarLetraObjetivo(JButton[] bto_imagen) {
        int[] frecuencia = new int[26];
        for (byte j = 0; j < bto_imagen.length; j++) {
            String nombre_ruta = "";
            try {
                nombre_ruta = ruta_imagen[bto_imagen[j].getName().charAt(0) - 'A'];
                frecuencia[nombre_ruta.charAt(0) - 'A']++;
            } catch (Exception e) {
                System.out.println("Cargando Imagenes");
            }

        }
        for (char letra = 'A'; letra <= 'Z'; letra++) {
            if (frecuencia[letra - 'A'] == 5) {
                return letra;
            }
        }
        // Si ninguna letra aparece 5 veces, se devuelveuna letra por defecto
        return 'C';
    }

    public void Imagenes_aleatorio(JButton[] bto_imagen) throws InterruptedException {
        // bto_imagen = bto_imagen;
        for (byte j = 0; j < bto_imagen.length; j++) {
            ale = generar_imagen_aleatoria();
            imagen_generada = new ImageIcon("src/IMG/" + ruta_imagen[ale]);

            String nombre_ruta = ruta_imagen[ale];
            bto_imagen[j].setIcon(new ImageIcon(imagen_generada.getImage().getScaledInstance(50, 50, Image.SCALE_FAST)));
            bto_imagen[j].setName(nombre_ruta);

        }
    }

    public boolean isDetenido() {
        return fin; // Método para obtener el estado de la bandera
    }

    public static void setFin(boolean fin) {
        BD_imagenes.fin = fin;
    }

    @Override
    public void run() {
        try {

            for (byte j = 0; j < bto_imagen.length; j++) {
                ale = generar_imagen_aleatoria();
                imagen_generada = new ImageIcon("src/IMG/" + ruta_imagen[ale]);

                sleep(velocidad);
                String nombre_ruta = ruta_imagen[ale];
                bto_imagen[j]
                        .setIcon(new ImageIcon(imagen_generada.getImage().getScaledInstance(50, 50, Image.SCALE_FAST)));
                bto_imagen[j].setName(nombre_ruta);

                if (j == bto_imagen.length - 1) {
                    fin = true; // para validar que esten todos lo botoens con imagenes
                }

                while (pausar == true) {
                    System.out.println("...");
                    if (Controladora_Nivel2.pausado == 1) {
                        pausar = false;
                    }

                }
            }
        } catch (InterruptedException e1) {

            e1.printStackTrace();
        }
    }

    public void setVelocidad(int velocidad) {
        BD_imagenes.velocidad = velocidad;
    }
}
