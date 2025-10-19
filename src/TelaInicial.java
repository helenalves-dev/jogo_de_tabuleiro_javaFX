import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TelaInicial {

    @FXML
    private Button IniciarJogo;

    @FXML
    private Button Sair;

    @FXML
    void iniciarJogo(ActionEvent event) throws Exception {//Botão para iniciar o jogo
        Parent root = FXMLLoader.load(getClass().getResource("QuantidadeJogadores.fxml"));//Indica a próxima tela
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean confirmacaoSair(){//Pop-up para verificar se o usuário deseja sair e fechar o jogo
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

}
