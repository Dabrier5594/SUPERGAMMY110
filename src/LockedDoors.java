import java.util.List;
import java.util.*;

public class LockedDoors {
    //hi
    private boolean opened = false;
    private boolean requiresKey;//uses key or not(used for anything that needs a key
    private String requiredKeyName; //name of key if needed Ex: "ChestKey#001"
    private Hub hubA;
    private Hub hubB;

    public LockedDoors(boolean requiresKey, String requiredKeyName, Hub hubA, Hub hubB) {
        this.requiresKey = requiresKey;
        this.requiredKeyName = requiredKeyName;
        this.opened = false;
        this.hubA = hubA;
        this.hubB = hubB;
    }
    public boolean isOpened() {
        return opened;
    }

    public boolean requiresKey() {
        return requiresKey;
    }

    public String getRequiredKeyName() {
        return requiredKeyName;
    }



    public void open() { this.opened = true; }

    public Hub getOtherSide(Hub current) {
        if (current == hubA) return hubB;
        if (current == hubB) return hubA;
        return null;
    }
}

class Key {
    private String name;
    private int id;

    public Key(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

