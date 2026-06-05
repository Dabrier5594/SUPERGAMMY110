import java.util.Map;
import java.util.Scanner;

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

    Scanner scannerdumb = new Scanner(System.in);

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
        private int temp;
        private int cooldownTurns;
        private int currentCooldown = 0;
        private boolean compound = false;

        public RouletteSkill(String name, int cooldownTurns, int attk) {
            super(name);
            this.cooldownTurns = cooldownTurns;
            this.currentCooldown = cooldownTurns;
            temp = attk;
        }

        public void reduceCooldown() {
            currentCooldown--;
        }

        public boolean canUse() {
            return currentCooldown <= 0;
        }

        public void applyR(Boss boss) {

            if (!compound){

                int go = (int)(Math.random() * 2);
                if (go == 0){
                    compound = true;
                    currentCooldown = cooldownTurns;
                } else {
                    System.out.print(boss.getName() + " used Roulette! He landed: FAIL! He got knocked back for his bad bet! ");
                    boss.getHealth().setHeealth((int)(boss.getHealth().getHeealth() * 0.90));
                    System.out.println("Modified health: " + boss.getHealth().getHeealth());

                }
            }

            if (compound) {

                boss.makeAttackPower((int) (boss.getAttackPower() * 1.2));
                if (currentCooldown > 2) {
                    System.out.println(boss.getName() + " used Roulette! He landed: JACKPOT! His power will compound (x1.20) for 3 turns!");
                } else if (currentCooldown < 0){
                    boss.makeAttackPower(temp);
                    currentCooldown = 3;
                    compound = false;
                } else if (currentCooldown == 0){
                    boss.makeAttackPower(temp);
                }

            }

        }

        public int getCurrentCooldown(){
            return currentCooldown;
        }
    }

    public static class FireGuySkill extends MobSkill {
        private int temp;
        private int cooldownTurns;
        private int currentCooldown = 0;
        private boolean compound = false;
        int fireFlameCount = 5;


        public FireGuySkill(String name, int attk) {
            super(name);
            temp = attk;
        }

        public void reduceCooldown() {
            currentCooldown--;
        }

        public boolean canUse() {
            return currentCooldown <= 0;
        }

        public void applyR(Boss boss) {

            System.out.println("All Father Tim has activated his unique skill: FIRE FLAME BOOST");
            System.out.println("In " + fireFlameCount + " turns All Father Tim will unleash FIRE FLAME TYCOON if not neutralized!");
            System.out.println("Oh... You can't neutralize it... UNLESS YOU GUESS HIS TRUE NAME!!!");
            System.out.println("HINT: \nI was blamed for a fall I never caused,\n" +
                    "Celebrated for a discovery I never made.\n" +
                    "I sit on desks, glow in pockets,\n" +
                    "And yet I begin my life in an orchard.\n" +
                    "What am I?");
            System.out.println("-> ");
            String trueName = scannerdumb.next();
            if (trueName.equalsIgnoreCase("apple")){
                System.out.println("NEUTRALIZED!");
            }

        }

        public int getCurrentCooldown(){
            return currentCooldown;
        }
    }

}