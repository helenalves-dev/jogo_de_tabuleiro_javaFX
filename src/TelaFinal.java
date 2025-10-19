import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TelaFinal {//Mostras as estatíticas do jogo, apresentando os jogadores, quantos turnos jogaram e qual foi sua última casa(Pontuação)

    @FXML
    private TableView<Jogador> estatisticas;

    @FXML
    private TableColumn<Jogador, String> jogadores;

    @FXML
    private TableColumn<Jogador, Integer> pontuacao;

    @FXML
    private TableColumn<Jogador, Integer> turnos;

    @FXML
    private Button sair;

    @FXML
    private Button voltaTelaInicial;

    @FXML
    public void initialize(ArrayList<Jogador> jogador){//Tabela com estatíticas
        jogadores.setCellValueFactory(new PropertyValueFactory<>("cor"));
        pontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        turnos.setCellValueFactory(new PropertyValueFactory<>("turnosJogados"));

        for (Jogador player:jogador){
            estatisticas.getItems().add(player);
        }
    }


    private boolean confirmacaoSair(){//Pop-up que confirma se o jogador gostaria de sair e fechar o jogo
        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confimação de Saída");
        alert.setHeaderText("Deseja realmente sair do jogo?");
        if(alert.showAndWait().get()==ButtonType.OK){
            return true;
        }
        return false;
    }

    @FXML
    void sair(ActionEvent event) {//Fecha o jogo
        if(confirmacaoSair()){
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void voltarTelaInicial(ActionEvent event) throws Exception {//Retorna para a Tela Inicial
        Parent root = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
