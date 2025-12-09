import java.util.Map;

public abstract class MobSkill {
    private String name;

    public MobSkill(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public static class StunSkill extends MobSkill {

        private int cooldownTurns; // number of turns needed to wait after use
        private int currentCooldown; // tracks cooldown progress

        public StunSkill(String name) {
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

        public void apply(Player player, Mob mob) {
            if (!canUse()) {
                return;
            }

            System.out.println(mob.getName() + " uses " + getName() + " to stun " + player.getName() + "!");
            player.setStunned(true);
            currentCooldown = cooldownTurns; // reset cooldown
        }
    }


    public static class VineSkill extends MobSkill {
        private static Item takeItem;
        private static int cooldownTurns; // number of turns needed to wait after use
        private static int currentCooldown; // tracks cooldown progress

        public VineSkill(String name, int cooldownTurns) {
            super(name);
            this.cooldownTurns = cooldownTurns;
            this.currentCooldown = 0;
        }

        public void reduceCooldown() {
            if (currentCooldown > 0) currentCooldown--;
        }

        public static boolean canUse() {
            return currentCooldown <= 0;
        }

        public static void apply(Player player, Boss boss, Map<String, Item> equippedItems, Equipment equipment) {
            if (!canUse()) {
                if(equipment.getItemBasedOnSlot("melee",equippedItems ) == null) {
                    equipment.equip(takeItem, player);
                    return;
                }
            }

            System.out.println(boss.getName() + " uses  VineSkill to take " + player.getName() + "'s weapon!");
            Item thing = new Item("null", "null", false);

            thing = equipment.getItemBasedOnSlot("melee",equippedItems);
            equipment.unequip("melee", thing, player);
            player.setAttackPower(player.getAttackPower()/2);
            takeItem = thing;

            currentCooldown = cooldownTurns; // reset cooldown

        }


    }
}