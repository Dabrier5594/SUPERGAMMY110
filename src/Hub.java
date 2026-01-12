import java.util.*;
import java.util.List;

public class Hub {
    private String roomName;
    private String roomDescription;
    private java.util.Map<String, Hub> exits;
    private List<String> objects;
    private java.util.Map<String, Door> doors = new java.util.HashMap<>();
    private List<Mob> mobs = new ArrayList<>();
    private List<Npca> npc = new ArrayList<>();
    private List<Boss> boss = new ArrayList<>();
    private List<Guard> guard = new ArrayList<>();
    private List<Merchant> merchant = new ArrayList<>();
    private FirstShopOwner firstShopOwners;
    private Map<String, Chest> chests = new HashMap<>();
    private static final List<Hub> allHubs = new ArrayList<>();
    private java.util.Map<String, LockedDoors> lockedDoors = new java.util.HashMap<>();
    private String structure;


    public Hub(String name, String description) {
        this.roomName = name;
        this.roomDescription = description;
        this.exits = new java.util.HashMap<>();
        this.objects = new ArrayList<>();
        allHubs.add(this);//adds room when made

    }

    public void acceptQuests(String playerName, Player player){ }

    public void updateGuildActivity() { }

    public void joinGuild(String name) { }

    public void displayRankingBoard() { }

    public void displayQuestBoard(String name) { }

    public void completeQuest(String name, String id) { }


    public FirstShopOwner getFirstShopOwners(){return firstShopOwners;}

    public void addFirstShopOwner(FirstShopOwner a){firstShopOwners = a;}

    public void setLockedDoor(String direction, LockedDoors gate) {
        lockedDoors.put(direction, gate);
    }

    public void setStructure(String s){ structure = s;}

    public String getStructure(){ return structure;}

    public LockedDoors getLockedDoor(String direction) {
        return lockedDoors.get(direction);
    }

    public void addObject(String obj) {
        objects.add(obj);
    }

    public List<String> getObjects() {
        return objects;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setExit(String direction, Hub neighbor) {
        this.exits.put(direction, neighbor);
    }

    public Hub getExit(String direction) {
        return this.exits.get(direction);
    }

    public void setDoor(String direction, Door door) {
        doors.put(direction, door);
    }

    public Door getDoor(String direction) {
        return doors.get(direction);
    }

    public Map<String, Chest> getChests() {
        return chests;
    }

    public void addChest(String key, Chest chest) {
        chests.put(key, chest);
    }

    public List<Mob> getMOBS() {
        return mobs;
    }

    public List<Boss> getBoss() {
        return boss;
    }

    public List<Npca> getNpc() {
        return npc;
    }

    public List<Guard> getGuard() {
        return guard;
    }

    public List<Merchant> getMerchant() {
        return merchant;
    }


    public static List<Hub> getAllHubs() {
        return allHubs;
    }

    public static Hub get(String name) {
        for (Hub hub : getAllHubs()) {
            if (hub.getRoomName().equalsIgnoreCase(name)) {
                return hub;
            }
        }
        return null;
    }

    public boolean containsMob(Mob specificMob) {
        for (Mob mob : this.getMOBS()) {
            if (mob == specificMob) {
                return true;
            }
        }
        return false;
    }

    public boolean containsBoss(Boss specificBoss) {
        for (Boss mob : this.getBoss()) {
            if (mob == specificBoss) {
                return true;
            }
        }
        return false;
    }

    public boolean containsNpc(Npca specificNpc) {
        for (Npca npc : this.getNpc()) {
            if (npc == specificNpc) {
                return true;
            }
        }
        return false;
    }

    public class FirstVilleShop extends Hub {
        private Map<Item, Integer> shopStock;  // item name -> price in copper
        private FirstShopOwner shopOwner;  // who's running this joint

        public FirstVilleShop(String name, String description, FirstShopOwner owner) {
            super(name, description);  // call Hub constructor cuz we want all that room stuff
            this.shopStock = new HashMap<>();
            this.shopOwner = owner;  // who owns the shop
        }

        public void addStock(Item item, int price) {
            shopStock.put(item, price);  // adds item to shop, price is how much it costs yo
        }

        public Map<Item, Integer> getStock() {
            return new HashMap<>(shopStock); // return COPY so nobody messes with the shop data!
        }

        public String getShopOwnerName() {
            return shopOwner.getName();
        }

        public void displayStock() {
            System.out.println("=== " + shopOwner.getName() + "'s SHOP STOCK ===");
            for (Map.Entry<Item, Integer> entry : shopStock.entrySet()) {
                // loops through EVERY item in shop and prints it with price
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " copper");
            }
            System.out.println("================================");
        }
    }

    public class FirstVilleGuild extends Hub {
        private Map<String, String> guildMembers = new HashMap<>();
        private List<Quest> availableQuests = new ArrayList<>();
        private List<Npca> guildNpcs = new ArrayList<>();
        private int guildActivityCounter = 0;

        // Ranks: Noob -> Bronze -> Silver -> Gold -> Platinum -> Legend
        private final String[] RANKS = {"Noob", "Bronze", "Silver", "Gold", "Platinum", "Legend"};
        private final int MAX_RANK = 5;  // Legend index

        public FirstVilleGuild(String name, String description) {
            super(name, description);
            this.availableQuests = new ArrayList<>();
            setupGuildNpcs();  // create dynamic NPCs
            //MOVE TO OBJECTS LIST this.getObjects().add("quest board"); allow to say "use" to use them
            //MOVE TO OBJECTS LIST this.getObjects().add("ranking podium");
        }

        private void setupGuildNpcs() {

            String[] rookieLines = {"Haha sucker, they call me the ranking genius!", "I'm a prodigy for a reason!", "What's your rank? Actually, I don't care."};
            Npca rookie = new Npca("Rookie Kael", "Adventurer", rookieLines, 0, new Health(30,30,0), "", Npca.QuestState.NONE);
            rookie.setQuestState(Npca.QuestState.COMPLETED);  // starts ranked
            guildNpcs.add(rookie);
            guildMembers.put("Chicken Trevor", "Bronze");

            String[] vetLines = {"Gold rank took me 20 quests. Beat that.", "Platinum quests are brutal.", "Show me your rank card."};
            Npca veteran = new Npca("Vet Mira", "Adventurer", vetLines, 0, new Health(50,50,0), "", Npca.QuestState.NONE);
            guildNpcs.add(veteran);
            guildMembers.put("Rapid Winny", "Gold");

            String[] mysteryLines = {"...", "I rank up silently.", "Watch and learn."};
            Npca mystery = new Npca("Silent Jax", "Adventurer", mysteryLines, 0, new Health(40,40,0), "", Npca.QuestState.NONE);
            guildNpcs.add(mystery);
            guildMembers.put("Silent Jax", "Silver");
        }

        public void joinGuild(String playerName) {
            if (!guildMembers.containsKey(playerName)) {
                guildMembers.put(playerName, "Noob");
                System.out.println(playerName + " joined FirstVille Guild as Noob! Welcome!");
                displayWelcomeMessage();
            } else {
                System.out.println("You're already in the guild, " + playerName + "!");
            }
        }

        public void addQuest(Quest a){ availableQuests.add(a);}

        public String getPlayerRank(String playerName) {
            return guildMembers.getOrDefault(playerName, "Noob");
        }

        public void displayRankingBoard() {
            System.out.println("=== FIRSTVILLE GUILD RANKINGS ===");
            List<Map.Entry<String, String>> sortedMembers = new ArrayList<>(guildMembers.entrySet());

            for (int i = 0; i < sortedMembers.size(); i++) {
                for (int j = 0; j < sortedMembers.size() - 1; j++) {
                    if (getRankIndex(sortedMembers.get(j).getValue()) < getRankIndex(sortedMembers.get(j+1).getValue())) {
                        Collections.swap(sortedMembers, j, j+1);
                    }
                }
            }
            for (Map.Entry<String, String> member : sortedMembers) {
                System.out.println("- " + member.getValue() + ": " + member.getKey());
            }
            System.out.println("================================");


        }

        public void updateGuildActivity() {
            guildActivityCounter++;
            if (guildActivityCounter % 5 == 0) {  // Every 5 "turns", NPCs grind
                for (Npca npc : guildNpcs) {
                    String npcName = npc.getName();
                    int npcRankIndex = getRankIndex(guildMembers.get(npcName));
                    if (npcRankIndex < MAX_RANK && (int) (Math.random() * 100) < 20) {  // 20% rank up chance
                        String newRank = RANKS[npcRankIndex + 1];
                        guildMembers.put(npcName, newRank);
                        System.out.println(npcName + " ranked up to " + newRank + " while you were away!");
                    }
                }
            }
        }


        public void displayQuestBoard(String playerName) {
            String rank = getPlayerRank(playerName);
            int rankLevel = getRankIndex(rank);

            System.out.println("FIRSTVILLE GUILD QUEST BOARD (Rank: " + rank + ")");
            List<Quest> rankQuests = getQuestsForRank(rankLevel, availableQuests);

            if (rankQuests.isEmpty()) {
                System.out.println("No quests for your rank yet! Grind to rank up!");
            } else {
                for (int i = 0; i < rankQuests.size(); i++) {
                    System.out.println((i+1) + ". " + rankQuests.get(i).getName());
                }
            }
            System.out.println("Talk to guild master to accept quests!");
        }

        public void acceptQuests(String playerName, Player player) {

            String rank = getPlayerRank(playerName);
            int rankLevel = getRankIndex(rank);

            System.out.println("FIRSTVILLE GUILD QUEST BOARD (Rank: " + rank + ")");
            List<Quest> rankQuests = getQuestsForRank(rankLevel, availableQuests);

            if (rankQuests.isEmpty()) {
                System.out.println("No quests for your rank yet! [have you joined the guild]");
                return;
            } else {
                for (int i = 0; i < rankQuests.size(); i++) {
                    System.out.println((i+1) + ". " + rankQuests.get(i).getName());
                }
            }

            System.out.print("Which item? (number) - ");
            String answer = Game.scanner.nextLine().trim();

            Quest chosen = null;

            try {
                int index = Integer.parseInt(answer) - 1;
                if (index >= 0 && index < rankQuests.size()) {
                    chosen = rankQuests.get(index);
                }

            } catch (NumberFormatException e) {
                System.out.println("Could not accept that QUEST, please try again..");
                return;
            }

            player.QUESTS.put(chosen.getId(), chosen);
            availableQuests.remove(chosen.getId());
            System.out.println("Remember to come back and check in once you complete a quest!!");

        }

        private List<Quest> getQuestsForRank(int rankLevel, List<Quest> quests) {

            List<Quest> rankBasedQuests = new ArrayList<>();

            if (rankLevel >= 0) { // Noob
                for (Quest i : quests){
                    if (i.getId().equalsIgnoreCase("") || i.getId().equalsIgnoreCase("") ){
                        rankBasedQuests.add(i);
                    }
                }
            }
            if (rankLevel >= 1) { // bronze
                for (Quest i : quests){
                    if (i.getId().equalsIgnoreCase("") || i.getId().equalsIgnoreCase("") ){
                        rankBasedQuests.add(i);
                    }
                }
            }
            if (rankLevel >= 2) { // Silver
                for (Quest i : quests){
                    if (i.getId().equalsIgnoreCase("") || i.getId().equalsIgnoreCase("") ){
                        rankBasedQuests.add(i);
                    }
                }
            }
            if (rankLevel >= 3) { // Gold
                for (Quest i : quests){
                    if (i.getId().equalsIgnoreCase("") || i.getId().equalsIgnoreCase("") ){
                        rankBasedQuests.add(i);
                    }
                }
            }
            if (rankLevel >= 0) { // Platinum
                for (Quest i : quests){
                    if (i.getId().equalsIgnoreCase("") || i.getId().equalsIgnoreCase("") ){
                        rankBasedQuests.add(i);
                    }
                }
            }
            if (rankLevel >= 0) { // Legend
                for (Quest i : quests){
                    if (i.getId().equalsIgnoreCase("") || i.getId().equalsIgnoreCase("") ){
                        rankBasedQuests.add(i);
                    }
                }
            }

            return rankBasedQuests;
        }

        public void completeQuest(String playerName, String questId) {

            String rank = getPlayerRank(playerName);
            int currentRank = getRankIndex(rank);

            guildActivityCounter++;
            if (guildActivityCounter % 3 == 0 && currentRank < MAX_RANK) {  // Every 3rd quest completion chance to rank up
                String newRank = RANKS[currentRank + 1];
                guildMembers.put(playerName, newRank);
                System.out.println(playerName + " ranked up to " + newRank + "! New quests unlocked!");
            }
            System.out.println("Quest '" + questId + "' completed! Check rankings.");
        }

        private int getRankIndex(String rank) {
            for (int i = 0; i < RANKS.length; i++) {
                if (RANKS[i].equalsIgnoreCase(rank)) return i;
            }
            return 0;
        }

        private void displayWelcomeMessage() {
            System.out.println("Guild Master: Welcome to FirstVille Guild! Complete quests to rank up from Noob to Legend.");
            System.out.println("- 'rankings' to see leaderboards");
            System.out.println("- 'quests' to see your available quests");
        }
    }


    public class CurrencyExchangeBooth extends Hub {

        public CurrencyExchangeBooth(String name, String description) {
            super(name, description);
            this.getObjects().add("exchange booth");
        }

        public void exchangeCurrency(Player player, List<String> inventory) {
            System.out.println("=== CURRENCY EXCHANGE ===");

            // Count money in inventory
            int copperCount = 0, silverCount = 0, goldCount = 0;
            for (String item : inventory) {
                if (item.equals("copper")) copperCount++;
                if (item.equals("silver")) silverCount++;
                if (item.equals("gold")) goldCount++;
            }

            System.out.println("You have: " + copperCount + " copper, " + silverCount + " silver, and " + goldCount + " gold");
            System.out.println("Rates: 10 copper = 1 silver || 5 silver = 1 gold");
            System.out.println("[1] Copper to Silver  [2] Silver to Copper");
            System.out.println("[3] Silver to Gold    [4] Gold to Silver  [5] Leave");
            System.out.print("Choose: ");

            String choice = Game.scanner.nextLine();

            if (choice.equals("1")) {
                copperToSilver(inventory);
            } else if (choice.equals("2")) {
                silverToCopper(inventory);
            } else if (choice.equals("3")) {
                silverToGold(inventory);
            } else if (choice.equals("4")) {
                goldToSilver(inventory);
            } else {
                System.out.println("Adios, if you aren't gonna trade, you got no place here!");
                return;
            }

            exchangeCurrency(player, inventory);  // Back to menu
        }

        private void copperToSilver(List<String> inventory) {
            System.out.print("How much copper? (multiples of 10): ");
            String input = Game.scanner.nextLine();
            int amount = Integer.parseInt(input);

            if (hasCurrency(inventory, "copper", amount) && amount % 10 == 0) {
                int silver = amount / 10;
                removeCurrency(inventory, "copper", amount);
                addCurrency(inventory, "silver", silver);
                System.out.println("Got " + silver + " silver!");
            } else {
                System.out.println("Trade has failed. Please try again, remember to follow trade rules!");
            }
        }

        private void silverToCopper(List<String> inventory) {
            System.out.print("How much silver? ");
            String input = Game.scanner.nextLine();
            int amount = Integer.parseInt(input);

            if (hasCurrency(inventory, "silver", amount)) {
                int copper = amount * 10;
                removeCurrency(inventory, "silver", amount);
                addCurrency(inventory, "copper", copper);
                System.out.println("Got " + copper + " copper!");
            } else {
                System.out.println("Not enough silver!");
            }
        }

        private void silverToGold(List<String> inventory) {
            System.out.print("How much silver? (multiples of 5): ");
            String input = Game.scanner.nextLine();
            int amount = Integer.parseInt(input);

            if (hasCurrency(inventory, "silver", amount) && amount % 5 == 0) {
                int gold = amount / 5;
                removeCurrency(inventory, "silver", amount);
                addCurrency(inventory, "gold", gold);
                System.out.println("Got " + gold + " gold!");
            } else {
                System.out.println("Trade has failed. Please try again, remember to follow trade rules!");
            }
        }

        private void goldToSilver(List<String> inventory) {
            System.out.print("How much gold? ");
            String input = Game.scanner.nextLine();
            int amount = Integer.parseInt(input);

            if (hasCurrency(inventory, "gold", amount)) {
                int silver = amount * 10;
                removeCurrency(inventory, "gold", amount);
                addCurrency(inventory, "silver", silver);
                System.out.println("Got " + silver + " silver!");
            } else {
                System.out.println("Not enough gold!");
            }
        }

        private boolean hasCurrency(List<String> inventory, String currency, int amount) {
            int count = 0;
            for (String item : inventory) {
                if (item.equals(currency)) count++;
            }
            return count >= amount;
        }

        private void removeCurrency(List<String> inventory, String currency, int amount) {
            for (int i = 0; i < amount; i++) {
                inventory.remove(currency);
            }
        }

        private void addCurrency(List<String> inventory, String currency, int amount) {
            for (int i = 0; i < amount; i++) {
                inventory.add(currency);
            }
        }
    }



}

