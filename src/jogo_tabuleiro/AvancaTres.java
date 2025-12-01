package jogo_tabuleiro;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class AvancaTres implements CasasEspeciaisStrategy {
    @Override
    public void executarAcao(ArrayList<Jogador> jogadores, int jogadorAtualIndex, ArrayList<HBox> casas) {
        //Pop-up informando o jogador sobre a ação da casa especial
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Casa Especial");
        alert.setHeaderText(null); // sem título interno
        alert.setContentText("Você pode avançar +3 casas");
        alert.showAndWait();
        //Lógica para avançar 3 casas
        Jogador jogador=jogadores.get(jogadorAtualIndex);
        int casaAntiga=jogador.getPontuacao();
        jogador.setPontuacao(jogador.getPontuacao()+3);
        //Atualiza a posição do jogador na interface gráfica
        casas.get(casaAntiga).getChildren().remove(jogador);
        for (Node node:casas.get(casaAntiga).getChildren()){
            Circle player=(Circle) node;
            player.setRadius(23/casas.get(casaAntiga).getChildren().size());
        }
        casas.get(jogador.getPontuacao()).getChildren().add(jogador);
        for (Node node:casas.get(jogador.getPontuacao()).getChildren()){
            Circle player=(Circle) node;
            player.setRadius(23/casas.get(jogador.getPontuacao()).getChildren().size());
        }
    }   
    
}
