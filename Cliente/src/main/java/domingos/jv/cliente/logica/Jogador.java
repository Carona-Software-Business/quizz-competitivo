package domingos.jv.cliente.logica;

public class Jogador {
    private String nome;
    private int pontuacaoTotal;
    private int acertos;
    private int tempoTotal;
    private String posicao;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacaoTotal = 0;
        this.acertos = 0;
        this.tempoTotal = 0;
        this.posicao = "";
    }

    public int getAcertos() {
        return acertos;
    }

    public void somarAcerto() {
        acertos++;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void somarPontos(int pontos) {
        this.pontuacaoTotal += pontos;
    }

    public int getTempoTotal() {
        return tempoTotal;
    }

    public void somarTempo(int tempo) {
        this.tempoTotal += tempo;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
    
    public String getPosicao() {
        return "Sua colocação foi: " + posicao + "° lugar!";
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nAcertos: " + acertos + "\nPontuacao: " + pontuacaoTotal +
                "\nTempo Total: " + tempoTotal;
    }
    
    
}
