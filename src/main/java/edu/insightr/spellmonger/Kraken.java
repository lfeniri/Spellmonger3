package edu.insightr.spellmonger;


import java.util.ArrayList;
import java.util.List;

public class Kraken extends Creature{

    public Kraken(){
        effect = 4;
        lifePoints = effect;
        energyCost = 4;
        capacity="Catch";
    }

    @Override
    public String toString() {
        return "Kraken : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Kraken";
    }

}
