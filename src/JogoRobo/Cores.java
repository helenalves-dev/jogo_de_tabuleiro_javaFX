import javafx.scene.paint.Color;

public enum Cores {
    AZUL(Color.BLUE,"Azul"), 
    VERMELHO(Color.RED,"Vermelho"), 
    AMARELO(Color.YELLOW,"Amarelo"), 
    VERDE(Color.GREEN,"Verde"), 
    LARANJA(Color.ORANGE,"Laranja"), 
    ROXO(Color.PURPLE,"Roxo");
    
    final Color colorValue;
    final String colorName;
    Cores(Color colorValue, String colorName){
        this.colorValue = colorValue;
        this.colorName = colorName;
    }   
}
