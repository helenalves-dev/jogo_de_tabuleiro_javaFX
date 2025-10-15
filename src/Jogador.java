import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Jogador extends Circle{
    private int turnosJogados;
    private boolean passaVez;
    private int pontuacao;
    public Jogador(Color cor, int turnosJogados, int pontuacao){
        setFill(cor);
        this.turnosJogados=turnosJogados;
        this.pontuacao=pontuacao;
        passaVez=false;
    }
    public int getTurnosJogados(){
        return turnosJogados;
    }
    public void setTurnosJogados(int turnosJogados){
        this.turnosJogados=turnosJogados;
    }
    public boolean getPassaVez(){
        return passaVez;
    }
    public void setPassaVez(boolean passaVez){
        this.passaVez=passaVez;
    }
    public int getPontuacao(){
        return pontuacao;
    }
    public void setPontuacao(int pontuacaoAtual){
        if (pontuacaoAtual>39){
            pontuacao=39;
        }else{
            pontuacao=pontuacaoAtual;
        }
    }
    public abstract int[] andarCasa();
}
