package domingos.jv.servidor;

public class TesteRank {
    public static void main(String[] args) {
        Rank rank = new Rank();
        
        rank.adicionarRank("Gui", 0, 50);
        rank.adicionarRank("Joao", 15, 20);
        rank.adicionarRank("Igor", 15, 19);
        rank.adicionarRank("Kauan", 25, 51);
        rank.adicionarRank("Augusto", 0, 19);
        
        System.out.println("Rank Normal:");
        rank.printarRank();
        
        System.out.println("Rank Ordenado:");
        rank.printarRankOrdenado();
        
        rank.salvarRank();
    }
}
