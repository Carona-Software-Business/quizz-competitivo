package domingos.jv.cliente.logica;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domingos.jv.cliente.Principal;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import java.util.stream.Collectors;

public class GameController {
    private List<Pergunta> perguntas;
    private List<Pergunta> perguntasAtual;
    private Pergunta perguntaAtual;
    
    private int quantidadePerguntas;
    private String dificuldadeAtual;
    
    private Jogador player;
    
    private Timer timer;
    private TimerTask tarefa;
    private int tempo;

    // Inicia o jogo
    public GameController(String nome) {
        // Carregar a lista de perguntas
        carregarLista();
        // Criar o jogador
        player = new Jogador(nome);
        
        // Começar o jogo
        dificuldadeAtual = "facil";
        
        // Filtra as perguntas
        filtrarPerguntas();
        
        // Setar zero na quantidade de perguntas
        quantidadePerguntas = 0;
    }
    
    public int getQuantidadesPerguntas() {
        return quantidadePerguntas;
    }
    
    public void reiniciarJogo(String nome) {
        // Reiniciar as perguntas
        // Recriar o jogador
        // Reiniciar a dificuldade
    }
    
    public Pergunta escolherPergunta() {
        // Escolher a pergunta aleatóriamente dependendo da dificuldade
        int pos = new Random().nextInt(perguntasAtual.size());
        
        perguntaAtual = perguntasAtual.remove(pos);
        
        quantidadePerguntas++;
        
        return perguntaAtual;
    }
    
    public Boolean verificarResposta(int res, int tempo) {
        player.somarTempo(tempo);
        
        // Verificar a resposta, define dificuldade.
        if(res == perguntaAtual.getCorreta()) {
            player.somarAcerto();
            calcularPontos(tempo);
            
            // Setar dificuldade, talvez, filtrar
            definirDificuldade();
            
            return true;
        }
        
        definirDificuldade();
        return false;
    }
    
    private void calcularPontos(int tempo) {
        int pontosTempo;
        
        if(tempo <= 3)
            pontosTempo = 10;
        else if(tempo <= 6)
            pontosTempo = 5;
        else
            pontosTempo = 0;
        
        switch (perguntaAtual.getNivel()) {
            case "facil":
                player.somarPontos(7 + pontosTempo);
                break;
            case "medio":
                player.somarPontos(10 + pontosTempo);
                break;
            case "dificil":
                player.somarPontos(12 + pontosTempo);
                break;
        }
    }
    
    private void carregarLista() {
        Gson gson = new Gson();
        
        try {
            FileReader arquivoJson = new FileReader("perguntasGeral.json");
            
            //System.out.println("Json: \n" + arquivoJson);
            
            perguntas = gson.fromJson(arquivoJson, new TypeToken<List<Pergunta>>() {}.getType());
            
            //System.out.println("Lista perguntas: \n" + perguntas);
            
            /*
            System.out.println("Tamanho: " + perguntas.size());
            System.out.println("Pergunta: " + perguntas.get(0).getPergunta());
            */
            
        } catch(FileNotFoundException ex) {
            System.out.println("Erro ao carregar o arquivo json:\n" + ex);
            JOptionPane.showMessageDialog(null, "Erro ao carregar o arquivo json!!!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public int getAcertos() {
        return player.getAcertos();
    }
    
    public Jogador enviarResultado() {
        try {
            Principal.escritor.println(player.getNome());
            Principal.escritor.println(player.getPontuacaoTotal());
            Principal.escritor.println(player.getTempoTotal());

            player.setPosicao(Principal.leitor.readLine());
            
        } catch(IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível se comunicar com o servidor", 
                    "Erro de Comunicação", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        } finally {
            return player;
        }
    }
    
    private void filtrarPerguntas() {
        perguntasAtual = perguntas.stream().filter(
                pergunta -> pergunta.getNivel().equals(dificuldadeAtual))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    private void definirDificuldade() {
        //System.out.println("QTD P: " + quantidadePerguntas);
        
        if(quantidadePerguntas == 3) {
            dificuldadeAtual = "medio";
            filtrarPerguntas();
        } else if(quantidadePerguntas == 6) {
            dificuldadeAtual = "dificil";
            filtrarPerguntas();
        }
    }
    
    public void iniciarCronometro() {
        tempo = 0;
        
        timer = new Timer();
        tarefa = new TimerTask() {
            @Override
            public void run() {
                tempo++;
            }
        };
        
        timer.scheduleAtFixedRate(tarefa, 3000, 1000);
    }
    
    public int pararCronometro() {
        if(timer != null)
            timer.cancel();
        
        return tempo;
    }
    
}
