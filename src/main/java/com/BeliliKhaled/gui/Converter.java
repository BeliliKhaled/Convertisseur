package com.BeliliKhaled.gui;

import com.BeliliKhaled.model.Unit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class Converter extends Application {

    private ToggleGroup group, group2;
    private RadioButton eur, usd, gbd, eur2, gbd2, usd2;
    private VBox vBox1, vBox2;
    private Unit unitEUR = new Unit("EUR", 1.06485);
    private Unit unitUSD = new Unit("USD", 1);
    private Unit unitGBD = new Unit("GBD", 1.51915);

    public static void main(String[] args) {
        Application.launch(Converter.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Convertisseur devises EUR/USD/GBD");
        Group root= new Group();

        Scene scene = new Scene(root,380,310,Color.CORAL);
        TextField numberFrom = new TextField();
        numberFrom.setPrefSize(120, 15);

        //Text Box
        TextField numberTo = new TextField();
        numberTo.setDisable(true);
        numberTo.setPrefSize(120, 15);

        //RadioGroup
        group=new ToggleGroup();
        eur=new RadioButton(unitEUR.symbol);
        usd=new RadioButton(unitUSD.symbol);
        gbd=new RadioButton(unitGBD.symbol);
        eur.setUserData(unitEUR);
        usd.setUserData(unitUSD);
        gbd.setUserData(unitGBD);
        eur.setToggleGroup(group);
        usd.setToggleGroup(group);
        gbd.setToggleGroup(group);
        usd.setSelected(true);

        //Box vertical
        vBox1 = new VBox(20, numberFrom, eur, usd, gbd);
        vBox1.setLayoutX(50);
        vBox1.setLayoutY(40);

        //RadioGroup2
        group2=new ToggleGroup();
        eur2=new RadioButton(unitEUR.symbol);
        usd2=new RadioButton(unitUSD.symbol);
        gbd2=new RadioButton(unitGBD.symbol);
        eur2.setUserData(unitEUR);
        usd2.setUserData(unitUSD);
        gbd2.setUserData(unitGBD);
        eur2.setToggleGroup(group2);
        usd2.setToggleGroup(group2);
        gbd2.setToggleGroup(group2);
        usd2.setSelected(true);

        //Box Vertical 2
        vBox2 = new VBox(20, numberTo, eur2, usd2, gbd2);
        vBox2.setLayoutX(210);
        vBox2.setLayoutY(40);
        vBox2.setAlignment(Pos.TOP_RIGHT);

        //bouton Calcul
        Button calcul = new Button();
        calcul.setText("CONVERSION");
        calcul.setLayoutX(140);
        calcul.setLayoutY(215);
        calcul.setPrefSize(100, 30);

        //Action de bouton
        calcul.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                    numberTo.setText(calcul((Unit) group.getSelectedToggle().getUserData(), (Unit) group2.getSelectedToggle().getUserData(), numberFrom.getText()));
            }
        });


        root.getChildren().add(vBox1);
        root.getChildren().add(vBox2);
        root.getChildren().add(calcul);
        primaryStage.setScene(scene);
        primaryStage.setMaxHeight(310);
        primaryStage.setMaxWidth(390);
        primaryStage.show();
    }

    //methode de conversion
    private String calcul(Unit fromMoney, Unit toMoney,String value){

        try{
            if(value.length()==0)
                return "";
            String result = String.valueOf(Double.parseDouble(value) * fromMoney.multiplier / toMoney.multiplier);
            try
            {
                return result.substring(0, result.indexOf(".") + 3);
            }
            catch (Throwable t)
            {
                return result;
            }
        }
        catch (Throwable t)
        {
            return "Invalide Number";
        }
    }

}
