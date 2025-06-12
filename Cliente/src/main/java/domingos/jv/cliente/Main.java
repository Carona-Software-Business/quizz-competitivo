package domingos.jv.cliente;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        System.out.print("Digite o seu nome: ");
        
        GameController gameController = new GameController(leitor.next());
        
        for(int i = 0; i < 3; i++){           
            Pergunta p = gameController.escolherPergunta();
            
            System.out.println(p);
            
            System.out.print("Digite sua resposta: ");
            
            leitor.nextInt();
            
            /*
            if(gameController.verificarResposta(leitor.nextInt())){
                System.out.println("Voce acertou!");
            } else{
                System.out.println("Voce errou...");
            }
            */
        }
        System.out.println("Fim de jogo!");
    }
}
