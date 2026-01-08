public class Enchantments {
    private String name;
    private String description;
    private int level;

    public Enchantments(String name, String description, int level){
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public void usePower(Enchantments enchantment, Mob mob){
        if (enchantment.getName().equalsIgnoreCase("fire")){
            int d = enchantment.getLevel() * 3;
            int burtTurns = enchantment.getLevel() + 1;
            if (!mob.isOnFire()) {
                mob.setBurnTurns(burtTurns);
                mob.setOnFire(true);
            }

            if (mob.isOnFire()){
                mob.burning(mob, d);
            }

        }
    }

    public void checkPower(Item item, Mob mob){
        if (item.getEnchantment() != null){
            usePower(item.getEnchantment(), mob);
        }
    }

    public void useSelf(Enchantments enchantments, Player player){

    }

    public void checkSelf(Enchantments enchantments, Item item, Player player){
        if (item.getEnchantment() != null){
            useSelf(enchantments, player);
        }
    }


    public String getName(){return name;}

    public int getLevel(){return level;}


}
