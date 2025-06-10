package domingos.jv.cliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author guilh
 */
public class Inicio extends JFrame{
    private JLabel label;
    private JTextField nome;
    private JButton iniciar;
    private JPanel painel;
    
    public Inicio() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        label = new JLabel("QUIZ INTERATIVO");
        nome = new JTextField("");
        iniciar = new JButton("INICIAR");
        painel = new JPanel(new GridLayout(3,3));
        
        painel.add(label);
        painel.add(nome);
        painel.add(iniciar);
        add(painel);
        
        setVisible(true);
}
    
}
