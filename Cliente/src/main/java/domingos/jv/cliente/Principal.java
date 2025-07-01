package domingos.jv.cliente;

import domingos.jv.cliente.interfaces.WindowMain;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Principal {
    
    public static Socket socket;
    public static BufferedReader leitor;
    public static PrintWriter escritor;

    public static void main(String[] args) {
        
        try {
            socket = new Socket("localhost", 1234);
            JOptionPane.showMessageDialog(null, "Conectado no Servidor", 
                    "Sucesso na Conexão", JOptionPane.INFORMATION_MESSAGE);
            
            leitor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            escritor = new PrintWriter(socket.getOutputStream(), true);
            
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao conectar no servidor", 
                    "Conection Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex); 
        } finally {
            new WindowMain();
        }
    }
}
