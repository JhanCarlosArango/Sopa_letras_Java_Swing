package Arango_Usuga_Jhan_Carlos.View;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


import Arango_Usuga_Jhan_Carlos.Controller.Controladora_Conf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;


/**
 *
 * @author parko
 */
public class Conf_Ventana extends JFrame {

    String[] niveles = { "", "Nivel 1", "Nivel 2" };
    JPanel jPanel_lienzo, jPanel_conf;
    JLabel label_img_sopa ;
    public JComboBox<String> nivel;
    public JButton bto_guarda,btnColorChooserA,btnColorChooserE;
    public JRadioButton l, n, r;
    public static JTextField txt_tiempo;
    Color kcolor = null, color = null;
    JColorChooser colorChooser;
    ImageIcon img_parner;
    ImageIcon _next;
    Controladora_Conf control = new Controladora_Conf(this);
    ButtonGroup br;

    public Conf_Ventana() {
        setTitle("Configuracion de Sopa de Letras");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        init_componet();
    }

    private void init_componet() {
        jpane();
        jlabel();
        jtextfield();
        jradio();
        jcombobox();
        jbutton();
        jchoosecolor();
    }

    private void jchoosecolor() {
        colorChooser = new JColorChooser();

        btnColorChooserA = new JButton("                            ");
        btnColorChooserA.addActionListener(control);
        btnColorChooserA.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnColorChooserA.setBackground(new Color(255, 255, 255));
        btnColorChooserA.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Color aciertos ", TitledBorder.CENTER,
        TitledBorder.DEFAULT_POSITION));

        btnColorChooserE = new JButton("                            ");
        btnColorChooserE.addActionListener(control);
        btnColorChooserE.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnColorChooserE.setBackground(new Color(255, 255, 255));
        btnColorChooserE.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Color errores ", TitledBorder.CENTER,
        TitledBorder.DEFAULT_POSITION));

        jPanel_lienzo.add(btnColorChooserA, BorderLayout.EAST);
        jPanel_lienzo.add(btnColorChooserE, BorderLayout.WEST);


    }

    private void jcombobox() {
        nivel = new JComboBox<String>(niveles);
        nivel.setBorder(BorderFactory.createTitledBorder("Dificultad"));
        jPanel_conf.add(nivel, BorderLayout.CENTER);
    }

    private void jradio() {
        l = new JRadioButton("Lento");
        l.addActionListener(control);
        jPanel_conf.add(l);

        n = new JRadioButton("Normal");
        n.addActionListener(control);
        jPanel_conf.add(n);

        r = new JRadioButton("Rapido");
        r.addActionListener(control);
        jPanel_conf.add(r);

        br = new ButtonGroup();
        br.add(l);
        br.add(n);
        br.add(r);
    }

    private void jtextfield() {

        txt_tiempo = new JTextField("", JTextField.CENTER);
        txt_tiempo.setBackground(null);
        txt_tiempo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Segundos ", TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION));
        txt_tiempo.setFont(new Font("Tahoma", Font.BOLD, 20));
        txt_tiempo.setSize(new Dimension(150, 40));
        jPanel_conf.add(txt_tiempo);

    }

    private void jbutton() {
        _next = new ImageIcon("src/IMG/next.png");
        bto_guarda = new JButton();
        bto_guarda.setIcon(new ImageIcon(_next.getImage().getScaledInstance(40, 40, Image.SCALE_FAST)));
        bto_guarda.setBorder(null);
        bto_guarda.setBackground(null);
        bto_guarda.addActionListener(control);
        jPanel_conf.add(bto_guarda);
    }

    private void jlabel() {
        img_parner = new ImageIcon("src/IMG/paner.jpg");
        label_img_sopa = new JLabel();
        label_img_sopa.setIcon(new ImageIcon(img_parner.getImage().getScaledInstance(600, 230, Image.SCALE_DEFAULT)));
        jPanel_lienzo.add(label_img_sopa, BorderLayout.CENTER);

    }

    private void jpane() {

        jPanel_lienzo = new JPanel(new BorderLayout());
        add(jPanel_lienzo);

        jPanel_conf = new JPanel(new GridLayout(1, 5));
        jPanel_conf.setBorder(BorderFactory.createLineBorder(Color.green));
        jPanel_lienzo.add(jPanel_conf, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        Conf_Ventana view = new Conf_Ventana();
        view.setVisible(true);
    }

}
