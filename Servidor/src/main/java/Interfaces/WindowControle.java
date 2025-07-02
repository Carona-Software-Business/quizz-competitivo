package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WindowControle extends JFrame {
    private JLabel titulo;
    private JButton botaoFecharRanking;
    private JButton botaoMostrarRaking;
    private JButton botaoDesligarServidor;
    
    public WindowControle() {
        setTitle("Controle Servidor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400); // tamanho pequeno
        setResizable(false);
        setLocationRelativeTo(null); // centraliza a janela

        JPanel painelControle = new JPanel();
        painelControle.setLayout(new BoxLayout(painelControle, BoxLayout.Y_AXIS));
        painelControle.setBackground(Color.GRAY);

        titulo = new JLabel("Controle Servidor", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoFecharRanking = new JButton("FECHAR RANKING");
        botaoFecharRanking.setBackground(new Color(0, 123, 255));
        botaoFecharRanking.setForeground(Color.WHITE);
        botaoFecharRanking.setFocusPainted(false);
        botaoFecharRanking.setFont(new Font("SansSerif", Font.BOLD, 32));
        botaoFecharRanking.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoMostrarRaking = new JButton("MOSTRAR RANKING");
        botaoMostrarRaking.setBackground(new Color(0, 123, 255));
        botaoMostrarRaking.setForeground(Color.WHITE);
        botaoMostrarRaking.setFocusPainted(false);
        botaoMostrarRaking.setFont(new Font("SansSerif", Font.BOLD, 32));
        botaoMostrarRaking.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoDesligarServidor = new JButton("DESLIGAR SERVIDOR");
        botaoDesligarServidor.setBackground(new Color(0, 123, 255));
        botaoDesligarServidor.setForeground(Color.WHITE);
        botaoDesligarServidor.setFocusPainted(false);
        botaoDesligarServidor.setFont(new Font("SansSerif", Font.BOLD, 32));
        botaoDesligarServidor.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Listeners
        botaoFecharRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("RANKING FECHADO!");
                
            }
        });

        botaoMostrarRaking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("RANKING MOSTRADO!");
               
            }
        });

        botaoDesligarServidor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("DESLIGANDO SERVIDOR!");
                 
            }
        });

        painelControle.add(titulo);
        painelControle.add(Box.createRigidArea(new Dimension(0, 40)));
        painelControle.add(botaoFecharRanking);
        painelControle.add(Box.createRigidArea(new Dimension(0, 40)));
        painelControle.add(botaoMostrarRaking);
        painelControle.add(Box.createRigidArea(new Dimension(0, 40)));
        painelControle.add(botaoDesligarServidor);

        add(painelControle, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new WindowControle();
    }
}
