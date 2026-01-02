import java.util.*;
import java.util.Collections;
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
    private Map<String, Chest> chests = new HashMap<>();
    private static final List<Hub> allHubs = new ArrayList<>();
    private java.util.Map<String, LockedDoors> lockedDoors = new java.util.HashMap<>();


    public Hub(String name, String description) {
        this.roomName = name;
        this.roomDescription = description;
        this.exits = new java.util.HashMap<>();
        this.objects = new ArrayList<>();
        allHubs.add(this);//adds room when made

    }

    public void setLockedDoor(String direction, LockedDoors gate) {
        lockedDoors.put(direction, gate);
    }

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
        private ShopOwner shopOwner;  // who's running this joint

        public FirstVilleShop(String name, String description, ShopOwner owner) {
            super(name, description);  // call Hub constructor cuz we want all that room stuff
            this.shopStock = new HashMap<>();
            this.shopOwner = owner;  // who owns the shop
        }

        public void addStock(Item item, int price) {
            shopStock.put(item, price);  // adds item to shop, price is how much it costs yo
        }

        public Map<Item, Integer> getStock() {
            return new HashMap<>(shopStock); // return COPY so nobody messes with shop data!
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

    public class Guild extends Hub {
        private List<String> availableQuests;  // quest IDs players can get
        private Map<String, String> guildMembers; // playerName -> their rank (Noob -> Legend)

        public Guild(String name, String description) {
            super(name, description);
            this.availableQuests = new ArrayList<>();  // empty quest list to fill later
            this.guildMembers = new HashMap<>();  // who's in the guild and their rank
        }

        public void addQuest(String questId) {
            availableQuests.add(questId);  // adds quest to the board, players see this
        }

        public void removeQuest(String questId) {
            availableQuests.remove(questId);  // adds quest to the board, players see this
        }

        public List<String> getAvailableQuests() {
            return new ArrayList<>(availableQuests);  // copy so they can't hack the quest list
        }

        public void joinGuild(String playerName) {
            guildMembers.put(playerName, "Novice");  // new guy starts at bottom, grind time!
            System.out.println(playerName + " joined the guild as Novice!");
        }

        public void displayQuestBoard() {
            System.out.println("🏛GUILD QUEST BOARD");
            if (availableQuests.isEmpty()) {
                System.out.println("No quests right now... come back later!");
            } else {
                for (int i = 0; i < availableQuests.size(); i++) {
                    // shows numbered quests, makes it easy to pick one
                    System.out.println((i+1) + ". " + availableQuests.get(i));
                }
            }
            System.out.println("Talk to guild master to accept quests!");
        }
    }


}

