import javafx.application.Application;
import javafx.stage.Stage;   
import javafx.scene.*;   
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GraphicsCalculator extends Application {   
    Tokenizer tok;
    Label label;
    
    @Override
    public void start(Stage stage) {
        stage.show();
        stage.setTitle("Calculatrice");
        stage.setWidth(200);
        stage.setHeight(200);
        
        HBox result = new HBox(label);



        Scene scene = new Scene(new VBox(
            result,
            b('7'), b('8'), b('9'), b('+'),
            b('4'), b('5'), b('6'), b('-'),
            b('1'), b('2'), b('3'), b('*'),
            b('0'), b('.'), b('C'), b('/'),
            b('('), b(')'), b('$'), b('=')
        ));
        stage.setScene(scene);
    }
    
    Button b(char c){
        Button ret = new Button();
        ret.setMinSize(30, 30);
        ret.setMaxSize(30, 30);
        ret.setText(String.valueOf(c));
        return ret;
    }

    public static void main(String[] args) {
        launch(args);
    }
}