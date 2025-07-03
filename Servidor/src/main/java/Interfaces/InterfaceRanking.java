package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InterfaceRanking extends JDialog {
    private JPanel painelNorte;
    private JPanel painelCentro;
    private JPanel painelSul;
    
    private JLabel labelTitulo;
    private JLabel labelRodape;
    private JLabel labelNomes;

    public InterfaceRanking(JFrame pai) {
        super(pai, "Ranking", false);
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(Color.YELLOW);
        setSize(1000, 800);
        
        painelNorte = new JPanel();
        painelNorte.setBackground(Color.YELLOW);
        add(painelNorte, BorderLayout.NORTH);
        
        labelTitulo = new JLabel("RANKING");
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 48));
        painelNorte.add(labelTitulo);
        
        // No painel central virá o estilo do rank
        painelCentro = new JPanel();
        painelCentro.setBackground(Color.red);
        add(painelCentro, BorderLayout.CENTER);
        
        
        painelSul = new JPanel(new GridLayout(1, 2));
        painelSul.setBackground(Color.YELLOW);
        add(painelSul, BorderLayout.SOUTH);
        
        labelRodape = new JLabel("Quizz Competitivo");
        labelRodape.setFont(new Font("SansSerif", Font.PLAIN, 24));
        painelSul.add(labelRodape);
        
        labelNomes = new JLabel("Guilherme - Igor - João - Kaun");
        labelNomes.setFont(new Font("SansSerif", Font.PLAIN, 24));
        painelSul.add(labelNomes);
        
        setVisible(true);
    }
    
}
