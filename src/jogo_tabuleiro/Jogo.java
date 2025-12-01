package jogo_tabuleiro;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Jogo {//Implementação do jogo e regras

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

    private String jogadorString="Jogador ";

    @FXML
    public void initialize(){//Cria o tabuleiro
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

    void setJogadores(ArrayList <Jogador> jogadores){//Organiza os jogadores no tabuleiro
        this.jogadores=jogadores;
        for (int i=0;i<jogadores.size();i++){
            jogadores.get(i).setRadius(radius/jogadores.size()-1);
            casas.get(0).getChildren().add(jogadores.get(i));
            pontuacaoJogadores.getItems().addAll(jogadorString+jogadores.get(i).getCor().colorName+"|Turnos: "+jogadores.get(i).getTurnosJogados());
        }
        mostrarPopUp("Turno Atual", jogadorString+jogadores.get(jogadorAtual).getCor().colorName);//Pop-up que avisa quem é o jogador atual
    }

    @FXML
    void jogarDados(ActionEvent event) throws Exception{//Botão que rola os dados
        turno();
        jogarDados.setVisible(false);
        proxJogador.setVisible(true);
        if(jogoTerminou){//Se o usuário atingir o fim do jogo, muda a tela para a Tela Final
            FXMLLoader loader=new FXMLLoader(getClass().getResource("TelaFinal.fxml"));
            Parent root = loader.load();
            TelaFinal tela=loader.getController();
            tela.initialize(jogadores);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void proxJogador(ActionEvent event) {//Espera pelo próximo jogador
        dado1.setText(" ");
        dado2.setText(" ");
        somaDados.setText(" ");
        pontuacaoJogadores.getItems().clear();
        for (int i=0;i<jogadores.size();i++){
            pontuacaoJogadores.getItems().addAll(jogadorString+jogadores.get(i).getCor().colorName+"|Turnos: "+jogadores.get(i).getTurnosJogados());
        }
        proxJogador.setVisible(false);
        jogarDados.setVisible(true);
        mostrarPopUp("Turno Atual", jogadorString+jogadores.get(jogadorAtual).getCor().colorName);//Mostrar quem é o próximo jogador
    }

    @FXML
    void debug(KeyEvent event) throws Exception{//Modo debug, ativado ao apertar a tecla d
        if(event.getCode()==KeyCode.D){
            modo_debug=true;
            turno();
            if(jogoTerminou){//Se o usuário chegar na última casa, passa para a Tela Final
                FXMLLoader loader=new FXMLLoader(getClass().getResource("TelaFinal.fxml"));
                Parent root = loader.load();
                TelaFinal tela=loader.getController();
                tela.initialize(jogadores);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        jogarDados.setVisible(false);
        proxJogador.setVisible(true);
    }

    private void mostrarPopUp(String titulo, String mensagem) {//Método utilizado para criar a estrutura do pop-ups que são utilizados ao longo do código
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // sem título interno
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private int modoDebug(){//Pop-up que recebe a casa que o usuário vai
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

    private void moverJogador(Jogador jogador, int casaAntiga, int casaAtual){//Move o jogador pelas casas do tabuleiro e adapta o tamanho dos jogadores
        casas.get(casaAntiga).getChildren().remove(jogador);
        for (Node node:casas.get(casaAntiga).getChildren()){
            Circle player=(Circle) node;
            player.setRadius(radius/casas.get(casaAntiga).getChildren().size());
        }
        jogador.setPontuacao(casaAtual);
        casas.get(jogador.getPontuacao()).getChildren().add(jogador);
        for (Node node:casas.get(jogador.getPontuacao()).getChildren()){
            Circle player=(Circle) node;
            player.setRadius(radius/casas.get(jogador.getPontuacao()).getChildren().size());
        }
    }

    void turno(){//Implementação da lógica do jogo
        Jogador jogador=jogadores.get(jogadorAtual);
        int casaAntiga=jogador.getPontuacao();
        if(jogador.getPassaVez()){
            mostrarPopUp("Perdeu a Vez", "Jogue na próxima rodada!");
            jogador.setPassaVez(false);
            if (jogadorAtual<jogadores.size()-1){
                jogadorAtual++;
            }else{
                jogadorAtual=0;
            }
            return;
        }
        int[] dados=jogador.jogarDados();
        if(!modo_debug){
            dado1.setText("\t  "+dados[0]);
            dado2.setText("\t  "+dados[1]);
            somaDados.setText("\t  "+(dados[0]+dados[1]));
            moverJogador(jogador, casaAntiga, jogador.getPontuacao());
        }else{
            int casaAtual=modoDebug();
            moverJogador(jogador, casaAntiga,casaAtual);
            modo_debug=false;
        }
        if (jogador.getPontuacao()>=39){
            jogador.setTurnosJogados(jogador.getTurnosJogados()+1);
            mostrarPopUp("Vitória!!", jogadorString+jogador.getCor().colorName+" venceu!!");
            jogoTerminou=true;
            return;
        }
        if (dados[0]==dados[1]){
            mostrarPopUp("Dados Iguais!", "Você pode jogar de novo!");
            return;
        }
        CasasEspeciais casaEspecial = CasasEspeciais.fromCasa(jogador.getPontuacao());
        if (casaEspecial != null) {
            casaEspecial.executar(jogadores, jogadorAtual, casas);
        }
        if (jogadorAtual<jogadores.size()-1){
            jogadorAtual++;
        }else{
            jogadorAtual=0;
        }
    }

}