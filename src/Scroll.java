public class Scroll {
    private final String name;
    private final Enchantments enchant;
    private final int level;

    public Scroll(String name, Enchantments enchant, int level) {
        this.name = name;
        this.enchant = enchant;
        this.level = level;
    }

    public String getName(){ return name; }
    public Enchantments getEnchantment(){ return enchant; }
    public String getLevel(){ return name; }

}
