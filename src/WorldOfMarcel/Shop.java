package WorldOfMarcel;

import java.awt.*;
import java.util.*;

public class Shop implements CellElement{

    @Override
    public char toChar() {
        return 'S';
    }

    ArrayList<Potion> potions;
    Random rand = new Random();

    public Shop(){

        int numberOfPotions = rand.nextInt(2,5);
        potions = new ArrayList<Potion>(numberOfPotions);
        potions.add(new HP_Potion());
        potions.add(new MP_Potion());
        for(int i = 2; i < numberOfPotions; i++){

            int potionType = rand.nextInt(2);
            if (potionType == 0){
                potions.add(new HP_Potion());
            }
            else{
                potions.add(new MP_Potion());
            }
        }
    }
    public Potion SellPotion(int index){
        if(index > potions.size()){
            index = potions.size() - 1;
        }
        Potion OldPotion = potions.get(index);
        return OldPotion;
    }
}
