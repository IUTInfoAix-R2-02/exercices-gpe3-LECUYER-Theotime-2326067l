package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        // code de l'exercice 1
        BorderPane mainContainer = new BorderPane();

        // Menubar
        Menu menu1 = new Menu("File");
        MenuItem newFile = new MenuItem("New");
        MenuItem openFile = new MenuItem("Open");
        MenuItem saveFile = new MenuItem("Save");
        MenuItem closeFile = new MenuItem("Close");
        menu1.getItems().addAll(newFile, openFile, saveFile, closeFile);
        Menu menu2 = new Menu("Edit");
        MenuItem cutEdit = new MenuItem("Cut");
        MenuItem copyEdit = new MenuItem("Copy");
        MenuItem pasteEdit = new MenuItem("Paste");
        menu2.getItems().addAll(cutEdit,copyEdit,pasteEdit);
        Menu menu3 = new Menu("Help");
        MenuBar menuBar = new MenuBar(menu1, menu2, menu3);
        mainContainer.setTop(menuBar);

        // Left buttons
        HBox leftContainer = new HBox();
        VBox leftButtons = new VBox();
        leftButtons.setAlignment(Pos.CENTER);
        VBox.setVgrow(leftButtons,Priority.ALWAYS);
        Separator sepV = new Separator(Orientation.VERTICAL);
        leftContainer.getChildren().addAll(leftButtons, sepV);
        mainContainer.setLeft(leftContainer);
        Label boutonLabel = new Label("Boutons :");
        Button bouton1 = new Button("Bouton 1");
        Button bouton2 = new Button("Bouton 2");
        Button bouton3 = new Button("Bouton 3");
        leftButtons.getChildren().addAll(boutonLabel,bouton1,bouton2,bouton3);
        leftButtons.getChildren().forEach(child -> VBox.setMargin(child, new Insets(5.0d,0.0d,5.0d,0.0d)));

        // Right content
        VBox mainContent = new VBox();
        mainContent.setAlignment(Pos.CENTER);
        GridPane form = new GridPane();
        mainContent.getChildren().add(form);
        mainContainer.setCenter(mainContent);
        form.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        GridPane.setColumnIndex(nameLabel,0);
        GridPane.setColumnIndex(nameField,1);
        form.getChildren().addAll(nameLabel,nameField);
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        GridPane.setColumnIndex(emailLabel,0);
        GridPane.setRowIndex(emailLabel,1);
        GridPane.setColumnIndex(emailField,1);
        GridPane.setRowIndex(emailField,1);
        form.getChildren().addAll(emailLabel,emailField);
        Label pwLabel = new Label("Password:");
        TextField pwField = new TextField();
        GridPane.setColumnIndex(pwLabel,0);
        GridPane.setRowIndex(pwLabel,2);
        GridPane.setColumnIndex(pwField,1);
        GridPane.setRowIndex(pwField,2);
        form.getChildren().addAll(pwLabel,pwField);
        form.getChildren().forEach(child -> GridPane.setMargin(child, new Insets(5.0d)));

        HBox formButtons = new HBox();
        formButtons.setAlignment(Pos.BOTTOM_CENTER);
        HBox.setHgrow(formButtons,Priority.ALWAYS);
        mainContent.getChildren().add(formButtons);
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");
        formButtons.getChildren().addAll(submit,cancel);
        formButtons.getChildren().forEach(child -> HBox.setMargin(child, new Insets(5.0d)));

        // Bottom label
        VBox footerContainer = new VBox();
        Separator sepH = new Separator(Orientation.HORIZONTAL);
        HBox footer = new HBox();
        footerContainer.getChildren().addAll(sepH,footer);
        mainContainer.setBottom(footerContainer);
        footer.getChildren().add(new Label("Ceci est un label de bas de page"));
        footer.setAlignment(Pos.BOTTOM_CENTER);
        HBox.setHgrow(footer,Priority.ALWAYS);

        Scene scene = new Scene(mainContainer, 500, 350);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}

