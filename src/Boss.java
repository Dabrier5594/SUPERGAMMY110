import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Boss {
    private String name;

    private Health health;

    private int attackPower;

    private boolean isStunned = false;

    private String description;

    private List<String> drops;//what the boss drops

    private boolean isAgrro = true;

    private int bossLevel;//could be used for later if we want to add boss levels as the player progresses

    private boolean isDead = false;

    List<MobSkill> skills;

    private boolean onFire = false;

    private int burnTurns = 3;

    public Boss(String name, int maxHealth, int health, int power, int damageResistance, String description, List<String> drops) {
        this.name = name;
        this.health = new Health(maxHealth, health, damageResistance);
        this.attackPower = power;
        this.description = description;
        this.drops = drops;
        this.skills = new ArrayList<>();
    }


    public void displayStats(Boss boss) {
        System.out.println("=== Boss Stats ===");
        System.out.println("Name: " + name);
        System.out.println("HP: " + health.getHeealth() + "/" + health.getMaxHealth());
        System.out.println("Attack: " + attackPower + " | Defense: " + boss.getHealth().getDamageResistance());
        System.out.println("");
    }

    public boolean isAggro(){
        return isAgrro;
    }

    public Health getHealth() {
        return health;
    }

    public String getName(){return name;}

    public void setHealth(){
        health.setHeealth(health.getHeealth() + 5);
    }

    public boolean getStunned(){
        return isStunned;
    }

    public void setStunned(boolean stunner){
        this.isStunned = stunner;
    }

    public void setAttackPower(int tint){
        attackPower += tint;
    }

    public boolean getDead(){return isDead;}

    public void setDead(boolean isDead){this.isDead = isDead;}

    public void attack(Player player, Equipment eq, Map<String, Item> eqI){

        if (getStunned()) {
            System.out.println("The GREAT " + name + " is stunned and skips its turn!");
            setStunned(false);
            return;
        }

        int roll = (int)(Math.random() * 10);

        for (MobSkill skill : skills) {
            if (skill.getName().equals("VineSkill")) {
                if (skill.getCurrentCooldown() < 2) {
                    ((MobSkill.VineSkill)skill).apply(player, this, eqI, eq);

                }
            }
        }

        if (roll < 5) {
            // Try VineSkill first
            for (MobSkill skill : skills) {
                if (skill.getName().equals("VineSkill")) {
                    if (skill.getCurrentCooldown() <= 0) {
                        ((MobSkill.VineSkill)skill).apply(player, this, eqI, eq);

                    }
                }
            }
        }
        if (roll > 4 || roll == 2) {
            // Try StunSkill second
            for (MobSkill skill : skills) {
                if (skill.getName().equals("StunSkill")) {
                    if (skill.getCurrentCooldown() <= 0) {
                        ((MobSkill.StunSkill)skill).apply(player, this);

                    }
                }
            }
        }

        System.out.println("The GREAT " + name + " attacks you for " + attackPower + " damage!");
        player.getHealth().takeDamage(attackPower);

    }



    public void drops(List<String> drops){
        this.drops = drops;
    }

    public List<MobSkill> getSkills(){
        return skills;
    }

    public void addSkills(MobSkill skill){
        skills.add(skill);
    }

    public List<String> getDrops(){
        return drops;
    }

    public boolean isOnFire(){
        return onFire;
    }
    public void setBurnTurns ( int i ){
        burnTurns = i;
    }

    public void setOnFire(boolean onFire){this.onFire = onFire;}

    public void burning(Boss mob, int d){

        if(onFire == true){
            System.out.println("Fire Bonus has been activated! " + mob.getName() + " becomes engulfed in flames!");
            mob.getHealth().mobTakeDamage(d);
            burnTurns--;
        }

        if (burnTurns <= 0 && burnTurns > -3){
            burnTurns--;
        }

        if (burnTurns <= -3){
            onFire = false;
        }
    }

}
