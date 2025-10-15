import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class QuantidadeJogadores {
    private int quantidadeDeJogadores;

    @FXML
    private Label nomeQuantidadeJogadores;

    @FXML
    private RadioButton doisJogadores;

    @FXML
    private RadioButton tresJogadores;

    @FXML
    private RadioButton quatroJogadores;

    @FXML
    private RadioButton cincoJogadores;

    @FXML
    private RadioButton seisJogadores;

    @FXML
    private Button continuar;
    
    @FXML
    public void initialize() {
        ToggleGroup grupo = new ToggleGroup();

        doisJogadores.setToggleGroup(grupo);
        tresJogadores.setToggleGroup(grupo);
        quatroJogadores.setToggleGroup(grupo);
        cincoJogadores.setToggleGroup(grupo);
        seisJogadores.setToggleGroup(grupo);

        doisJogadores.setUserData(2);
        tresJogadores.setUserData(3);
        quatroJogadores.setUserData(4);
        cincoJogadores.setUserData(5);
        seisJogadores.setUserData(6);

        grupo.selectedToggleProperty().addListener((newToggle) -> {
            if (newToggle != null) {
                quantidadeDeJogadores = (int) grupo.getSelectedToggle().getUserData();
            }
        });
        doisJogadores.setSelected(true);
    }
    @FXML
    void continuar(ActionEvent event) throws Exception {
        if (quantidadeDeJogadores!=0){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaTipoJogador.fxml"));
            Parent root = loader.load();
            TelaTipoJogador tela=loader.getController();
            tela.setQuantidadeJogadores(quantidadeDeJogadores);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
