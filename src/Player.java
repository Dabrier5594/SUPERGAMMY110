import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private String name;

    private Health health;

    private int attackPower;

    private boolean isStunned = false;

    private List<Skill> skills = new ArrayList<>();

    public static final Map<String, Quest> QUESTS = new HashMap<>();

    private int coins = 0;

    private int fame = 0;

    private boolean inCombat = false;


    public Player(String name, int maxHealth, int health, int power, int damageResistance) {

        this.name = name;
        this.health = new Health(maxHealth, health, damageResistance);
        this.attackPower = power;

    }


    public boolean isInCombat() { return inCombat; }

    public void setInCombat(boolean combat) { this.inCombat = combat; }

    public int getCoins(){ return coins; }

    public void addCoins(int a){coins += a;}

    public String getName(){
        return name;
    }

    public List<Skill> getSkills(){
        return skills;
    }

    public void attack(Mob mob) {

        if (isStunned == true){
            System.out.println("You are stunned and cannot do anything.");
            setStunned(false);
            return;
        }

        if (mob == null) {
            System.out.println("There is nothing to attack.");
            return;
        }

        System.out.println(name + " attacks " + mob.getName() + " for " + attackPower + " damage!");

        mob.getHealth().mobTakeDamage(attackPower);

    }

    public void attackNpc(Npca npc) {

        if (isStunned == true){
            System.out.println("You are stunned and cannot do anything.");
            setStunned(false);
            return;
        }

        if (npc == null) {
            System.out.println("There is nothing to attack.");
            return;
        }

        System.out.println(name + " attacks " + npc.getName() + " for " + attackPower + " damage!");

        npc.getHealth().mobTakeDamage(attackPower);

    }

    public void attackGuard(Guard npc) {

        if (isStunned == true){
            System.out.println("You are stunned and cannot do anything.");
            setStunned(false);
            return;
        }

        if (npc == null) {
            System.out.println("There is nothing to attack.");
            return;
        }

        System.out.println(name + " attacks " + npc.getName() + " for " + attackPower + " damage!");

        npc.getHealth().mobTakeDamage(attackPower);

    }

    public void takeDamage(int damage) {
        health.takeDamage(damage);
    }

    public void displayStats(Player player, XpLv playerStats) {
        System.out.println("=== Player Stats ===");
        System.out.println("Name: " + name);
        System.out.println("HP: " + health.getHeealth() + "/" + health.getMaxHealth());
        System.out.println("Attack: " + attackPower + " | Defense: " + player.getHealth().getDamageResistance());
        System.out.print("Lv: " + playerStats.getLevel() + " || XP: ");
        System.out.println(playerStats.getXp());
        System.out.println("");
    }

    public Health getHealth() {
        return health;
    }

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

    public int getFame(){
        return fame;
    }

    public void addFame(int a){
        fame += a;
    }

}