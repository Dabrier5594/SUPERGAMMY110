import java.util.List;

public class Player {

    private String name;

    private Health health;

    private int attackPower;


    private List<String> inventory;

    public Player(String name, int maxHealth, int health, int power, int damageResistance) {

        this.name = name;
        this.health = new Health(maxHealth, health, damageResistance);
        this.attackPower = power;

    }

    public void attack(Mob mob) {
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

}