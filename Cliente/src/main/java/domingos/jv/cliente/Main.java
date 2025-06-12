package domingos.jv.cliente;

public class Main {
    public static void main(String[] args) {
        
        GameController g = new GameController("Joao");
        
        Pergunta p = g.escolharPergunta();
        
        System.out.println("Pergunta: " + p.getPergunta());
        
    }
}
