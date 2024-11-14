package Arango_Usuga_Jhan_Carlos.View;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


import Arango_Usuga_Jhan_Carlos.Controller.Controladora_Nivel2;
import Arango_Usuga_Jhan_Carlos.Model.BD_imagenes;

public class Lienzo extends Canvas implements Runnable {

    Boolean bandera = false;
    int x = 0, y = 10;
    String ima;
    public static boolean pausar = false, terminar = false;

    public Lienzo() {

    }

    public void pintar(String ima) {
        this.ima = ima;
        repaint();
    }

    @Override
    public void paint(Graphics g) {

        if ((bandera)) {
            Toolkit t = Toolkit.getDefaultToolkit();
            Image imagen = t.getImage(ima);
            g.drawImage(imagen, x, 15, 140, 140, this);
        }
    }

    public Boolean getBandera() {
        return bandera;
    }

    public void setBandera(Boolean bandera) {
        this.bandera = bandera;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void run() {
        terminar = false;
        while (x < 1350) {

            try {
                Thread.sleep(BD_imagenes.velocidad);
                repaint();
                x = x + 15;
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            while (pausar == true) {
                System.out.println("...");
                if (Controladora_Nivel2.pausado == 1) {
                    pausar = false;
                }

            }
        }
        terminar =true;
    }

    public static boolean isTerminar() {
        return terminar;
    }

    
}
