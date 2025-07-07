package Interfaces;

import Logica.EstatisticaJogador;
import java.awt.*;
import java.util.Map;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

public class InterfaceRanking extends JDialog {
    private JPanel painelNorte;
    private JPanel painelCentro;
    private JPanel painelSul;

    private JLabel labelTitulo;
    private JLabel labelRodape;
    private JLabel labelNomes;
    
    private DefaultTableModel model;

    public InterfaceRanking(List<Map.Entry<String, EstatisticaJogador>> rank) {
        //super(pai, "Ranking", false);

        setTitle("Ranking");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        // Cores
        Color corPrincipal = new Color(245, 245, 255);
        Color corCabecalho = new Color(80, 110, 200);
        Color corTextoCabecalho = Color.WHITE;
        Color corTexto = new Color(40, 40, 40);
        Color corRodape = new Color(230, 230, 250);
        Color corSelecao = new Color(200, 220, 255);

        setLayout(new BorderLayout());
        getContentPane().setBackground(corPrincipal);

        painelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelNorte.setBackground(corPrincipal);
        painelNorte.setBorder(new EmptyBorder(30, 10, 10, 10));
        add(painelNorte, BorderLayout.NORTH);

        labelTitulo = new JLabel("RANKING");
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 48));
        labelTitulo.setForeground(new Color(50, 50, 120));
        painelNorte.add(labelTitulo);

        painelCentro = new JPanel(new BorderLayout());
        painelCentro.setBackground(corPrincipal);
        painelCentro.setBorder(new EmptyBorder(10, 100, 10, 100));
        add(painelCentro, BorderLayout.CENTER);
        
        
        String[] columnNames = {"Posição", "Nome", "Pontos"};
        /*
        Object[][] nomesEPontos = {
            {"João", "10"}, {"Kauan", "55"}, {"Guilherme", "20"}, {"Lenda", "30"},
            {"Gabriel", "50"}, {"Augusto", "32"}, {"Jairo", "12"}, {"Jabuti", "300"},
            {"Jairo", "12"}, {"Jairo", "12"}
        };
        */
        
        Object[][] data = new Object[10][3];
        for (int i = 0; i < 10; i++) {
            String posicao;
            switch (i) {
                case 0: posicao = "🥇"; break;
                case 1: posicao = "🥈"; break;
                case 2: posicao = "🥉"; break;
                default: posicao = String.valueOf(i + 1);
            }
            data[i][0] = posicao;
            if(rank != null) {
                if(i < rank.size()) {
                    data[i][1] = rank.get(i).getKey();
                    data[i][2] = rank.get(i).getValue().getPontos();
                }
            }
        }

        model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        JTable tabela = new JTable(model) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    if (row == 0) c.setBackground(new Color(255, 240, 180));     // Ouro
                    else if (row == 1) c.setBackground(new Color(220, 220, 220)); // Prata
                    else if (row == 2) c.setBackground(new Color(255, 228, 196)); // Bronze
                    else c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(corSelecao);
                }
                return c;
            }
        };

        tabela.setRowHeight(52);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 24));
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 26));
        tabela.setShowVerticalLines(false);
        tabela.setGridColor(new Color(220, 220, 220));
        tabela.setForeground(corTexto);
        tabela.setSelectionBackground(corSelecao);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        JTableHeader header = tabela.getTableHeader();
        header.setBackground(corCabecalho);
        header.setForeground(corTextoCabecalho);
        header.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        painelCentro.add(scrollPane, BorderLayout.CENTER);
        

        painelSul = new JPanel(new GridLayout(1, 2));
        painelSul.setBackground(corRodape);
        painelSul.setBorder(new EmptyBorder(20, 50, 20, 50));
        add(painelSul, BorderLayout.SOUTH);

        labelRodape = new JLabel("Quizz Competitivo");
        labelRodape.setFont(new Font("SansSerif", Font.PLAIN, 24));
        labelRodape.setHorizontalAlignment(SwingConstants.LEFT);

        labelNomes = new JLabel("Guilherme - Igor - João - Kauan");
        labelNomes.setFont(new Font("SansSerif", Font.PLAIN, 24));
        labelNomes.setHorizontalAlignment(SwingConstants.RIGHT);

        painelSul.add(labelRodape);
        painelSul.add(labelNomes);

        setVisible(true);

    }
    
    public void atualizarTabela(List<Map.Entry<String, EstatisticaJogador>> rank) {
        int max;
        
        if(rank.size() <= 10)
            max = rank.size();
        else {
            max = 10;
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                for(int i = 0; i < max; i++) {
                    model.setValueAt(rank.get(i).getKey(), i, 1);
                    model.setValueAt(rank.get(i).getValue().getPontos(), i, 2);
                }
            }
        });
    }
}
