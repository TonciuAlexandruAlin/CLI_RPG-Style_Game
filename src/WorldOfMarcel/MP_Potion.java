package WorldOfMarcel;

public class MP_Potion implements Potion{

    private int price = 5;
    private int weigth = 1;
    private int value = 250;

    @Override
    public void useHP_Potion(Character character) {
        return;
    }

    @Override
    public void useMP_Potion(Character character) {
        character.RegenMP(value);
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
