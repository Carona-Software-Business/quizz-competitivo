package domingos.jv.cliente;

public class Jogador {
    private String nome;
    private int pontuacaoTotal;
    private int acertos;
    private int tempoTotal;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacaoTotal = 0;
        this.acertos = 0;
        this.tempoTotal = 0;
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

    public void setTempoTotal(int tempoTotal) {
        this.tempoTotal = tempoTotal;
    }
}
