import java.util.ArrayList;
import java.util.List;

public class Hub {
    private String roomName;
    private String roomDescription;
    private java.util.Map<String, Hub> exits;
    private List<String> objects;


    public Hub(String name, String description) {
        this.roomName = name;
        this.roomDescription = description;
        this.exits = new java.util.HashMap<>();
        this.objects = new ArrayList<>();
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

}

