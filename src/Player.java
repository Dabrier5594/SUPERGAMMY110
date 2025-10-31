import java.util.List;

public class Player {

    private String name;

    private Health health;

    private int attackPower;

    private boolean isStunned = false;

    private List<Skill> skills;

    private List<String> inventory;

    public Player(String name, int maxHealth, int health, int power, int damageResistance) {

        this.name = name;
        this.health = new Health(maxHealth, health, damageResistance);
        this.attackPower = power;

    }

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

        mob.getHealth().takeDamage(attackPower);

        if (mob.getHealth().isDead()) {
            System.out.println(mob.getName() + " has been defeated!");
        } else {
            System.out.println(mob.getName() + " has " + mob.getHealth().getHealth() + " HP remaining.");
        }

    }

    public void takeDamage(int damage) {
        health.takeDamage(damage);
    }

    public void displayStats(Player player) {
        System.out.println("=== Player Stats ===");
        System.out.println("Name: " + name);
        System.out.println("HP: " + health.getHealth() + "/" + health.getMaxHealth());
        System.out.println("Attack: " + attackPower + " | Defense: " + player.getHealth().getDamageResistance());
    }

    public Health getHealth() {
        return health;
    }

    public boolean getStunned(){
        return isStunned;
    }

    public void setStunned(boolean stunner){
        this.isStunned = stunner;
    }


}