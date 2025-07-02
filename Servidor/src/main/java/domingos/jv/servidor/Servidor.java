package domingos.jv.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JOptionPane;

public class Servidor {
    
    private static final int PORTA = 1234;
    
    private static Set<Socket> clientes = new HashSet<>();

    public static void main(String[] args) {
        
        // Dependencies -> add Dependencie
        
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
            
            
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o servidor", 
                    "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
}
}
