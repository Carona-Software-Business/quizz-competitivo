package domingos.jv.servidor;

import Interfaces.InterfaceRanking;
import Logica.Rank;
import Interfaces.WindowControle;
import java.util.Scanner;

public class TesteRank {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        Rank rank = new Rank();
        
        WindowControle windowController = new WindowControle();
        
        /*
        Thread adicionar = new Thread(() -> {
            while(true) {
                String nome = teclado.nextLine();
                int pontos = teclado.nextInt();
                int tempo = teclado.nextInt();
                
                if(pontos == -1) break;
                
                rank.adicionarRank(nome, pontos, tempo);
                InterfaceRanking interfaceRanking = windowController.getInter;
                interfaceRanking.atualizarTabela(rank.getRankOrdenado());
            }
        });
        adicionar.start();
        */
    }
}
