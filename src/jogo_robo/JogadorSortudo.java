package jogo_robo;
import java.util.Random;

public class JogadorSortudo extends Jogador{
    private Random random=new Random();
    public JogadorSortudo(Cores cor, int turnosJogados, int pontuacao){
        super(cor, turnosJogados, pontuacao);
    }
    public JogadorSortudo(Cores cor){
        super(cor, 0, 0);
    }
    @Override
    public int[] jogarDados(){
        int[] dados = new int[2];
        int pontuacaoAtual;
        do{
            dados[0] = 1+random.nextInt(6);
            dados[1] = 1+random.nextInt(6);
        }while(dados[0]+dados[1]<7);
        pontuacaoAtual=getPontuacao()+dados[0]+dados[1];
        setPontuacao(pontuacaoAtual);
        if(dados[0]!=dados[1]){
            setTurnosJogados(getTurnosJogados()+1);
        }
        return dados;
    }
}