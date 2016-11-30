package edu.insightr.spellmonger;

import java.util.Objects;

abstract class Creature extends Card implements Comparable<Creature> {
    String capacity;
    int lifePoints;

    public Creature() {
        capacity = "";
        lifePoints = 0;
        energyCost = 0;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public String getCapacity() {
        return capacity;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    void attackCreature(Player currentPlayer, Player opponent) {
        Creature defCreature = null;

        if (Objects.equals(this.getCapacity(), "Flying")) {
            for (int i = 0; i < opponent.getPlayerCreature().size(); i++) {
                if (Objects.equals(opponent.getPlayerCreature().get(i).getCapacity(), "Flying") || Objects.equals(opponent.getPlayerCreature().get(i).getCapacity(), "Catch")) {
                    defCreature = opponent.getPlayerCreature().get(i);
                    break;
                }
            }

            if (defCreature != null) {
                if (this.getEffect() > defCreature.getEffect()) {
                    CreatureDead(opponent, defCreature); // coooool
                } else if (this.getEffect() < defCreature.getEffect()) {
                    CreatureDead(currentPlayer, this);
                } else {
                    CreatureDead(opponent, defCreature);
                    CreatureDead(currentPlayer, this);
                }
            } else {
                this.attackPlayer(opponent); // cool
            }

        } else if (Objects.equals(this.getCapacity(), "Deathtouch")) {

            if (opponent.getPlayerCreature().size() != 0) {
                defCreature = opponent.getPlayerCreature().get(0);
                CreatureDead(opponent, defCreature);
                CreatureDead(currentPlayer, this); // coooolll
            } else {
                this.attackPlayer(opponent);
            }
        } else {

            if (opponent.getPlayerCreature().size() != 0) {
                boolean test=false;
                for (int i = 0; i < opponent.getPlayerCreature().size(); i++) {
                    if (opponent.getPlayerCreature().get(i).getEffect() <= this.getEffect()) {
                        if( !Objects.equals(opponent.getPlayerCreature().get(i).getCapacity(), "Flying")) {
                            defCreature = opponent.getPlayerCreature().get(i);
                            test=false;
                        }
                        else{
                            test=true;
                        }
                    }
                }
                if (defCreature != null) {
                    if (Objects.equals(defCreature.getCapacity(), "Deathtouch")) {
                        System.out.println("on est ici");
                        CreatureDead(currentPlayer,this);
                        CreatureDead(opponent,defCreature);
                    }
                    else {
                        if (this.getEffect() > defCreature.getEffect()) {
                            CreatureDead(opponent, defCreature);
                        } else if (this.getEffect() < defCreature.getEffect()) {
                            CreatureDead(currentPlayer, this);
                        } else {
                            CreatureDead(opponent, defCreature);
                            CreatureDead(currentPlayer, this);
                        }
                    }
                }
                else if(test)
                {
                    this.attackPlayer(opponent);
                }
            } else {
                this.attackPlayer(opponent);
            }
        }
    }

    private static void CreatureDead(Player player, Creature card) {
        player.getDiscards().add(card);
        player.getPlayerCreature().remove(card);
        player.getPlayerCreatureDead().add(card);
    }

    private void attackPlayer(Player opponent) {
        int damage = this.getEffect();
        opponent.setLifePoint(opponent.getLifePoint() - damage);
    }

    @Override
    public int compareTo(Creature other) {
        Integer obj1 = this.effect;
        Integer obj2 = other.effect;
        return obj2.compareTo(obj1); // pour que ce soit décroissant
    }// compareTo pour ordonner les creatures, et créér le systeme d'attaque entre creatures
}