public class Health {

    private int maxHealth;
    private int health;
    private int damageResistance;

    public Health(int maxHealth, int health, int damageResistance) {

        this.health = health;
        this.maxHealth = maxHealth;
        this.damageResistance = damageResistance;

    }

    public int getHeealth() {
        return health;
    }

    public void setHeealth(int amount) { this.health = amount;}

    public int getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(int amount) {
        this.maxHealth = amount;
    }

    public int getDamageResistance(){
        return damageResistance;
    }

    public void setDamageResistance(int amount) {
        this.damageResistance = amount;
    }

    public void takeDamage(int amount){

        int finalDamage = amount - damageResistance;

        if (damageResistance > 0){
            System.out.println("The damage has been muffled by " + damageResistance + " due to damage resistance.");
        }

        if (finalDamage < 0){
            finalDamage = 0;
            System.out.println("Your defenses are too strong for the puny attack!");
        }

        if (health - finalDamage > 0) {

            health -= finalDamage;

            System.out.println("You have taken " + finalDamage + " damage (" + health + "/" + maxHealth + " HP remaining).");

        }

        else {
            health = 0;
            System.out.println("You have taken " + finalDamage + " damage (0/" + maxHealth + ") HP remaining).");
        }


    }

    public void mobTakeDamage(int amount){


        int finalDamage = amount - damageResistance;

        if (damageResistance > 1){
            System.out.println("The damage has been muffled by " + damageResistance + " due to damage resistance.");
        }


        if (finalDamage < 0){
            finalDamage = 0;
            System.out.println("The enemy's defenses are too strong for your puny attack!");
        }

        if (health - finalDamage > 0) {

            health -= finalDamage;

            System.out.println("The enemy has taken " + finalDamage + " damage (" + health + "/" + maxHealth + " HP remaining).");

        }

        else {
            health = 0;
            System.out.println("The enemy has taken " + finalDamage + " damage (0/" + maxHealth + ") HP remaining).");
        }


    }

    public void mobTakeBonusDamage(int amount){


        int finalDamage = amount;

        if (health - finalDamage > 0) {

            health -= finalDamage;

            System.out.println("(" + health + "/" + maxHealth + " HP remaining).");

        }

        else {
            health = 0;
            System.out.println("The enemy has taken " + finalDamage + " damage (0/" + maxHealth + ") HP remaining).");
        }


    }

    public void heal(int amount) {

        health += amount;
        if (health > maxHealth){
            health = maxHealth;
            System.out.println("You have recovered the max amount possible. HP: " + health + "/" + maxHealth);

        }

        else {

            System.out.println("You have recovered " + amount + "HP (" + health + "/" + maxHealth);
        }
    }


    public boolean isDead() {
        return health <= 0;
    }

    public void displayHealth(){
        System.out.println("Health: " + health + "/" + maxHealth);
    }




}
