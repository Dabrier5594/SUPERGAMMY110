import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Boss {
    private String name;

    private Health health;

    private int attackPower;

    private boolean isStunned = false;

    private String description;

    private List<String> drops;//what the boss drops

    private List<MobSkill> skills = new ArrayList<>();

    private boolean isAgrro = true;

    private int bossLevel;//could be used for later if we want to add boss levels as the player progresses

    private boolean isDead;

    public Boss(String name, int maxHealth, int health, int power, int damageResistance, String description, boolean isDead, List<String> drops) {

        this.name = name;
        this.health = new Health(maxHealth, health, damageResistance);
        this.attackPower = power;
        this.description = description;
        this.drops = drops;


    }

    public void displayStats(Boss boss) {
        System.out.println("=== Boss Stats ===");
        System.out.println("Name: " + name);
        System.out.println("HP: " + health.getHeealth() + "/" + health.getMaxHealth());
        System.out.println("Attack: " + attackPower + " | Defense: " + boss.getHealth().getDamageResistance());
        System.out.println("");
    }

    public boolean isAggro(){
        return isAgrro;
    }

    public Health getHealth() {
        return health;
    }

    public String getName(){return name;}

    public void setHealth(){
        health.setHeealth(health.getHeealth() + 5);
    }

    public boolean getStunned(){
        return isStunned;
    }

    public void setStunned(boolean stunner){
        this.isStunned = stunner;
    }

    public void setAttackPower(int tint){
        attackPower += tint;
    }

    public boolean getDead(){return isDead;}

    public void setDead(boolean isDead){this.isDead = isDead;}

    public void attack(Player player, Equipment eq, Map<String, Item> eqI){

        if (getStunned()) {
            System.out.println("The GREAT " + name + " is stunned and skips its turn!");
            setStunned(false);
            // Reset stun after skipping one turn

        }

        else {

            System.out.println("The GREAT " + name + " attacks you for " + attackPower + " damage!");

            player.getHealth().takeDamage(attackPower);
            MobSkill.VineSkill.apply(player, this, eqI, eq);
            MobSkill.StunSkill.apply(player, this);

        }

    }
    public void drops(List<String> drops){

    }

    public List<MobSkill> getSkills(){
        return skills;
    }

    public List<String> getDrops(){
        return drops;
    }
}
