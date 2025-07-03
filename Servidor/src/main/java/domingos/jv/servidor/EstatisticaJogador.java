package domingos.jv.servidor;

public class EstatisticaJogador {
    private int pontos;
    private int tempo;
    
    public EstatisticaJogador() {}

    public EstatisticaJogador(int pontos, int tempo) {
        this.pontos = pontos;
        this.tempo = tempo;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return "Pontos: " + pontos + " - Tempo: " + tempo;
    }
    
    
}
