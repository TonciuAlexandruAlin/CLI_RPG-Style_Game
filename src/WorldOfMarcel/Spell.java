package WorldOfMarcel;

public abstract class Spell implements Visitor {

    int damage, manaCost;
    abstract void SetDamage();
    abstract void SetManaCost();
}
