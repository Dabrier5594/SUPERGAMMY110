import java.util.List;

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
    private List<String> stock; // for now, just item names

    public Merchant(String name, String[] lines, List<String> stock) {
        super(name, "Merchant", lines);
        this.stock = stock;
    }

    public void showStock() {
        System.out.println(getName() + "'s stock:");
        for (String item : stock) {
            System.out.println(" - " + item);
        }
    }

    // later add buy/sell methods
}


