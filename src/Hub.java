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
    private Map<String, Chest> chests = new HashMap<>();
    private static final List<Hub> allHubs = new ArrayList<>();


    public Hub(String name, String description) {
        this.roomName = name;
        this.roomDescription = description;
        this.exits = new java.util.HashMap<>();
        this.objects = new ArrayList<>();
        allHubs.add(this);//adds room when made

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

    public List<Npca> getNpc() {
        return npc;
    }

    public static List<Hub> getAllHubs() {
        return allHubs;
    }

    public boolean containsMob(Mob specificMob) {
        for (Mob mob : this.getMOBS()) {
            if (mob == specificMob) {
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
}

