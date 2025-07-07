package Logica;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.Comparator;
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
    private Map<String, EstatisticaJogador> rank;
    private List<Map.Entry<String, EstatisticaJogador>> rankOrdenado;
    
    private File rankFile;
    
    private Gson gson;

    public Rank() {
        gson = new Gson();
        
        rankOrdenado = null;
        
        carregarArquivo();
    }

    public List<Map.Entry<String, EstatisticaJogador>> getRankOrdenado() {
        return rankOrdenado;
    }
    
    public int pegarPosicao(String nome) {
        int pos = -1;
        
        for(int i = 0; i < rankOrdenado.size(); i++) {
            if(rankOrdenado.get(i).getKey().equals(nome)) {
                pos = i + 1;
                break;
            }
        }
        
        return pos;
    }
    
    public void adicionarRank(String nome, int pontos, int tempo) {
        rank.putIfAbsent(nome, new EstatisticaJogador(pontos, tempo));
        ordenarRank();
    }
    
    public void ordenarRank() {
        //rankOrdenado = rank.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed());
        
        rankOrdenado = rank.entrySet()
                        .stream()
                        .sorted(
                           Comparator.comparingInt(
                               (Map.Entry<String, EstatisticaJogador> e) -> e.getValue().getPontos()
                           ).reversed().thenComparing(e -> e.getValue().getTempo())
                        ).toList();
    }
    
    public void printarRank() {
        rank.entrySet().forEach(System.out::println);
    }
    
    public void printarRankOrdenado() {
        if(rankOrdenado != null) {
                for(var entry : rankOrdenado) {
                    System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        } else 
            System.out.println("Vazio");
    }
    
    private void carregarArquivo() {
        rankFile = new File("ranking.json");
        
        if(rankFile.exists()) {
            System.out.println("Arquivo existe!");
            try(FileReader reader =  new FileReader(rankFile)) {
                Type type = new TypeToken<Map<String, EstatisticaJogador>>() {}.getType();
                rank = gson.fromJson(reader, type);
                reader.close();
                ordenarRank();
                
            } catch (IOException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "Erro ao carregar o arquivo de rank", 
                        "File Error", JOptionPane.ERROR_MESSAGE);
                rank = new HashMap<>();
            }
            
        } else {
            System.out.println("Arquivo nao existe");
            rank = new HashMap<>();
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
