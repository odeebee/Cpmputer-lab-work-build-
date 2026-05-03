package com.atmbanksimulator;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

class View {
    int H = 650;
    int W = 620;

    Controller controller;

    private Label laMsg;
    private TextField tfInput;
    private TextArea taResult;
    private ScrollPane scrollPane;
    private GridPane grid;
    private GridPane buttonPane;

    private Button login;
    private Button create;

    public void start(Stage window) {

        grid = new GridPane();
        grid.setId("Layout");
        grid.setPadding(new Insets(18));
        grid.setVgap(14);
        grid.setHgap(10);
        grid.setAlignment(Pos.TOP_CENTER);

        laMsg = new Label("Welcome to the ATM");
        laMsg.setId("titleLabel");
        grid.add(laMsg, 0, 0);

        tfInput = new TextField();
        tfInput.setEditable(false);
        tfInput.setPrefWidth(540);
        tfInput.setPrefHeight(48);
        grid.add(tfInput, 0, 1);

        taResult = new TextArea();
        taResult.setEditable(false);
        taResult.setWrapText(true);
        taResult.setPrefWidth(540);
        taResult.setPrefHeight(180);

        scrollPane = new ScrollPane();
        scrollPane.setContent(taResult);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(540);
        scrollPane.setPrefHeight(190);
        grid.add(scrollPane, 0, 2);

        create = new Button("Create an account");
        create.setId("createAccountButton");
        create.setPrefWidth(540);
        create.setPrefHeight(55);
        create.setOnAction(this::buttonClicked);
        grid.add(create, 0, 3);

        login = new Button("Login");
        login.setId("loginButton");
        login.setPrefWidth(540);
        login.setPrefHeight(55);
        login.setOnAction(this::buttonClicked);
        grid.add(login, 0, 4);

        buttonPane = new GridPane();
        buttonPane.setId("Buttons");
        buttonPane.setHgap(14);
        buttonPane.setVgap(12);
        buttonPane.setAlignment(Pos.CENTER);

        String[][] buttonTexts = {
                {"7",   "8", "9", "Dep"},
                {"4",   "5", "6", "W/D"},
                {"1",   "2", "3", "Bal"},
                {"CLR", "0", "Pas", "Ent"},
                {"",    "",  "Fin", ""}
        };

        for (int row = 0; row < buttonTexts.length; row++) {
            for (int col = 0; col < buttonTexts[row].length; col++) {

                String text = buttonTexts[row][col];

                if (!text.isEmpty()) {
                    Button btn = new Button(text);

                    if (text.matches("[0-9]")) {
                        btn.setPrefSize(70, 70);
                        btn.setMinSize(70, 70);
                        btn.setMaxSize(70, 70);
                        btn.setId("numberButton");
                    } else {
                        btn.setPrefSize(95, 65);
                        btn.setMinSize(95, 65);
                        btn.setMaxSize(95, 65);

                        switch (text) {
                            case "CLR":
                                btn.setId("clearButton");
                                break;
                            case "Ent":
                                btn.setId("enterButton");
                                break;
                            case "Fin":
                                btn.setId("finishButton");
                                break;
                            case "Dep":
                                btn.setId("depositButton");
                                break;
                            case "W/D":
                                btn.setId("withdrawButton");
                                break;
                            case "Bal":
                                btn.setId("balanceButton");
                                break;
                            case "Pas":
                                btn.setId("passwordButton");
                                break;
                        }
                    }

                    btn.setOnAction(this::buttonClicked);
                    buttonPane.add(btn, col, row);
                }
            }
        }

        grid.add(buttonPane, 0, 5);
        buttonPane.setVisible(false);

        Scene scene = new Scene(grid, W, H);

        scene.getStylesheets().add(
                getClass().getResource("/atm.css").toExternalForm()
        );

        window.setScene(scene);
        window.setTitle("ATM-Bank Simulator");
        window.show();
    }

    private void buttonClicked(ActionEvent event) {
        Button b = (Button) event.getSource();
        String text = b.getText();

        System.out.println("View::buttonClicked: label = " + text);
        controller.process(text);
    }

    public void update(String msg, String tfInputMsg, String taResultMsg) {
        laMsg.setText(msg);
        tfInput.setText(tfInputMsg);
        taResult.setText(taResultMsg);
    }

    public void changeButtons() {
        login.setVisible(false);
        create.setVisible(false);
        buttonPane.setVisible(true);
    }
}