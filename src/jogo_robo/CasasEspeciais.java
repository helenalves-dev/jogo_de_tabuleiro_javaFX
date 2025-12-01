package jogo_robo;
import java.util.ArrayList;
import javafx.scene.layout.HBox;

public enum CasasEspeciais {
    AVANCA_TRES(new AvancaTres(), 5,15,30),
    PERDEU_VEZ(new PerdeuVez(),10,25,38),
    REDEFININDO_JOGADOR(new RedefineJogador(),13),
    VOLTA_INICIO(new VoltaInicio(),17,27),
    TROCA_JOGADORES(new TrocaJogadores(),20,35);

    final int[] casas;
    final CasasEspeciaisStrategy estrategia;

    CasasEspeciais(CasasEspeciaisStrategy estrategia, int... casas){
        this.estrategia=estrategia;
        this.casas=casas;
    }

    public void executar(ArrayList<Jogador> jogadores, int jogadorAtualIndex, ArrayList<HBox> casas){
        estrategia.executarAcao(jogadores, jogadorAtualIndex, casas);
    }

    public boolean contemCasa(int casaAtual){
        for (int casa:casas){
            if(casa==casaAtual){
                return true;
            }
        }
        return false;
    }

    public static CasasEspeciais fromCasa(int valor) {
        for (CasasEspeciais ce : values()) {
            if (ce.contemCasa(valor)) {
                return ce;
            }
        }
            return null;
    }

}
