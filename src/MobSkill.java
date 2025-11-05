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


}