package jogo_robo;
import javafx.scene.layout.*;
import java.util.ArrayList;

public interface CasasEspeciaisStrategy {
    void executarAcao(ArrayList<Jogador> jogadores, int jogadorAtualIndex,ArrayList<HBox> casas);
}
