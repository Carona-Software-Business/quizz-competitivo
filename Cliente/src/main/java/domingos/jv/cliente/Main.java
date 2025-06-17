package domingos.jv.cliente;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        
        System.out.print("Digite o seu nome: ");
        
        GameController gameController = new GameController(leitor.nextLine());
        
        for(int i = 0; i < 9; i++){           
            Pergunta p = gameController.escolherPergunta();
            
            System.out.println(p);
            
            System.out.print("Digite sua resposta: ");
            
            gameController.iniciarCronometro();
            
            int res = leitor.nextInt();
            
            int tempo = gameController.pararCronometro();
            
            if(gameController.verificarResposta(res, tempo)){
                System.out.println("Voce acertou!\n");
            } else{
                System.out.println("Voce errou...\n");
            }
            
            System.out.println("Tempo: " + tempo);
            //System.out.println("Continuar? digite qualquer coisa: ");
            leitor.next();
        }
        System.out.println("Fim de jogo!");
        Jogador palyer = gameController.enviarResultado();
        System.out.println(palyer);
    }
}
