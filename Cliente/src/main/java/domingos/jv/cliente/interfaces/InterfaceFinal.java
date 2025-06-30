package domingos.jv.cliente.interfaces;
import domingos.jv.cliente.Principal;
import domingos.jv.cliente.logica.GameController;
import domingos.jv.cliente.logica.Jogador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceFinal extends JFrame{
    
    JLabel tituloFinal;
    JLabel pontuacaoFinal;
    JPanel painelFinalCentro;
    JPanel painelFinalEsquerda;
    JPanel painelFinalDireita;
    JPanel painelFinalNorte;
    JPanel painelFinalSul;
    JButton botaoProximo;
    JLabel tempoFinal;
    JLabel qtdAcertos;
    
    Jogador player;
    
    public InterfaceFinal(GameController gameController){
        
        player = gameController.enviarResultado(Principal.leitor, Principal.escritor);
        
        JOptionPane.showMessageDialog(null, player.getPosicao(), "Posicao", 
                JOptionPane.INFORMATION_MESSAGE);
        
        setLayout(new BorderLayout());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            setUndecorated(true);
            gd.setFullScreenWindow(this);
        } else {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        
        tituloFinal = new JLabel("FIM DE JOGO!");
        pontuacaoFinal = new JLabel("Sua pontuação final é: " + 
                player.getPontuacaoTotal() + " pontos");
        tempoFinal = new JLabel("Tempo total: " + player.getTempoTotal() + " segundos");
        painelFinalCentro = new JPanel(new GridLayout(4, 0));
        painelFinalEsquerda = new JPanel(new FlowLayout());
        painelFinalDireita = new JPanel(new GridBagLayout());
        painelFinalNorte = new JPanel(new FlowLayout());
        painelFinalSul = new JPanel(new FlowLayout());
        botaoProximo = new JButton("PRÓXIMO JOGADOR");
        qtdAcertos = new JLabel("Quantidade de acertos: " + 
                gameController.getAcertos() + " questões");
        
        botaoProximo.setPreferredSize(new Dimension(250, 100));
        botaoProximo.setBackground(Color.WHITE);
        botaoProximo.setForeground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        
        painelFinalCentro.setBackground(Color.YELLOW);
        painelFinalDireita.setBackground(Color.YELLOW);
        painelFinalEsquerda.setBackground(Color.YELLOW);
        painelFinalNorte.setBackground(Color.YELLOW);
        painelFinalSul.setBackground(Color.YELLOW);
        
        tituloFinal.setFont(new Font("Arial", Font.BOLD, 100));
        tituloFinal.setForeground(Color.BLACK);
        pontuacaoFinal.setFont(new Font("Arial", Font.BOLD, 35));
        pontuacaoFinal.setForeground(Color.BLACK);
        botaoProximo.setFont(new Font("Arial", Font.BOLD, 20));
        botaoProximo.setVerticalAlignment(SwingConstants.CENTER);
        botaoProximo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        tempoFinal.setFont(new Font("Arial", Font.BOLD, 35));
        tempoFinal.setForeground(Color.BLACK);
        qtdAcertos.setFont(new Font("Arial", Font.BOLD, 35));
        qtdAcertos.setForeground(Color.BLACK);
        
        painelFinalEsquerda.setPreferredSize(new Dimension(300, 0));
        painelFinalDireita.setPreferredSize(new Dimension(300, 0));
        painelFinalNorte.setPreferredSize(new Dimension(0, 100));
        painelFinalSul.setPreferredSize(new Dimension(0, 100));
        
        painelFinalCentro.add(tituloFinal);
        painelFinalCentro.add(pontuacaoFinal);
        painelFinalCentro.add(tempoFinal);
        painelFinalCentro.add(qtdAcertos);
        painelFinalDireita.add(botaoProximo, gbc);
        
        add(painelFinalCentro, BorderLayout.CENTER);
        add(painelFinalEsquerda, BorderLayout.WEST);
        add(painelFinalDireita, BorderLayout.EAST);
        add(painelFinalSul, BorderLayout.SOUTH);
        add(painelFinalNorte, BorderLayout.NORTH);
        
        botaoProximo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new WindowMain();
                    dispose();
                    setVisible(false);
                }
            });


        setVisible(true);
    }
    
}
