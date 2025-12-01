import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

public class PerdeuVez implements CasasEspeciaisStrategy {
    @Override
    public void executarAcao(ArrayList<Jogador> jogadores, int jogadorAtualIndex, ArrayList<HBox> casas) {
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
    
}
