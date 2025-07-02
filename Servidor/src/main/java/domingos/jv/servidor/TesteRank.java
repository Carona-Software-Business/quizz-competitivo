package domingos.jv.servidor;

public class TesteRank {
    public static void main(String[] args) {
        Rank rank = new Rank();
        
        /*
        rank.adicionarRank("Gui", 0);
        rank.adicionarRank("Joao", 15);
        rank.adicionarRank("Igor", 50);
        rank.adicionarRank("Kauan", 25);
        */
        
        System.out.println("Rank: " + rank.getRank());
        
        rank.ordenarRank();
        
        System.out.println("Rank Ordenado:");
        rank.getRankOrdenado().forEach(System.out::println);
    }
}
