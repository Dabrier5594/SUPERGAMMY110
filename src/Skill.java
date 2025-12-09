public abstract class Skill {
    private static String name;
    private String description;
    private boolean usableInCombat;  // true if can be used during combat, false if only outside combat

    public Skill(String name, String description, boolean usableInCombat) {
        this.name = name;
        this.description = description;
        this.usableInCombat = usableInCombat;
    }

    public static String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUsableInCombat() {
        return usableInCombat;
    }

    public abstract void reduceCooldown();

    public static class StunSkill extends Skill {

        private static int cooldownTurns; // number of turns needed to wait after use
        private static int currentCooldown; // tracks cooldown progress

        public StunSkill(String name, String description, boolean usableInCombat, int cooldownTurns) {
            super(name, description, usableInCombat);
            this.cooldownTurns = cooldownTurns;
            this.currentCooldown = 0;
        }

        public void reduceCooldown() {
            if (currentCooldown > 0) {
                currentCooldown--;
            }
        }

        public static boolean canUse() {
            return currentCooldown <= 0;
        }

        public static void apply(Player user, Mob target) {
            if (!canUse()) {
                System.out.println("Skill is recharging for use.");
                return;
            }

            if (target == null || target.getHealth().isDead()) {
                System.out.println("No valid target to stun.");
                return;
            }

            System.out.println(user.getName() + " uses " + getName() + " to stun " + target.getName() + "!");
            target.setStunned(true);
            currentCooldown = cooldownTurns; // reset cooldown
        }

        public static void applyNpc(Player user, Npca target) {
            if (!canUse()) {
                System.out.println("Skill is recharging for use.");
                return;
            }

            if (target == null || target.getHealth().isDead()) {
                System.out.println("No valid target to stun.");
                return;
            }

            System.out.println(user.getName() + " uses " + getName() + " to stun " + target.getName() + "!");
            target.setStunned(true);
            currentCooldown = cooldownTurns; // reset cooldown
        }

        public static void applyGuard(Player user, Guard target) {
            if (!canUse()) {
                System.out.println("Skill is recharging for use.");
                return;
            }

            if (target == null || target.getHealth().isDead()) {
                System.out.println("No valid target to stun.");
                return;
            }

            System.out.println(user.getName() + " uses " + getName() + " to stun " + target.getName() + "!");
            target.setStunned(true);
            currentCooldown = cooldownTurns; // reset cooldown
        }

        public static void applyBoss(Player user, Boss target) {
            if (!canUse()) {
                System.out.println("Skill is recharging for use.");
                return;
            }

            if (target == null || target.getHealth().isDead()) {
                System.out.println("No valid target to stun.");
                return;
            }

            System.out.println(user.getName() + " uses " + getName() + " to stun " + target.getName() + "!");
            target.setStunned(true);
            currentCooldown = cooldownTurns; // reset cooldown
        }

    }

}

