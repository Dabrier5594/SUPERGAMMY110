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
        if (mobName.equals("Rabbit") || mobName.equals("Chicken")){
            return (int)(Math.random() * 30 + 25); //24-54
        } else if (mobName.equals("Squirrel")) {
            return (int)(Math.random() * 30 + 45); // 44-74
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

        }

        System.out.println("");

        xpCount = xp;
        level = lv;
    }

}
