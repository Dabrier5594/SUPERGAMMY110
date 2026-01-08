import java.util.List;

public class Mob {

    private String name;
    private int attackPower;
    private Health health;
    private boolean isStunned = false;
    private boolean isAggro;
    private List<Mob> mobsInWorld;
    private boolean onFire = false;
    private int burnTurns = 3;


    public Mob (String name, Health health, int attackPower, boolean isAggro){

        this.name = name;
        this.attackPower = attackPower;
        this.health = health;
        this.isAggro = isAggro;


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

    public boolean isOnFire(){
        return onFire;
    }
    public void setBurnTurns ( int i ){
        burnTurns = i;
    }

    public void burning(Mob mob, int d){

        if(onFire == true){
            mob.getHealth().takeDamage(d);
            burnTurns--;
        }

        if (burnTurns == 0){
            onFire = false;
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


