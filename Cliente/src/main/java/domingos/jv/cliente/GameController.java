package domingos.jv.cliente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.stream.Collectors;

public class GameController {
    private List<Pergunta> perguntas;
    private List<Pergunta> perguntasAtual;
    private int quantidadePerguntas;
    private String dificuldadeAtual;
    private Jogador player;

    // Inicia o jogo
    public GameController(String nome) {
        // Carregar a lista de perguntas
        carregarLista();
        // Criar o jogador
        player = new Jogador(nome);
        
        // Começar o jogo
        dificuldadeAtual = "fácil";
        
        // Filtra as perguntas
        filtrarPerguntas();
    }
    
    public void reiniciarJogo(String nome) {
        // Reiniciar as perguntas
        // Recriar o jogador
        // Reiniciar a dificuldade
    }
    
    private void definirDificuldade() {
        // Defini a dificuldade baseado nas quantidades de perguntas
    }
    
    public Pergunta escolharPergunta() {
        // Escolher a pergunta aleatóriamente dependendo da dificuldade
        int pos = new Random().nextInt(perguntasAtual.size());
        System.out.println("Num: " + pos);
        
        System.out.println("Tamanho: " + perguntasAtual.size());
        
        Pergunta p = perguntasAtual.remove(pos);
        
        //System.out.println("Pergunta: " + p.getPergunta());
        
        System.out.println("Tamanho: " + perguntasAtual.size());
        
        return p;
        
        
        /*
        System.out.println("Tamanho: " + perguntasAtual.size());
        for (Pergunta p : perguntasAtual) {
            System.out.println(p.getPergunta());
            System.out.println(p.getTema());
        }
        */  
    }
    
    public Boolean verificarResposta() {
        // Verificar a resposta, define dificuldade.
        return null;
    }
    
    private void calcularPontos() {
        
    }
    
    private void carregarLista() {
        Gson gson = new Gson();
        
        try {
            FileReader arquivoJson = new FileReader("perguntasGeral.json");
            
            System.out.println("Json: \n" + arquivoJson);
            
            perguntas = gson.fromJson(arquivoJson, new TypeToken<List<Pergunta>>() {}.getType());
            
            System.out.println("Lista perguntas: \n" + perguntas);
            
            /*
            System.out.println("Tamanho: " + perguntas.size());
            System.out.println("Pergunta: " + perguntas.get(0).getPergunta());
            */
            
        } catch(FileNotFoundException ex) {
            System.out.println("Erro ao carregar o arquivo json:\n" + ex);
            JOptionPane.showMessageDialog(null, "Erro ao carregar o arquivo json!!!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Jogador enviarResultado() {
        return player;
    }
    
    private void filtrarPerguntas() {
        perguntasAtual = perguntas.stream().filter(
                pergunta -> pergunta.getNivel().equals(dificuldadeAtual))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    
}
