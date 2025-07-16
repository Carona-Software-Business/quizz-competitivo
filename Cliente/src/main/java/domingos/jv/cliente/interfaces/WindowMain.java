package domingos.jv.cliente.interfaces;

import domingos.jv.cliente.Principal;
import domingos.jv.cliente.interfaces.InterfacePergunta;
import domingos.jv.cliente.logica.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class WindowMain extends JFrame {
    private JLabel titulo;
    private JTextField campoNome;
    private JButton botaoIniciar;
    private JLabel integrantes;
    private JLabel serie_curso;
    private JLabel colocar_nome;
    private JLabel erroNome;

    public WindowMain() {
        setTitle("Quiz Interativo");
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

        // Cria o JPanel customizado que desenha a imagem de fundo
        JPanel painelFundo = new JPanel() {
            Image imagemFundo = new ImageIcon(getClass().getResource("/quizz.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Desenha a imagem de fundo redimensionada para o tamanho do painel
                g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Usa BorderLayout para o painelFundo para encaixar painelCentral no centro
        painelFundo.setLayout(new BorderLayout());

        // Seu painel central, que vai conter os componentes
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(altura / 6, largura / 4, altura / 6, largura / 4));
        // Deixa o painelCentral transparente para o fundo aparecer
        painelCentral.setOpaque(false);

        titulo = new JLabel("QUIZ INTERATIVO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 72));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        colocar_nome = new JLabel("DIGITE SEU NOME");
        colocar_nome.setFont(new Font("Arial", Font.BOLD, 24));
        colocar_nome.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoNome = new JTextField();
        campoNome.setPreferredSize(new Dimension(700, 50));
        campoNome.setMaximumSize(new Dimension(700, 50));
        campoNome.setFont(new Font("SansSerif", Font.PLAIN, 24));
        campoNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoNome.setBorder(new LineBorder(Color.BLACK, 4));

        erroNome = new JLabel();
        erroNome.setFont(new Font("Arial", Font.BOLD, 16));
        erroNome.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoIniciar = new JButton("INICIAR");
        botaoIniciar.setBackground(new Color(0, 123, 255));
        botaoIniciar.setForeground(Color.WHITE);
        botaoIniciar.setFocusPainted(false);
        botaoIniciar.setFont(new Font("SansSerif", Font.BOLD, 48));
        botaoIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);

        serie_curso = new JLabel("2° ANO - CIÊNCIA DA COMPUTAÇÃO");
        serie_curso.setFont(new Font("Arial", Font.BOLD, 36));
        serie_curso.setAlignmentX(Component.CENTER_ALIGNMENT);

        integrantes = new JLabel("INTEGRANTES: GUILHERME, IGOR, JOÃO, KAUAN");
        integrantes.setFont(new Font("Arial", Font.BOLD, 24));
        integrantes.setAlignmentX(Component.CENTER_ALIGNMENT);

        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText().trim();
                if (!Principal.nomeEhValido(nome))
                    erroNome.setText("Por favor, sem palavras de baixo calão.");
                else if (!nome.isEmpty() && nome.length() <= 20) {
                    GameController gameController = new GameController(nome);
                    new InterfacePergunta(gameController, gameController.escolherPergunta());
                    dispose();
                    setVisible(false);
                } else if (nome.isEmpty()) {
                    erroNome.setText("Por favor, digite seu nome.");
                } else if (nome.length() > 20) {
                    erroNome.setText("Por favor, digite um nome com menos de 20 caracteres.");
                }
            }
        });

        painelCentral.add(titulo);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 40)));
        painelCentral.add(colocar_nome);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 40)));
        painelCentral.add(campoNome);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 80)));
        painelCentral.add(erroNome);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        painelCentral.add(botaoIniciar);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 120)));
        painelCentral.add(serie_curso);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));
        painelCentral.add(integrantes);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 10)));

        // Adiciona o painel central sobre o painel de fundo
        painelFundo.add(painelCentral, BorderLayout.CENTER);

        // Define o painel de fundo como content pane do JFrame
        setContentPane(painelFundo);

        setVisible(true);
    }
}
