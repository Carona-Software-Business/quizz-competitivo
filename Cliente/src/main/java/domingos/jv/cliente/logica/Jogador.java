package domingos.jv.cliente.logica;

public class Jogador {
    private String nome;
    private int pontuacaoTotal;
    private int acertos;
    private int tempoTotal;
    private int posicao;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacaoTotal = 0;
        this.acertos = 0;
        this.tempoTotal = 0;
        this.posicao = 0;
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

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    
    public int getPosicao() {
        //return "Sua colocação foi: " + posicao + "° lugar!";
        return posicao;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nAcertos: " + acertos + "\nPontuacao: " + pontuacaoTotal +
                "\nTempo Total: " + tempoTotal;
    }
    
    
}
