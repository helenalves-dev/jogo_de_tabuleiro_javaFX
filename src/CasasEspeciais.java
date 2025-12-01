import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public enum CasasEspeciais {
    AVANCA_TRES(5,15,30){
        public void executar(ArrayList<Jogador> jogadores, int jogadorAtualIndex,ArrayList<HBox> casas){
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
    },
    PERDEU_VEZ(10,25,38){
        public void executar(ArrayList<Jogador> jogadores, int jogadorAtualIndex,ArrayList<HBox> casas){
            //Pop-up informando o jogador sobre a ação da casa especial
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Casa Especial");
            alert.setHeaderText(null); // sem título interno
            alert.setContentText("Você perdeu seu próximo turno");
            alert.showAndWait();
            //Lógica para fazer o jogador perder a vez
            Jogador jogador=jogadores.get(jogadorAtualIndex);
            jogador.setPassaVez(true);
        }
    },
    REDEFININDO_JOGADOR(13){
        public void executar(ArrayList<Jogador> jogadores, int jogadorAtualIndex,ArrayList<HBox> casas){
            //Pop-up informando o jogador sobre a ação da casa especial
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Casa Especial");
            alert.setHeaderText(null); // sem título interno
            alert.setContentText("Redefinindo Jogador");
            alert.showAndWait();
            //Lógica para redefinir o tipo do jogador
            Jogador jogador=jogadores.get(jogadorAtualIndex);
            Random random=new Random();
            int tipo=random.nextInt(3);
            if (tipo==0){
                jogador=new JogadorSortudo(jogadores.get(jogadorAtualIndex).getCor(),jogadores.get(jogadorAtualIndex).getTurnosJogados(),jogadores.get(jogadorAtualIndex).getPontuacao());
            }else if(tipo==1){
               jogador=new JogadorNormal(jogadores.get(jogadorAtualIndex).getCor(),jogadores.get(jogadorAtualIndex).getTurnosJogados(),jogadores.get(jogadorAtualIndex).getPontuacao());
            }else{
                jogador=new JogadorAzarado(jogadores.get(jogadorAtualIndex).getCor(),jogadores.get(jogadorAtualIndex).getTurnosJogados(),jogadores.get(jogadorAtualIndex).getPontuacao());
            }
            //Pop-up informando o jogador sobre seu novo tipo
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Casa Especial");
            alert.setHeaderText(null); // sem título interno
            alert.setContentText("Você agora é um "+jogador.getClass());
            alert.showAndWait();
        }
    },
    VOLTA_INICIO(17,27){
        public void executar(ArrayList<Jogador> jogadores, int jogadorAtualIndex,ArrayList<HBox> casas){
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
    },
    TROCA_JOGADORES(20,35){
        public void executar(ArrayList<Jogador> jogadores, int jogadorAtualIndex,ArrayList<HBox> casas){
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
    };

    final int[] casas;

    CasasEspeciais(int... casas){
        this.casas=casas;
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

    public abstract void executar(ArrayList<Jogador> jogadores, int jogadorAtualIndex,ArrayList<HBox> casas);
}
