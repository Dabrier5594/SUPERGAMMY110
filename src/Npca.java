import java.util.List;
import java.util.Map;

public class Npca {
    private String name;
    private String prof; // e.g., "Merchant", "Guild persin"
    private String[] lines;

    public Npca(String name, String role, String[] lines) {
        this.name = name;
        this.prof = role;
        this.lines = lines;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return prof;
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
        super(name, "Merchant", lines, hp, attk);
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

    public Item buy(Map.Entry<String, Integer> entry : stock.entrySet(){

        )
    }
    // later add buy/sell methods
}

class Guard extends Npca{
    private Item helmet;
    private Item chestplate;
    private Item leggings;
    private Item boots;
    private Item weapon;


    public Guard(String name, String[] lines, int health, int attackpower, Item helmet, Item chestplate, Item leggings, Item boots){
        super(name, "Guard", lines, health, attackpower);
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }


}





