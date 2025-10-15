import java.util.ArrayList;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Jogo {

    @FXML
    private Label dado1;

    @FXML
    private Label dado2;

    @FXML
    private Button jogarDados;

    @FXML
    private Button proxJogador;

    @FXML
    private GridPane grid;

    private ArrayList<HBox> casas=new ArrayList<>();

    private ArrayList<Jogador> jogadores;

    private boolean jogoTerminou=false;

    private boolean modo_debug=false;

    @FXML
    private Label somaDados;

    @FXML
    private ListView<String> pontuacaoJogadores=new ListView<>();
    
    private int radius=23;

    private int jogadorAtual=0;

    @FXML
    public void initialize(){
        for(int i=0;i<5;i++){
            if((i%2)==0){
                for(int j=0;j<8;j++){
                    HBox casa=new HBox();
                    casas.add(casa);
                    grid.add(casa,j,i);
                }
            }else{
                for(int j=7;j>=0;j--){
                    HBox casa=new HBox();
                    casas.add(casa);
                    grid.add(casa,j,i);
                }
            }   
        }
        proxJogador.setVisible(false);
        dado1.setText(" ");
        dado2.setText(" ");
        somaDados.setText(" ");
    }

    void setJogadores(ArrayList <Jogador> jogadores){
        this.jogadores=jogadores;
        for (int i=0;i<jogadores.size();i++){
            jogadores.get(i).setRadius(radius/jogadores.size()-1);
            casas.get(0).getChildren().add(jogadores.get(i));
            pontuacaoJogadores.getItems().addAll("Jogador "+mostrarCor(jogadores.get(i))+"|Turnos: "+jogadores.get(i).getTurnosJogados());
        }
    }

    private Jogador redefinirJogador(Color cor, int turnosJogados, int pontuacao){
        Random random=new Random();
        int tipo=random.nextInt(3);
        if (tipo==0){
            return new JogadorSortudo(cor,turnosJogados,pontuacao);
        }else if(tipo==1){
            return new JogadorNormal(cor,turnosJogados,pontuacao);
        }else{
            return new JogadorAzarado(cor,turnosJogados,pontuacao);
        }
    }

    @FXML
    void jogarDados(ActionEvent event) throws Exception{
        turno();
        jogarDados.setVisible(false);
        proxJogador.setVisible(true);
        if(jogoTerminou){
            Parent root = FXMLLoader.load(getClass().getResource("TelaFinal.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void proxJogador(ActionEvent event) {
        dado1.setText(" ");
        dado2.setText(" ");
        somaDados.setText(" ");
        pontuacaoJogadores.getItems().clear();
        for (int i=0;i<jogadores.size();i++){
            pontuacaoJogadores.getItems().addAll("Jogador "+mostrarCor(jogadores.get(i))+"|Turnos: "+jogadores.get(i).getTurnosJogados());
        }
        proxJogador.setVisible(false);
        jogarDados.setVisible(true);
        mostrarPopUp("Turno Atual", "Jogador "+mostrarCor(jogadores.get(jogadorAtual)));
    }

    @FXML
    void debug(KeyEvent event) throws Exception{
        if(event.getCode()==KeyCode.D){
            modo_debug=true;
            turno();
            if(jogoTerminou){
                Parent root = FXMLLoader.load(getClass().getResource("TelaFinal.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            stage.show();
        }
        }
        jogarDados.setVisible(false);
        proxJogador.setVisible(true);
    }

    private void mostrarPopUp(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // sem título interno
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private int modoDebug(){
        Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Modo Debug");
        alert.setHeaderText("Insira a casa que deseja ir: ");
        TextField casa=new TextField();
        casa.setPromptText("Ex.: 27");
        VBox vbox=new VBox(casa);
        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();
        int selecionado=Integer.parseInt(casa.getText());
        return selecionado;
    }

    private int jogadorParaInicio(Jogador jogador){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Casa Especial");
        alert.setHeaderText("Escolha um jogador para voltar para o início: ");
        ToggleGroup grupo=new ToggleGroup();
        ArrayList<RadioButton> opcoes=new ArrayList<>();
        int jogadorIndex=0;
        for (Jogador player:jogadores){
            if(player==jogador){
                jogadorIndex++;
                continue;
            }else{
                RadioButton opcaoJogador=new RadioButton("Jogador "+mostrarCor(player));
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
        int selecionado=(int) grupo.getSelectedToggle().getUserData();
        return selecionado;
    }

    private String mostrarCor(Jogador jogador){
        Color cor=(Color)jogador.getFill();
        if (cor.equals(Color.BLUE)) return "Azul";
        if (cor.equals(Color.RED)) return "Vermelho";
        if (cor.equals(Color.YELLOW)) return "Amarelo";
        if (cor.equals(Color.GREEN)) return "Verde";
        if (cor.equals(Color.ORANGE)) return "Laranja";
        if (cor.equals(Color.PURPLE)) return "Roxo";
        return "Erro";
    }

    private void moverJogador(Jogador jogador, int casaAntiga){
        casas.get(casaAntiga).getChildren().remove(jogador);
        casas.get(jogador.getPontuacao()).getChildren().add(jogador);
    }

    private void moverJogador(Jogador jogador, int casaAntiga, int casaAtual){
        casas.get(casaAntiga).getChildren().remove(jogador);
        jogador.setPontuacao(casaAtual);
        casas.get(jogador.getPontuacao()).getChildren().add(jogador);
    }

    void turno(){
        Jogador jogador=jogadores.get(jogadorAtual);
        int casaAntiga=jogador.getPontuacao();
        if(jogador.getPassaVez()){
            mostrarPopUp("Perdeu a Vez", "Jogue na próxima rodada!");
            jogador.setPassaVez(false);
            return;
        }
        int[] dados=jogador.andarCasa();
        if(!modo_debug){
            dado1.setText(String.valueOf(dados[0]));
            dado2.setText(String.valueOf(dados[1]));
            somaDados.setText(String.valueOf(dados[0]+dados[1]));
            moverJogador(jogador, casaAntiga);
        }else{
            int casaAtual=modoDebug();
            moverJogador(jogador, casaAntiga,casaAtual);
            modo_debug=false;
        }
        if (jogador.getPontuacao()>=39){
            mostrarPopUp("Vitória!!", "Jogador "+mostrarCor(jogador)+" venceu!!");
            jogoTerminou=true;
            return;
        }
        if (dados[0]==dados[1]){
            mostrarPopUp("Dados Iguais!", "Você pode jogar de novo!");
            return;
        }
        switch(jogador.getPontuacao()){
            case 5:
            case 15:
            case 30:
                casaAntiga=jogador.getPontuacao();
                mostrarPopUp("Casa Especial", "Você pode avançar +3 casas");
                jogador.setPontuacao(jogador.getPontuacao()+3);
                moverJogador(jogador, casaAntiga);
                break;
            case 10:
            case 25:
            case 38:
                mostrarPopUp("Casa Especial", "Você perdeu seu próximo turno");
                jogador.setPassaVez(true);
                break;
            case 13:
                mostrarPopUp("Casa Especial", "Redefinindo Jogador");
                jogador=redefinirJogador((Color) jogador.getFill(),jogador.getTurnosJogados(),jogador.getPontuacao());
                mostrarPopUp("Casa Especial", "Você agora é um "+jogador.getClass());
                System.out.println("---Você agora é um "+jogador.getClass()+"---");
                break;
            case 17:
            case 27:
                int jogadorEscolhidoIndex=jogadorParaInicio(jogador);
                casaAntiga=jogadores.get(jogadorEscolhidoIndex).getPontuacao();
                jogadores.get(jogadorEscolhidoIndex).setPontuacao(0);
                moverJogador(jogadores.get(jogadorEscolhidoIndex), casaAntiga);
                break;
            case 20:
            case 35:
                mostrarPopUp("Casa Especial", "Troque de lugar com o jogador na última posição");
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
                jogadores.get(menorPontuacaoIndex).setPontuacao(jogador.getPontuacao());
                jogador.setPontuacao(menorPontuacao);
                break;
        }
        jogador.setTurnosJogados(jogador.getTurnosJogados()+1);
        if (jogadorAtual<jogadores.size()-1){
            jogadorAtual++;
        }else{
            jogadorAtual=0;
        }
    }

}
