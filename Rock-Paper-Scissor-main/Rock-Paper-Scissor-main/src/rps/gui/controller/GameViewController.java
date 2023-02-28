package rps.gui.controller;

// Java imports
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    private static final String PAPER = "paper";
    private static final String STONE = "stone";
    private static final String SCISSORS = " scissors";
    @FXML
    private ImageView imageViewPlayer,
              imageViewComputer;
    @FXML
    private Label lblPlayerScore,
                  lblComputerScore,
                  lblResult;
    @FXML
    private Button btnStone,
                   btnPaper,
                   btnScissors;
    private Image image;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    public void playerTurn(javafx.event.ActionEvent actionEvent) {
        String playerChoice = null;
        switch (((Button) actionEvent.getSource()).getId()) {
            case "btnPaper" :
                image = new Image("rps/gui/view/icon game/PAPER.png");
                playerChoice = PAPER;
                break;
            case "btnStone" :
                image = new Image("rps/gui/view/icon game/ROCK.png");
                playerChoice = STONE;
                break;

            case "btnScissors" :
                image = new Image("rps/gui/view/icon game/SCISSORS.png");
                playerChoice = SCISSORS;
                break;
        }
        imageViewPlayer.setImage(image);
        winner(playerChoice,computerTurn());
    }

    public String computerTurn() {
        String computerChoice = null;
        int index = (int) (Math.random() * 3);
        switch (index) {
            case 0 :
                image = new Image("rps/gui/view/icon game/ROCK.png");
                computerChoice = STONE;
                break;
            case 1 :
                image = new Image("rps/gui/view/icon game/paper.png");
                computerChoice = PAPER;
                break;
            case 2 :
                image = new Image("rps/gui/view/icon game/Scissors.png");
                computerChoice = SCISSORS;
                break;
        }
        imageViewComputer.setImage(image);

        return computerChoice;
    }

    public void playerWin() {
        lblResult.setText("You Win");
        lblPlayerScore.setText(String.valueOf(Integer.parseInt(lblPlayerScore.getText()) + 1 ));
    }

    public void computerWin() {
        lblResult.setText("You Lose");
        lblComputerScore.setText(String.valueOf(Integer.parseInt(lblComputerScore.getText())+ 1 ));
    }

    public void draw() {
        lblResult.setText("Draw");
    }

    private void winner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            draw();
            return;
        }
        if(playerChoice.equals(STONE)) {
            if (computerChoice.equals(PAPER)) {
                computerWin();
            } else if (computerChoice.equals(SCISSORS)) {
                playerWin();
            }

        } else if (playerChoice.equals(PAPER)) {
            if (computerChoice.equals(STONE)) {
                playerWin();
            } else if (computerChoice.equals(SCISSORS)) {
                computerWin();
            }

        } else {
            if (computerChoice.equals(STONE)) {
                computerWin();
            } else if (computerChoice.equals(PAPER)) {
                playerWin();

            }
        }
    }

    public void endGame(){

    }


}

