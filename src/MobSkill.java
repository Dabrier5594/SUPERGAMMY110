import java.util.Map;

public abstract class MobSkill {
    private String name;

    public MobSkill(String name) {
        this.name = name;

    }

    public abstract void reduceCooldown();

    public abstract int getCurrentCooldown();

    public abstract boolean canUse();

    public String getName() {
        return name;
    }



    public static class StunSkill extends MobSkill {
        private int cooldownTurns;
        private int currentCooldown = 0;

        public StunSkill(String name, int cooldownTurns) {
            super(name);
            this.cooldownTurns = cooldownTurns;
            this.currentCooldown = 0;
        }

        public void reduceCooldown() {
            if (currentCooldown > 0) currentCooldown--;
        }

        public boolean canUse() {
            return currentCooldown <= 0;
        }

        public void apply(Player player, Boss mob) {
            if (!canUse()) {
                return;
            }

            System.out.println(mob.getName() + " uses StunSkill to stun " + player.getName() + "!");

            player.setStunned(true);
            currentCooldown = cooldownTurns;
        }

        public int getCurrentCooldown(){
            return currentCooldown;
        }
    }



    public static class VineSkill extends MobSkill {
        private Item takeItem = null;
        private int cooldownTurns;
        private int currentCooldown = 0;

        public VineSkill(String name, int cooldownTurns) {
            super(name);
            this.cooldownTurns = cooldownTurns;
            this.currentCooldown = 0;
        }

        public void reduceCooldown() {
            if (currentCooldown > 0) currentCooldown--;
        }

        public boolean canUse() {
            return currentCooldown <= 0;
        }

        public void apply(Player player, Boss boss, Map<String, Item> equippedItems, Equipment equipment) {

            if (!canUse()) {

                if (takeItem != null){
                    equipment.equip(takeItem, player);
                    takeItem = null;
                }

                return;
            }

            Item weapon = equipment.getItemBasedOnSlot("melee", equippedItems);
            if (weapon == null) {
                return;
            }

            System.out.println(boss.getName() + " uses VineSkill to rip " + player.getName() + "'s weapon away!");
            equipment.unequip("melee", weapon, player);
            takeItem = weapon;
            currentCooldown = cooldownTurns;
        }

        public int getCurrentCooldown(){
            return currentCooldown;
        }
    }

    public static class RouletteSkill extends MobSkill {
        private int temp = 0;
        private int cooldownTurns;
        private int currentCooldown = 0;
        private boolean compound = true;

        public RouletteSkill(String name, int cooldownTurns) {
            super(name);
            this.cooldownTurns = cooldownTurns;
            this.currentCooldown = 0;
        }

        public void reduceCooldown() {
            if (currentCooldown > 0) currentCooldown--;
        }

        public boolean canUse() {
            return currentCooldown <= 0;
        }

        public void apply(Boss boss) {

            if (canUse()) {

                boss.setAttackPower(temp);
                compound = false;

            }

            if (!compound){
                int go = (int)(Math.random() * 2);
                    if (go == 0){
                        compound = true;
                } else {
                        System.out.println(boss.getName() + " used Roulette! He landed: FAIL! He took damage for his bad bet!");
                        boss.getHealth().setHeealth((int)(boss.getHealth().getHeealth() * 0.90));
                        System.out.println(boss.getHealth().getHeealth());
                    }
            }

            if (compound) {
                boss.setAttackPower((int) (boss.getAttackPower() * 1.2));
                System.out.println(boss.getName() + " used Roulette! He landed: JACKPOT! His power will compound (x1.2) for 3 turns!");
            }

            currentCooldown = cooldownTurns;
        }

        public int getCurrentCooldown(){
            return currentCooldown;
        }
    }

}