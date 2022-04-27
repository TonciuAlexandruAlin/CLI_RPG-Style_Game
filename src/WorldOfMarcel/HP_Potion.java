package WorldOfMarcel;

public class HP_Potion implements Potion{

    private int price = 10;
    private int weigth = 2;
    private int value = 50;

    @Override
    public void useHP_Potion(Character character) {
        character.RegenHP(value);
    }

    @Override
    public void useMP_Potion(Character character) {
        return;
    }

    @Override
    public int getPotionPrice() {
        return price;
    }

    @Override
    public int getPotionWeigth() {
        return weigth;
    }

    @Override
    public int getPotionRegenValue() {
        return value;
    }
}
