package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
        
        
        String[] columnNames = {"Posição",
                                "Nome",
                                "Pontos"
        };

        Object[][] nomesEPontos = {
            {"João", "10",},
            {"Kauan", "55"},
            {"Guilherme", "20"},
            {"Lenda", "30"},
            {"Gabriel", "50",},
            {"Augusto", "32"},
            {"Jairo", "12"},
            {"Jabuti", "300"}
        };
        Object[][] data = new Object[nomesEPontos.length][3];
            
        for (int i = 0; i < nomesEPontos.length; i++) {
            data[i][0] = i + 1;
            data[i][1] = nomesEPontos[i][0];
            data[i][2] = nomesEPontos[i][1];
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames){
        @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
            
        JTable tabela = new JTable(model);
        
            
        JScrollPane scrollPane = new JScrollPane(tabela);

        tabela.setFillsViewportHeight(true);
            
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(JLabel.CENTER);
            
        for (int i = 0; i < tabela.getColumnCount(); i++){
            tabela.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
        tabela.setRowHeight(50);
        //tabela.setRowHeight(0, 75); aumenta a primeira linha
        tabela.setPreferredScrollableViewportSize(new Dimension(300, 300));
        tabela.setShowVerticalLines(false);
        
        tabela.getTableHeader().setFont(
            tabela.getTableHeader().getFont().deriveFont(Font.BOLD, 28)
        );
        tabela.setFont(tabela.getFont().deriveFont(Font.PLAIN, 28));
       
        Color fundoTabela = new Color(250, 250, 250);
        Color textoTabela = Color.BLACK;
        Color grade = Color.LIGHT_GRAY;
        Color selecao = Color.BLUE;
        Color cabecalhoFundo = Color.LIGHT_GRAY; 
        Color cabecalhoTexto = new Color(50, 0, 20);

        tabela.setBackground(fundoTabela);
        tabela.setForeground(textoTabela);
        tabela.setGridColor(grade);

        tabela.setSelectionBackground(selecao);
        tabela.setSelectionForeground(textoTabela);

        tabela.getTableHeader().setBackground(cabecalhoFundo);
        tabela.getTableHeader().setForeground(cabecalhoTexto);
        tabela.getTableHeader().setFont(tabela.getTableHeader().getFont().deriveFont(Font.BOLD));

        // No painel central virá o estilo do rank
        painelCentro = new JPanel(new BorderLayout());
        painelCentro.setBackground(Color.YELLOW);
        painelCentro.setBorder(new EmptyBorder(20, 200, 20, 200));
        painelCentro.add(scrollPane, BorderLayout.CENTER);
        add(painelCentro, BorderLayout.CENTER);
        
        
        painelSul = new JPanel(new GridLayout(1, 2));
        painelSul.setBackground(Color.YELLOW);
        add(painelSul, BorderLayout.SOUTH);
        
        labelRodape = new JLabel("   Quizz Competitivo");
        labelRodape.setFont(new Font("SansSerif", Font.PLAIN, 24));
        painelSul.add(labelRodape);
        
        labelNomes = new JLabel("Guilherme - Igor - João - Kauan");
        labelNomes.setFont(new Font("SansSerif", Font.PLAIN, 24));
        painelSul.add(labelNomes);
        
        setVisible(true);
    }
    
}
