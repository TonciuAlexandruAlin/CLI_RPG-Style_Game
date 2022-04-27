package WorldOfMarcel;

public class CharacterFactory {

    public Character Factory(String type, String name, int currentExp, int currentLvl) throws InvalidCommandException {
        if(type.compareTo("Warrior") == 0){
            return new Warrior(name, currentExp, currentLvl);
        }
        if(type.compareTo("Rogue") == 0){
            return new Rogue(name, currentExp, currentLvl);
        }
        if(type.compareTo("Mage") == 0){
            return new Mage(name, currentExp, currentLvl);
        }
        throw new InvalidCommandException("Nu exista acest caracter");
    }

}
