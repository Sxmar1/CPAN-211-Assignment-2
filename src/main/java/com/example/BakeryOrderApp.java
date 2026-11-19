package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BakeryOrderApp extends Application {

    private TextField nameField;
    private TextField phoneField;
    private ComboBox<String> cakeTypeComboBox;
    private ToggleGroup sizeGroup;
    private RadioButton smallRadio;
    private RadioButton mediumRadio;
    private RadioButton largeRadio;
    private CheckBox freeDeliveryCheckBox;

    @Override
    public void start(Stage primaryStage) {
        nameField = new TextField();
        phoneField = new TextField();
        cakeTypeComboBox = new ComboBox<>();

        cakeTypeComboBox.getItems().addAll(
                "Apple", "Carrot", "Cheesecake", "Chocolate", "Coffee", "Opera", "Tiramisu"
        );

        sizeGroup = new ToggleGroup();
        smallRadio = new RadioButton("Small");
        mediumRadio = new RadioButton("Medium");
        largeRadio = new RadioButton("Large");
        smallRadio.setToggleGroup(sizeGroup);
        mediumRadio.setToggleGroup(sizeGroup);
        largeRadio.setToggleGroup(sizeGroup);
        smallRadio.setSelected(true);

        HBox sizeBox = new HBox(10, smallRadio, mediumRadio, largeRadio);

        freeDeliveryCheckBox = new CheckBox("Free Delivery");

        Button saveButton = new Button("Save");
        Button quitButton = new Button("Quit");

        saveButton.setOnAction(e -> saveOrder());
        quitButton.setOnAction(e -> Platform.exit());

        HBox buttonBox = new HBox(10, saveButton, quitButton);

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(15));
        pane.setHgap(10);
        pane.setVgap(10);

        pane.add(new Label("Customer's Name:"), 0, 0);
        pane.add(nameField, 1, 0);

        pane.add(new Label("Customer's Phone Number:"), 0, 1);
        pane.add(phoneField, 1, 1);

        pane.add(new Label("Cake Type:"), 0, 2);
        pane.add(cakeTypeComboBox, 1, 2);

        pane.add(new Label("Cake Size:"), 0, 3);
        pane.add(sizeBox, 1, 3);

        pane.add(freeDeliveryCheckBox, 1, 4);
        pane.add(buttonBox, 1, 5);

        Scene scene = new Scene(pane, 400, 260);
        primaryStage.setTitle("Bakery Orders Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveOrder() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String cakeType = cakeTypeComboBox.getValue();
        String cakeSize = getSelectedSize();
        boolean freeDelivery = freeDeliveryCheckBox.isSelected();

        BakeryOrder order = new BakeryOrder(name, phone, cakeType, cakeSize, freeDelivery);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Orders.txt", true))) {
            writer.write(order.formatOrder());
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        resetFields();
    }

    private String getSelectedSize() {
        if (smallRadio.isSelected()) {
            return "Small";
        }
        if (mediumRadio.isSelected()) {
            return "Medium";
        }
        if (largeRadio.isSelected()) {
            return "Large";
        }
        return "";
    }

    private void resetFields() {
        nameField.clear();
        phoneField.clear();
        cakeTypeComboBox.setValue(null);
        smallRadio.setSelected(true);
        freeDeliveryCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}