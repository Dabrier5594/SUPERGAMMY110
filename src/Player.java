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

    private int stomachSize = 10;
    private int fullness = 10;

    private boolean inCombat = false;

    private Item meleeSlot;

    private Item helmetSlot;

    private Item chestSlot;

    private Item leggingSlot;

    private Item bootSlot;

    private Item rangedSlot;

    private int ciritcalChance;


    public Player(String name, int maxHealth, int health, int power, int damageResistance, int criticalChance) {

        this.name = name;
        this.health = new Health(maxHealth, health, damageResistance);
        this.attackPower = power;
        this.ciritcalChance = criticalChance;

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

    public int getAttackPower(){return attackPower;}

    public void setMeleeSlot(Item meleeSlot){this.meleeSlot = meleeSlot;}

    public void setHelmetSlot(Item helmetSlot){this.helmetSlot = helmetSlot;}

    public void setChestSlot(Item chestSlot){this.chestSlot = chestSlot;}

    public void setLeggingSlot(Item leggingSlot){this.leggingSlot = leggingSlot;}

    public void setBootSlot(Item bootSlot){this.bootSlot = bootSlot;}

    public void setRangedSlot(Item rangedSlot){this.rangedSlot = rangedSlot;}

    public Item getMeleeSlot(){return meleeSlot;}

    public Item getHelmetSlot(){return helmetSlot;}

    public Item getChestSlot(){return chestSlot;}

    public Item getLeggingSlot(){return leggingSlot;}

    public Item getBootSlot(){return bootSlot;}

    public Item getRangedSlot(){return rangedSlot;}

    public int getCiritcalChance(){return ciritcalChance;}



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

    public void attackBoss(Boss npc) {

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

    public int getFullness(){
        return fullness;
    }

    public void addFullness(int a){
        fullness += a;
    }

    public void setFullness(){
        fullness = stomachSize;
    }

    public void lessFullness() {fullness--;}

    public int getStomachSize(){
        return stomachSize;
    }

    public void addStomachSize(int a){
        stomachSize += a;
    }


}