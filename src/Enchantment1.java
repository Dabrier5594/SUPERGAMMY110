public class Enchantment1 {
    private String name;
    private String description;
    private int level;

    public Enchantment1(String name, String description, int level){
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public void usePower(Enchantment1 enchantment, Mob mob){
        if (enchantment.getName().contains("fire")){
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

    public void usePowerBoss(Enchantment1 enchantment, Boss mob){
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

    public void useSelf(Enchantment1 enchantments, Player player){

    }

    public void checkSelf(Enchantment1 enchantments, Item item, Player player){
        if (item.getEnchantment1() != null){
            useSelf(enchantments, player);
        }
    }


    public String getName(){return name;}

    public int getLevel(){return level;}


}
