import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Npca {
    private String name;
    private String prof; // e.g., "Merchant", "Guild persin"
    private String[] lines;
    private int attackPower;
    private Health health;
    private boolean isStunned = false;
    private String questId;        // e.g. "SQ2" for Collect Twigs
    private QuestState questState;

    public enum QuestState {
        NONE,        // no quest
        OFFERED,     // user has seen the offer but did not accepted
        ACCEPTED,    // quest is in the Player.QUESTS just not done yet
        COMPLETED    // quest completed, NPC in post‑quest mode
    }

    public Npca(String name, String role, String[] lines, int attackPower, Health health, String questId, QuestState questState) {
        this.name = name;
        this.prof = role;
        this.lines = lines;
        this.attackPower = attackPower;
        this.health = health;
        this.questId = questId;
        this.questState = questState;
    }

    public String getQuestId() { return questId; }

    public QuestState getQuestState() { return questState; }

    public void setQuestState(QuestState s) { this.questState = s; }

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

    public Merchant(String name, String[] lines, Map<String, Integer> stock, Health hp, int attk, String questId, QuestState questState) {
        super(name, "Merchant", lines, attk, hp, questId, questState);
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


    public Guard(String name, String[] lines, Health health, int attackPower, Item helmet, Item chestplate, Item leggings, Item boots, String questId, QuestState questState){
        super(name, "Guard", lines, attackPower, health, questId, questState);
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    public Item getItem(){
        int a = (int) (Math.random() * 5 + 1);
        if (a == 1){
            return helmet;
        }

        else if (a == 2){
            return boots;
        }

        else if (a == 3){
            return chestplate;
        }

        else {
            return leggings;
        }
    }


}





