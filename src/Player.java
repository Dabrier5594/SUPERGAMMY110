import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private int easterEggs;

    private String name;

    private String title = "";

    private String titleo = "";

    private String titleoo = "";

    private Health health;

    private int attackPower;

    private boolean isStunned = false;

    private List<Skill> skills = new ArrayList<>();

    public static final Map<String, Quest> QUESTS = new HashMap<>();

    private int coins = 0;

    private int stomachSize = 20;
    private int fullness = 20;

    private boolean inCombat = false;

    private Item meleeSlot;

    private Item helmetSlot;

    private Item chestSlot;

    private Item leggingSlot;

    private Item bootSlot;

    private Item rangedSlot;

    private int ciritcalChance;

    private boolean ownsBoat = false;


    public Player(String name, int maxHealth, int health, int power, int damageResistance, int criticalChance) {

        this.name = name;
        this.health = new Health(maxHealth, health, damageResistance);
        this.attackPower = power;
        this.ciritcalChance = criticalChance;

    }


    public boolean isInCombat() { return inCombat; }

    public void setInCombat(boolean combat) { this.inCombat = combat; }

    public int getCoins(){ return coins; }

    public void addCoins(int a){coins += a;}

    public boolean ownsBoat() {
        return ownsBoat;
    }

    public void setOwnsBoat(boolean ownsBoat) {
        this.ownsBoat = ownsBoat;
    }


    public String getName(){
        return name;
    }

    public List<Skill> getSkills(){
        return skills;
    }

    public void addEasterEggs(){
        easterEggs++;
        if (easterEggs == 1){
            System.out.println("A GOLDEN GLOW EMITS FROM YOUR HEART! [enter to continue]");
            Game.scanner.next();
            System.out.println("What IS this...? [enter to continue]");
            Game.scanner.next();
            System.out.println("It seems you have unlocked one of the WORLD'S greatest secrets... [enter to continue]");
            Game.scanner.next();
            System.out.println("You have been granted TWO THINGS for you success... [enter to continue]");
            Game.scanner.next();
            title = "[THE";
            titleo = "???";
            titleoo = "???]";


            System.out.println("ONE: The world now knows you as " + title + " " + titleo + " " + titleoo + " [enter to continue]");
            Game.scanner.next();
            System.out.println("TWO: PART OF THE FINAL STORY HAS BEEN ADDED TO YOUR BOOK. Type: 'book' to see YOUR story");

        } else if (easterEggs == 2){
            System.out.println("A GOLDEN GLOW EMITS FROM YOUR HEART! [enter to continue]");
            Game.scanner.next();
            System.out.println("What IS this...? [enter to continue]");
            Game.scanner.next();
            System.out.println("It seems you have unlocked one of the WORLD'S greatest secrets... [enter to continue]");
            Game.scanner.next();
            System.out.println("You have been granted TWO THINGS for you success... [enter to continue]");
            Game.scanner.next();
            titleo = "SECRETS";
            System.out.println("ONE: The world now knows you as " + title + " " + titleo + " " + titleoo + " [enter to continue]");
            Game.scanner.next();
            System.out.println("TWO: PART OF THE FINAL STORY HAS BEEN ADDED TO YOUR BOOK. Type: 'book' to see YOUR story");

        } else {

            System.out.println("A GOLDEN GLOW EMITS FROM YOUR HEART! [enter to continue]");
            Game.scanner.next();
            System.out.println("What IS this...? [enter to continue]");
            Game.scanner.next();
            System.out.println("It seems you have unlocked the WORLD'S greatest and FINAL secret... [enter to continue]");
            Game.scanner.next();
            System.out.println("You have been granted TWO THINGS for you success... [enter to continue]");
            Game.scanner.next();
             titleoo = "UNRAVELER]";
            System.out.println("ONE: The world now knows you as " + title + " " + titleo + " " + titleoo + " [enter to continue]");
            Game.scanner.next();
            System.out.println("TWO: PART OF THE FINAL STORY HAS BEEN ADDED TO YOUR BOOK. Type: 'book' to see YOUR story");

        }

        name = name + " " + title + " " +  titleo + " " + titleoo;
    }

    public void seeBook(){
        if (easterEggs == 0){
            System.out.println("YOU HAVE NOT UNLOCKED ANY OF THE WORLDS DARKEST SECRETS. THERE IS NO STORY.");
        } else if (easterEggs == 1){
            System.out.println("YOUR STORY. THE WORLDS STORY.");
            System.out.println("");
            System.out.println("CHAPTER I: THE FALLEN SPRING");
            System.out.println("Vernorexia was eternal spring - mountains piercing endless skies,");
            System.out.println("forests weaving secrets through every leaf, villages alive with song.");
            System.out.println("The Magisterians built havens of stone and light across the land.");
            System.out.println("");
            System.out.println("Then corruption came. Black veins in the soil. Cattle went mad.");
            System.out.println("Trees rotted overnight. Goblins tore from the earth like wounds.");
            System.out.println("Magisterian outposts fell. You fled into endless forest.");
            System.out.println("");
            System.out.println("There, you found Tom's Dark Cave. And darkness took you.");
            System.out.println("");
            System.out.println("*** 2 MORE SECRETS REMAIN ***");

        } else if (easterEggs == 2){
            System.out.println("YOUR STORY. THE WORLDS STORY.");
            System.out.println("");
            System.out.println("CHAPTER I: THE FALLEN SPRING");
            System.out.println("Vernorexia was eternal spring - mountains piercing endless skies,");
            System.out.println("forests weaving secrets through every leaf, villages alive with song.");
            System.out.println("The Magisterians built havens of stone and light across the land.");
            System.out.println("");
            System.out.println("Then corruption came. Black veins in the soil. Cattle went mad.");
            System.out.println("Trees rotted overnight. Goblins tore from the earth like wounds.");
            System.out.println("Magisterian outposts fell. You fled into endless forest.");
            System.out.println("");
            System.out.println("There, you found Tom's Dark Cave. And darkness took you.");
            System.out.println("");
            System.out.println("CHAPTER II: THE ROOTKEEPER'S FAILURE");
            System.out.println("Tom was no hermit. Hidden carvings in his cave walls reveal:");
            System.out.println("He was ROOTKEEPER - last guardian of Vernorexia's heart.");
            System.out.println("For centuries, he sealed 'The First Root' beneath his cave.");
            System.out.println("A pulsating corruption older than the mountains themselves.");
            System.out.println("");
            System.out.println("One night, it broke free. Tom fell to the first goblin it birthed.");
            System.out.println("You awoke to his failure - and inherited his burden.");
            System.out.println("");
            System.out.println("*** 1 FINAL SECRET REMAINS ***");

        } else {
            System.out.println("YOUR STORY. THE WORLDS STORY.");
            System.out.println("");
            System.out.println("CHAPTER I: THE FALLEN SPRING");
            System.out.println("Vernorexia was eternal spring - mountains piercing endless skies,");
            System.out.println("forests weaving secrets through every leaf, villages alive with song.");
            System.out.println("The Magisterians built havens of stone and light across the land.");
            System.out.println("");
            System.out.println("Then corruption came. Black veins in the soil. Cattle went mad.");
            System.out.println("Trees rotted overnight. Goblins tore from the earth like wounds.");
            System.out.println("Magisterian outposts fell. You fled into endless forest.");
            System.out.println("");
            System.out.println("There, you found Tom's Dark Cave. And darkness took you.");
            System.out.println("");
            System.out.println("CHAPTER II: THE ROOTKEEPER'S FAILURE");
            System.out.println("Tom was no hermit. Hidden carvings in his cave walls reveal:");
            System.out.println("He was ROOTKEEPER - last guardian of Vernorexia's heart.");
            System.out.println("For centuries, he sealed 'The First Root' beneath his cave.");
            System.out.println("A pulsating corruption older than the mountains themselves.");
            System.out.println("");
            System.out.println("One night, it broke free. Tom fell to the first goblin it birthed.");
            System.out.println("You awoke to his failure - and inherited his burden.");
            System.out.println("");
            System.out.println("CHAPTER III: THE UNRAVELING");
            System.out.println("The final secret: YOU NEVER LEFT THE HEART.");
            System.out.println("");
            System.out.println("Every step beyond Tom's cave circled back to its pulse.");
            System.out.println("FirstVille? A mirage sustained by the Root's power.");
            System.out.println("The ocean? Its veins reaching for new lands.");
            System.out.println("Third Ville? Built atop its buried spawn-points.");
            System.out.println("");
            System.out.println("The FINAL BOSS never moved. It slumbers where you awoke:");
            System.out.println("TOM'S DARK CAVE - directly beneath the floorboards.");
            System.out.println("");
            System.out.println("Your title echoes through reality itself. The world was");
            System.out.println("never what it seemed. Return to where you began.");
            System.out.println("Dig. Confront the First Root. End the nightmare.");
            System.out.println("");
            System.out.println("🏆 THE CIRCLE CLOSES 🏆");
        }
    }

    public int getAttackPower(){return attackPower;}

    public void setMeleeSlot(Item meleeSlot){this.meleeSlot = meleeSlot;}

    public void setHelmetSlot(Item helmetSlot){this.helmetSlot = helmetSlot;}

    public void setChestSlot(Item chestSlot){this.chestSlot = chestSlot;}

    public void setLeggingSlot(Item leggingSlot){this.leggingSlot = leggingSlot;}

    public void setBootSlot(Item bootSlot){this.bootSlot = bootSlot;}

    public void setRangedSlot(Item rangedSlot){this.rangedSlot = rangedSlot;}

    public Item getMeleeSlot(){return meleeSlot;}

    public Item getHelmetSlot(){return helmetSlot;}

    public Item getChestSlot(){return chestSlot;}

    public Item getLeggingSlot(){return leggingSlot;}

    public Item getBootSlot(){return bootSlot;}

    public Item getRangedSlot(){return rangedSlot;}

    public int getCiritcalChance(){return ciritcalChance;}



    public void attack(Mob mob) {



        if (isStunned == true){
            System.out.println("You are stunned and cannot do anything.");
            setStunned(false);
            return;
        }

        if (mob == null) {
            System.out.println("There is nothing to attack.");
            return;
        }

        System.out.println(name + " attacks " + mob.getName() + " for " + attackPower + " damage!");

        mob.getHealth().mobTakeDamage(attackPower);

    }

    public void attackNpc(Npca npc) {


        if (isStunned == true){
            System.out.println("You are stunned and cannot do anything.");
            setStunned(false);
            return;
        }

        if (npc == null) {
            System.out.println("There is nothing to attack.");
            return;
        }

        System.out.println(name + " attacks " + npc.getName() + " for " + attackPower + " damage!");

        npc.getHealth().mobTakeDamage(attackPower);

    }

    public void attackGuard(Guard npc) {

        if (isStunned == true){
            System.out.println("You are stunned and cannot do anything.");
            setStunned(false);
            return;
        }

        if (npc == null) {
            System.out.println("There is nothing to attack.");
            return;
        }

        System.out.println(name + " attacks " + npc.getName() + " for " + attackPower + " damage!");

        npc.getHealth().mobTakeDamage(attackPower);

    }

    public void attackBoss(Boss npc) {

        if (isStunned == true){
            System.out.println("You are stunned and cannot do anything.");
            setStunned(false);
            return;
        }

        if (npc == null) {
            System.out.println("There is nothing to attack.");
            return;
        }

        System.out.println(name + " attacks " + npc.getName() + " for " + attackPower + " damage!");

        npc.getHealth().mobTakeDamage(attackPower);

    }

    public void takeDamage(int damage) {
        health.takeDamage(damage);
    }

    public void displayStats(Player player, XpLv playerStats) {
        System.out.println("=== Player Stats ===");
        System.out.println("Name: " + name);
        System.out.println("HP: " + health.getHeealth() + "/" + health.getMaxHealth());
        System.out.println("Attack: " + attackPower + " | Defense: " + player.getHealth().getDamageResistance());
        System.out.print("Lv: " + playerStats.getLevel() + " || XP: ");
        System.out.println(playerStats.getXp());
        System.out.println("");
    }

    public Health getHealth() {
        return health;
    }

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

    public int getFullness(){
        return fullness;
    }

    public void addFullness(int a){
        fullness += a;
    }

    public void setFullness(){
        fullness = stomachSize;
    }

    public void lessFullness() {fullness--;}

    public int getStomachSize(){
        return stomachSize;
    }

    public void addStomachSize(int a){
        stomachSize += a;
    }


}