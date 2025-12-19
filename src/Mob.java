import java.util.List;

public class Mob {

    private String name;
    private int attackPower;
    private Health health;
    private boolean isStunned = false;
    private boolean isAggro;
    private List<Mob> mobsInWorld;
    private boolean onFire;


    public Mob (String name, Health health, int attackPower, boolean isAggro, boolean onFire ){

        this.name = name;
        this.attackPower = attackPower;
        this.health = health;
        this.isAggro = isAggro;
        this.onFire = onFire;

    }

    public void attack(Player player){

        if (isStunned()) {
            System.out.println(name + " is stunned and skips its turn!");
            setStunned(false);
            // Reset stun after skipping one turn

        }
        else {

            System.out.println(name + " attacks you for " + attackPower + " damage!");

            player.getHealth().takeDamage(attackPower);
        }

    }

    public void burning(Mob mob){
        int burnTurns = 3;
        if(onFire == true){
            mob.getHealth().takeDamage(2);
            burnTurns--;
        }
    }

    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }

    public boolean isAggro() {
        return isAggro;
    }

    public boolean isStunned(){
        return isStunned;
    }

    public void setStunned(boolean hm){
        this.isStunned = hm;
    }

    public void setOnFire(boolean onFire){this.onFire = onFire;}

    public void addMobToWorld(Mob mob){
        mobsInWorld.add(mob);
    }

    public void removeMobInWorld(Mob mob){
        mobsInWorld.remove(mob);
    }

    public List<Mob> getMobsInWorld(){
        return mobsInWorld;
    }

}


