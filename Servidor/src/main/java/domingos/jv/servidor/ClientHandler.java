package domingos.jv.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ClientHandler extends Thread {
    
    private Socket cliente;
    private BufferedReader leitor;
    private PrintWriter escritor;
    
    private boolean ligado;

    public ClientHandler(Socket cliente) {
        try {
            this.cliente = cliente;
            leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            escritor = new PrintWriter(cliente.getOutputStream(), true);
            
            ligado = true;
            
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar o cliente", 
                    "Conection Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        
    }

    @Override
    public void run() {
        while(ligado) {
            String posicao = receberResultado();
        
            enviarPosicao(posicao);
        }
    }
    
    private synchronized String receberResultado() {
        try {
            String nome = leitor.readLine();
            int pontos = Integer.parseInt(leitor.readLine());
            
            // Ranking
            
            return "Jogador " + nome + " com " + pontos + " pontos";
            
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao ler a mensagem do cliente", 
                    "Erro de Leitura", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        
        return "";
    }
    
    private synchronized void enviarPosicao(String posicao) {
        escritor.println(posicao);
    }
    
    public void desconectar() {
        try {
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
