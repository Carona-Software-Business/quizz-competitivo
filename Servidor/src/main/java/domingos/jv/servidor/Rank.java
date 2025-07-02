package domingos.jv.servidor;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JOptionPane;

public class Rank {
    private Map<String, Integer> rank;
    private Stream<Map.Entry<String, Integer>> rankOrdenado;
    
    private File rankFile;
    
    private Gson gson;

    public Rank() {
        gson = new Gson();
        
        rank = null;
        rankOrdenado = null;
        
        carregarArquivo();
    }

    public Map<String, Integer> getRank() {
        return rank;
    }

    public Stream<Map.Entry<String, Integer>> getRankOrdenado() {
        return rankOrdenado;
    }
    
    public void adicionarRank(String nome, int pontos) {
        rank.putIfAbsent(nome, pontos);
        ordenarRank();
    }
    
    public void ordenarRank() {
        rankOrdenado = rank.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed());
    }
    
    private void carregarArquivo() {
        rankFile = new File("ranking.json");
        
        if(rankFile.exists()) {
            System.out.println("Arquivo existe!");
            try {
                FileReader reader =  new FileReader(rankFile);
                Type type = new TypeToken<Map<String, Integer>>() {}.getType();
                rank = gson.fromJson(reader, type);
                reader.close();
                
            } catch (IOException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "Erro ao carregar o arquivo de rank", 
                        "File Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            System.out.println("Arquivo nao existe");
            rank = new HashMap<String, Integer>();
        }
    }
    
    
    public void salvarRank() {
        try(FileWriter writer = new FileWriter("ranking.json");) {
            gson.toJson(rank, writer);
            
        } catch (IOException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar o rank", 
                        "Filer Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
