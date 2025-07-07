package Logica;

import Interfaces.WindowControle;
import domingos.jv.servidor.Servidor;
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
             
            if(posicao == null) break;
        }
    }
    
    private synchronized String receberResultado() {
        try {
            String nome = leitor.readLine();
            int pontos = Integer.parseInt(leitor.readLine());
            int tempo = Integer.parseInt(leitor.readLine());
            
            // Ranking
            Servidor.rank.adicionarRank(nome, pontos, tempo);
            
            // Tabela
            WindowControle.interfaceRank.atualizarTabela(Servidor.rank.getRankOrdenado());
            
            int pos = Servidor.rank.pegarPosicao(nome);
            
            if(pos != -1)
                return "Você ficou em " + pos + "° lugar."; 
            else
                return "";
            
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao ler a mensagem do cliente", 
                    "Erro de Leitura", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
            desconectarCliente();
        }
        
        return null;
    }
    
    private synchronized void enviarPosicao(String posicao) {
        escritor.println(posicao);
    }
    
    public void desconectarCliente() {
        try {
            cliente.close();
            ligado = false;
            JOptionPane.showMessageDialog(null, "Cliente desconectado", 
                    "Sucesso na desconexão", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao desconectar cliente", 
                    "Erro de Desconexão", JOptionPane.ERROR_MESSAGE);
        }
    }
}
