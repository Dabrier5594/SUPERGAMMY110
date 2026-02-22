import java.util.*;

public class Npca {
    private String name;
    private String prof; // e.g., "Merchant", "Guild persin"
    private String[] lines;
    private int attackPower;
    private Health health;
    private boolean isStunned = false;
    private String questId;        // e.g., "SQ2" for Collect Twigs
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
            System.out.println(name + " (" + prof + ")  says: \"" + lines[a] + "\"");
        }
    }
}

//ALL NPCS SHOULD ALL HAVE THE SAME INDEX 0-2 LINES (LIKE LIKE SIMPLE ONES).

class Merchant extends Npca {

    private Map<String, Integer> stock;  // "Iron Sword" -> 50
    private String currency;

    public Merchant(String name, String[] lines, String currency, Map<String, Integer> stock, Health hp, int attk, String questId, QuestState questState) {
        super(name, "Merchant", lines, attk, hp, questId, questState);
        this.stock = stock;
        this.currency = currency;
    }

    public void showStock() {
        System.out.println(getName() + "'s stock:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(" - " + entry.getKey() + " ( " + entry.getValue() +  " " + currency + " )");
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

class FirstShopOwner extends Npca {

    private Hub.FirstVilleShop myShop;

    private Hub.FirstVilleScrollShop myScrollShop;

    private Hub.FirstVilleStringShop myStringShop;

    private String currency;

    public FirstShopOwner(String currency, String name, String[] lines, Health health, int attackPower, Hub.FirstVilleShop myShop, String questId, QuestState questState) {
        super(name, "Shop Owner", lines, attackPower, health, questId, questState);
        this.myShop = myShop;
        this.currency = currency;
    }

    public void setMyShop(Hub.FirstVilleShop shop) {
        this.myShop = shop;
    }

    public void setMyShop(Hub.FirstVilleScrollShop shop) {
        this.myScrollShop = shop;
    }

    public void setMyShop(Hub.FirstVilleStringShop shop) {
        this.myStringShop = shop;
    }

    public List<String> buyItem(Player player, List<String> invin) {

        if (myShop != null) {
            Scanner scanner = new Scanner(System.in);
            String answer;
            List<Map.Entry<Item, Integer>> stockList = Collections.emptyList();

            if (!myShop.getStock().isEmpty()) {
                stockList = new ArrayList<>(myShop.getStock().entrySet());
            }
            System.out.println("");

            if (stockList.isEmpty()) {
                System.out.println("Shop is empty! Nothing to buy.");
                return invin;
            }


            System.out.println(getName() + "'s SHOP:");
            for (int i = 0; i < stockList.size(); i++) {
                Map.Entry<Item, Integer> entry = stockList.get(i);  // GET ith entry!
                System.out.println((i + 1) + ") " + entry.getKey().getName() +
                        ": " + entry.getValue() + " " + currency);
            }

            System.out.print("Which item? (number) - ");
            answer = scanner.nextLine().trim();

            Item chosen = null;
            int price = 0;

            if (answer.isEmpty()) {
                System.out.println("Aren't you a smarty pants, go eat some sourdough bread. *you are shoo-ed out of the store.");
                return invin;
            }

            try {
                int index = Integer.parseInt(answer) - 1;
                if (index >= 0 && index < stockList.size()) {
                    chosen = stockList.get(index).getKey();
                    price = stockList.get(index).getValue();
                }

            } catch (NumberFormatException e) {
                return invin;
            }

            int to = 0;

            if (chosen != null) {

                if (Collections.frequency(invin, currency) > price) {
                    for (int i = 0; i < price; i++) {
                        invin.remove(currency);
                    }
                    invin.add(chosen.getName());  // add to inventory
                    System.out.println("Bought " + chosen.getName() + " for " + price + " " + currency + "!\n");
                    to = 1;


                } else {
                    System.out.println("Need " + price + " copper! You got... not enough! \n");
                    to = 1;
                }


            }

            if (to == 0) {
                System.out.println("Aren't you a smarty pants, go eat some sourdough bread.\n");
            }

            return invin;
        }

        else if (myScrollShop != null) {
            Scanner scanner = new Scanner(System.in);
            String answer;
            List<Map.Entry<Enchantment1, Integer>> stockList = Collections.emptyList();

            if (!myScrollShop.getStock().isEmpty()) {
                stockList = new ArrayList<>(myScrollShop.getStock().entrySet());
            }
            System.out.println("");

            if (stockList.isEmpty()) {
                System.out.println("Shop is empty! Nothing to buy.");
                return invin;
            }


            System.out.println(getName() + "'s SHOP:");
            for (int i = 0; i < stockList.size(); i++) {
                Map.Entry<Enchantment1, Integer> entry = stockList.get(i);  // GET ith entry!
                System.out.println((i + 1) + ") " + entry.getKey().getName() +
                        ": " + entry.getValue() + " " + currency);
            }

            System.out.print("Which spell? (number) - ");
            answer = scanner.nextLine().trim();

            Enchantment1 chosen = null;
            int price = 0;

            if (answer.isEmpty()) {
                System.out.println("Aren't you a smarty pants, go eat some sourdough bread. *you are shoo-ed out of the store.");
                return invin;
            }

            try {
                int index = Integer.parseInt(answer) - 1;
                if (index >= 0 && index < stockList.size()) {
                    chosen = stockList.get(index).getKey();
                    price = stockList.get(index).getValue();
                }

            } catch (NumberFormatException e) {
                return invin;
            }

            int to = 0;

            if (chosen != null) {

                if (Collections.frequency(invin, currency) > price) {
                    for (int i = 0; i < price; i++) {
                        invin.remove(currency);
                    }
                    invin.add(chosen.getName());  // add to inventory
                    System.out.println("Bought " + chosen.getName() + " for " + price + " " + currency + "!\n");
                    to = 1;


                } else {
                    System.out.println("Need " + price + " copper! You got... not enough! \n");
                    to = 1;
                }


            }

            if (to == 0) {
                System.out.println("Aren't you a smarty pants, go eat some sourdough bread.\n");
            }

            return invin;

        } else if (myStringShop != null) {
            Scanner scanner = new Scanner(System.in);
            String answer;
            List<Map.Entry<String, Integer>> stockList = Collections.emptyList();

            if (!myStringShop.getStock().isEmpty()) {
                stockList = new ArrayList<>(myStringShop.getStock().entrySet());
            }

            System.out.println("");

            if (stockList.isEmpty()) {
                System.out.println("Shop is empty! Nothing to buy.");
                return invin;
            }


            System.out.println(getName() + "'s SHOP:");
            for (int i = 0; i < stockList.size(); i++) {
                Map.Entry<String, Integer> entry = stockList.get(i);  // GET ith entry!
                System.out.println((i + 1) + ") " + entry.getKey() +
                        ": " + entry.getValue() + " " + currency);
            }

            System.out.print("Which thing? (number) - ");
            answer = scanner.nextLine().trim();

            String chosen = null;
            int price = 0;

            if (answer.isEmpty()) {
                System.out.println("Aren't you a smarty pants, go eat some sourdough bread. *you are shoo-ed out of the store.");
                return invin;
            }

            try {
                int index = Integer.parseInt(answer) - 1;
                if (index >= 0 && index < stockList.size()) {
                    chosen = stockList.get(index).getKey();
                    price = stockList.get(index).getValue();
                }

            } catch (NumberFormatException e) {
                return invin;
            }

            int to = 0;

            if (chosen != null) {

                if (Collections.frequency(invin, currency) > price) {
                    for (int i = 0; i < price; i++) {
                        invin.remove(currency);
                    }

                    invin.add(chosen);  // add to inventory
                    System.out.println("Bought " + chosen + " for " + price + " " + currency + "!\n");
                    to = 1;


                } else {
                    System.out.println("Need " + price + " copper! You got... not enough! \n");
                    to = 1;
                }


            }

            if (to == 0) {
                System.out.println("Aren't you a smarty pants, go eat some sourdough bread.\n");
            }

            return invin;
        }


        return invin;

    }

    public List<String> sellItem(Player player, List<String> invin, Equipment equipment) {

        Scanner scanner = new Scanner(System.in);
        String answer;

        List<String> playerItems = new ArrayList<>();

        for (String itemName : invin) {
            if (!playerItems.contains(itemName) && !isCurrency(itemName)) {
                playerItems.add(itemName);
            }
        }

        System.out.println("");


        if (playerItems.isEmpty()) {
            System.out.println(getName() + " says: \"Nothing worth buying here, sucker.\"");
            return invin;
        }

        System.out.println(getName() + "'s BUY BACK:");

        for (int i = 0; i < playerItems.size(); i++) {
            String item = playerItems.get(i);
            int sellPrice = getSellPrice(item);  // 50-70% of buy price or fixed value
            System.out.println((i + 1) + ") " + item + " (" + sellPrice + " " + currency + ")");
        }

        System.out.print("Sell which? (number) or 'none' - ");
        answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("none") || answer.isEmpty()) {
            System.out.println(getName() + " says: \"Smart choice, come back anytime.\"");
            return invin;
        }

        try {
            int index = Integer.parseInt(answer) - 1;
            if (index >= 0 && index < playerItems.size()) {
                String sellItem = playerItems.get(index);
                int sellPrice = getSellPrice(sellItem);

                // Check if player has the item and it's not equipped
                if (Collections.frequency(invin, sellItem) > 0 && !isEquipped(player, sellItem, equipment)) {

                    // Remove one instance of item
                    invin.remove(sellItem);

                    // Add currency (sellPrice times)
                    for (int i = 0; i < sellPrice; i++) {
                        invin.add(currency);
                    }

                    System.out.println("Sold " + sellItem + " for " + sellPrice + " " + currency + "!");
                    System.out.println(getName() + " says: \"Good sale!\"\n");
                    return invin;
                } else {
                    System.out.println(getName() + " says: \"Can't sell that—it's equipped or you don't have it!\"");
                }
            }
        } catch (NumberFormatException e) {

        }

        System.out.println(getName() + " says: \"Don't waste my time!\"\n");
        return invin;
    }

    private boolean isCurrency(String itemName) {
        return itemName.equals("copper") || itemName.equals("silver") || itemName.equals("gold");
    }

    private int getSellPrice(String item) {

        Map<String, Integer> sellPrices = new HashMap<>();

        sellPrices.put("dagger", 3);
        sellPrices.put("leather armor", 4);
        sellPrices.put("copper sword", 25);

        Integer price = sellPrices.get(item.toLowerCase()); //Why does integer work but int doesn't...
        return price != null ? price : 1;
    }

    private boolean isEquipped(Player player, String itemName, Equipment equipment) {

        return itemName.equals(equipment.getWeapon()) || itemName.equals(equipment.getHelmet()) || itemName.equals(equipment.getBody()) ||
               itemName.equals(equipment.getLegs()) || itemName.equals(equipment.getBoots());

    }


}



