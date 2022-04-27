package WorldOfMarcel;

public class Ice extends Spell{

    public Ice(){
        SetDamage();
        SetManaCost();
    }

    public void visit(Entity entity){
        if(entity.ice == true){
            if(entity.getClass().getSimpleName().compareTo("Warrior") == 0){
                ((Warrior)entity).receiveDamage(0);
                System.out.println("Zeul dragon te-a binecuvantat cu imunitate.");
            }
            else if(entity.getClass().getSimpleName().compareTo("Rogue") == 0){
                ((Rogue)entity).receiveDamage(0);
                System.out.println("Zeul dragon te-a binecuvantat cu imunitate.");
            }
            else if(entity.getClass().getSimpleName().compareTo("Mage") == 0){
                ((Mage)entity).receiveDamage(0);
                System.out.println("Zeul dragon te-a binecuvantat cu imunitate.");
            }
            else if(entity.getClass().getSimpleName().compareTo("Enemy") == 0){
                ((Enemy)entity).receiveDamage(0);
                System.out.println("Inamicul este imun.");
            }
        }
        else{
            if(entity.getClass().getSimpleName().compareTo("Warrior") == 0){
                ((Warrior)entity).receiveDamage(damage);
            }
            else if(entity.getClass().getSimpleName().compareTo("Rogue") == 0){
                ((Rogue)entity).receiveDamage(damage);
            }
            else if(entity.getClass().getSimpleName().compareTo("Mage") == 0){
                ((Mage)entity).receiveDamage(damage);
            }
            else if(entity.getClass().getSimpleName().compareTo("Enemy") == 0){
                ((Enemy)entity).receiveDamage(damage);
            }
        }
    }

    @Override
    void SetDamage() {
        damage = 20;
    }

    @Override
    void SetManaCost() {
        manaCost = 100;
    }
}
