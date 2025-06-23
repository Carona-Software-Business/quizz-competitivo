package domingos.jv.cliente.interfaces;
import domingos.jv.cliente.logica.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceErro extends JFrame{
    
    JLabel tituloFinal;
    JLabel pontuacaoFinal;
    JPanel painelFinalCentro;
    JPanel painelFinalEsquerda;
    JPanel painelFinalDireita;
    JPanel painelFinalNorte;
    JPanel painelFinalSul;
    JButton botaoProximo;
    JLabel perguntasRestantes;
    
    public InterfaceErro(GameController gameController, int acertos){
        
        setLayout(new BorderLayout());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int largura = screenSize.width;
        int altura = screenSize.height;

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            setUndecorated(true);
            gd.setFullScreenWindow(this);
        } else {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        
        tituloFinal = new JLabel("ERROU!");
        pontuacaoFinal = new JLabel("Seus acertos: " + acertos + "/9");
        painelFinalCentro = new JPanel(new GridLayout(3, 0));
        painelFinalEsquerda = new JPanel(new FlowLayout());
        painelFinalDireita = new JPanel(new GridBagLayout());
        painelFinalNorte = new JPanel(new FlowLayout());
        painelFinalSul = new JPanel(new FlowLayout());
        botaoProximo = new JButton("PRÓXIMA PERGUNTA");
        perguntasRestantes = new JLabel("Perguntas restantes: ");
        
        botaoProximo.setPreferredSize(new Dimension(250, 100));
        botaoProximo.setBackground(Color.WHITE);
        botaoProximo.setForeground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        
        painelFinalCentro.setBackground(Color.RED);
        painelFinalDireita.setBackground(Color.RED);
        painelFinalEsquerda.setBackground(Color.RED);
        painelFinalNorte.setBackground(Color.RED);
        painelFinalSul.setBackground(Color.RED);
        
        tituloFinal.setFont(new Font("Arial", Font.BOLD, 100));
        tituloFinal.setForeground(Color.WHITE);
        pontuacaoFinal.setFont(new Font("Arial", Font.BOLD, 50));
        pontuacaoFinal.setForeground(Color.WHITE);
        botaoProximo.setFont(new Font("Arial", Font.BOLD, 20));
        botaoProximo.setVerticalAlignment(SwingConstants.CENTER);
        botaoProximo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        perguntasRestantes.setFont(new Font("Arial", Font.BOLD, 50));
        perguntasRestantes.setForeground(Color.WHITE);
        
        painelFinalEsquerda.setPreferredSize(new Dimension(300, 0));
        painelFinalDireita.setPreferredSize(new Dimension(300, 0));
        painelFinalNorte.setPreferredSize(new Dimension(0, 100));
        painelFinalSul.setPreferredSize(new Dimension(0, 100));
        
        painelFinalCentro.add(tituloFinal);
        painelFinalCentro.add(pontuacaoFinal);
        painelFinalCentro.add(perguntasRestantes);
        painelFinalDireita.add(botaoProximo, gbc);
        
        add(painelFinalCentro, BorderLayout.CENTER);
        add(painelFinalEsquerda, BorderLayout.WEST);
        add(painelFinalDireita, BorderLayout.EAST);
        add(painelFinalSul, BorderLayout.SOUTH);
        add(painelFinalNorte, BorderLayout.NORTH);

        //Botao do proximo, apenas fazer a logica
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameController.getQuantidadesPerguntas() < 9){
                    new InterfacePergunta(gameController, gameController.escolherPergunta());
                    dispose();
                    setVisible(false);
                }else 
                    new InterfaceFinal();
                    dispose();
                    setVisible(false);
            }
        };
        
        botaoProximo.addActionListener(listener);
        
        setVisible(true);
    }
    
}
