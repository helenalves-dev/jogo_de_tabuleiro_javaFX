import java.util.Random;
import javafx.scene.paint.Color;

public class JogadorSortudo extends Jogador{
    public JogadorSortudo(Color cor, int turnosJogados, int pontuacao){
        super(cor, turnosJogados, pontuacao);
    }
    public JogadorSortudo(Color cor){
        super(cor, 0, 0);
    }
    @Override
    public int[] andarCasa(){
        Random random = new Random();
        int[] dados = new int[2];
        int pontuacaoAtual;
        do{
            dados[0] = 1+random.nextInt(6);
            dados[1] = 1+random.nextInt(6);
        }while(dados[0]+dados[1]<7);
        pontuacaoAtual=getPontuacao()+dados[0]+dados[1];
        if(pontuacaoAtual>39){
            pontuacaoAtual=39;
        }
        setPontuacao(pontuacaoAtual);
        return dados;
    }
}