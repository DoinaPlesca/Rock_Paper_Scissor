package rps.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    @FXML
    private Label labelRules,
            lblTitle;
    @FXML
    private Button btnPlay;

    public void startGame(ActionEvent actionEvent) throws IOException {
        startPopUp();
        Stage stage = (Stage) btnPlay.getScene().getWindow();
        stage.close();
    }


    public void startPopUp () throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("rps/gui/view/GameView.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle(" Rock-Paper-Scissor game!");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelRules.setText(
                "Rock beats Scissors" + "\n" +
                        "Paper beats Rock" + "\n" +
                        "Scissors beats Paper" + "\n" +
                "In order to make your choice you simply click on the relevant button " + "\n" +
                "You are playing against the computer and the computer's "+ "\n" +
                "choice is randomly selected." + "\n" +"\n" );



    }
}
