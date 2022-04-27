package WorldOfMarcel;

import java.util.*;

public abstract class Entity implements Element {

    ArrayList<Spell> Spells;
    int currentHP;
    int maxHP = 100;
    int currentMP;
    int maxMP = 500;
    boolean fire, ice, earth;

    public void RegenHP(int value){

        currentHP = currentHP + value;
        if (currentHP > maxHP){
            currentHP = maxHP;
        }
    }

    public void RegenMP(int value){

        currentMP = currentMP + value;
        if (currentMP > maxMP){
            currentMP = maxMP;
        }
    }

    public void useSpell(Spell ability, Entity entity){

        if (ability.manaCost < currentMP){
            currentMP = currentMP - ability.manaCost;
            entity.accept(ability);
            Spells.remove(Spells.indexOf(ability));
        }
    }

}
