package jogo_tabuleiro;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class TrocaJogadores implements CasasEspeciaisStrategy{
    @Override
    public void executarAcao(ArrayList<Jogador> jogadores, int jogadorAtualIndex, ArrayList<HBox> casas){
        //Pop-up informando o jogador sobre a ação da casa especial
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Casa Especial");
        alert.setHeaderText(null); // sem título interno
        alert.setContentText("Troque de lugar com o jogador na última posição");
        alert.showAndWait();
        //Lógica para trocar de lugar com o jogador na última posição
        int menorPontuacao=0;
        int menorPontuacaoIndex=0;
        for (int k=0;k<jogadores.size();k++){
            if(k==0){
                menorPontuacao=jogadores.get(k).getPontuacao();
            }else if(jogadores.get(k).getPontuacao()<menorPontuacao){
                menorPontuacao=jogadores.get(k).getPontuacao();
                menorPontuacaoIndex=k;
            }
        }
        Jogador ultimoJogador=jogadores.get(menorPontuacaoIndex);
        Jogador jogador=jogadores.get(jogadorAtualIndex);
        int pontuacaoAtual=jogadores.get(jogadorAtualIndex).getPontuacao();
        //Atualiza a posição dos jogadores na interface gráfica
        casas.get(pontuacaoAtual).getChildren().remove(jogador);
        casas.get(menorPontuacao).getChildren().remove(ultimoJogador);
        for (Node node:casas.get(pontuacaoAtual).getChildren()){
            Circle player=(Circle) node;
            player.setRadius(23/casas.get(pontuacaoAtual).getChildren().size());
        }
        jogador.setPontuacao(menorPontuacao);
        casas.get(jogador.getPontuacao()).getChildren().add(jogador);
        for (Node node:casas.get(jogador.getPontuacao()).getChildren()){
            Circle player=(Circle) node;
            player.setRadius(23/casas.get(jogador.getPontuacao()).getChildren().size());
        }
        for (Node node:casas.get(menorPontuacaoIndex).getChildren()){
            Circle player=(Circle) node;
            player.setRadius(23/casas.get(menorPontuacao).getChildren().size());
        }
        ultimoJogador.setPontuacao(pontuacaoAtual);
        casas.get(ultimoJogador.getPontuacao()).getChildren().add(ultimoJogador);
        for (Node node:casas.get(ultimoJogador.getPontuacao()).getChildren()){
            Circle player=(Circle) node;
            player.setRadius(23/casas.get(ultimoJogador.getPontuacao()).getChildren().size());
        }
    }
    
}
