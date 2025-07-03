package domingos.jv.servidor;

import Logica.Rank;
import Interfaces.WindowControle;

public class TesteRank {
    public static void main(String[] args) {
        Rank rank = new Rank();
        
        new WindowControle(rank);
    }
}
