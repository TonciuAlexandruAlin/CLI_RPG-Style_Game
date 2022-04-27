package WorldOfMarcel;

import java.util.ArrayList;
import java.util.Random;

public class Rogue extends Character{
    public Rogue(String name, int currentExp, int currentLvl) {
        super(name, currentExp, currentLvl);
        storage = new Inventory(15);
        dexterity = 10 +  currentLvl * 5 / 2;
        strength = 10 + currentLvl * 5 / 8;
        charisma = 10 + currentLvl * 5 / 8;
        this.earth = true;
    }

    public void receiveDamage(int damage){

        Random rand = new Random();
        int chance = rand.nextInt(5);
        if (strength > 25 && charisma > 20){
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

        int damage = dexterity * 2 / 3;
        Random rand = new Random();
        int chance = rand.nextInt(5);
        if (strength > 27){
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
