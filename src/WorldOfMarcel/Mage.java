package WorldOfMarcel;

import java.util.ArrayList;
import java.util.Random;

public class Mage extends Character{
    public Mage(String name, int currentExp, int currentLvl) {
        super(name, currentExp, currentLvl);
        storage = new Inventory(20);
        charisma = 10 +  currentLvl* 5 / 2;
        dexterity = 10 + currentLvl * 5 / 8;
        strength = 10 + currentLvl * 5 / 8;
        this.ice = true;
    }

    public void receiveDamage(int damage){

        Random rand = new Random();
        int chance = rand.nextInt(5);
        if (dexterity > 25 && strength > 20){
            if(chance > 2){
                currentHP = currentHP - damage / 2;
                if(currentHP < 0){
                    currentHP = 0;
                }
                return;
            }
        }
        currentHP = currentHP - damage;
        if(currentHP < 0){
            currentHP = 0;
        }
    }

    public int getDamage(){

        int damage = charisma * 2 / 3;
        Random rand = new Random();
        int chance = rand.nextInt(5);
        if (charisma > 27){
            if (chance > 2){
                return damage * 2;
            }
        }
        return damage;
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
