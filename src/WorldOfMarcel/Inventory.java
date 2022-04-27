package WorldOfMarcel;

import java.util.*;

public class Inventory {
    int maxWeigth, currentWeigth, coins;
    ArrayList<Potion> potions;

    public Inventory(int maxWeigth){

        this.maxWeigth = maxWeigth;
        coins = 0;
        currentWeigth = 0;
        potions = new ArrayList<Potion>(maxWeigth);
    }

    public void addPotion(Potion potion){
        if(currentWeigth > maxWeigth - potion.getPotionWeigth() || coins < potion.getPotionPrice()){
            return;
        }
        potions.add(potion);
        currentWeigth = currentWeigth + potion.getPotionWeigth();
        coins = coins - potion.getPotionPrice();
    }
    public void removePotion(Potion potion){
        if(potions.contains(potion)){
            potions.remove(potion);
            currentWeigth = currentWeigth - potion.getPotionWeigth();
        }
    }
    public int remainingWeigth(){
        return maxWeigth - currentWeigth;
    }
}
