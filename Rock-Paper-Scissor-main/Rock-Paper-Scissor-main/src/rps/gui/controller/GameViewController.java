package rps.gui.controller;

// Java imports
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    private static final String PAPER = "paper";
    private static final String STONE = "stone";
    private static final String SCISSORS = "scissors";
    @FXML
    private BorderPane borderPane;
    @FXML
    private ImageView imageViewPlayer,
            imageViewComputer;
    @FXML
    private Label lblPlayerScore,
            lblComputerScore,
            lblResult,
            lblCompName;

    @FXML
    private Button btnStone,
            btnPaper,
            btnScissors;
    private Image image;
    private Boolean gamaOver = false;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblCompName.setText(playerName());

    }

    private void displayResults(String choice){
        imageViewComputer.setImage(new Image("rps/gui/view/icon game/ROCK.png"));
        imageViewPlayer.setImage(new Image("rps/gui/view/icon game/ROCK.png"));
        animateHand(imageViewComputer);
        animateHand(imageViewPlayer, choice);
    }

    private void animateHand(ImageView image) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(image);
        translate.setDuration(Duration.millis(100));
        translate.setCycleCount(6);
        translate.setByX(20);
        translate.setByY(-50);
        translate.setAutoReverse(true);
        translate.play();
    }
    private void animateHand(ImageView image, String choice) {

        TranslateTransition translate = new TranslateTransition();
        translate.setNode(image);
        translate.setDuration(Duration.millis(100));
        translate.setCycleCount(6);
        translate.setByX(20);
        translate.setByY(-50);
        translate.setAutoReverse(true);
        translate.play();

        translate.setOnFinished(e->{
            if(choice.equalsIgnoreCase("paper"))
                imageViewPlayer.setImage(new Image("rps/gui/view/icon game/PAPER.png"));
            else if (choice.equalsIgnoreCase("scissors"))
                imageViewPlayer.setImage(new Image("rps/gui/view/icon game/SCISSORS.png"));

            winner(choice, computerTurn());
        });
    }

    public String playerName (){
        String [] names = new String[] {"Jack" , "William", "Alex", "Lucas"};
        Random r = new Random();
        int index = r.nextInt(names.length);
        return names[index];
    }


    public void playerTurn(javafx.event.ActionEvent actionEvent) {
        String playerChoice = null;
        switch (((Button) actionEvent.getSource()).getId()) {
            case "btnPaper":
                image = new Image("rps/gui/view/icon game/PAPER.png");
                playerChoice = PAPER;
                break;

            case "btnStone":
                image = new Image("rps/gui/view/icon game/ROCK.png");
                playerChoice = STONE;
                break;

            case "btnScissors":
                image = new Image("rps/gui/view/icon game/SCISSORS.png");
                playerChoice = SCISSORS;
                break;
        }
        imageViewPlayer.setImage(image);
        displayResults(playerChoice);

    }

    public String computerTurn() {
        String computerChoice = null;
        int index = (int) (Math.random() * 3);
        switch (index) {
            case 0:
                image = new Image("rps/gui/view/icon game/ROCK.png");
                computerChoice = STONE;
                break;
            case 1:
                image = new Image("rps/gui/view/icon game/paper.png");
                computerChoice = PAPER;
                break;
            case 2:
                image = new Image("rps/gui/view/icon game/Scissors.png");
                computerChoice = SCISSORS;
                break;
        }
        imageViewComputer.setImage(image);

        return computerChoice;
    }

    public void playerWin() {
        lblResult.setText("You Win");
        lblPlayerScore.setText(String.valueOf(Integer.parseInt(lblPlayerScore.getText()) + 1));

    }

    public void computerWin() {
        lblResult.setText("You Lose");
        lblComputerScore.setText(String.valueOf(Integer.parseInt(lblComputerScore.getText()) + 1));
    }

    public void draw() {
        lblResult.setText("Draw");
    }

    private void winner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            draw();
            return;
        }
        if (playerChoice.equals(STONE)) {
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

    public void endGame() {
    }
}

