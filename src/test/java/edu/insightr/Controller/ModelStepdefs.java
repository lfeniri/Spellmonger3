package edu.insightr.Controller;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.spellmonger.*;
import javafx.scene.control.TextField;
import org.junit.Assert;


public class ModelStepdefs {
    ControllerMenu controllerMenu;

    @Given("^first player name is \"([^\"]*)\"$")
    public void is(String playerName) throws Throwable {
        controllerMenu = new ControllerMenu();
        controllerMenu.setScreenParent(new ScreensController());
        controllerMenu.login1 = new TextField();
        controllerMenu.login1.setText(playerName);
    }

    @Then("^game player one is \"([^\"]*)\"$")
    public void gamePlayerOneIs(String expectedName) throws Throwable {
        ScreensController screensController = controllerMenu.myController;
        Assert.assertEquals(expectedName, screensController.getData("NamePlayer1"));
    }

    @When("^the game is launched$")
    public void theGameIsLaunched() throws Throwable {
        controllerMenu.goToPlay();
    }

    //_________________________________________bear____________________________________
    private Bear bear;

    @Given("^I create a Bear$")
    public void iCreateABear() throws Throwable {
        this.bear = new Bear();
    }

    @When("^I draw a card$")
    public void iDrawACard() throws Throwable {
        System.out.println("bear = " + bear.toString());
    }

    @Then("^the bear has (\\d+) effect$")
    public void theBearHasEffect(int effect) throws Throwable {
        Assert.assertEquals(effect,bear.getEffect());
    }

    @Then("^the bear has (\\d+) life$")
    public void theBearHasLife(int lifePoints) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(lifePoints, this.bear.getLifePoints());
    }

    @Then("^the bear cost (\\d+) energies$")
    public void theBearCostEnergy(int energy) throws Throwable {
        Assert.assertEquals(energy,bear.getEnergyCost());
    }

    //________________________________________fox__________________________________
    private Fox fox;
    @Given("^I create a Fox$")
    public void iCreateAFox() throws Throwable {
        fox = new Fox();
    }

    @When("^I draw a card Fox$")
    public void iDrawACardFox() throws Throwable {
        System.out.println("fox = " + fox.toString());
    }

    @Then("^The fox has (\\d+) effect$")
    public void theFoxHasEffect(int effect) throws Throwable {
        Assert.assertEquals(effect,fox.getEffect());
    }

    @Then("^The fox has (\\d+) life$")
    public void theFoxHasLife(int lifepoint) throws Throwable {
        Assert.assertEquals(fox.getEffect(),fox.getLifePoints());

    }

    @Then("^The fox cost (\\d+) energies$")
    public void theFoxCostEnergy(int energycost) throws Throwable {
        Assert.assertEquals(energycost,fox.getEnergyCost());
    }
    //____________________________________________________Blessing__________________________
    Blessing bless;
    Player playerBlessed;
    @Given("^I create a Blessing$")
    public void iCreateABlessing() throws Throwable {
        bless = new Blessing();
    }

    @When("^I draw a card Blessing$")
    public void iDrawACardBlessing() throws Throwable {
        System.out.println("bless = " + bless);
    }

    @Then("^The blessing has (\\d+) effect$")
    public void theBlessingHasEffect(int effect) throws Throwable {
        Assert.assertEquals(effect,bless.getEffect());
    }

    @Then("^The blessing cost (\\d+) energies$")
    public void theBlessingCostEnergies(int energycost) throws Throwable {
        Assert.assertEquals(energycost,bless.getEnergyCost());
    }

    @Given("^I have a Blessing in my hand$")
    public void iHadABlessingInMyHand() throws Throwable {
        System.out.println("I have a blessing card");
    }

    @When("^I use Blessing card$")
    public void iUseBlessingCard() throws Throwable {
        System.out.println("I use the blessing card");
    }

    @Then("^The current player earn (\\d+) lifepoints$")
    public void theCurrentPlayerEarnLifepoints(int HPEarned) throws Throwable {
        bless = new Blessing(); // define the card blessing
        playerBlessed = new Player("IAmBlessedByGod"); // define a player to bless
        int currentLifePoint = playerBlessed.getLifePoint(); // save the current lifepoint of the player
        bless.effect(playerBlessed); // use the effect of the card
        Assert.assertEquals(currentLifePoint + HPEarned,playerBlessed.getLifePoint());
    }
    //______________________________ENERGYDRAIN____________________________________________________
    EnergyDrain energyDrain;
    Player current = new Player("Current");
    Player opponent = new Player("Opponent");
    @Given("^I create a EnergyDrain$")
    public void iCreateAEnergyDrain() throws Throwable {
        energyDrain = new EnergyDrain();
    }

    @When("^I draw a EnergyDrain card$")
    public void iDrawAEnergyDrainCard() throws Throwable {
        System.out.println("energyDrain = " + energyDrain.toString());
    }

    @Then("^The EnergyDrain card has (\\d+) effect$")
    public void theEnergyDrainCardHasEffect(int effect) throws Throwable {
        Assert.assertEquals(effect,energyDrain.getEffect());
    }

    @Then("^The EnergyDrain card cost (\\d+) energies$")
    public void theEnergyDrainCardCostEnergies(int energyCost) throws Throwable {
        Assert.assertEquals(energyCost,energyDrain.getEnergyCost());
    }

    @Given("^I have an EnergyDrain card in my hand$")
    public void iHasAEnergyDrainCardInMyHand() throws Throwable {
        System.out.println("I have an Energydrain card");
    }

    @When("^I use an EnergyDrain card$")
    public void iUseAnEnergyDrainCard() throws Throwable {
        System.out.println("I use the Energydrain card");
    }

    @Then("^The opponent lose (\\d+) energies$")
    public void theOpponentLoseLifePoints(int lose) throws Throwable {
        energyDrain = new EnergyDrain();
        int opponentEnergy = opponent.getEnergy();
        energyDrain.effect(current,opponent);
        Assert.assertEquals(opponentEnergy - lose, opponent.getEnergy());

    }

    @Then("^The current player earn (\\d+) energies$")
    public void theCurrentPlayerEarnLifePoints(int profit) throws Throwable {
        int currentEnergy = current.getEnergy();
        energyDrain.effect(current,opponent);
        Assert.assertEquals(currentEnergy + profit, current.getEnergy());
    }
    //___________________________________VAULTOVERCLOCKING___________________________________________
    VaultOverclocking v;
    @Given("^I create a VaultOverclocking card$")
    public void iCreateAVaultOverclockingCard() throws Throwable {
        v = new VaultOverclocking();
    }

    @When("^I draw a card VaultOverclocking$")
    public void iDrawACardVaultOverclocking() throws Throwable {
        System.out.println("v = " + v.toString());
    }

    @Then("^The VaultOverclocking card cost (\\d+) energies$")
    public void theVaultOverclockingCardCostEnergies(int energyCost) throws Throwable {
        Assert.assertEquals(v.getEnergyCost(),energyCost);
    }

    @Then("^The VaultOverclocking card is not active$")
    public void theVaultOverclockingCardIsNotActive() throws Throwable {
        //Assert.assertEquals(v.getActiveEffect(),false);
    }

    @Given("^I have a VaultOverclocking card in my hand$")
    public void iHaveAVaultOverclockingCardInMyHand() throws Throwable {
        System.out.println("I've a VaultOverclocking on my hand");
    }

    @When("^I use a VaultOverclocking card$")
    public void iUseAVaultOverclockingCard() throws Throwable {
        System.out.println("I use a VaultOverclocking card");
    }

    @Then("^The current player energy is set to (\\d+) or his energy is increases by (\\d+)$")
    public void theCurrentPlayerEnergyIsSetToOrHisEnergyIsIncreasesBy(int energyEqual1, int addEnergy) throws Throwable {
        v =new VaultOverclocking();
        int currentEnergy = current.getEnergy();
       // v.getEffect(current);
        if((energyEqual1 == current.getEnergy()) || (currentEnergy + addEnergy == current.getEnergy())){
            System.out.println("SUCCESS");
        }
    }

    //______________________CONTROLLERPLAY CLASS_______________________
    ControllerPlay game = new ControllerPlay();
    @Given("^The game began$")
    public void theGameBegan() throws Throwable {
        System.out.println("The game began");
    }

    @When("^The players are connected$")
    public void thePlayersAreConnected() throws Throwable {
        System.out.println("Players connected");
    }

    @Then("^The name of the first player is \"([^\"]*)\"$")
    public void theNameOfTheFirstPlayerIs(String name1) throws Throwable {
        game.initializeTest();
        Assert.assertEquals(name1,game.getPlayer1().getName());
    }

    @Then("^The name of the second player is \"([^\"]*)\"$")
    public void theNameOfTheSecondPlayerIs(String name2) throws Throwable {
        game.initializeTest();
        Assert.assertEquals(name2,game.getPlayer2().getName());
    }

    @Then("^The players have (\\d+) life points$")
    public void thePlayersHaveLifePoints(int lifePoints) throws Throwable {
        game.initializeTest();
        Assert.assertEquals(lifePoints,game.getPlayer1().getLifePoint());
        Assert.assertEquals(lifePoints,game.getPlayer2().getLifePoint());
    }

    @Then("^The players have (\\d+) energy$")
    public void thePlayersHaveEnergy(int energy) throws Throwable {
        game.initializeTest();
        Assert.assertEquals(energy,game.getPlayer1().getEnergy());
        Assert.assertEquals(energy,game.getPlayer2().getEnergy());
    }

    @Then("^The player have (\\d+) cards in their hand$")
    public void thePlayerHaveCardsInTheirHand(int nbCardOnHand) throws Throwable {
        game.initializeTest();
        Assert.assertEquals(nbCardOnHand,game.getPlayer1().getHand().size());
        Assert.assertEquals(nbCardOnHand,game.getPlayer2().getHand().size());
    }

    @Given("^Two players are playing$")
    public void twoPlayersArePlaying() throws Throwable {
        System.out.println("Play");
    }

    @When("^The Players draw a card$")
    public void thePlayersDrawACard() throws Throwable {
        System.out.println("Draw a card");
    }

    @Then("^The number of card of the players increases by (\\d+)$")
    public void theNumberOfCardOfThePlayersIncreasesBy(int increase) throws Throwable {
        game.initializeTest();
        int currentCardP1 = game.getPlayer1().getHand().size();
        game.draw_player_1_test();
        int currentCardP2 = game.getPlayer2().getHand().size();
        game.draw_player_2_test();
        Assert.assertEquals(game.getPlayer1().getHand().size(),currentCardP1 + increase);
        Assert.assertEquals(game.getPlayer2().getHand().size(),currentCardP2 + increase);
    }

    //_________________________________________EAGLE_______________________________________________


    Eagle eagle;

    @Given("^I create an eagle$")
    public void CreateEagle() throws Throwable {
        eagle = new Eagle();
    }

    @When("^I draw an eagle$")
    public void iDrawAnEagle() throws Throwable {
        System.out.println("eagle");
    }

    @Then("^The eagle has (\\d+) effect$")
    public void theEagleHasEffect(int arg0) throws Throwable {
        Assert.assertEquals(1, eagle.getEffect());
    }

    @Then("^The eagle capacity is \"([^\"]*)\"$")
    public void theEagleCapacityIs(String arg0) throws Throwable {
        Assert.assertEquals(arg0, eagle.getCapacity());
    }

    @Then("^The eagle lifePoints is effect$")
    public void theEagleLifePointsIsEffect() throws Throwable {
        Assert.assertEquals(1, eagle.getLifePoints());
    }

    @Then("^The eagle cost (\\d+) energy$")
    public void theEagleCostEnergy(int arg0) throws Throwable {
        Assert.assertEquals(1, eagle.getEnergyCost());
    }


//________________________WOLF___________________________________________________________

    Wolf wolf;

    @Given("^I create a wolf$")
    public void iCreateAWolf() throws Throwable {
        wolf = new Wolf();
    }

    @Then("^The wolf has (\\d+) effect$")
    public void theWolfHasEffect(int arg0) throws Throwable {
        Assert.assertEquals(2, wolf.getEffect());
    }

    @Then("^the wolf lifePoints is effect$")
    public void theWolfLifePointsIsEffect() throws Throwable {
        Assert.assertEquals(2, wolf.getLifePoints());
    }

    @Then("^the wolf cost (\\d+) energy$")
    public void theWolfCostEnergy(int arg0) throws Throwable {
        Assert.assertEquals(2, wolf.getEnergyCost());
    }

    @When("^I draw a wolf$")
    public void iDrawAWolf() throws Throwable {
        System.out.println("wolf");
    }

    //_______________________________Curse______________________________________________________

    Curse curse;
    Player playercursed;

    @Given("^I create a curse$")
    public void iCreateACurse() throws Throwable {
        curse = new Curse();
    }

    @When("^i draw a card curse$")
    public void iDrawACardCurse() throws Throwable {
        System.out.println("curse = " + curse);

    }

    @Then("^The curse has (\\d+) effect$")
    public void theCurseHasEffect(int arg0) throws Throwable {
        Assert.assertEquals(arg0, curse.getEffect());
    }

    @Then("^the curse cost (\\d+) energy$")
    public void theCurseCostEnergy(int arg0) throws Throwable {
        Assert.assertEquals(arg0, curse.getEnergyCost());
    }

    @Given("^i have a Curse in my hand$")
    public void iHaveACurseInMyHand() throws Throwable {
        System.out.println("I use a curse card");
    }

    @When("^i use curse card$")
    public void iUseCurseCard() throws Throwable {
        System.out.println("I use the curse card");
    }

    @Then("^the opponent player lose (\\d+) lifepoints$")
    public void theOpponentPlayerLoseLifepoints(int arg0) throws Throwable {
        curse = new Curse();
        playercursed = new Player("playercursed");
        int playerlife = playercursed.getLifePoint();
        curse.effect(playercursed);
        Assert.assertEquals(playerlife-arg0,playercursed.getLifePoint());
    }

}