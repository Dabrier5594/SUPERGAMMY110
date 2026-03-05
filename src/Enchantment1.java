public class Enchantment1 {
    private String name;
    private String description;
    private int level;

    private boolean rouletteActivated = false;

    public Enchantment1(String name, String description, int level){
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public void usePower(Enchantment1 enchantment, Mob mob, Player player){

        if (enchantment.getName().contains("fire")){
            int d = enchantment.getLevel() * 3;
            int burtTurns = enchantment.getLevel();
            if (!mob.isOnFire()) {
                mob.setBurnTurns(burtTurns);
                mob.setOnFire(true);
            }

            else if (mob.isOnFire()){
                mob.burning(mob, d);
            }

        } else if (enchantment.getName().contains("roulette")){
            if (rouletteActivated){
                player.getMeleeSlot().setIncreaseNum(player.getMeleeSlot().getIncreaseNum() / 2);
            }
            System.out.println("ROULETTE ACTIVATED! and...");
            int lv = enchantment.getLevel();
            int chance = (int)(Math.random() * 4) + lv;
            if (chance >= 4){
                System.out.println("Against the odds you land a winning hand! Double DAMAGE!");
                player.getMeleeSlot().setIncreaseNum(player.getMeleeSlot().getIncreaseNum() * 2);
                rouletteActivated = true;
            } else {
                System.out.println("Bad spin. The house always wins.");
                if (player.getHealth().getHeealth() > ((int)player.getHealth().getHeealth() * 0.15)){
                    player.getHealth().takeDamage((int)(player.getHealth().getHeealth() * 0.15));
                    System.out.println("DAMAGE TAKEN!");
                }
            }

        }
    }

    public void usePowerBoss(Enchantment1 enchantment, Boss mob, Player player){
        if (enchantment.getName().contains("fire")){
            int d = enchantment.getLevel() * 3;
            int burtTurns = enchantment.getLevel();
            if (!mob.isOnFire()) {
                mob.setBurnTurns(burtTurns);
                mob.setOnFire(true);
            }

            else if (mob.isOnFire()){
                mob.burning(mob, d);
            }

        } else if (enchantment.getName().contains("roulette")){
            if (rouletteActivated){
                player.getMeleeSlot().setIncreaseNum(player.getMeleeSlot().getIncreaseNum() / 2);
            }
            System.out.println("ROULETTE ACTIVATED! and...");
            int lv = enchantment.getLevel();
            int chance = (int)(Math.random() * 4) + lv;
            if (chance >= 4){
                System.out.println("Against the odds you land a winning hand! Double DAMAGE!");
                player.getMeleeSlot().setIncreaseNum(player.getMeleeSlot().getIncreaseNum() * 2);
                rouletteActivated = true;
            } else {
                System.out.println("Bad spin. The house always wins.");
                if (player.getHealth().getHeealth() > ((int)player.getHealth().getHeealth() * 0.15)){
                    player.getHealth().takeDamage((int)(player.getHealth().getHeealth() * 0.15));
                    System.out.println("DAMAGE TAKEN!");
                }
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
