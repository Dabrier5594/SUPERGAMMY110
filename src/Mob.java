public class Mob {

    private String name;
    private int attackPower;
    private Health health;

    private boolean isAggro;


        public Mob (String name, Health health, int attackPower, boolean isAggro ){

        this.name = name;
        this.attackPower = attackPower;
        this.health = health;
        this.isAggro = isAggro;


    }

    public void attack(Player player){

        System.out.println(name + " attacks you for " + attackPower + " damage!");

        player.getHealth().takeDamage(attackPower);

    }

    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }

    public boolean isAggro() {
        return isAggro;
    }

}


