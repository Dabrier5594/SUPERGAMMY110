import java.util.List;
import java.util.Map;

public class Npca {
    private String name;
    private String prof; // e.g., "Merchant", "Guild persin"
    private String[] lines;
    private int attackPower;
    private Health health;
    private boolean isStunned = false;



    public Npca(String name, String role, String[] lines, int attackPower, Health health) {
        this.name = name;
        this.prof = role;
        this.lines = lines;
        this.attackPower = attackPower;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return prof;
    }

    public boolean isStunned(){
        return isStunned;
    }

    public void setStunned(boolean hm){
        this.isStunned = hm;
    }

    public Health getHealth() {
        return health;
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

    public void talk() { // SAY ALL LINES
        System.out.println(name + " (" + prof + ") says:");
        for (String line : lines) {
            System.out.println("  \"" + line + "\"");
        }
    }

    public void sayLine(int a) { // SAY SPECIFIC LINE
        if (a >= 0 && a < lines.length) {
            System.out.println(name + " says: \"" + lines[a] + "\"");
        }
    }
}

//ALL NPCS SHOULD ALL HAVE THE SAME INDEX 0-2 LINES (LIKE LIKE SIMPLE ONES).

class Merchant extends Npca {

    private Map<String, Integer> stock;  // "Iron Sword" -> 50

    public Merchant(String name, String[] lines, Map<String, Integer> stock, int hp, int attk) {
        super(name, "Merchant", lines);
        this.stock = stock;
    }

    public void showStock() {
        System.out.println(getName() + "'s stock:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(" - " + entry.getKey() + " (" + entry.getValue() + " gold)");
        }
    }

    // Returns price or -1 if item not found
    public int getPrice(String item) {
        Integer price = stock.get(item);
        return price != null ? price : -1;

    }


    // later add buy/sell methods
}

class Guard extends Npca{
    private Item helmet;
    private Item chestplate;
    private Item leggings;
    private Item boots;
    private Item weapon;


    public Guard(String name, String[] lines, Health health, int attackPower, Item helmet, Item chestplate, Item leggings, Item boots){
        super(name, "Guard", lines, health, attackPower);
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }
    public guardFight(){

    }

}





