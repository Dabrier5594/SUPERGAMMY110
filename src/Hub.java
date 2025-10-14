public class Hub {
    private String roomName;
    private String roomDescription;
    private java.util.Map<String, Hub> exits;

    public Hub(String name, String description) {
        this.roomName = name;
        this.roomDescription = description;
        this.exits = new java.util.HashMap<>();
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

