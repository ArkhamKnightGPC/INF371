package com.td8;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;   
import javafx.scene.*;   
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class GraphicsCalculator extends Application {   
    Tokenizer tok = new Tokenizer();

    final StringProperty calcText = new SimpleStringProperty();
    public final String getCalcText() {
        return this.calcText.get();
    }

    public final void setCalcText(String value) {
        this.calcText.set(value);
    }

    Label calcLabel = new Label("");

    @Override
    public void start(Stage stage) {
        stage.show();
        stage.setTitle("Calculatrice");
        stage.setWidth(200);
        stage.setHeight(400);

        calcLabel.textProperty().bind(calcText);
        calcLabel.setMinWidth(200);
        calcLabel.setMinHeight(80);
        HBox result = new HBox(calcLabel);

        Button b7 = b('7');

        Scene scene = new Scene(new VBox(
            result,
            new HBox(b7, b('8'), b('9'), b('+')),
            new HBox(b('4'), b('5'), b('6'), b('-')),
            new HBox(b('1'), b('2'), b('3'), b('*')),
            new HBox(b('0'), b('.'), b('C'), b('/')),
            new HBox(b('('), b(')'), b('$'), b('='))
        ));

        scene.setOnKeyPressed(e -> {
            handlekey(e);
        });

        stage.setScene(scene);
    }
    
    private void handlekey(KeyEvent e) {   
        String tx = e.getText();
        for(int i=0; i<tx.length(); i++){
            update(tx.charAt(i));
        }
    }

    Button b(char c){
        Button ret = new Button();
        ret.setMinSize(50, 50);
        ret.setMaxSize(50, 50);
        ret.setText(String.valueOf(c));
        ret.setOnAction(e -> {
            update(c);
        });
        return ret;
    }

    void update(char c){
        tok.readChar(c);
        if(c=='='){
            setCalcText(Double.toString(tok.calc.getResult()));
            tok.readString("$1");
        }else if(c=='C'){
            setCalcText("");
        }else{
            String curText = getCalcText();
            if(curText == null)curText = "" + c;
            else curText += c;
            setCalcText(curText);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}