package edu.insightr.spellmonger;

public abstract class Card  {

    protected int effect;
    protected int energyCost;

    public int getEffect() {
        return effect;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public String getTypeCard(){
        String result;
        if(this instanceof Ritual)
            result = "Ritual";
        else if(this instanceof Enchantment)
            result = "Enchantment";
        else
            result = "Creature";
        return result;
    }

    @Override
    public String toString() {
        return "Effect = " + this.effect;
    }

    public String getName() {
        return "Abstract";
    }

   public boolean canPlayCard(Player current){
       if(this.getEnergyCost()<=current.getEnergyPerTurn()){
           current.setEnergyPerTurn(current.getEnergyPerTurn()-this.getEnergyCost());
           return true;
       }else{
           return false;
       }

    }
}
