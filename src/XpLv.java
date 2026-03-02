public class XpLv {

    private int level;
    private int xpCount;

    public XpLv(int level, int xpCount) {
        this.level = level;
        this.xpCount = xpCount;
    }

    public int getLevel() {
        return level;
    }

    public int getXp(){
        return xpCount;
    }

    public void addXp(int adding){
        xpCount += adding;
    }

    public void setLevel(int l ){
        level = l;
    }

    public int calculateXp(String mobName){
        if (mobName.toLowerCase().contains("rabbit") || mobName.toLowerCase().contains("chicken")){
            return (20);
        } else if (mobName.toLowerCase().contains("squirrel")) {
            return 35;
        } else if (mobName.toLowerCase().contains("goblin")) {
            return 50;
        } else if (mobName.toLowerCase().contains("mutebandit")) {
            return 70;
        } else if (mobName.toLowerCase().contains("wolf")) {
            return 45;
        } else if (mobName.toLowerCase().contains("bear")) {
            return 85;
        } else if (mobName.toLowerCase().contains("fox witch")) {
            return 90;
        } else if (mobName.toLowerCase().contains("blood witch")) {
            return 130;
        } else if (mobName.toLowerCase().contains("forest devil")) {
            return 235;
        } else if (mobName.toLowerCase().contains("greater goblin")) {
            return 70;
        }

        return 0;
    }

    public void calculateLv(int xp, int lv, Player player){
        while(xp >= lv*100){
            xp -= lv*100;
            lv += 1;
            System.out.println("Your level has increased by one! (->> " + lv + ")");
            player.addStomachSize(1);
            System.out.println("Your STOMACH SIZE has increased by one! (->> " + player.getStomachSize() + ")");
            player.addCoins(lv * 25);
            System.out.println("You have gained fame! (->> " + player.getCoins() + ")");
            player.getHealth().setMaxHealth(player.getHealth().getMaxHealth() + 5);
            player.setAttackPower(1);

            System.out.println("New max health -> " + player.getHealth().getMaxHealth());

            System.out.println("New attack damage -> " + player.getAttackPower());

        }

        System.out.println("");

        xpCount = xp;
        level = lv;
    }

}
