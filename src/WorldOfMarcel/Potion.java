package WorldOfMarcel;

public interface Potion {

    public void useHP_Potion(Character character);
    public void useMP_Potion(Character character);
    public int getPotionPrice();
    public int getPotionWeigth();
    public int getPotionRegenValue();
}
