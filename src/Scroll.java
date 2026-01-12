public class Scroll {
    private final String name;
    private final Enchantments enchant;

    public Scroll(String name, Enchantments enchant) {
        this.name = name;
        this.enchant = enchant;
    }

    public String getName(){ return name; }
    public Enchantments getEnchantment(){ return enchant; }
}
