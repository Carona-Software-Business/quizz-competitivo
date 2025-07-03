
package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.*;

public class InterfaceRanking extends JDialog {
    
    String recebeNome;
    JPanel painelNorte;
    JPanel painelOeste;
    JPanel painelLeste;
    JPanel painelSul;
    JLabel titulo;
    
    
    public InterfaceRanking(JFrame pai){
        
        super(pai, "Ranking - QUIZ", false);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int largura = screenSize.width;
        int altura = screenSize.height;

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            setUndecorated(true);
            gd.setFullScreenWindow(this); 
        } else {
            //setExtendedState(JFrame.MAXIMIZED_BOTH); 
        }
        
        setLayout(new BorderLayout());
        
        painelNorte = new JPanel();
        painelOeste = new JPanel();
        painelLeste = new JPanel();
        painelSul = new JPanel();
        
        titulo = new JLabel("RANKING");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 50));
        painelNorte.add(titulo);
        
        painelNorte.setPreferredSize(new Dimension(50, 100));
        painelOeste.setPreferredSize(new Dimension(400, 50));
        painelLeste.setPreferredSize(new Dimension(400, 50));
        painelSul.setPreferredSize(new Dimension(50, 100));
        
        add(painelNorte, BorderLayout.NORTH);
        add(painelOeste, BorderLayout.WEST);
        add(painelLeste, BorderLayout.EAST);
        add(painelSul, BorderLayout.SOUTH);
        
        painelSul.setBackground(Color.BLACK);
        painelNorte.setBackground(Color.BLACK);
        painelOeste.setBackground(Color.BLACK);
        painelLeste.setBackground(Color.BLACK);
        
        DefaultListModel<String> modeloLista = new DefaultListModel();
        //exemplo:
        String nomesIniciais = ("1. Igor, 32 pontos");
        
        for(int i = 0; i < 100; i++){
            modeloLista.addElement(nomesIniciais);
        }
        
        JList<String> listaRanking = new JList<>(modeloLista);
        JScrollPane deslizadorLista = new JScrollPane(listaRanking);
        
        listaRanking.setFont(new Font("Arial", Font.BOLD, 30));
        
        add(deslizadorLista, BorderLayout.CENTER);
        
        setVisible(true);
        
    }
    
    
    
}
