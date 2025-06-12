package domingos.jv.cliente;

public class Pergunta {
    private String pergunta;
    private String[] alternativas;
    private int correta;
    private String tema;
    private String nivel;

    public String getPergunta() {
        return pergunta;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public String getNivel() {
        return nivel;
    }

    public int getCorreta() {
        return correta;
    }

    public String getTema() {
        return tema;
    }
    
    
    @Override
    public String toString() {
        return pergunta + "\n" + "alternativas: " + alternativas + '\n';
    }
    
    
}
