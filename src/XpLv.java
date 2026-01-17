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
            return (int)(Math.random() * 30 + 25);
        } else if (mobName.toLowerCase().contains("squirrel")) {
            return (int)(Math.random() * 30 + 35);
        } else if (mobName.toLowerCase().contains("goblin")) {
            return (int)(Math.random() * 40 + 45);
        } else if (mobName.toLowerCase().contains("mutebandit")) {
            return (int)(Math.random() * 65 + 50);
        } else if (mobName.toLowerCase().contains("wolf")) {
            return (int)(Math.random() * 45 + 60);
        } else if (mobName.toLowerCase().contains("bear")) {
            return (int)(Math.random() * 50 + 60);
        } else if (mobName.toLowerCase().contains("wolf witch")) {
            return (int)(Math.random() * 50 + 60);
        } else if (mobName.toLowerCase().contains("blood witch")) {
            return (int)(Math.random() * 70 + 60);
        } else if (mobName.toLowerCase().contains("forest devil")) {
            return (int)(Math.random() * 100 + 200);
        }

        return 0;
    }

    public void calculateLv(int xp, int lv, Player player){
        while(xp >= lv*100){
            xp -= lv*100;
            lv += 1;
            System.out.println("Your level has increased by one! (->> " + lv + ")");
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
