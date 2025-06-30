package domingos.jv.cliente.logica;

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
        String msg = "Pergunta: " + pergunta + "\n";
        
        for(int i = 0; i < 4; i++) {
            msg += (i+1) + " - " + alternativas[i] + "\n";
        }
        
        return msg;
    }
    
    
}
