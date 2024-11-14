package Arango_Usuga_Jhan_Carlos.View;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Arango_Usuga_Jhan_Carlos.Controller.Controladora_Nivel2;
import Arango_Usuga_Jhan_Carlos.Model.BD_imagenes;

public class Lienzo_arriba extends Canvas implements Runnable {

    Boolean bandera = false;
    public static boolean pausar = false, terminar = false;
    // int x = -135, y = 205; sirvio de base para calcular
    int x = -1315, y = 208;
    String ima;

    public Lienzo_arriba() {

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
            g.drawImage(imagen, x, y, 140, 140, this);
        }
        // g.drawLine(0, 30, 130, 30);
    }

    public Boolean getBandera() {
        return bandera;
    }

    public void setBandera(Boolean bandera) {
        this.bandera = bandera;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void run() {
        terminar = false;
        while (x < 30) {
            try {
                Thread.sleep(BD_imagenes.velocidad);
                repaint();
                x = x + 15;
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            while (pausar == true) {
                System.out.println("..."); // super importante esta liena, hace funciiona el pausado
                if (Controladora_Nivel2.pausado == 1) {
                    pausar = false;
                    break;
                }

            }
        }

        while (y > 30) {

            try {
                Thread.sleep(BD_imagenes.velocidad);
                repaint();
                y = y - 15;
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            while (pausar == true) {
                System.out.println("...");
                if (Controladora_Nivel2.pausado == 1) {
                    pausar = false;
                    break;
                }

            }
        }
        terminar = true;
    }

    public static boolean isTerminar() {
        return terminar;
    }

    
}
