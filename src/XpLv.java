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

    public int calculateXp(String mobName){
        if (mobName.equals("Rabbit") || mobName.equals("Chicken")){
            return (int)(Math.random() * 30 + 25);
        }

        return 0;
    }

    public int calculateLv(int xp, int lv){
        while(xp >= lv*100){
            xp -= lv*100;
            lv += 1;
            System.out.println("Your level has increased by one! (->> " + lv + ")");
        }

        System.out.println("");

        return lv;
    }

}
