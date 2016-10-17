package sample;


import edu.insightr.spellmonger.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import org.apache.log4j.Logger;


public class Controller {
    private static final Logger logger = Logger.getLogger(Controller.class);

    private SpellmongerApp game;
    private Player player1;
    private Player player2;
    @FXML
    private Text name1, life_points1, name2, life_points2;
    public Pane discard1, discard2;
    public ScrollPane list_creatures1, list_creatures2, hand1, hand2;
    public Button button_play1, button_play2, deck1, deck2;


    @FXML
    public void initialize() {
        update();
        button_play1.setDisable(false);
        button_play2.setDisable(true);
    }

    public Controller() {
        player1 = new Player("Valentin");
        player2 = new Player("Natacha");
        game = new SpellmongerApp(player1, player2);
    }

    public void draw1() {
        if (player1.size() == 0) player1.reCreateCardPool();
        player1.addToHand(player1.getCards().get(0));
        player1.getCards().remove(0);
        update();
        button_play1.setDisable(true);
        button_play2.setDisable(false);
    }

    public void draw2() {
        if (player2.size() == 0) player2.reCreateCardPool();
        player2.addToHand(player2.getCards().get(0));
        player2.getCards().remove(0);
        update();
        button_play1.setDisable(false);
        button_play2.setDisable(true);
    }

    // attaque entre creatures des deux joueurs
    private void attack(int index, Player current, Player oppenent, Pane discard) {
        if (!current.isDead()) {
            game.drawCard(current, oppenent, current.getHand(), index, current.getDiscards());
            current.attack(oppenent);
            Card lastElementDiscard = current.getDiscards().get(current.getDiscards().size() - 1);
            Rectangle rectangle = new Rectangle(100, 120);
            discard.getChildren().add(rectangle);
            if (lastElementDiscard instanceof Bear) {
                Image img = new Image("resources/images/Spellmonger_Bear.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (lastElementDiscard instanceof Wolf) {
                Image img = new Image("resources/images/Spellmonger_Wolf.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (lastElementDiscard instanceof Eagle) {
                Image img = new Image("resources/images/Spellmonger_Eagle.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (lastElementDiscard instanceof Ritual) {
                rectangle.setFill(Color.BLACK);
            }
            update();
        }
    }

    //met le jeu  jour apres chaque attack, pioche, etc..
    private void update() {
        if (player1.isDead() || player2.isDead()) {
            button_play1.setDisable(true);
            button_play2.setDisable(true);
        }
        name1.setText("\t" + player1.getName());
        life_points1.setText("Life point : " + player1.getLifePoint() + "\n Energy : " + player1.getEnergy());
        name2.setText("\t" + player2.getName());
        life_points2.setText("Life point : " + player2.getLifePoint() + "\n Energy : " + player2.getEnergy());
        // creatures sur la piste
        listCreatureContents(player1,list_creatures1);
        listCreatureContents(player2,list_creatures2);
        // hands
        hands(player1,player2,discard1,hand1);
        hands(player2,player1,discard2,hand2);
    }

    // les creatures en piste des joueurs
    private void listCreatureContents(Player p,ScrollPane scroll){
        HBox content = new HBox();
        scroll.setContent(content);
        content.setSpacing(20);
        content.setPadding(new Insets(10, 10, 10, 10));
        for (Card c : p.getPlayerCreature()) {
            Rectangle rectangle = new Rectangle(100, 120);
            if (c instanceof Bear) {
                Image img = new Image("resources/images/Spellmonger_Bear.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Eagle) {
                Image img = new Image("resources/images/Spellmonger_Eagle.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Wolf) {
                Image img = new Image("resources/images/Spellmonger_Wolf.png");
                rectangle.setFill(new ImagePattern(img));
            } // fox a ajouter, quand le modèle ajoutera la classe!

            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
        }
    }

    // la main courante des joueurs
    private void hands(Player current,Player oppenent, Pane discard,ScrollPane hand){
        HBox content = new HBox();
        hand.setContent(content);
        content.setSpacing(20);
        content.setPadding(new Insets(10, 10, 10, 10));
        int index = 0;  // pour obtenir l'index quand il va choisir la carte a joué ( utilisé dans le hand pas la)
        for (Card c : current.getHand()) {
            Rectangle rectangle = new Rectangle(100, 120);
            if (c instanceof Bear) {
                Image img = new Image("resources/images/Spellmonger_Bear.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Eagle) {
                Image img = new Image("resources/images/Spellmonger_Eagle.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Wolf) {
                Image img = new Image("resources/images/Spellmonger_Wolf.png");
                rectangle.setFill(new ImagePattern(img));
            } // fox a ajouter, quand le modèle ajoutera la classe!
            else if (c instanceof Ritual) {
                rectangle.setFill(Color.BLACK);
            }
            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
            // obtenir l'index du rectangle qu'il choisit
            int index1 = index;
            rectangle.setOnMouseClicked(t -> {
                    attack(index1, current, oppenent, discard);
            });
            index++;
        }
    }
}
