package jogo_tabuleiro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TelaTipoJogador {//O usuário define qual tipo de jogador deseja ser: Sortudo, Azarado ou Normal

    @FXML
    private Label tipoJogador=new Label("Tipo de Jogador");

    @FXML
    private VBox vboxJogadores;

    @FXML
    private Button play;

    @FXML
    private Button voltar;

    private ArrayList<ChoiceBox<String>> choiceBoxes = new ArrayList<>();

    private int quantidadeJogadores;

    // Método chamado pelo controlador anterior para passar a quantidade
    public void setQuantidadeJogadores(int quantidade) {
        this.quantidadeJogadores = quantidade;
        criarChoiceBoxes();
    }

    // Cria dinamicamente uma ChoiceBox para cada jogador
    private void criarChoiceBoxes() {
        tipoJogador.setFont(new Font("Broadway",30));
        tipoJogador.setTextFill(Color.WHITE);
        vboxJogadores.getChildren().clear();
        choiceBoxes.clear();
        vboxJogadores.getChildren().add(tipoJogador);
        for (int i = 1; i <= quantidadeJogadores; i++) {
            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().addAll("Sortudo", "Azarado", "Normal");
            //Intercala a opções pré-selecionadas entre Normal e Azarado
            if((i%2)==0){
                choiceBox.setValue("Normal"); 
            }else{
                choiceBox.setValue("Azarado"); 
            }
            choiceBoxes.add(choiceBox);

            vboxJogadores.getChildren().add(choiceBox);
        }
        vboxJogadores.getChildren().add(play);
        vboxJogadores.getChildren().add(voltar);
    }

    private boolean jogadorDiferente(){//Verifica se tem ao menos dois jogadores diferentes
        for (int i=1;i<choiceBoxes.size();i++){
            if(!choiceBoxes.get(i).getValue().equals(choiceBoxes.get(i-1).getValue())){
                return true;
            }
        }
        return false;
    }

    private ArrayList<Jogador> criarJogadores(){//Cria os jogadores que serão apresentados na próxima tela, no tabuleiro
        ArrayList<Jogador> jogadores=new ArrayList<>();
        for (int i=0;i<quantidadeJogadores;i++){
            if(choiceBoxes.get(i).getValue().equals("Sortudo")){
                jogadores.add(new JogadorSortudo(Cores.values()[i]));
            }else if(choiceBoxes.get(i).getValue().equals("Azarado")){
                jogadores.add(new JogadorAzarado(Cores.values()[i]));
            }else{
                jogadores.add(new JogadorNormal(Cores.values()[i]));
            }
        }
        return jogadores;
    }

    @FXML
    void play(ActionEvent event) throws Exception {//Passa para a próxima tela, no caso, a Tela Jogo
        if (jogadorDiferente()){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Jogo.fxml"));
            Parent root = loader.load();
            Jogo telaJogo=loader.getController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            telaJogo.setJogadores(criarJogadores());
        }
    }

    @FXML
    void voltar(ActionEvent event) throws Exception{//Volta para a tela anterior, no caso, a Tela Quantidade de Jogadores
        Parent root = FXMLLoader.load(getClass().getResource("QuantidadeJogadores.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}