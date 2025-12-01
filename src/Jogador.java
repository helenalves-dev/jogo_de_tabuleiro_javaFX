import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public abstract class Jogador extends Circle{
    private Cores cor;
    private int turnosJogados;
    private boolean passaVez;
    private int pontuacao;
    public Jogador(Cores cor, int turnosJogados, int pontuacao){
        this.cor=cor;
        setFill(cor.colorValue);
        setStroke(Color.BURLYWOOD);
        setStrokeWidth(1);
        this.turnosJogados=turnosJogados;
        this.pontuacao=pontuacao;
        passaVez=false;
    }

    public Cores getCor(){
        return cor;
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
    
    public abstract int[] jogarDados();

    public void andarCasa(int novaPontuacao){
        pontuacao=novaPontuacao;
        turnosJogados+=1;
    }

}
