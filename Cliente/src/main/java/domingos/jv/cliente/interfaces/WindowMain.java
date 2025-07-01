package domingos.jv.cliente.interfaces;

import domingos.jv.cliente.logica.GameController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class WindowMain extends JFrame {
    private JLabel titulo;
    private JTextField campoNome;
    private JButton botaoIniciar;
    private JLabel integrantes;
    private JLabel serieCurso;
    private JLabel colocarNome;
    private JLabel labelRelogio;

    public WindowMain() {
        setTitle("Quiz Interativo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tela cheia
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            setUndecorated(true);
            gd.setFullScreenWindow(this);
        } else {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        // Cor de fundo azul pastel
        Color corFundo = new Color(230, 240, 250);
        Color corBorda = new Color(28, 61, 90);

        // Painel principal
        JPanel painelCentral = new JPanel();
        painelCentral.setBackground(corFundo);
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));

        // Borda azul marinho com espessura 20 e margem interna
        Border bordaColorida = BorderFactory.createLineBorder(corBorda, 20);
        Border margemInterna = BorderFactory.createEmptyBorder(80, 300, 80, 300);
        Border bordaComposta = new CompoundBorder(bordaColorida, margemInterna);
        painelCentral.setBorder(bordaComposta);

        // Título
        titulo = new JLabel("QUIZ INTERATIVO");
        titulo.setFont(new Font("Arial", Font.BOLD, 60));
        titulo.setForeground(new Color(33, 37, 41));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Texto de instrução
        colocarNome = new JLabel("DIGITE SEU NOME");
        colocarNome.setFont(new Font("Arial", Font.BOLD, 24));
        colocarNome.setForeground(new Color(60, 60, 60));
        colocarNome.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campo de nome
        campoNome = new JTextField();
        campoNome.setMaximumSize(new Dimension(500, 45));
        campoNome.setFont(new Font("SansSerif", Font.PLAIN, 22));
        campoNome.setHorizontalAlignment(JTextField.CENTER);
        campoNome.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão iniciar com estilo e animação
        botaoIniciar = new JButton("INICIAR");
        botaoIniciar.setFont(new Font("SansSerif", Font.BOLD, 32));
        botaoIniciar.setBackground(new Color(0, 123, 255));
        botaoIniciar.setForeground(Color.WHITE);
        botaoIniciar.setFocusPainted(false);
        botaoIniciar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Animação de hover
        botaoIniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoIniciar.setBackground(new Color(0, 105, 220));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoIniciar.setBackground(new Color(0, 123, 255));
            }
        });

        // Atalho ENTER
        campoNome.addActionListener(e -> botaoIniciar.doClick());

        // Série do curso
        serieCurso = new JLabel("2° ANO - CIÊNCIA DA COMPUTAÇÃO");
        serieCurso.setFont(new Font("Arial", Font.BOLD, 26));
        serieCurso.setForeground(new Color(60, 60, 60));
        serieCurso.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Integrantes
        integrantes = new JLabel("INTEGRANTES: GUILHERME, IGOR, JOÃO, KAUAN");
        integrantes.setFont(new Font("Arial", Font.BOLD, 23));
        integrantes.setForeground(new Color(60, 60, 60));
        integrantes.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Relógio no rodapé
        labelRelogio = new JLabel();
        labelRelogio.setFont(new Font("SansSerif", Font.BOLD, 20)); // Negrito e maior
        labelRelogio.setForeground(new Color(60, 60, 60));
        labelRelogio.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ação do botão iniciar
        botaoIniciar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            if (!nome.isEmpty() && nome.length() <= 20) {
                GameController gameController = new GameController(nome);
                new InterfacePergunta(gameController, gameController.escolherPergunta());
                dispose();
            } else if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, digite seu nome.");
            } else {
                JOptionPane.showMessageDialog(this, "Digite um nome com até 20 caracteres.");
            }
        });

        // Adiciona componentes ao painel
        painelCentral.add(Box.createVerticalStrut(50));
        painelCentral.add(titulo);
        painelCentral.add(Box.createVerticalStrut(40));
        painelCentral.add(colocarNome);
        painelCentral.add(Box.createVerticalStrut(15));
        painelCentral.add(campoNome);
        painelCentral.add(Box.createVerticalStrut(30));
        painelCentral.add(botaoIniciar);
        painelCentral.add(Box.createVerticalStrut(60));
        painelCentral.add(serieCurso);
        painelCentral.add(Box.createVerticalStrut(15));
        painelCentral.add(integrantes);
        painelCentral.add(Box.createVerticalStrut(60)); // Espaço extra antes do relógio
        painelCentral.add(labelRelogio);
        painelCentral.add(Box.createVerticalStrut(20)); // Espaço inferior

        add(painelCentral, BorderLayout.CENTER);
        getContentPane().setBackground(corFundo);

        setVisible(true);
        iniciarRelogio(); // Inicia relógio
    }

    private void iniciarRelogio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEEE, dd 'DE' MMMM 'DE' yyyy - HH:mm:ss", new Locale("pt", "BR"));

        Timer timer = new Timer(1000, e -> {
            LocalDateTime agora = LocalDateTime.now();
            String texto = agora.format(formatter).toUpperCase(); // MAIÚSCULO
            labelRelogio.setText(texto);
        });
        timer.start();
    }
}
