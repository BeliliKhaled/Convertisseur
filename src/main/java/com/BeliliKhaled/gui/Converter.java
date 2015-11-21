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
        primaryStage.setTitle("Convertisseur de devises EUR/USD/GBD");
        Group root= new Group();
        Scene scene = new Scene(root,400,300,Color.ANTIQUEWHITE);
        TextField numberFrom = new TextField();
        numberFrom.setPrefSize(100, 15);

        TextField numberTo = new TextField();
        numberTo.setDisable(true);
        numberTo.setPrefSize(100, 15);


        group=new ToggleGroup();
        eur=new RadioButton("EUR");
        usd=new RadioButton("USD");
        gbd=new RadioButton("GBD");
        eur.setUserData(unitEUR);
        usd.setUserData(unitUSD);
        gbd.setUserData(unitGBD);
        eur.setToggleGroup(group);
        usd.setToggleGroup(group);
        gbd.setToggleGroup(group);
        usd.setSelected(true);
        vBox1 = new VBox(20, numberFrom, eur, usd, gbd);
        vBox1.setLayoutX(50);
        vBox1.setLayoutY(50);

        group2=new ToggleGroup();
        eur2=new RadioButton("EUR");
        usd2=new RadioButton("USD");
        gbd2=new RadioButton("GBD");
        eur2.setUserData(unitEUR);
        usd2.setUserData(unitUSD);
        gbd2.setUserData(unitGBD);
        eur2.setToggleGroup(group2);
        usd2.setToggleGroup(group2);
        gbd2.setToggleGroup(group2);
        usd2.setSelected(true);
        vBox2 = new VBox(20, numberTo, eur2, usd2, gbd2);
        vBox2.setLayoutX(210);
        vBox2.setLayoutY(50);

        Button calcul = new Button();
        calcul.setText("CONVERSION");
        calcul.setLayoutX(125);
        calcul.setLayoutY(225);
        calcul.setPrefSize(100, 30);

        calcul.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                numberTo.setText(calcul((Unit) group.getSelectedToggle().getUserData(), (Unit) group2.getSelectedToggle().getUserData(), numberFrom.getText()));
            }
        });

        root.getChildren().add(vBox1);
        root.getChildren().add(vBox2);
        root.getChildren().add(calcul);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private String calcul(Unit fromMoney, Unit toMoney,String value)
    {
        return String.valueOf(Integer.parseInt(value)*fromMoney.multiplier/toMoney.multiplier);
    }

}