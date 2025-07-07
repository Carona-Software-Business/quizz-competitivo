package domingos.jv.servidor;

import Interfaces.WindowControle;
import Logica.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Servidor {
    
    private static final int PORTA = 1234;
    
    private static Set<Socket> clientes = new HashSet<>();

    public static void main(String[] args) {
        
        // Dependencies -> add Dependencie
        
        // Puxando Rank
        
        
        try {
            ServerSocket servidor = new ServerSocket(PORTA);
            JOptionPane.showMessageDialog(null, "Esperando Conexão...", 
                    "Servidor Aberto", JOptionPane.INFORMATION_MESSAGE);
            
            //Thread teste = new Thread(task)
            Thread conectar = new Thread(() -> {
                while(true) {
                    try {
                        Socket cliente = servidor.accept();
                        clientes.add(cliente);
                        new ClientHandler(cliente).start();
                        JOptionPane.showMessageDialog(null, "Um cliente se conectou", 
                            "Cliente Conectado", JOptionPane.INFORMATION_MESSAGE);

                    } catch(IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na conexão do cliente", 
                                "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex);
                    }
                }
            });
            conectar.start();
            
            new WindowControle();
            
            
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o servidor", 
                    "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
    }
    
    public static void desconectarClientes() {
        for (Socket cliente : clientes) {
            try {
                cliente.close();
            } catch (IOException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, 
                    "Algum erro ocorreu ao desconectar os clientes", 
                    "Fatal Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
