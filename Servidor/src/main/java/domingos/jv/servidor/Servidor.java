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
        
        try(ServerSocket servidor = new ServerSocket(PORTA)) {
            
            JOptionPane.showMessageDialog(null, "Esperando Conexão...", 
                    "Servidor Aberto", JOptionPane.INFORMATION_MESSAGE);
            
            for(int i = 0; i < 1; i++) {
                Socket cliente = servidor.accept();
                clientes.add(cliente);
                new ClientHandler(cliente).start();
                JOptionPane.showMessageDialog(null, "O " + (i+1) + " Cliente se conectou", 
                    "Cliente Conectado", JOptionPane.INFORMATION_MESSAGE);
            }
            
            JOptionPane.showMessageDialog(null, "Todos os cliente se conectaram", 
                    "Sucesso na conexão", JOptionPane.INFORMATION_MESSAGE);
            
            
            
            
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar o servidor", 
                    "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        
        
        
        
    }
}
