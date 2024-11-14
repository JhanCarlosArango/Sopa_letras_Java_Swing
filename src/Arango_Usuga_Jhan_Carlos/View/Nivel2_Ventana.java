package Arango_Usuga_Jhan_Carlos.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Arango_Usuga_Jhan_Carlos.Controller.Controladora_Conf;
import Arango_Usuga_Jhan_Carlos.Controller.Controladora_Nivel2;

public class Nivel2_Ventana extends JFrame {

    public static JPanel p_grid_letras;
    JPanel lienzo, p_nort,  panel_inferior, p_info, panel_centro;
    ImageIcon _play, _stop, _update, _close, _pausar;
    public JLabel jlabel_aciertos, jlabel_errores, jlabel_tiempo, jlabel_buscar;
    public JButton bto_letras[], boton_terminar, bto_play, bto_update, boton_close, pausar_reanudar;
    public JTextArea info;
    JScrollPane scrollPane;
    Controladora_Nivel2 control;
    public Lienzo cv_sout;
    public Lienzo_arriba cv_east;
    public JSlider velocidad;
    Font t = new Font("Tahoma", Font.BOLD, 15);

    public Nivel2_Ventana() {
        control = new Controladora_Nivel2(this);
        setTitle("Sopa de Letras NIVEL 2");
        // setSize(1380, 820);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setUndecorated(true);

        init_componet();
        setVisible(true);
    }

    private void init_componet() {
        jpane();
        jlabel();
        jbutton();
        jarea();
        Slieder();
    }

    private void jarea() {
        info = new JTextArea();
        info.setSize(new Dimension(200, 600));
        info.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "REGISTRO", TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION));
        info.setLineWrap(true);
        info.setEditable(false);
        info.setBackground(null);
        scrollPane = new JScrollPane(info);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(new Color(255, 255, 255));
        p_info.add(scrollPane);

        cv_east = new Lienzo_arriba();
        cv_east.setBackground(new Color(255, 255, 255));
        System.out.println(cv_east.getWidth());
        p_info.add(cv_east);
    }

    private void jbutton() {
        bto_letras = new JButton[100];

        for (int i = 0; i < bto_letras.length; i++) {
            p_grid_letras.add(bto_letras[i] = new JButton());
            bto_letras[i].addActionListener(control);
            bto_letras[i].setBackground(new Color(255, 255, 255));
        }

        _play = new ImageIcon("src/IMG/play.png");

        bto_play = new JButton("JUGAR");
        bto_play.setForeground(Color.WHITE);
        bto_play.setIcon(new ImageIcon(_play.getImage().getScaledInstance(60, 60, Image.SCALE_FAST)));
        bto_play.setBorder(null);
        bto_play.setBackground(null);
        bto_play.addActionListener(control);
        panel_inferior.add(bto_play);

        _pausar = new ImageIcon("src/IMG/pausa.png");
        pausar_reanudar = new JButton("PAUSAR");
        pausar_reanudar.setForeground(Color.WHITE);
        pausar_reanudar.setIcon(new ImageIcon(_pausar.getImage().getScaledInstance(60, 60, Image.SCALE_FAST)));
        pausar_reanudar.setBorder(null);
        pausar_reanudar.setBackground(null);
        panel_inferior.add(pausar_reanudar);
        pausar_reanudar.addActionListener(control);

        _update = new ImageIcon("src/IMG/recargar.png");

        bto_update = new JButton("INTERNAR DE NUEVO");
        bto_update.setForeground(Color.WHITE);
        bto_update.setIcon(new ImageIcon(_update.getImage().getScaledInstance(60, 46, Image.SCALE_FAST)));
        bto_update.setBorder(null);
        bto_update.setBackground(null);
        bto_update.setEnabled(false);
        bto_update.addActionListener(control);
        panel_inferior.add(bto_update);

        _stop = new ImageIcon("src/IMG/stop.png");
        boton_terminar = new JButton("TERMINAR JUEGO");
        boton_terminar.setForeground(Color.WHITE);
        boton_terminar.setIcon(new ImageIcon(_stop.getImage().getScaledInstance(60, 60, Image.SCALE_FAST)));
        boton_terminar.setBorder(null);

        boton_terminar.setBackground(null);

        panel_inferior.add(boton_terminar);
        boton_terminar.addActionListener(control);

        _close = new ImageIcon("src/IMG/close.png");
        boton_close = new JButton("SALIR");
        boton_close.setForeground(Color.WHITE);
        boton_close.setIcon(new ImageIcon(_close.getImage().getScaledInstance(60, 60, Image.SCALE_FAST)));
        boton_close.setBorder(null);
        boton_close.setBackground(null);
        panel_inferior.add(boton_close);
        boton_close.addActionListener(control);

    }

    private void jlabel() {

        jlabel_aciertos = new JLabel("0", JLabel.CENTER);
        jlabel_aciertos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Controladora_Conf.color_acierots), "ACIERTOS", TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, t, Controladora_Conf.color_acierots));
        p_nort.add(jlabel_aciertos);

        jlabel_errores = new JLabel("0", JLabel.CENTER);
        jlabel_errores.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Controladora_Conf.color_erroes), "ERRORES", TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, t, Controladora_Conf.color_erroes));
        p_nort.add(jlabel_errores);

        jlabel_tiempo = new JLabel("0", JLabel.CENTER);
        jlabel_tiempo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "TIEMPO RESTANTE",TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, t));
        p_nort.add(jlabel_tiempo);
    }

    private void jpane() {
        lienzo = new JPanel(new BorderLayout());
        add(lienzo);

        p_nort = new JPanel(new GridLayout());
        p_nort.setBackground(new Color(255, 255, 255));
        lienzo.add(p_nort, BorderLayout.NORTH);

        panel_centro = new JPanel(new BorderLayout());
        panel_centro.setBackground(new Color(255, 255, 255));
        lienzo.add(panel_centro, BorderLayout.CENTER);

        p_grid_letras = new JPanel(new GridLayout(10, 10));
        panel_centro.add(p_grid_letras, BorderLayout.CENTER);

        cv_sout = new Lienzo();
        cv_sout.setBounds(0, 0, 1, 170);
        cv_sout.setBackground(new Color(255, 255, 255));
        panel_centro.add(cv_sout, BorderLayout.SOUTH);

        p_info = new JPanel(new GridLayout(2, 1));
        p_info.setBackground(new Color(255, 255, 255));
        lienzo.add(p_info, BorderLayout.EAST);

        panel_inferior = new JPanel(new GridLayout(1, 2));
        panel_inferior.setBackground(new Color(4, 82, 43));

        lienzo.add(panel_inferior, BorderLayout.SOUTH);
    }

    public void Slieder() {
        velocidad = new JSlider(0, 2000, 1000);
        velocidad.addChangeListener(control);
        velocidad.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "VELOCIDAD", TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,t));
        velocidad.setMajorTickSpacing(400);
        velocidad.setPaintTicks(true);
        velocidad.setPaintLabels(true);
        velocidad.setBackground(new Color(255, 255, 255));

        p_nort.add(velocidad);
    }

}