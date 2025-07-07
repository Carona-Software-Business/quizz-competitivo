package domingos.jv.cliente;

import domingos.jv.cliente.interfaces.WindowMain;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Principal {
    
    public static Socket socket;
    public static BufferedReader leitor;
    public static PrintWriter escritor;
    
    private static List<String> palavrasProibidas;

    public static void main(String[] args) {
        
        carregarPalavrasProibidas();
        
        try {
            socket = new Socket("192.168.243.1", 1234);
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
    
    private static void carregarPalavrasProibidas() {
        palavrasProibidas = new ArrayList<>();
        
        try(BufferedReader reader = new BufferedReader(new FileReader("palavrasProibidas.txt"))) {
            String linha;
            while((linha = reader.readLine()) != null) {
                palavrasProibidas.add(linha.trim().toLowerCase());
            }
            
        } catch(IOException ex) {
            System.out.println("Erro ao carregar o arquivo de palavras proibidas");
            System.out.println(ex);
        }
    }
    
    public static boolean nomeEhValido(String nome) {
        for(String palavra : palavrasProibidas) {
            if(nome.toLowerCase().trim().contains(palavra)) {
                return false;
            }
        }
        return true;
    }
}
