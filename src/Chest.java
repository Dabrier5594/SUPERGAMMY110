import java.util.*;

class Chest {

    private boolean opened = false;
    private boolean requiresKey;
    private String requiredKeyName; // Ex: "ChestKey#001"
    private List<String> contents;
    private String level; // C, B, A, AA

    public Chest(boolean requiresKey, String requiredKeyName, List<String> contents, String level) {
        this.requiresKey = requiresKey;
        this.requiredKeyName = requiredKeyName;
        this.contents = contents;
        this.level = level;
        this.opened = false;
    }
    public boolean isOpened() { return opened; }
    public boolean requiresKey() { return requiresKey; }
    public String getRequiredKeyName() { return requiredKeyName; }
    public List<String> getContents() { return contents; }
    public void open() { this.opened = true; }
}
