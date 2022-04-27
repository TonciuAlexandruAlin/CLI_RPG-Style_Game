package WorldOfMarcel;

import java.util.ArrayList;
import java.util.Random;

public class Character extends Entity{

    String name;
    int ox,oy;
    Inventory storage;
    int currentExp;
    int currentLvl;
    int strength;
    int dexterity;
    int charisma;

    public Character(String name, int currentExp, int currentLvl){
        this.name = name;
        this.currentExp = currentExp;
        this.currentLvl = currentLvl;
        currentHP = maxHP;
        currentMP = maxMP;
        strength = 15;
        dexterity = 15;
        charisma = 15;
        ox = 0;
        oy = 0;
        Spells = new ArrayList<Spell>();
        Random rand = new Random();
        int spellsNumber = rand.nextInt(2,5);
        for(int i = 0; i < spellsNumber; i++){
            int spellType = rand.nextInt(6);
            if(spellType < 2){
                Spells.add(new Ice());
            }
            else if (spellType < 5 && spellType > 2){
                Spells.add(new Earth());
            }
            else if(spellType < 6){
                Spells.add(new Fire());
            }
        }
    }

    public void buyPotion(Potion potion){
        storage.addPotion(potion);
    }

    public void usePotion(Potion potion){
        if(storage.potions.contains(potion)){
            if(potion.getClass().getSimpleName().compareTo("HP_Potion") == 0){
                potion.useHP_Potion(this);
            }
            else{
                potion.useMP_Potion(this);
            }
            storage.removePotion(potion);
        }
    }

    public void receiveDamage(int value){
        if(this.getClass().getSimpleName().compareTo("Warrior") == 0){
            ((Warrior)this).receiveDamage(value);
        }
        if(this.getClass().getSimpleName().compareTo("Rogue") == 0){
            ((Rogue)this).receiveDamage(value);
        }
        if(this.getClass().getSimpleName().compareTo("Mage") == 0){
            ((Mage)this).receiveDamage(value);
        }
    }
    public int getDamage(){
        if(this.getClass().getSimpleName().compareTo("Warrior") == 0){
            return ((Warrior)this).getDamage();
        }
        else if(this.getClass().getSimpleName().compareTo("Rogue") == 0){
            return ((Rogue)this).getDamage();
        }
        else {
            return ((Mage)this).getDamage();
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
