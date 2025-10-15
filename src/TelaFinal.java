import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TelaFinal {

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
    public void initialize(ArrayList<Jogador> jogador){
        jogadores.setCellValueFactory(new PropertyValueFactory<>("cor"));
        pontuacao.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));
        turnos.setCellValueFactory(new PropertyValueFactory<>("turnosJogados"));

        for (Jogador player:jogador){
            estatisticas.getItems().add(player);
        }
    }

    @FXML
    void sair(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void voltarTelaInicial(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
