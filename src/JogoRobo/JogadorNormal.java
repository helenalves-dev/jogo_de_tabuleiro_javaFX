import java.util.Random;

public class JogadorNormal extends Jogador {

  public JogadorNormal(Cores cor, int turnosJogados, int pontuacao) {
    super(cor, turnosJogados, pontuacao);
  }

  public JogadorNormal(Cores cor) {
    super(cor, 0, 0);
  }

  @Override
  public int[] jogarDados() {
    Random random = new Random();
    int pontuacaoAtual;
    int dados[] = new int[2];
    dados[0] = 1 +  random.nextInt(6);
    dados[1] = 1 + random.nextInt(6);
    pontuacaoAtual=getPontuacao() + dados[0] + dados[1];
    setPontuacao(pontuacaoAtual);
    if(dados[0]!=dados[1]){
      setTurnosJogados(getTurnosJogados()+1);
    }
    return dados;
  }
}