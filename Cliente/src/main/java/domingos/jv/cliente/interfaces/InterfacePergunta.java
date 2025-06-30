package domingos.jv.cliente.interfaces;

import domingos.jv.cliente.logica.GameController;
import domingos.jv.cliente.logica.Pergunta;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax    .swing.*;

public class InterfacePergunta extends JFrame {
    private JLabel tituloPerg;
    private JLabel campoPergunta;
    private JButton letra_a;
    private JButton letra_b;
    private JButton letra_c;
    private JButton letra_d;
    private String respostaRecebida;

    public InterfacePergunta(GameController gameController, Pergunta pergunta) {
        setTitle("Pergunta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        Color fundoAzul = new Color(220, 220, 220);
        getContentPane().setBackground(fundoAzul);

        JPanel painelPergunta = new JPanel();
        painelPergunta.setLayout(new BoxLayout(painelPergunta, BoxLayout.Y_AXIS));
        painelPergunta.setBorder(BorderFactory.createEmptyBorder(altura / 6, largura / 4, altura / 6, largura / 4));
        painelPergunta.setBackground(fundoAzul);

        tituloPerg = new JLabel("PERGUNTA", SwingConstants.CENTER);
        tituloPerg.setFont(new Font("Arial", Font.BOLD, 48));
        tituloPerg.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoPergunta = new JLabel(
            "<html><div style='text-align: center;'>" +
                    pergunta.getPergunta()
            + "</div></html>"
            );
        campoPergunta.setFont(new Font("SansSerif", Font.PLAIN, 26));
        campoPergunta.setOpaque(true);
        campoPergunta.setBackground(Color.WHITE);
        campoPergunta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        campoPergunta.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza horizontalmente
        campoPergunta.setVerticalAlignment(SwingConstants.CENTER);   // Centraliza verticalmente
        campoPergunta.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoPergunta.setPreferredSize(new Dimension(1200, 200));
        campoPergunta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200)); // corrigido altura

        letra_a = new JButton("<html><div style='text-align: center;'>" +
                              "A) " + pergunta.getAlternativas()[0] + 
                              "</div></html>");
        letra_a.setName("0");
        letra_a.setPreferredSize(new Dimension(500, 40));
        letra_a.setMaximumSize(new Dimension(500, 40));
        letra_a.setBackground(new Color(240, 128, 128));
        letra_a.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        letra_a.setForeground(Color.BLACK);
        letra_a.setFocusPainted(false);
        letra_a.setFont(new Font("SansSerif", Font.BOLD, 24));
        letra_a.setAlignmentX(Component.CENTER_ALIGNMENT);

        letra_b = new JButton("<html><div style='text-align: center;'>" +
                              "B) " + pergunta.getAlternativas()[1] + 
                              "</div></html>");
        letra_b.setName("1");
        letra_b.setPreferredSize(new Dimension(300, 30));
        letra_b.setMaximumSize(new Dimension(300, 30));
        letra_b.setBackground(new Color(70, 130, 180));
        letra_b.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        letra_b.setForeground(Color.BLACK);
        letra_b.setFocusPainted(false);
        letra_b.setFont(new Font("SansSerif", Font.BOLD, 24));
        letra_b.setAlignmentX(Component.CENTER_ALIGNMENT);

        letra_c = new JButton("<html><div style='text-align: center;'>" +
                              "C) " + pergunta.getAlternativas()[2] + 
                              "</div></html>");
        letra_c.setName("2");
        letra_c.setPreferredSize(new Dimension(300, 30));
        letra_c.setMaximumSize(new Dimension(300, 30));
        letra_c.setBackground(new Color(255, 250, 20));
        letra_c.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        letra_c.setForeground(Color.BLACK);
        letra_c.setFocusPainted(false);
        letra_c.setFont(new Font("SansSerif", Font.BOLD, 24));
        letra_c.setAlignmentX(Component.CENTER_ALIGNMENT);

        letra_d = new JButton("<html><div style='text-align: center;'>" +
                              "D) " + pergunta.getAlternativas()[3] + 
                              "</div></html>");
        letra_d.setName("3");
        letra_d.setPreferredSize(new Dimension(300, 30));
        letra_d.setMaximumSize(new Dimension(300, 30));
        letra_d.setBackground(new Color(173, 216, 230));
        letra_d.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        letra_d.setForeground(Color.BLACK);
        letra_d.setFocusPainted(false);
        letra_d.setFont(new Font("SansSerif", Font.BOLD, 24));
        letra_d.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelPergunta.add(tituloPerg);
        painelPergunta.add(Box.createRigidArea(new Dimension(0, 30)));
        painelPergunta.add(campoPergunta);
        painelPergunta.add(Box.createRigidArea(new Dimension(0, 40)));

        JPanel painelAlternativas = new JPanel();
        painelAlternativas.setLayout(new GridLayout(2, 2, 40, 40));
        painelAlternativas.setBackground(fundoAzul);

        painelAlternativas.add(letra_a);
        painelAlternativas.add(letra_b);
        painelAlternativas.add(letra_c);
        painelAlternativas.add(letra_d);

        painelPergunta.add(painelAlternativas);
        add(painelPergunta);
        
        //Ação dos botões, resposta recebida é registrada na variavel "respostaRecebida"
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tempo = gameController.pararCronometro();
                
                JButton btnClicado = (JButton) e.getSource();
                
                int res = Integer.parseInt(btnClicado.getName());
                
                if(gameController.verificarResposta(res, tempo)){
                    new InterfaceAcerto(gameController, gameController.getAcertos());
                    dispose();
                    setVisible(false);
                }else {
                    new InterfaceErro(gameController, gameController.getAcertos());
                    dispose();
                    setVisible(false);
                }
            }
        };
        
        letra_a.addActionListener(listener);
        letra_b.addActionListener(listener);
        letra_c.addActionListener(listener);
        letra_d.addActionListener(listener);

        setVisible(true);
        
        gameController.iniciarCronometro();
    }
}