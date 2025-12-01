package jogo_robo;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class VoltaInicio implements CasasEspeciaisStrategy {
    @Override
    public void executarAcao(ArrayList<Jogador> jogadores, int jogadorAtualIndex, ArrayList<HBox> casas) {
        //Pop-up informando o jogador sobre a ação da casa especial
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Casa Especial");
        alert.setHeaderText("Escolha um jogador para voltar para o início: ");
        ToggleGroup grupo=new ToggleGroup();
        ArrayList<RadioButton> opcoes=new ArrayList<>();
        int jogadorIndex=0;
        for (Jogador player:jogadores){
            if(player==jogadores.get(jogadorAtualIndex)){
                jogadorIndex++;
                continue;
            }else{
                RadioButton opcaoJogador=new RadioButton("Jogador "+player.getCor().colorName);
                opcaoJogador.setUserData(jogadorIndex);
                opcoes.add(opcaoJogador);
                opcaoJogador.setToggleGroup(grupo);
                jogadorIndex++;
            }
        }
        opcoes.get(0).setSelected(true);
        VBox vbox=new VBox();
        for(RadioButton opcao:opcoes){
            vbox.getChildren().add(opcao);
        }
        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();
        //Lógica para fazer o jogador escolhido voltar para o início
        int jogadorEscolhidoIndex=(int) grupo.getSelectedToggle().getUserData();
        Jogador jogador=jogadores.get(jogadorEscolhidoIndex);
        int casaAntiga=jogadores.get(jogadorEscolhidoIndex).getPontuacao();
        jogador.setPontuacao(0);
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
