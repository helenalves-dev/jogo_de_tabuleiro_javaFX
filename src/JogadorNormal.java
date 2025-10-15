import java.util.Random;
import javafx.scene.paint.Color;

public class JogadorNormal extends Jogador {

  public JogadorNormal(Color cor, int turnosJogados, int pontuacao) {
    super(cor, turnosJogados, pontuacao);
  }

  public JogadorNormal(Color cor) {
    super(cor, 0, 0);
  }

  @Override
  public int[] andarCasa() {
    Random random = new Random();
    int dados[] = new int[2];
    dados[0] = 1 +  random.nextInt(6);
    dados[1] = 1 + random.nextInt(6);
    setPontuacao(getPontuacao() + dados[0] + dados[1]);
    return dados;
  }
}