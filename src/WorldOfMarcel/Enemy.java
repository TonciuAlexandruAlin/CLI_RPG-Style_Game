package WorldOfMarcel;

import java.util.*;

public class Enemy extends Entity implements CellElement{

    @Override
    public char toChar() {
        return 'E';
    }

    Random rand = new Random();
    public Enemy(){

        currentHP = rand.nextInt(80,110);
        currentMP = rand.nextInt(300,500);
        int SpellNumber = rand.nextInt(2,5);
        int fire_protection = rand.nextInt(10);
        int ice_protection = rand.nextInt(10);
        int earth_protection = rand.nextInt(10);
        if(fire_protection < 5){
            fire = true;
        }
        else{
            fire = false;
        }
        if(ice_protection < 5){
            ice = true;
        }
        else{
            ice = false;
        }
        if(earth_protection < 5){
            earth = true;
        }
        else{
            earth = false;
        }
        Spells = new ArrayList<Spell>(SpellNumber);
        for(int i = 0; i < SpellNumber; i++){
            int SpellType = rand.nextInt(6);
            if (SpellType == 0 || SpellType == 1){
                Spells.add(new Fire());
            }
            if (SpellType == 2 || SpellType == 3){
                Spells.add(new Ice());
            }
            if (SpellType == 4 || SpellType == 5){
                Spells.add(new Earth());
            }
        }
    }

    public void receiveDamage(int damage){
        int Eschiva = rand.nextInt(2);
        if(Eschiva == 0){
            currentHP = currentHP - damage;
            if(currentHP < 0){
                currentHP = 0;
            }
        }
        else{
            System.out.println("Inamicul s-a ferit de acest atac.");
        }
    }

    public int getDamage(){
        int chance = rand.nextInt(100);
        int damage = 7;
        if (chance < 50){
            return damage * 2;
        }
        return damage;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
