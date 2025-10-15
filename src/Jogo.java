import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Jogo {

    @FXML
    private GridPane grid;

    private ArrayList<HBox> casas=new ArrayList<>();

    private ArrayList<Jogador> jogadores;

    @FXML
    private ListView<String> pontuacaoJogadores=new ListView<>();
    

    int radius=23;

    @FXML
    public void initialize(){
        for(int i=0;i<5;i++){
            for(int j=0;j<8;j++){
                HBox casa=new HBox();
                casas.add(casa);
                grid.add(casa,i,j);
            }
        }
        pontuacaoJogadores.getItems().addAll("Jogador 1", "Jogador 2");
    }

    void setJogadores(ArrayList <Jogador> jogadores){
        this.jogadores=jogadores;
        for (int i=0;i<jogadores.size();i++){
            jogadores.get(i).setRadius(radius/jogadores.size()-1);
            casas.get(0).getChildren().add(jogadores.get(i));
            
        }
    }

    



}
