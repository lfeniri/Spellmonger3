package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Abdel on 02/10/2016.
 */
public class Deck {

    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private ArrayList<Card> cardPool= new ArrayList<>();

    public Deck()
    {
    }
    public Deck(String name)
    {
        createCardPool();
        logger.info(name+"'s cardpool created");
    }

    public Deck(ArrayList<Card> other)
    {
        cardPool= new ArrayList<>(other);
    }

    public ArrayList<Card> getCardPool()
    {
        return cardPool;
    }

    public int size()
    {
        return cardPool.size();
    }

    public Card get(int index)
    {
        return cardPool.get(index);
    }



    public void add(Card c)
    {
         cardPool.add(c);
    }

    public Card remove(int index)
    {
        return cardPool.remove(index);
    }

   /* public void createCardPool()
    {
        for (int i = 0; i < 40; i++) {
            Random rand = new Random();
            int choix = rand.nextInt(2);

            if(choix == 0)//creature
            {
                Random rand2 = new Random();
                int type = rand2.nextInt(3);
                if(type==0)//eagle
                {
                    Eagle aigle= new Eagle();
                    this.cardPool.add(aigle);
                }
                if(type==1)//wolf
                {
                    Wolf loup= new Wolf();
                    this.cardPool.add(loup);
                }
                if(type==2)//bear
                {
                    Bear ours= new Bear();
                    this.cardPool.add(ours);
                }

            }
            if(choix==1)//ritual
            {
                Random rand3 = new Random();
                int spell = rand3.nextInt(3);
                if(spell==0)//curse
                {
                    Curse malediction = new Curse();
                    this.cardPool.add(malediction);
                }
                if(spell==1)//blessing
                {
                    Blessing soin = new Blessing();
                    this.cardPool.add(soin);
                }
                if(spell==2)//EnergyDrain
                {
                    EnergyDrain drain = new EnergyDrain();
                    this.cardPool.add(drain);
                }
            }
        }
    }*/

   public void createCardPool() {
       int nbMaxEagle = 10;
       int nbMaxWolf = 10;
       int nbMaxBear = 10;
       int nbMaxCurse = 2;
       int nbMaxShield = 5;
       int nbMaxBlessing = 3;

       while(nbMaxBear>0 && nbMaxWolf>0 && nbMaxEagle>0 && nbMaxBlessing>0 && nbMaxCurse>0 && nbMaxShield>0)
       {
           Random rand = new Random ();
           int nbRand = rand.nextInt (5);

           if(nbRand == 0 && nbMaxEagle >0) {
               Eagle eagle = new Eagle ();
               cardPool.add (eagle);
               nbMaxEagle--;
           }
           else if(nbRand == 1 && nbMaxWolf>0){
               Wolf wolf = new Wolf ();
               cardPool.add (wolf);
               nbMaxWolf--;
           }
           else if(nbRand == 2 && nbMaxBear>0){
               Bear bear = new Bear ();
               cardPool.add (bear);
               nbMaxBear--;
           }
           else if(nbRand == 3 && nbMaxCurse>0){
               Curse curse = new Curse ();
               cardPool.add (curse);
               nbMaxCurse--;
           }
           else if(nbRand == 4 && nbMaxShield>0){
               Shield shield = new Shield ();
               cardPool.add (shield);
               nbMaxShield--;
           }
           else{
               Blessing blessing = new Blessing ();
               cardPool.add(blessing);
               nbMaxBlessing--;
           }
       }


   }

    public void clearCards()
    {
        cardPool.clear();
    }



}
