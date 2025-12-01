package jogo_robo;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

public class RedefineJogador implements CasasEspeciaisStrategy {
    private Random random = new Random();
    @Override
    public void executarAcao(ArrayList<Jogador> jogadores, int jogadorAtualIndex, ArrayList<HBox> casas) {
        //Pop-up informando o jogador sobre a ação da casa especial
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Casa Especial");
        alert.setHeaderText(null); // sem título interno
        alert.setContentText("Redefinindo Jogador");
        alert.showAndWait();
        //Lógica para redefinir o tipo do jogador
        Jogador jogador=jogadores.get(jogadorAtualIndex);
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
}
