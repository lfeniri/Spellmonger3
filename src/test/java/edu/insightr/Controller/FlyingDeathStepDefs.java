package edu.insightr.Controller;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.spellmonger.*;
import org.junit.Assert;

public class FlyingDeathStepDefs {

    private Player current;
    private Player opponent;

    @Given("^two players have no cards$")
    public void twoPlayersHaveNoCards() {
        current = new Player("Current");
        opponent = new Player("Opponent");
        current.getPlayerCreature().clear();
        opponent.getPlayerCreature().clear();
    }

    @When("^both of then have an eagle now$")
    public void anEagleIsThrownFaceOfAnotherOne() throws Throwable {
        current.getPlayerCreature().add(new Eagle());
        opponent.getPlayerCreature().add(new Eagle());
    }

    @When("^current attack opponent$")
    public void currentAttackOpponent() throws Throwable {
        current.attackCreatures(opponent);
    }

    @Then("^both of them are dead$")
    public void bothOfThemAreDead() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 0);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 0);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
    }

    @When("^first one have an eagle,second have a dragon$")
    public void firstOneHaveAnEagleSecondHaveADragon() throws Throwable {
        current.getPlayerCreature().add(new Eagle());
        opponent.getPlayerCreature().add(new Dragoon());
    }

    @Then("^nothing happens$")
    public void nothingHappens() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 1);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 1);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
    }

    @When("^first one have an dragon,second have a eagle$")
    public void firstOneHaveAnDragonSecondHaveAEagle() throws Throwable {
        current.getPlayerCreature().add(new Dragoon());
        opponent.getPlayerCreature().add(new Eagle());
    }

    @Then("^Eagle died, thus lifePoints didn't changed$")
    public void eagleDiedThusLifePointsDidnTChanged() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 1);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 0);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
    }

    @When("^first one have an eagle,second have a bear$")
    public void firstOneHaveAnEagleSecondHaveABear() throws Throwable {
        current.getPlayerCreature().add(new Eagle());
        opponent.getPlayerCreature().add(new Bear());
    }

    @Then("^Attack opponents lifepoints with (\\d+), thus creatures dont change$")
    public void attackLifepointsThusCreaturesDontChange(int cost) throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 1);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 1);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20 - cost);
    }

    @When("^first one have an eagle,second have a fox$")
    public void firstOneHaveAnEagleSecondHaveAFox() throws Throwable {
        current.getPlayerCreature().add(new Eagle());
        opponent.getPlayerCreature().add(new Fox());
    }

    @When("^first one have an dragon,second have a bear$")
    public void firstOneHaveAnDragonSecondHaveABear() throws Throwable {
        current.getPlayerCreature().add(new Dragoon());
        opponent.getPlayerCreature().add(new Bear());
    }

    @When("^first one have an eagle,second have a craken$")
    public void firstOneHaveAnEagleSecondHaveACraken() throws Throwable {
        current.getPlayerCreature().add(new Eagle());
        opponent.getPlayerCreature().add(new Kraken());
    }

    @Then("^Opponent Eagle died, thus lifePoints didn't changed$")
    public void opponentEagleDiedThusLifePointsDidnTChanged() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 0);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 1);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
    }

    @Then("^Both died, thus lifePoints didn't changed$")
    public void bothDiedThusLifePointsDidnTChanged() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 0);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 0);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
    }

    @When("^first one have an dragon,second have a craken$")
    public void firstOneHaveAnDragonSecondHaveACraken() throws Throwable {
        current.getPlayerCreature().add(new Dragoon());
        opponent.getPlayerCreature().add(new Kraken());
    }

    @When("^first one have an eagle,second have a snake$")
    public void firstOneHaveAnEagleSecondHaveASnake() throws Throwable {
        current.getPlayerCreature().add(new Eagle());
        opponent.getPlayerCreature().add(new Snake());
    }

    @When("^first one have an dragon,second have a snake$")
    public void firstOneHaveAnDragonSecondHaveASnake() throws Throwable {
        current.getPlayerCreature().add(new Dragoon());
        opponent.getPlayerCreature().add(new Snake());
    }

    @When("^first one have an eagle and dragon, second have a dragon$")
    public void firstOneHaveAnEagleAndDragonSecondHaveADragon() throws Throwable {
        current.getPlayerCreature().add(new Eagle());
        current.getPlayerCreature().add(new Dragoon());
        opponent.getPlayerCreature().add(new Dragoon());
        current.sortCreatures();
        opponent.sortCreatures();
    }

    @Then("^Dragon attacks the other dragon,and eagle attack the oppenent and cause a damage$")
    public void dragonAttacksTheOtherDragonAndEagleAttackTheOppenentAndCauseADamage() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 1);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 0);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 19);
    }

    @When("^first one have an eagle and dragon,second have an eagle$")
    public void firstOneHaveAnEagleAndDragonSecondHaveAnEagle() throws Throwable {
        current.getPlayerCreature().add(new Eagle());
        current.getPlayerCreature().add(new Dragoon());
        opponent.getPlayerCreature().add(new Eagle());
        current.sortCreatures();
        opponent.sortCreatures();
    }

    @Then("^Eagle attacks the eagle, and the dragon attack the person$")
    public void eagleAttacksTheEagleAndTheDragonAttackThePerson() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 2);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 0);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 19);
    }

    @When("^both have an eagle and dragon$")
    public void bothHaveAnEagleAndDragon() throws Throwable {
        current.getPlayerCreature().add(new Eagle());
        current.getPlayerCreature().add(new Dragoon());
        opponent.getPlayerCreature().add(new Eagle());
        opponent.getPlayerCreature().add(new Dragoon());
        current.sortCreatures();
        opponent.sortCreatures();
    }

    @Then("^all creatures died, no player is damaged$")
    public void allCreaturesDiedNoPlayerIsDamaged() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 0);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 0);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
    }

    @When("^first one have an eagle and dragon,second have two eagle$")
    public void firstOneHaveAnEagleAndDragonSecondHaveTwoEagle() throws Throwable {
        current.getPlayerCreature().add(new Eagle());
        current.getPlayerCreature().add(new Dragoon());
        opponent.getPlayerCreature().add(new Eagle());
        opponent.getPlayerCreature().add(new Eagle());
        current.sortCreatures();
        opponent.sortCreatures();
    }


    @Then("^dragon attacks eagle and beat him,for the second battle, all eagle died, all player arent damaged$")
    public void dragonAttacksEagleAndBeatHimForTheSecondBattleAllEagleDiedAllPlayerArentDamaged() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 1);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 0);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
    }

    @When("^first one have an eagle and a dragon, the second have two dragons$")
    public void firstOneHaveAnEagleAndTwoDragonsTheSecondHaveTwoDragons() throws Throwable {
        current.getPlayerCreature().add(new Eagle());
        current.getPlayerCreature().add(new Dragoon());
        opponent.getPlayerCreature().add(new Dragoon());
        opponent.getPlayerCreature().add(new Dragoon());
        current.sortCreatures();
        opponent.sortCreatures();
    }

    @Then("^the eagle dont attack,the second attacks the dragon and both died, all players arent damaged$")
    public void theEagleDontAttackTheSecondAttacksTheDragonAndBothDiedAllPlayersArentDamaged() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 1);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 1);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
        Assert.assertEquals(current.getPlayerCreature().get(0) instanceof Eagle, true);
        Assert.assertEquals(opponent.getPlayerCreature().get(0) instanceof Dragoon, true);
    }

    @When("^the first one have a rat, the second have a snake$")
    public void theFirstOneHaveARatTheSecondHaveASnake() throws Throwable {
        current.getPlayerCreature().add(new Rat());
        opponent.getPlayerCreature().add(new Snake());
    }

    @When("^first one have a snake,second have a bear$")
    public void firstOneHaveASnakeSecondHaveABear() throws Throwable {
        current.getPlayerCreature().add(new Snake());
        opponent.getPlayerCreature().add(new Bear());
    }

    @When("^first one have a snake,second have a fox$")
    public void firstOneHaveASnakeSecondHaveAFox() throws Throwable {
        current.getPlayerCreature().add(new Snake());
        opponent.getPlayerCreature().add(new Fox());
    }

    @When("^first one have a snake,second have a craken$")
    public void firstOneHaveASnakeSecondHaveACraken() throws Throwable {
        current.getPlayerCreature().add(new Snake());
        opponent.getPlayerCreature().add(new Kraken());
    }

    @When("^first one have a snake,second have an eagle$")
    public void firstOneHaveASnakeSecondHaveAnEagle() throws Throwable {
        current.getPlayerCreature().add(new Snake());
        opponent.getPlayerCreature().add(new Eagle());
    }

    @When("^first one have a snake,second have a dragon$")
    public void firstOneHaveASnakeSecondHaveADragon() throws Throwable {
        current.getPlayerCreature().add(new Snake());
        opponent.getPlayerCreature().add(new Dragoon());
    }

    @When("^the first one have a Wolf, the second have a Lizard$")
    public void theFirstOneHaveAWolfTheSecondHaveALizard() throws Throwable {
        current.getPlayerCreature().add(new Wolf());
        opponent.getPlayerCreature().add(new Lizard());
    }

    @Then("^Both  died, thus life points didnt changed$")
    public void bothDiedThusLifePointsDidntChanged() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 0);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 0);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
    }

    @When("^the first one have a Lizard, the second have a Wolf$")
    public void theFirstOneHaveALizardTheSecondHaveAWolf() throws Throwable {
        current.getPlayerCreature().add(new Lizard());
        opponent.getPlayerCreature().add(new Wolf());
    }

    @Then("^The wolf died and the lizard is still alive, thus life points didnt changed$")
    public void theWolfDiedAndTheLizardIsStillAliveThusLifePointsDidntChanged() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 1);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 0);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
        Assert.assertEquals(current.getPlayerCreature().get(0) instanceof Lizard, true);
    }

    @When("^first have a fox, second have a bear$")
    public void firstHaveAFoxSecondHaveABear() throws Throwable {
        current.getPlayerCreature().add(new Fox());
        opponent.getPlayerCreature().add(new Bear());
    }

    @Then("^nothings happens$")
    public void nothingsHappens() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 1);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 1);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
        Assert.assertEquals(current.getPlayerCreature().get(0) instanceof Fox, true);
        Assert.assertEquals(opponent.getPlayerCreature().get(0) instanceof Bear, true);
    }

    @When("^the first one have a bear, the second have a lizard$")
    public void theFirstOneHaveABearTheSecondHaveALizard() throws Throwable {
        current.getPlayerCreature().add(new Bear());
        opponent.getPlayerCreature().add(new Lizard());
    }

    @Then("^the bear kills the lizard,thus life points didnt changed$")
    public void theBearKillsTheLizardThusLifePointsDidntChanged() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 1);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 0);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
        Assert.assertEquals(current.getPlayerCreature().get(0) instanceof Bear, true);
    }

    @When("^the first one have a fox, the second have a bear$")
    public void theFirstOneHaveAFoxTheSecondHaveABear() throws Throwable {
        current.getPlayerCreature().add(new Fox());
        opponent.getPlayerCreature().add(new Bear());
    }

    @When("^first one have an bear,second have a kraken$")
    public void firstOneHaveAnBearSecondHaveAKraken() throws Throwable {
        current.getPlayerCreature().add(new Bear());
        opponent.getPlayerCreature().add(new Kraken());
    }

    @When("^first one have a bear,second have an eagle$")
    public void firstOneHaveABearSecondHaveAnEagle() throws Throwable {
        current.getPlayerCreature().add(new Bear());
        opponent.getPlayerCreature().add(new Eagle());
    }

    @When("^first one have a fox,second have an eagle$")
    public void firstOneHaveAFoxSecondHaveAnEagle() throws Throwable {
        current.getPlayerCreature().add(new Fox());
        opponent.getPlayerCreature().add(new Eagle());
    }

    @When("^first one have a fox,second have an eagle and a bear$")
    public void firstOneHaveAFoxSecondHaveAnEagleAndABear() throws Throwable {
        current.getPlayerCreature().add(new Fox());
        opponent.getPlayerCreature().add(new Eagle());
        opponent.getPlayerCreature().add(new Bear());
    }

    @Then("^Attack opponents lifepoints with (\\d+),thus creatures dont change$")
    public void attackLifepointsTThusCreaturesDontChange(int cost) throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 1);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 2);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20 - cost);
    }

    @When("^first one have a Bear,second have an eagle and a Eagle$")
    public void firstOneHaveABearSecondHaveAnEagleAndAEagle() throws Throwable {
        current.getPlayerCreature().add(new Bear());
        opponent.getPlayerCreature().add(new Eagle());
    }

    @When("^the first one have a bear, the second have a rat$")
    public void theFirstOneHaveABearTheSecondHaveARat() throws Throwable {
        current.getPlayerCreature().add(new Bear());
        opponent.getPlayerCreature().add(new Rat());
    }

    @Then("^both of them are dead,thus life points didnt changed$")
    public void bothOfThemAreDeadThusLifePointsDidntChanged() throws Throwable {
        Assert.assertEquals(current.getPlayerCreature().size(), 0);
        Assert.assertEquals(opponent.getPlayerCreature().size(), 0);
        Assert.assertEquals(current.getLifePoint(), 20);
        Assert.assertEquals(opponent.getLifePoint(), 20);
    }

    @When("^the first one have a fox, the second have a rat$")
    public void theFirstOneHaveAFoxTheSecondHaveARat() throws Throwable {
        current.getPlayerCreature().add(new Fox());
        opponent.getPlayerCreature().add(new Rat());
    }
}

