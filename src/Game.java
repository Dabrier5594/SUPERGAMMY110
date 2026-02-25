import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;import java.util.Timer;
import java.util.TimerTask;

/// (a, b) -> a + b [-> is basically a lamba that splits stuff, in other words, it tells the code where parameters end and code begins.]


///NOTE TO EVERYONE!!
/// WHENEVER YOU CREATE ANYTHING (ITEM, NPC, ANYTHING that can be INTERACTED with), YOU MUST ADD IT TO OBJECTS!!!
/// FOR ITEMS: AFTER YOU MAKE THEM. YOU MUST ADD THEM TO EXISTINGITEMS LIST. (SEE WHERE I MADE ALL THE ITEMS w/ "new Item" in search

/// TIPS:
/// 'admin0' as name gives you cheats || 'monty python' as name allows you to play game as normal, but death barely affects you...
/// 'jack sparrow' as name gives you money a scuba mask and such || 'one punch man' as name allows you to play as normal, but you feel almighty...
/// 'wizard' as name allows you to play as normal, but you get wizard goodies || 'cheapo' gives you 1 DAMAGE and 10000 HP

/// ADDS ( TODO LIST )
// Add NPCS to FirstVille, some with quests.
// Add HOMES.
// make it so that scrolls work on people as well
// Fill in things, like the shopper bag, and just make more items. add a bakery and a stove and stuff. Add quests to the guild. And just items into the STREETS
// UPDATE THE COMMANDS LIST & UPDATE ROOM DESCRIPTIONS

// COMPLETE THE PLAINS AND SECOND AND OCEAN (THIRD ONLY IF TIME ALLOWS)

// Make it so Zak can give you the ability to enter into the army/solidery/minion thing
// ADD EASTER EGGS ( like a quest from lagger where he gifts you a relic, and then use the command addEasterEgg )
// Add something the first ville cell, also, make the key attainable
// AND MORE SKILLS!

/// QUEST IDEAS:
// find all the darumas in the village!
// Deliver a leaflet to Bagger
// Find a ball for a kid
// Gather materials and craft some food

public class Game {

    public static Hub.FirstVilleShop baggerShop;
    public static Hub.Inn firstVilleInn;

    //DESCRIPTIONS:

    public static final Map<String, String> INSPECT_DESCRIPTIONS = new HashMap<>();
    public static final List<KeyFigureSpawn> KEY_FIGURES = new ArrayList<>();


    static {
        INSPECT_DESCRIPTIONS.put("dagger", "An old weathered dagger.");
        INSPECT_DESCRIPTIONS.put("leather armor", "Light leather armor. Won't protect you much but it's better than walking into a goblin nest naked.");
        INSPECT_DESCRIPTIONS.put("thornshield", "A shield covered in thorns. Once enemies attack you they take some damage they dealt.");
        INSPECT_DESCRIPTIONS.put("copper", "A dull copper coin. 10 make 1 silver.");
        INSPECT_DESCRIPTIONS.put("silver", "A silver coin. 5 make 1 gold.");
        INSPECT_DESCRIPTIONS.put("gold", "A shiny gold coin.");
        INSPECT_DESCRIPTIONS.put("cabinet", "An old cabinet. Useful for storing things.");
        INSPECT_DESCRIPTIONS.put("leaflet", "A tattered, worn out piece of paper. Most of the text is weathered away.");
        INSPECT_DESCRIPTIONS.put("twig", "Just a twig. Useful for starting fires.");
        INSPECT_DESCRIPTIONS.put("snarkflower", "A snarkflower. Smells of ozone, and something floral.");
        INSPECT_DESCRIPTIONS.put("door", "A door made of sturdy wood.");
        INSPECT_DESCRIPTIONS.put("rabbit", "A small rabbit wandering through the brush. It's refreshing to see life around.");
        INSPECT_DESCRIPTIONS.put("chicken", "A wild chicken. Rare to see these anymore.");
        INSPECT_DESCRIPTIONS.put("squirrel", "A squirrel, you watch it run around for a moment.");
        INSPECT_DESCRIPTIONS.put("goblin", "A dubious goblin, short and green, a small dagger in hand.");
        INSPECT_DESCRIPTIONS.put("chest", "A heavy chest. The lid feels nailed shut.");
        INSPECT_DESCRIPTIONS.put("key", "A small key. It could probably fit in a chest.");
        INSPECT_DESCRIPTIONS.put("white whispberry", "A whispberry. Smells sweet and of hiraeth, reminding you of the morning dew.");
        INSPECT_DESCRIPTIONS.put("bagger", "It's Bagger. Laggers brother-in-law, Jaggers brother, and Raggers cousin.");
        INSPECT_DESCRIPTIONS.put("ragger", "It's Ragger. Laggers cousin-in-law, Jaggers cousin, and Baggers cousin.");
        INSPECT_DESCRIPTIONS.put("gate", "A large metal gate leading to the inside of Firstville.");
        INSPECT_DESCRIPTIONS.put("firstville guards plate", "A heavy steel plate. Will protect you from damage and could work as a disguise.");
        INSPECT_DESCRIPTIONS.put("firstville guards helm", "A heavy steel helm. Will protect you from damage and could work as a disguise.");
        INSPECT_DESCRIPTIONS.put("firstville guards boots", "Heavy steel boots. Will protect you from damage and could work as a disguise.");
        INSPECT_DESCRIPTIONS.put("firstville guards legs", "Heavy steel greaves. Will protect you from damage and could work as a disguise.");
        INSPECT_DESCRIPTIONS.put("inventory", "A leather satchel full of all your items.");
        //continue from here
        INSPECT_DESCRIPTIONS.put("admin sword", "A legendary blade glowing with unnatural power. Clearly not meant for mortals.");
        INSPECT_DESCRIPTIONS.put("forest devil", "A twisted forest guardian with eyes like burning coals. The woods themselves seem to fear it.");
        INSPECT_DESCRIPTIONS.put("oliver", "A stern guard with a merchant's cunning. His armor gleams, but his eyes are sharper.");
        INSPECT_DESCRIPTIONS.put("zak", "The captain of the guard, built like a walking fortress. His polished muscles gleam as proudly as his armor—and he clearly knows it.");
        INSPECT_DESCRIPTIONS.put("trap", "A crude but effective snare. Rusty spikes and pressure plates promise pain to the unwary.");
        INSPECT_DESCRIPTIONS.put("bear claw", "A massive claw from a fearsome bear. Still faintly warm, stained with old blood.");
        INSPECT_DESCRIPTIONS.put("bear essence", "A mystical extract from a Mama Bear's core, can be used in crafting jewelery, or sold! Smells faintly of musk and wilderness.");
        INSPECT_DESCRIPTIONS.put("wolfbane", "Toxic purple flowers that could be used as an ingredient.");
        INSPECT_DESCRIPTIONS.put("raven eye", "A polished black gem that seems to watch you. Perfect for crafting.");
        INSPECT_DESCRIPTIONS.put("blood vial", "A small glass vial filled with thick, dark blood. Still warm—what kind of creature bled this? A handy ingredient.");
        INSPECT_DESCRIPTIONS.put("cauldron", "A dented iron pot bubbling with someones failed creation.");
        INSPECT_DESCRIPTIONS.put("tree", "A crooked looking tree which has been through a lot, although red orbs seem to be peaking through the dried leaves.");


    }

    public static final Map<String, String> COMBAT_DESCRIPTIONS = new HashMap<>();

    // COMBAT DESCRIPTIONS
    static {
        // --- Goblin ---
        COMBAT_DESCRIPTIONS.put("goblin_attack", "The goblin lunges with a rusty dagger, screeching for your blood!");
        COMBAT_DESCRIPTIONS.put("goblin_death", "The goblin collapses with a guttural cry.");

        // --- Rabbit ---
        COMBAT_DESCRIPTIONS.put("rabbit_attack", "The rabbit charges you head-on. It’s… strangely fierce?");
        COMBAT_DESCRIPTIONS.put("rabbit_death", "The rabbit lies still. You can’t help but feel a little guilty.");

        // --- Chicken ---
        COMBAT_DESCRIPTIONS.put("chicken_attack", "The chicken flaps and pecks wildly, a blur of feathers and rage.");
        COMBAT_DESCRIPTIONS.put("chicken_death", "The chicken topples over dramatically. Dinner, anyone?");

        // --- Squirrel ---
        COMBAT_DESCRIPTIONS.put("squirrel_attack", "The squirrel leaps onto your shoulder, scratching like mad!");
        COMBAT_DESCRIPTIONS.put("squirrel_death", "The squirrel collapses, tiny claws still clutching a nut.");

        // --- Forest Boss ---
        COMBAT_DESCRIPTIONS.put("forest_boss_attack", "The Forest Boss roars, slamming a root-covered fist into the ground as vines lash toward you!");
        COMBAT_DESCRIPTIONS.put("forest_boss_defend", "You duck as thorned branches whip past—each one sharp enough to tear armor apart.");
        COMBAT_DESCRIPTIONS.put("forest_boss_death", "The beast lets out a shrill, echoing cry before collapsing. The forest falls eerily silent.");

        // --- Gate Guardian ---
        COMBAT_DESCRIPTIONS.put("gate_guardian_attack", "The massive guardian raises a stone blade, bringing it down with earth-shaking force!");
        COMBAT_DESCRIPTIONS.put("gate_guardian_defend", "Your weapon bounces off the guardian’s iron plating—it barely notices.");
        COMBAT_DESCRIPTIONS.put("gate_guardian_death", "Cracks spread across the guardian’s body before it crumbles into rubble.");

        // --- Corrupted Knight ---
        COMBAT_DESCRIPTIONS.put("corrupted_knight_attack", "The corrupted knight swings in a brutal arc, his armor groaning as midnight light trails behind his blade!");
        COMBAT_DESCRIPTIONS.put("corrupted_knight_defend", "You parry his dark blade. The shock runs through your arms like ice.");
        COMBAT_DESCRIPTIONS.put("corrupted_knight_death", "The knight falls to one knee. As the darkness leaves his eyes, he whispers, 'Free... at last.'");

        // --- Ancient Spirit ---
        COMBAT_DESCRIPTIONS.put("ancient_spirit_attack", "The air chills as the spirit’s ethereal claw swipes through your defenses.");
        COMBAT_DESCRIPTIONS.put("ancient_spirit_defend", "Your weapon passes through mist. The spirit reforms behind you silently.");
        COMBAT_DESCRIPTIONS.put("ancient_spirit_death", "A sigh echoes as the spirit fades, scattering into faint motes of light.");

        // --- Thornbeast ---
        COMBAT_DESCRIPTIONS.put("thornbeast_attack", "The Thornbeast lunges, its spiked limbs tearing through the air!");
        COMBAT_DESCRIPTIONS.put("thornbeast_defend", "You roll aside as vines whip dangerously close.");
        COMBAT_DESCRIPTIONS.put("thornbeast_death", "The creature falls apart into a pile of withered vines and thorns.");
    }

    // OTHER CLASS SCOPE VARIABLES!


    public static volatile Hub boatLocation;

    public static volatile boolean onBoat = false;

    public static volatile boolean scannerOrNo = false; // true = GO FOR IT, false = HOLD INFO

    public static volatile boolean timeOfDay = true; // true = day, false = night

    public static volatile String timeChange = null; // day or night

    public static volatile LocalTime changeTime = null; //when above change happened

    public static volatile boolean fighting = false; // FALSE when not in combat, true otherwise

    public static volatile boolean talking = false; // true when user communicates to other things

    public static volatile int applesFallen = 0; // how many apples you've collected from a tree

    public static volatile boolean treeReset = true; // If you can get apples from a tree or not

    public static volatile boolean cabinetDaggerCaveN = true; // If you've tried to open the first cabinet (tutorial)

    public static volatile int nightCounter = 0; // How many nights its been

    //RESPAWNING MOBS:
    public static void setupRespawnScheduler() {
        try {
            long threeMinutes = 3 * 60 * 1000;
            Timer respawnTimer = new Timer(true);
            respawnTimer.scheduleAtFixedRate(new RespawnKeyThings(), threeMinutes, threeMinutes);
        } catch (Exception e) {
        }
    }


    public static class KeyFigureSpawn {
        public final String name;
        public final Hub hub;
        public final Runnable spawner;
        public KeyFigureSpawn(String name, Hub hub, Runnable spawner) {
            this.name = name;
            this.hub = hub;
            this.spawner = spawner;
        }
    }

    static class RespawnKeyThings extends TimerTask {
        @Override
        public void run() {

            if (KEY_FIGURES == null || KEY_FIGURES.isEmpty()) {
                return;
            }

            if (Game.fighting) return;

            for (KeyFigureSpawn kf : KEY_FIGURES) {

                if (kf == null || kf.hub == null || kf.spawner == null) {
                    continue;
                }

                boolean present = false;

                if (kf.hub.getNpc() != null) {
                    for (Npca npc : kf.hub.getNpc()) {
                        if (npc != null && npc.getName() != null &&
                                npc.getName().equalsIgnoreCase(kf.name)) {
                            present = true;
                            break;
                        }
                    }
                }

                if (!present && kf.hub.getGuard() != null) {
                    for (Guard g : kf.hub.getGuard()) {
                        if (g != null && g.getName() != null &&
                                g.getName().equalsIgnoreCase(kf.name)) {
                            present = true;
                            break;
                        }
                    }
                }

                if (!present && kf.hub.getBoss() != null) {
                    for (Boss b : kf.hub.getBoss()) {
                        if (b != null && b.getName() != null &&
                                b.getName().equalsIgnoreCase(kf.name)) {
                            present = true;
                            break;
                        }
                    }
                }

                if (!present) {
                    try {
                        kf.spawner.run();
                    } catch (Exception e) {
                        System.out.println("Failed to respawn NPCS");
                    }
                }
            }
        }
    }


    public static void setupDayNightSchedulers() {

        long threeMinutes = 3 * 60 * 1000;  // 3 minutes in ms

        Timer dayTimer = new Timer();
        dayTimer.scheduleAtFixedRate(new TimeDayNotifier(), threeMinutes, threeMinutes);

    }

    public static void movingTheMobs() {

        long oneMin = 88 * 1000;  // 1  minutes in ms

        Timer dayTimer = new Timer();
        dayTimer.scheduleAtFixedRate(new MoveMob(), oneMin, oneMin);

    }

    public static void setupHealthRegen(Player player) {
        long threeSeconds = 3 * 1000;
        Timer healthTimer = new Timer(true);

        healthTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Skip healing during combat
                if (fighting) {
                    return;
                }

                if (player.getHealth().getHeealth() < player.getHealth().getMaxHealth()) {
                    player.getHealth().setHeealth(player.getHealth().getHeealth() + 1);
                }
            }
        }, threeSeconds, threeSeconds); //Can start using this for other timers too (add',' and connect the rest)
    }


    static class TimeDayNotifier extends TimerTask {
        @Override
        public void run() {

            if (!scannerOrNo || fighting || talking) { // SCANNER IS ACTIVE - HOLD LATEST CHANGES
                timeChange = timeOfDay ? "day" : "night";
                changeTime = LocalTime.now();
                timeOfDay = !timeOfDay;
                treeReset = true;

            } else {

                System.out.println("It is turning " + (timeOfDay ? "day" : "night") + "...");

                timeOfDay = !timeOfDay;
                treeReset = true;


            }

        }
    }

    static class MoveMob extends TimerTask {
        @Override
        public void run() {

            if (!fighting) {
                for (Hub hub : Hub.getAllHubs()) {
                    for (Mob mob : new ArrayList<>(hub.getMOBS())) {
                        int a = (int) (Math.random() * 2 + 1);

                        if (a == 1) {

                        } else {

                            List<String> exits = new ArrayList<>();

                            for (String dir : Arrays.asList("n", "s", "e", "w")) {

                                if (hub.getExit(dir) != null) {

                                    exits.add(dir);

                                }
                            }

                            Random rand = new Random();

                            String randomString = exits.get(rand.nextInt(exits.size()));

                            Hub nextRoom = hub.getExit(randomString);

                            if (!nextRoom.getRoomName().equalsIgnoreCase("Abandon House's Entrance") && !nextRoom.getRoomName().equalsIgnoreCase("Tom's Dark Entrance")) {
                                nextRoom.getMOBS().add(mob);
                                hub.getMOBS().remove(mob);
                            }

                        }
                    }
                }
            }

        }
    }

    //LEAFLETS
    public static final Map<String, Leaflet> LEAFLETS = new HashMap<>();

    static {
        LEAFLETS.put("leaflet001",
                new Leaflet("leaflet001", "leaflet",
                        "Welcome to the world of Tim", "This world is one which mysteries and secrets about itself. \nPrepare yourself, to delve deeper into the world of Tim and experience the un-imaginable. \nHint: (Use 'help' to get help)"));

        LEAFLETS.put("leaflet002",
                new Leaflet("leaflet002", "leaflet",
                        "The Money Challenges", "The world is in a money crisis, and people are going on more dangerous quests to get more money. \nOne gold is 5 silver, 1 silver is 10 copper. \nHint: (More dangerous quests make more money! But they are also much harder to complete...)"));

        LEAFLETS.put("leaflet003",
                new Leaflet("leaflet003", "leaflet",
                        "Trump gives grudging praise to liberal trio who helped sink his tariffs", "President Donald Trump grudgingly praised the trio of liberal justices on the U.S. Supreme Court who helped block his use of an emergency law to impose sweeping tariffs on trading partners, pointing to their \"loyalty\" to Democrats. \n" +
                        "\n" +
                        "\"The Democrats on the court are thrilled, but they will automatically vote no,\" Trump said Friday during a press conference. \"They're an automatic no. Just like in Congress, they're an automatic no. They're against anything that makes America strong, healthy and great again. They also are frankly a disgrace to our nation. Those justices, they're an automatic no. No matter how good a case you have. It's a no.\"\n" +
                        "\n" +
                        "\"You can't knock their loyalty. It's one thing you can do with some of our people,\" he said. \n" +
                        "\n" +
                        "\n" +
                        "DEMOCRATS CHEER SUPREME COURT MOVE BLOCKING TRUMP TARIFFS — DESPITE PAST SUPPORT FOR TRADE DUTIES\n" +
                        "\n" +
                        "Trump at tariff press conference \n" +
                        "President Donald Trump grudgingly praised the trio of liberal justices on the U.S. Supreme Court who helped block his use of an emergency law to impose sweeping tariffs on trading partners.  (Mandel Ngan/Getty Images)\n" +
                        "\n" +
                        "The Court’s three liberal justices are Sonia Sotomayor, Elena Kagan and Ketanji Brown Jackson.\n" +
                        "\n" +
                        "Trump held a press conference at the White House Friday, just hours after the Supreme Court ruled that Trump exceeded his authority by imposing sweeping tariffs using emergency powers. The decision was a 6-3 ruling, with conservative Justices Brett Kavanaugh, Clarence Thomas and Samuel Alito dissenting. \n" +
                        "\n" +
                        "Trump praised the three conservatives who dissented.\n" +
                        "\n" +
                        "\"I'd like to thank and congratulate Justices Thomas, Alito and Kavanaugh for their strength and wisdom and love of our country, which is right now very proud of those justices,\" he said. \"When you read the dissenting opinions, there's no way that anyone can argue against them.\" \n" +
                        "\n" +
                        "Kavanaugh authored the main dissent, which argued that presidents have \"commonly\" imposed tariffs to regulate imports. He also warned there would be \"serious practical consequences\" over the high court's decision in terms of refunding the imposed tariffs. \n" +
                        "\n" +
                        "\"The United States may be required to refund billions of dollars to importers who paid the IEEPA tariffs, even though some importers may have already passed on costs to consumers or others,\" Kavanaugh wrote. \"As was acknowledged at oral argument, the refund process is likely to be a ‘mess.’\"\n" +
                        "\n" +
                        "placeholder\n" +
                        "‘TARIFFS SUCK’: SOME REPUBLICANS PRIVATELY CELEBRATE AS SUPREME COURT BLOCKS TRUMP POLICY\n" +
                        "\n" +
                        "President Donald Trump holding a poster of his administration's reciprocal tariffs.\n" +
                        "Trump held a press conference at the White House Friday, just hours after the Supreme Court ruled that Trump exceeded his authority by imposing sweeping tariffs using emergency powers. (Chip Somodevilla/Getty Images)\n" +
                        "\n" +
                        "Trump had promoted tariffs under emergency orders as a key piece of his policy related to the economy, arguing tariffs would usher in a flood of revenue from foreign nations that had been \"ripping off\" the U.S. for decades. \n" +
                        "\n" +
                        "Tariffs also were leveraged to attract foreign businesses, as well as U.S. companies, to open up shop on U.S. soil to avoid the taxes, while simultaneously bolstering U.S. jobs and manufacturing. \n" +
                        "\n" +
                        "AS TRUMP TOUTS TARIFF WINDFALL, BATTLEGROUND STATES SHOULDER BILLIONS IN COSTS\n" +
                        "\n" +
                        "Supreme Court exterior during daytime\n" +
                        "Trump praised the three conservatives who dissented. (J. Scott Applewhite, File/The Associated Press )\n" +
                        "\n" +
                        "Trump continued that the ruling \"did not overrule tariffs, but \"merely overruled a particular use of IEEPA tariffs.\" \n" +
                        "\n" +
                        "The International Emergency Economic Powers Act is a 1977 emergency powers law that lets a president, after declaring a national emergency tied to a foreign threat, restrict or regulate certain international economic transactions — most commonly to impose sanctions.\n" +
                        "\n" +
                        "Trump relied on IEEPA as the legal basis for the tariffs the Court struck down, ruling that the statute does not authorize tariffs.\n" +
                        "\n" +
                        "\n" +
                        "As a first step following the decision, Trump announced a 10% global tariff while touting \"other alternatives\" separated from IEEPA will be used to impose tariffs on foreign nations. \n" +
                        "\n" +
                        "CLICK HERE TO DOWNLOAD THE FOX NEWS APP \n" +
                        "\n" +
                        "\"Other alternatives will now be used to replace the ones that the court incorrectly rejected,\" Trump said. \"We have alternatives. Great alternatives. Could be more money. We'll take in more money, and we'll be a lot stronger for it. We're taking in hundreds of billions of dollars. We'll continue to do so.\"\n" +
                        "\n" +
                        "\"Today I will sign an order to impose a 10% global tariff under section 122 over and above our normal tariffs already being charged,\" Trump said. \"And we're also initiating several section 301 and other investigations to protect our country from unfair trading practices of other countries and companies.\" \n. \nHint: FUCK YOU JOHAN"));

    }


    //CHEST TOKENS

    public static final Map<String, Token> TOKENS = new HashMap<>();

    static {
        TOKENS.put("KEY001",
                new Token(001, "KEY"));

        TOKENS.put("KEY002",
                new Token(002, "KEY"));

        TOKENS.put("KEY003",
                new Token(003, "KEY"));

    }

    //MERCHANTS!!!
    //Ragger
    static String[] raggerLines = {
            "Welcome, traveler. Looking to buy or sell?",  // index 0
            "I only deal in quality goods, sucker.",               // index 1
            "Come back if you change your mind."           // index 2
    };

    public static Map<String, Integer> raggerStock = new HashMap<>();

    static {
        raggerStock.put("Iron Sword", 50);
        raggerStock.put("Health Potion", 20);
        raggerStock.put("Leather Armor", 80);
    }

    static Health raggerH = new Health(180, 180, 0);

    //GUARD!!
    //Oliver
    static String[] oliverLines = {
            "Wow! I have never seen anybody come from that forest before!",  // index 0
            "Unfortunately, you can only pass this gate to FirstVille with a guard pass or by being a guard...",               // index 1
            "Would you like to complete a quest for me in return for a guard pass?",           // index 2
            "That's too bad. Come back if you change your mind!"

    };

    static Health oliverP = new Health(60, 60, 0);

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //QUESTS

        Player.QUESTS.put("MQ1", new Quest("MQ1", "Leave The Cave", 3, "Southern Forest Area #1", 1, 25, 5));  // main quest - go outside cave
        Player.QUESTS.put("SQ1", new Quest("SQ1", "Kill Goblins", 1, "goblin", 3, 40, 15));                 // side quest - kill 3 goblins
        Player.QUESTS.put("SQ2", new Quest("SQ2", "Collect Twigs", 2, "twig", 5, 30, 10));                  // side quest - get 5 twigs
        Player.QUESTS.put("SQ3", new Quest("SQ3", "Breach the Southern Forest's Abandon House's Upper Floor...", 3, "Abandon House's Upper Room", 1, 80, 25)); // side quest - go upstairs

        // TOMS CAVE

        Hub cave = new Hub("Tom's Dark Cave", "The back of the ancient cave where Tom the hermit lived for many years. \nEXITS: (N) ");

        Hub caveN = new Hub("Tom's Dark Kitchen", "Still in Tom's cave, but now you have moved into his kitchen which consists of cabinet, the door always slightly ajar. \nEXITS: (N) (S)");

        Hub caveNN = new Hub("Tom's Dark Treasure Room", "Still in Tom's cave, but now you have moved into his treasure room which has no furniture. \nEXITS: (W) (E) (S)");

        Hub caveNE = new Hub("Tom's Dark Living Room", "Still in Tom's cave, but now you have moved into his living room which. \nEXITS: (W)");

        Hub caveNW = new Hub("Tom's Dark Entrance", "Still in Tom's cave, but now you have moved to the cave's entrance, where a door stands. \nEXITS: (N) (E)");

        // S FOREST

        Hub forest1 = new Hub("Southern Forest Area #1", "Just outside Tom's cave and just inside The Southern Area of the Great Makiss Forest. An ugly tree sways in the wind. \nEXITS: (S) (N) ");
        forest1.setStructure("tree");

        Hub forest2 = new Hub("Southern Forest Area #2", "The Southern Area of the Great Makiss Forest. \nOnly minor prey lay in wait in this forest. \nEXITS: (S) (W) ");

        Hub forest4 = new Hub("Southern Forest Area #4", "The Southern Area of the Great Makiss Forest. \nThe air feels heavy here, and the forest is unusually silent. The ground is littered with broken twigs, as though something recently passed through... \nEXITS: (E) (W)");

        Hub forest3 = new Hub("Southern Forest Area #3", "The Southern Area of the Great Makiss Forest. \nThorny bushes block much of the way south, their barbs glinting sharply. However, the path seems clearer to the north, where faint sunlight filters through. \nEXITS: (E) (W)");

        Hub forest5 = new Hub("Southern Forest Area #5", "The Southern Area of the Great Makiss Forest. \nReminiscence of something eating a chicken is visible under scattered leaves...\nEXITS: (E) (N)");

        Hub forest6 = new Hub("Southern Forest Area #6", "The Southern Area of the Great Makiss Forest. \nThe trees grow apart here, their canopies still blotting out much of the sunlight. The air feels damp, and the dull chest#001 sits quietly in the middle of the area... \nEXITS: (S) (N) (W)");

        Hub forest7 = new Hub("Southern Forest Area #7", "The Southern Area of the Great Makiss Forest. \nSmells like bear blood... Clue for bear hunt. One that must have ended poorly for the Poacher. A tree sways in the wind. \nEXITS: (S) (W)");
        forest7.setStructure("tree");

        Hub forest8 = new Hub("Southern Forest Area #8", "The Southern Area of the Great Makiss Forest. \nThe chirping of birds fills the air as soft beams of light break through the leaves. The ground here is softer, marked by faint paw prints. \nEXITS: (E) (S)");

        Hub forest9 = new Hub("Southern Forest Area #9", "The Southern Area of the Great Makiss Forest. \nA clearing opens up, surrounded by mossy boulders. A faint breeze carries the scent of pine and damp earth. Something moves just beyond sight... \nEXITS: (N) (E) (W) ");

        Hub forest10 = new Hub("Southern Forest Area #10", "The Southern Area of the Great Makiss Forest. \nA wooden goblin watchtower overlooks the dense woods, faint sounds echo from its guarded platform. It seems something has been eating bears...\nEXITS: (E) (W)");

        Hub forest11 = new Hub("Southern Forest Area #11", "The Southern Area of the Great Makiss Forest. \nA goblin camp with crudely made tents surrounds a roaring fire, shadows flickering with restless movement. \nEXITS: (E) (N)");

        Hub forest12 = new Hub("Southern Forest Area #12", "The Southern Area of the Great Makiss Forest. \nAn abandoned house overrun with vines and moss, the air heavy with age and forgotten stories. The house's door is made of heavy steel. \nEXITS: (S) (N) (W)");

        Hub forest13 = new Hub("Southern Forest Area #13", "The Southern Area of the Great Makiss Forest. \nAn open clearing dotted with wildflowers and a calm, shallow pond reflecting the sky above.\nEXITS: (S) (E)");


        //S HOUSE ROOMS
        Hub sHouseEntrance = new Hub("Abandon House's Entrance", "Inside an abandon house located in the Southern part of the forest. \nThe scent of damp wood and dust fills the air, and every creak of the old floorboards echoes through the empty house.\nEXITS: (E) (N) (S)");

        Hub sHouseEntranceLeft = new Hub("Abandon House's Southern Room", "Inside an abandon house located in the Southern part of the forest. \nTorn wallpaper hangs loosely, and a broken chair rests in the corner. The faint sound of wind howling through unseen cracks adds an eerie rhythm.\nEXITS: (N)");

        Hub sHouseEntranceRight = new Hub("Abandon House's Northern Room", "Inside an abandon house located in the Southern part of the forest. \nThe northern room is slightly larger, packed with dust-covered furniture and an old fireplace filled with cold ashes. \nEXITS: (S) (W)");

        Hub sHouseHallway = new Hub("Abandon House's Hallway", "Inside an abandon house located in the Southern part of the forest. \nA faint draft snakes through cracks in the walls. A closed wooden door separates the hallway from a nearby room to the West.\nEXITS: (E) (S)");

        Hub sHouseRoom = new Hub("Abandon House's Suite Room", "Inside an abandon house located in the Southern part of the forest. \nThis was once a grand suite, now fallen to decay.\nEXITS: (N) (S)");

        Hub sHouseStairs = new Hub("Abandon House's Staircase", "Inside an abandon house located in the Southern part of the forest. \nThe narrow staircase ascends with a groan at every step. Dust dances in the beams of pale light filtering from above.\nEXITS: (N) (U)");

        Hub sHouseUpper = new Hub("Abandon House's Upper Room", "Inside an abandon house located in the Southern part of the forest. \nThe upper room smells of mildew and forgotten time. A rusty chest sits in the corner...\nEXITS: (D)");

        Hub forest14 = new Hub("Southern Forest Area #14", "The Southern Area of the Great Makiss Forest. \nDense thickets and thorn bushes threaten to slow travelers, whispering secrets among the branches. \nEXITS: (W) (N)");

        Hub forest15 = new Hub("Southern Forest Area #15", "The Southern Area of the Great Makiss Forest. \nA hidden hideout for  rouge bandits with makeshift barricades. \nEXITS: (S) (E)");

        Hub forest16 = new Hub("Southern Forest Area #16", "The Southern Area of the Great Makiss Forest. \n. \nEXITS: (W) (E) (N)");

        Hub forest17 = new Hub("Southern Forest Area #17", "The Southern Area of the Great Makiss Forest. \nA narrow forest path winds past towering trees and thick underbrush, shadows flickering with every breeze. \nEXITS: (N) (S)");

        Hub forest18 = new Hub("Southern Forest Area #18", "The Southern Area of the Great Makiss Forest. \nA moss-covered cave entrance yawns before you, hinting at mysteries hidden within. \nEXITS: (E) (W)");

        Hub forest19 = new Hub("Southern Forest Area #19", "The Southern Area of the Great Makiss Forest. \nA damp underground tunnel echoes faint droplets, its darkness inviting caution. \nEXITS: (E) (W) (N)");

        Hub forest21 = new Hub("Southern Forest Area #21", "The Southern Area of the Great Makiss Forest. \nA fallen log forms a bridge over a burbling stream, surrounded by vibrant greenery. \nEXITS: (E) (W)");

        Hub forest22 = new Hub("Southern Forest Area #22", "The Southern Area of the Great Makiss Forest. \nA colossal old oak tree hosts nests of owls, its branches creaking with nocturnal life. \nEXITS: (W) (E)");

        Hub forest23 = new Hub("Southern Forest Area #23", "The Southern Area of the Great Makiss Forest. \nA quaint beekeeper’s shack buzzes with activity, hives hung carefully nearby. \nEXITS: (S) ");

        Hub forest24 = new Hub("Southern Forest Area #24", "The Southern Area of the Great Makiss Forest. \nA circle of giant mushrooms gives off an earthy, moist scent, pulsating softly. \nEXITS: (W) (E)");

        Hub forest25 = new Hub("Southern Forest Area #25", "The Southern Area of the Great Makiss Forest. \nA ring of giant oaks forms a natural amphitheater, ancient and majestic. \nEXITS: (W)");

        // N Forest

        Hub forest26 = new Hub("Northern Forest Area #26", "The Northern Area of the Great Makiss Forest. \nA quiet grove filled with whispering leaves, shadows play softly in the breeze. \nEXITS: (S) (N) ");

        Hub forest27 = new Hub("Northern Forest Area #27", "The Northern Area of the Great Makiss Forest. \nA circle of ancient oaks, roots twisting over centuries deep into the earth. \nEXITS: (S) (N) (E)");

        Hub forest28 = new Hub("Northern Forest Area #28", "The Northern Area of the Great Makiss Forest. \nA crystal-clear stream burbles happily beneath stepping stones covered in emerald moss. \nEXITS: (S) (N)");

        Hub forest29 = new Hub("Northern Forest Area #29", "The Northern Area of the Great Makiss Forest. \nYou could call this area a clearing, but what is most unusual is the cauldron which sits in the middle.... \nEXITS: (S) (E)");
        forest29.setStructure("cauldron");

        Hub forest30 = new Hub("Northern Forest Area #30", "The Northern Area of the Great Makiss Forest. \nA crooked witch’s hut with twisted branches and pungent herbs drying in the air. \nEXITS: (W)");

        Hub forest31 = new Hub("Northern Forest Area #31", "The Northern Area of the Great Makiss Forest. \nAn abandoned forge filled with rusted tools and a cold, empty furnace. \nEXITS: (E) (W)");

        Hub forest32 = new Hub("Northern Forest Area #32", "The Northern Area of the Great Makiss Forest. \nA ruined tower partially collapsed, its stones littered with ancient runes. \nEXITS: (W) (E) (N)");

        Hub forest33 = new Hub("Northern Forest Area #33", "The Northern Area of the Great Makiss Forest. \nA clearing where faint magical lights flicker at dusk, and mushrooms bloom. \nEXITS: (S) (N) ");

        Hub forest34 = new Hub("Northern Forest Area #34", "The Northern Area of the Great Makiss Forest. \nA rocky outcrop overlooking the vast forest, winds carrying distant calls. \nEXITS: (W)");

        Hub forest35 = new Hub("Northern Forest Area #35", "The Northern Area of the Great Makiss Forest. \nAn ancient burial mound covered in moss and wildflowers, silent and solemn. \nEXITS: (S) (E)");

        Hub forest36 = new Hub("Northern Forest Area #36", "The Northern Area of the Great Makiss Forest. \nA bandit lookout perched atop a tall pine offering views of the forest below. \nEXITS: (E) (W)");

        Hub forest37 = new Hub("Northern Forest Area #37", "The Northern Area of the Great Makiss Forest. \nA forest gate approached by winding paths, threats lurk nearby as you draw closer. \nEXITS: (W) (S)");

        Hub forest38 = new Hub("Northern Forest Area #38", "The Northern Area of the Great Makiss Forest. \nA watch post reinforced with crude barricades and sharp stakes. \nEXITS: (N) (E)");

        Hub forest39 = new Hub("Northern Forest Area #39", "The Northern Area of the Great Makiss Forest. \nA spider-infested clearing with thick webs and ominous silence. \nEXITS: (W) (E)");

        Hub forest40 = new Hub("Northern Forest Area #40", "The Northern Area of the Great Makiss Forest. \nA secret herb garden tucked beneath thick brush, healing scents abound. \nEXITS: (E) (W)");

        Hub forest41 = new Hub("Northern Forest Area #41", "The Northern Area of the Great Makiss Forest. \nA natural spring bubbling into a crystal pool surrounded by lush ferns. \nEXITS: (E) (W) (N)");

        Hub forest42 = new Hub("Northern Forest Area #42", "The Northern Area of the Great Makiss Forest. \nAn ancient oak carved with strange symbols, glowing faintly in moonlight. \nEXITS: (S) (N)");

        Hub forest43 = new Hub("Northern Forest Area #43", "The Northern Area of the Great Makiss Forest. \nA rocky ridge where you can see the whole forest stretching far below. \nEXITS: (W)");

        Hub forest44 = new Hub("Northern Forest Area #44", "The Northern Area of the Great Makiss Forest. \nAn overgrown path leading toward a long-forgotten shrine. \nEXITS: (N) (S)");

        Hub forest45 = new Hub("Northern Forest Area #45", "The Northern Area of the Great Makiss Forest. \nA dense thicket teeming with small wildlife and rustling leaves. \nEXITS: (S) (W)");

        Hub forest46 = new Hub("Northern Forest Area #46", "The Northern Area of the Great Makiss Forest. \nA mossy cave mouth invites exploration into the dark earth beyond. \nEXITS: (E) (N)");

        Hub forest47 = new Hub("Northern Forest Area #47", "The Northern Area of the Great Makiss Forest. \nA ruined chapel with stained glass shattered and walls crumbling. \nEXITS: (S) (N) ");

        Hub forest48 = new Hub("Northern Forest Area #48", "The Northern Area of the Great Makiss Forest. \nA massive tree with ancient roots forming natural arches. \nEXITS: (S) (E)");

        Hub forest49 = new Hub("Northern Forest Area #49", "The Northern Area of the Great Makiss Forest. \nA clearing just before the forest exit, quiet but tense as danger nears. \nEXITS: (W) (N)");

        Hub forest50 = new Hub("Northern Forest Area #50", "The Northern Area of the Great Makiss Forest. \nThe forest exit, hints of a fierce mini-boss—beyond lay in the area. \nEXITS: (S) (N)");

        //FIRSTVILLE
        Hub firstVilleGate = new Hub("Gate of FirstVille", "The Great Gate of FirstVille.\nYou stand in a clearing, opposite from the forest, in front of a great booming gate: (S) (N)");
        firstVilleGate.setStructure("gate");

        Hub firstVillePassage = new Hub("The Great Passage into FirstVille", "A short passage that reaches the Great Gate and the town square. \nTo the south stand wood line gates, to the north you see bustling people and lights. \nEXITS: (N) (S)");
        firstVillePassage.setStructure("gate");

        Hub firstVilleSquare = new Hub("FirstVille Square", "The bustling heart of FirstVille. Merchants shout their wares and townsfolk mill about. \nEXITS: (N) (S)");

        Hub firstVilleLane1 = new Hub("FirstVille Streets #1", "North of the Town Square, houses line the streets, children can be seen playing in them. \nEXITS: (E) (W) (S)");

        Hub firstVilleLane2 = new Hub("FirstVille Streets #2", "The bustling heart of FirstVille. Merchants shout their wares and townsfolk mill about. \nEXITS: (W) (S) (E)");

        Hub firstVilleLane3 = new Hub("FirstVille Streets #3", "The bustling heart of FirstVille. Merchants shout their wares and townsfolk mill about. \nEXITS: (E) (N)");

        Hub firstVilleLane4 = new Hub("FirstVille Streets #4", " \nEXITS: (S) (N) (W)");

        Hub firstVilleLane5 = new Hub("FirstVille Streets #5", " \nEXITS: (S) (W) (N)");

        Hub firstVilleLane6 = new Hub("FirstVille Streets #6", " \nEXITS: (S) (N)");

        Hub firstVilleLane7 = new Hub("FirstVille Streets #7", " \nEXITS: (E) (N) (S) (W)");

        Hub firstVilleLane8 = new Hub("FirstVille Streets #8", " \nEXITS: (E) (N) (S) ");

        Hub firstVilleLane9 = new Hub("FirstVille Streets #9", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane10 = new Hub("FirstVille Streets #10", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane11 = new Hub("FirstVille Streets #11", " \nEXITS: (E) (S) (W) (E)");

        Hub firstVilleLane12 = new Hub("FirstVille Streets #12", "To the north is the door to one of FirstVille's residents. \nEXITS: (N) (W) (S) (E)");
        Hub firstVilleHouse1Main = new Hub("FirstVille House 1 Main Room", "Inside a cozy family home. \nEXITS: (N) (S)"); // add desc. of room with a desk and a man hunched over it. Then if you kill him then CHANGE the desc.
        firstVilleLane12.setExit("n", firstVilleHouse1Main);
        firstVilleHouse1Main.setExit("s", firstVilleLane12);
        Hub firstVilleHouse1Bedroom = new Hub("FirstVille House 1 Bedroom", "A cramped bedroom with two straw‑stuffed beds pushed against the wall. \nEXITS (S)");
        firstVilleHouse1Main.setExit("n", firstVilleHouse1Bedroom);
        firstVilleHouse1Bedroom.setExit("s", firstVilleHouse1Main);

        Hub firstVilleLane13 = new Hub("FirstVille Streets #13", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane14 = new Hub("FirstVille Streets #14", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane15 = new Hub("FirstVille Streets #15", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane16 = new Hub("FirstVille Streets #16", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane17 = new Hub("FirstVille Streets #17", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane18 = new Hub("FirstVille Streets #18", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane19 = new Hub("FirstVille Streets #19", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane20 = new Hub("FirstVille Streets #20", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane21 = new Hub("FirstVille Streets #21", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane22 = new Hub("FirstVille Streets #22", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane23 = new Hub("FirstVille Streets #23", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane24 = new Hub("FirstVille Streets #24", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane25 = new Hub("FirstVille Streets #25", " \nEXITS: (E) (S) (W)");
        Hub firstVilleHouse2Main = new Hub("FirstVille Townhome Main Room", "A tidy main room \nEXITS: (S) (U)");
        firstVilleHouse2Main.setExit("n", firstVilleLane25);
        firstVilleLane25.setExit("s", firstVilleHouse2Main);
        Hub firstVilleHouse2Bed = new Hub("FirstVille Townhome  Bedroom", "\nEXITS: (N)");
        firstVilleHouse2Main.setExit("s", firstVilleHouse2Bed);
        firstVilleHouse2Bed.setExit("n", firstVilleHouse2Main);
        Hub firstVilleHouse2Upstairs = new Hub("FirstVille Townhome Upstairs Room", " \nEXITS: (D) (N)");
        firstVilleHouse2Main.setExit("u", firstVilleHouse2Upstairs);
        firstVilleHouse2Upstairs.setExit("s", firstVilleHouse2Main);
        Hub firstVilleHouse2Upstairs2 = new Hub("FirstVille Townhome Upstairs Kitchen", "A stove is tucked into the corner, ready for use. \nEXITS: (S)");
        firstVilleHouse2Upstairs2.setStructure("stove");
        firstVilleHouse2Upstairs.setExit("n", firstVilleHouse2Upstairs2);
        firstVilleHouse2Upstairs2.setExit("s", firstVilleHouse2Upstairs);

        Hub firstVilleLane26 = new Hub("FirstVille Streets #26", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane27 = new Hub("FirstVille Streets #27", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane28 = new Hub("FirstVille Streets #28", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane29 = new Hub("FirstVille Streets #29", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane30 = new Hub("FirstVille Streets #30", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane31 = new Hub("FirstVille Streets #31", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane32 = new Hub("FirstVille Streets #32", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane33 = new Hub("FirstVille Streets #33", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane34 = new Hub("FirstVille Streets #34", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane35 = new Hub("FirstVille Streets #35", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane36 = new Hub("FirstVille Streets #36", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane37 = new Hub("FirstVille Streets #37", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane38 = new Hub("FirstVille Streets #38", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane39 = new Hub("FirstVille Streets #39", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane40 = new Hub("FirstVille Streets #40", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane41 = new Hub("FirstVille Streets #41", "The door to the shack of one of FirstVille's residents lays south. \nEXITS: (E) (S) (W)");
        Hub firstVilleShackMain = new Hub("FirstVille Shack Interior", "The shack interior is cramped and bare. \nEXITS: (N)");
        firstVilleLane41.setExit("s", firstVilleShackMain);
        firstVilleShackMain.setExit("n", firstVilleLane41);

        Hub firstVilleLane42 = new Hub("FirstVille Streets #42", " \nEXITS: (E) (S) (W)");

        Hub firstVilleLane43 = new Hub("FirstVille Streets #43", "A manhole in the middle of the road. \nEXITS: (E) (S) (W) (D)");

        Hub firstVilleManHoleEnt = new Hub("Entrance to a manhole", " \nEXITS: (U) (E)");
        firstVilleLane43.setExit("d", firstVilleManHoleEnt);
        firstVilleManHoleEnt.setExit("u", firstVilleLane43);

        Hub firstVilleManHole1 = new Hub("FirstVille Manhole #1", " \nEXITS: (W) (E)");
        firstVilleManHole1.setExit("w", firstVilleManHoleEnt);
        firstVilleManHoleEnt.setExit("e", firstVilleManHole1);

        Hub firstVilleManHole2 = new Hub("FirstVille Manhole #2", " \nEXITS: (W) (E)");
        firstVilleManHole1.setExit("e", firstVilleManHole2);
        firstVilleManHole2.setExit("w", firstVilleManHole1);

        Hub firstVilleManHole3 = new Hub("FirstVille Manhole #3", " \nEXITS: (W) (E)");
        firstVilleManHole3.setExit("w", firstVilleManHole2);
        firstVilleManHole2.setExit("e", firstVilleManHole3);

        Hub firstVilleManHole4 = new Hub("FirstVille Manhole #4", " \nEXITS: (W) (S)");
        firstVilleManHole3.setExit("e", firstVilleManHole4);
        firstVilleManHole4.setExit("w", firstVilleManHole3);

        Hub firstVilleManHole5 = new Hub("FirstVille Manhole #5", " \nEXITS: (N) (S)");
        firstVilleManHole5.setExit("n", firstVilleManHole4);
        firstVilleManHole4.setExit("s", firstVilleManHole5);

        Hub firstVilleManHole6 = new Hub("FirstVille Manhole #6", " \nEXITS: (N) (W)");
        firstVilleManHole5.setExit("s", firstVilleManHole6);
        firstVilleManHole6.setExit("n", firstVilleManHole5);

        Hub firstVilleManHole7 = new Hub("FirstVille Manhole #7", " \nEXITS: (E) (N)");
        firstVilleManHole7.setExit("e", firstVilleManHole6);
        firstVilleManHole6.setExit("w", firstVilleManHole7);

        Hub firstVilleManHoleCELL = new Hub("FirstVille Manhole CELL", " \nEXITS: (S)");
        firstVilleManHole7.setExit("n", firstVilleManHoleCELL);
        firstVilleManHoleCELL.setExit("s", firstVilleManHole7);

        Hub firstVilleBarracks = new Hub("FirstVille Barracks", " \nEXITS: (E) (S) (W)");

        //PLAINS BIOME [between 1st and 2cd ville]

        // PLAINS BIOME HUBS (Ideally it is structured so you can go directly across the dessert in like 3 rooms, but to explore you have to go UP


        Hub plainsShaftEntrance = new Hub("Vast plains Shaft Entrance", " \nEXITS: (D)");

        Hub plainsShaft1 = new Hub("Entry to the Great Vast Plains Mine Shaft", " \nEXITS: (U)");

        Hub plainsShaft2 = new Hub("Great Plains Shaft Area #1", " \nEXITS: ");

        Hub plainsShaft3 = new Hub("Great Plains Shaft Area #2", " \nEXITS: ");

        //PLAINS BIOME EXITS:

        plainsShaftEntrance.setExit("d", plainsShaft1);


        //SECOND VILLE
        //PERSONAL HOME (cost -  a lot, add upstairs etc etc).
        Hub secondVillePersonalHomeDoor = new Hub("Personal Home Door", " \nEXITS: ");

        Hub secondVillePersonalHomeEntrance = new Hub("Personal Home Entrance", " \nEXITS: ");

        Hub secondVillePersonalHomeKitchen = new Hub("Personal Home Kitchen", " \nEXITS: ");

        Hub secondVillePersonalHomeBedRoom = new Hub("Personal Home Bedroom", " \nEXITS: ");

        Hub secondVillePersonalHomeStorage = new Hub("Personal Home Storage", " \nEXITS: ");

        //OCEAN BIOME [between 2cd and 3rd ville]
        // PORTS
        Hub westPort = new Hub("West Port", "You average port. A nice looking boat is sits somewhere bloah blah blah, oliver help \nExits: (E)");
        Hub eastPort = new Hub("East Port", "\nExits: (W)");

        // OCEAN SURFACE LAYER (boat layer only)
        Hub oceanSurface1 = new Hub("Open Sea West", "\nExits: (E), (W)");
        Hub oceanSurface2 = new Hub("Open Sea Mid", "\nExits: (E), (W), (D)");
        Hub oceanSurface3 = new Hub("Open Sea East", "\nExits: (E), (W)");

        // OCEAN UNDERWATER LAYERS (scuba only)
        Hub oceanDepth1 = new Hub("Shallow Depths", "\nExits: (U), (D)");
        Hub oceanDepth2 = new Hub("Midwater", "\nExits: (U), (D)");
        Hub oceanDepth3 = new Hub("Abyssal Trench", "\nExits: (U)");

        //OCEAN EXITS:
        westPort.setExit("e", oceanSurface1);
        oceanSurface1.setExit("w", westPort);

        oceanSurface1.setExit("e", oceanSurface2);
        oceanSurface2.setExit("w", oceanSurface1);

        oceanSurface2.setExit("e", oceanSurface3);
        oceanSurface3.setExit("w", oceanSurface2);

        oceanSurface2.setExit("d", oceanDepth1);
        oceanDepth1.setExit("u", oceanSurface2);

        oceanDepth1.setExit("d", oceanDepth2);
        oceanDepth2.setExit("u", oceanDepth1);

        oceanDepth2.setExit("d", oceanDepth3);
        oceanDepth3.setExit("u", oceanDepth2);

        oceanSurface3.setExit("e", eastPort);
        eastPort.setExit("w", oceanSurface3);

        //Toms Cave EXITS:
        cave.setExit("n", caveN);
        caveN.setExit("s", cave);
        caveN.setExit("n", caveNN);
        caveNN.setExit("s", caveN);
        caveNN.setExit("e", caveNE);
        caveNN.setExit("w", caveNW);
        caveNW.setExit("e", caveNN);
        caveNE.setExit("w", caveNN);
        caveNW.setExit("n", forest1);

        //S Forest EXITS:
        forest1.setExit("s", caveNW);
        forest1.setExit("n", forest2);
        forest2.setExit("s", forest1);
        forest2.setExit("w", forest3);
        forest3.setExit("e", forest2);
        forest3.setExit("w", forest4);
        forest4.setExit("e", forest3);
        forest4.setExit("w", forest5);
        forest5.setExit("e", forest4);
        forest5.setExit("n", forest6);
        forest6.setExit("s", forest5);
        forest6.setExit("n", forest7);
        forest6.setExit("w", forest9);
        forest7.setExit("s", forest6);
        forest7.setExit("w", forest8);
        forest8.setExit("e", forest7);
        forest8.setExit("s", forest9);
        forest9.setExit("n", forest8);
        forest9.setExit("e", forest6);
        forest9.setExit("w", forest10);
        forest10.setExit("e", forest9);
        forest10.setExit("w", forest11);
        forest11.setExit("e", forest10);
        forest11.setExit("n", forest12);
        forest12.setExit("s", forest11);
        forest12.setExit("n", forest13);

        //TO ABONDNOED HOUSE
        forest12.setExit("w", sHouseEntrance);
        sHouseEntrance.setExit("e", forest12);
        sHouseEntrance.setExit("s", sHouseEntranceLeft);
        sHouseEntrance.setExit("n", sHouseEntranceRight);
        sHouseEntranceRight.setExit("s", sHouseEntrance);
        sHouseEntranceLeft.setExit("n", sHouseEntrance);
        sHouseEntranceRight.setExit("w", sHouseHallway);
        sHouseHallway.setExit("e", sHouseEntranceRight);
        sHouseHallway.setExit("s", sHouseRoom);
        sHouseRoom.setExit("n", sHouseHallway);
        sHouseRoom.setExit("s", sHouseStairs);
        sHouseStairs.setExit("n", sHouseHallway);
        sHouseStairs.setExit("u", sHouseUpper);
        sHouseUpper.setExit("d", sHouseStairs);

        forest13.setExit("s", forest12);
        forest13.setExit("e", forest14);
        forest14.setExit("w", forest13);
        forest14.setExit("n", forest15);
        forest15.setExit("s", forest14);
        forest15.setExit("e", forest16);
        forest16.setExit("w", forest15);
        forest16.setExit("e", forest18);
        forest18.setExit("e", forest19);
        forest18.setExit("w", forest16);
        forest19.setExit("w", forest18);
        forest19.setExit("n", forest23);
        forest19.setExit("e", forest21);
        forest19.setExit("n", forest23);
        forest23.setExit("s", forest19);
        forest19.setExit("n", forest23);
        forest21.setExit("e", forest22);
        forest22.setExit("e", forest24);
        forest24.setExit("e", forest25);
        forest25.setExit("w", forest24);
        forest24.setExit("w", forest22);
        forest22.setExit("w", forest21);
        forest21.setExit("w", forest19);
        forest16.setExit("n", forest17);
        forest17.setExit("s", forest16);

        // N Forest exits
        forest17.setExit("n", forest26);
        forest26.setExit("s", forest17);
        forest26.setExit("n", forest27);
        forest27.setExit("s", forest26);
        forest27.setExit("n", forest28);
        forest28.setExit("s", forest27);
        forest28.setExit("n", forest29);
        forest29.setExit("s", forest28);
        forest27.setExit("e", forest30);
        forest30.setExit("w", forest27);
        forest29.setExit("e", forest31);
        forest31.setExit("w", forest29);
        forest31.setExit("e", forest32);
        forest32.setExit("w", forest31);
        forest32.setExit("e", forest34);
        forest34.setExit("w", forest32);
        forest32.setExit("n", forest33);
        forest33.setExit("s", forest32);
        forest33.setExit("n", forest35);
        forest35.setExit("s", forest33);
        forest35.setExit("e", forest36);
        forest36.setExit("w", forest35);
        forest36.setExit("e", forest37);
        forest37.setExit("w", forest36);
        forest37.setExit("s", forest38);
        forest38.setExit("n", forest37);
        forest38.setExit("e", forest39);
        forest39.setExit("w", forest38);
        forest39.setExit("e", forest40);
        forest40.setExit("w", forest39);
        forest40.setExit("e", forest41);
        forest41.setExit("w", forest40);
        forest41.setExit("e", forest43);
        forest43.setExit("w", forest41);
        forest41.setExit("n", forest42);
        forest42.setExit("s", forest41);
        forest42.setExit("n", forest44);
        forest44.setExit("s", forest42);
        forest44.setExit("n", forest45);
        forest45.setExit("s", forest44);
        forest45.setExit("w", forest46);
        forest46.setExit("e", forest45);
        forest46.setExit("n", forest47);
        forest47.setExit("s", forest46);
        forest47.setExit("n", forest48);
        forest48.setExit("s", forest47);
        forest48.setExit("e", forest49);
        forest49.setExit("w", forest48);
        forest49.setExit("n", forest50);
        forest50.setExit("s", forest49);
        forest50.setExit("n", firstVilleGate);


        //Town exits
        firstVilleGate.setExit("n", firstVillePassage);
        firstVilleGate.setExit("s", forest50);
        firstVillePassage.setExit("s", firstVilleGate);
        firstVillePassage.setExit("n", firstVilleSquare);
        firstVilleSquare.setExit("s", firstVillePassage);
        firstVilleSquare.setExit("n", firstVilleLane1);
        firstVilleLane1.setExit("s", firstVilleSquare);
        firstVilleLane1.setExit("n", firstVilleLane2);
        firstVilleLane2.setExit("s", firstVilleLane1);
        firstVilleLane2.setExit("w", firstVilleLane3);
        firstVilleLane3.setExit("e", firstVilleLane2);
        firstVilleLane4.setExit("n", firstVilleLane5);
        firstVilleLane4.setExit("w", firstVilleLane7);
        firstVilleLane5.setExit("s", firstVilleLane4);
        firstVilleLane5.setExit("n", firstVilleLane6);
        firstVilleLane5.setExit("w", firstVilleLane8);
        firstVilleLane6.setExit("s", firstVilleLane5);
        firstVilleLane7.setExit("e", firstVilleLane4);
        firstVilleLane7.setExit("s", firstVilleLane2);
        firstVilleLane7.setExit("n", firstVilleLane8);
        firstVilleLane8.setExit("n", firstVilleLane9);
        firstVilleLane8.setExit("s", firstVilleLane7);
        firstVilleLane8.setExit("e", firstVilleLane5);
        firstVilleLane8.setExit("e", firstVilleLane5);
        firstVilleLane9.setExit("s", firstVilleLane8);
        firstVilleLane9.setExit("e", firstVilleLane6);
        firstVilleLane9.setExit("w", firstVilleLane10);
        firstVilleLane10.setExit("e", firstVilleLane9);
        firstVilleLane10.setExit("n", firstVilleLane11);
        firstVilleLane10.setExit("e", firstVilleLane9);
        firstVilleLane10.setExit("n", firstVilleLane11);
        firstVilleLane11.setExit("n", firstVilleLane12);
        firstVilleLane11.setExit("e", firstVilleLane35);
        firstVilleLane11.setExit("w", firstVilleLane14);
        firstVilleLane11.setExit("s", firstVilleLane10);
        firstVilleLane12.setExit("s", firstVilleLane11);
        firstVilleLane12.setExit("e", firstVilleLane36);
        firstVilleLane12.setExit("w", firstVilleLane13);
        firstVilleLane35.setExit("w", firstVilleLane11);
        firstVilleLane35.setExit("n", firstVilleLane36);
        firstVilleLane35.setExit("s", firstVilleLane9);
        firstVilleLane36.setExit("s", firstVilleLane35);
        firstVilleLane36.setExit("n", firstVilleLane37);
        firstVilleLane36.setExit("w", firstVilleLane12);
        firstVilleLane37.setExit("s", firstVilleLane36);
        firstVilleLane38.setExit("w", firstVilleLane37);
        firstVilleLane38.setExit("n", firstVilleLane39);
        firstVilleLane39.setExit("s", firstVilleLane38);
        firstVilleLane39.setExit("n", firstVilleLane40);

        firstVilleLane12.setExit("s", firstVilleLane11);
        firstVilleLane12.setExit("e", firstVilleLane36);
        firstVilleLane12.setExit("w", firstVilleLane13);


        firstVilleLane35.setExit("w", firstVilleLane11);
        firstVilleLane35.setExit("n", firstVilleLane36);
        firstVilleLane35.setExit("s", firstVilleLane9);


        firstVilleLane36.setExit("s", firstVilleLane35);
        firstVilleLane36.setExit("n", firstVilleLane37);
        firstVilleLane36.setExit("w", firstVilleLane12);


        firstVilleLane37.setExit("s", firstVilleLane36);


        firstVilleLane38.setExit("w", firstVilleLane37);
        firstVilleLane38.setExit("n", firstVilleLane39);


        firstVilleLane39.setExit("s", firstVilleLane38);
        firstVilleLane39.setExit("n", firstVilleLane40);


        firstVilleLane40.setExit("n", firstVilleLane42);
        firstVilleLane40.setExit("s", firstVilleLane39);
        firstVilleLane40.setExit("e", firstVilleLane41);


        firstVilleLane41.setExit("w", firstVilleLane40);


        firstVilleLane42.setExit("s", firstVilleLane40);
        firstVilleLane42.setExit("w", firstVilleLane43);
        firstVilleLane43.setExit("e", firstVilleLane42);


        firstVilleLane14.setExit("e", firstVilleLane11);
        firstVilleLane14.setExit("n", firstVilleLane13);
        firstVilleLane14.setExit("w", firstVilleInn);


        firstVilleLane13.setExit("s", firstVilleLane14);
        firstVilleLane13.setExit("w", firstVilleLane15);


        firstVilleLane15.setExit("s", firstVilleInn);
        firstVilleLane15.setExit("n", firstVilleLane16);
        firstVilleLane15.setExit("w", firstVilleLane22);


        firstVilleLane16.setExit("s", firstVilleLane15);
        firstVilleLane16.setExit("n", firstVilleLane17);
        firstVilleLane16.setExit("e", firstVilleLane21);


        firstVilleLane17.setExit("w", firstVilleLane20);
        firstVilleLane17.setExit("n", firstVilleLane18);


        firstVilleLane18.setExit("n", firstVilleLane19);
        firstVilleLane18.setExit("e", firstVilleLane31);


        firstVilleLane19.setExit("s", firstVilleLane18);


        firstVilleLane31.setExit("e", firstVilleLane31);


        firstVilleLane20.setExit("s", firstVilleLane21);
        firstVilleLane20.setExit("e", firstVilleLane17);


        firstVilleLane21.setExit("n", firstVilleLane20);
        firstVilleLane21.setExit("e", firstVilleLane16);
        firstVilleLane21.setExit("s", firstVilleLane22);


        firstVilleLane22.setExit("s", firstVilleLane23);
        firstVilleLane22.setExit("n", firstVilleLane21);
        firstVilleLane22.setExit("e", firstVilleLane15);


        firstVilleLane23.setExit("s", firstVilleLane26);
        firstVilleLane23.setExit("w", firstVilleLane24);
        firstVilleLane23.setExit("n", firstVilleLane22);
        firstVilleLane23.setExit("e", firstVilleInn);

        firstVilleLane24.setExit("s", firstVilleLane25);
        firstVilleLane24.setExit("e", firstVilleLane23);


        firstVilleLane25.setExit("n", firstVilleLane24);
        firstVilleLane23.setExit("e", firstVilleLane26);


        firstVilleLane26.setExit("n", firstVilleLane23);
        firstVilleLane26.setExit("w", firstVilleLane25);
        firstVilleLane26.setExit("s", firstVilleLane27);


        firstVilleLane27.setExit("n", firstVilleLane23);
        firstVilleLane27.setExit("e", firstVilleBarracks);
        firstVilleLane27.setExit("s", firstVilleLane28);

        firstVilleBarracks.setExit("w", firstVilleLane27);


        firstVilleLane28.setExit("n", firstVilleLane27);
        firstVilleLane28.setExit("s", firstVilleLane29);


        firstVilleLane29.setExit("n", firstVilleLane23);
        firstVilleLane29.setExit("e", firstVilleLane32);
        firstVilleLane29.setExit("s", firstVilleLane30);


        firstVilleLane30.setExit("n", firstVilleLane29);


        firstVilleLane32.setExit("w", firstVilleLane29);
        firstVilleLane32.setExit("e", firstVilleLane33);


        firstVilleLane33.setExit("w", firstVilleLane32);
        firstVilleLane33.setExit("e", firstVilleLane3);

        // PLAINS EXITS


        //ENHANCEMENTS & SCROLLS!
        List<Enchantment1> enchantment1List = new ArrayList<>();

        Enchantment1 fire = new Enchantment1("fire scroll 1", "Lights enemies on fire", 1);
        enchantment1List.add(fire);
        Enchantment1 fire2 = new Enchantment1("fire scroll 2", "Lights enemies on fire", 2);
        enchantment1List.add(fire2);
        Enchantment1 fire3 = new Enchantment1("fire scroll 3", "Lights enemies on fire", 3);
        enchantment1List.add(fire3);

        Enchantment1 protection = new Enchantment1("protection scroll 1", "Your armor feels thicker", 1);
        enchantment1List.add(protection);
        Enchantment1 protection2 = new Enchantment1("protection scroll 2", "Your armor feels thicker", 2);
        enchantment1List.add(protection2);
        Enchantment1 protection3 = new Enchantment1("protection scroll 3", "Your armor feels thicker", 3);
        enchantment1List.add(protection3);

        Enchantment1 roulette = new Enchantment1("roulette scroll 1", "It isn't gambling if you know you're going to win", 1);
        enchantment1List.add(roulette);
        Enchantment1 roulette2 = new Enchantment1("roulette scroll 2", "You feel like putting all of your savings on red", 2);
        enchantment1List.add(roulette2);
        Enchantment1 roulette3 = new Enchantment1("roulette scroll 3", "You can only lose 100% of your money but you can earn 2000%", 3);
        enchantment1List.add(roulette3);
        // CLINICS

        Hub.Clinic firstVilleClinic = new Hub("FirstVille Clinic", "A clinic for the crippled").new Clinic("FirstVille Clinic", "A clinic for the crippled");

        firstVilleLane3.setExit("n", firstVilleClinic);
        firstVilleClinic.setExit("s", firstVilleLane3);

        firstVilleClinic.setExit("e", firstVilleLane7);
        firstVilleLane7.setExit("w", firstVilleClinic);


        // GUILDS

        Hub.FirstVilleGuild firstVilleGuild = new Hub("FirstVille Guild Hall", "The legendary FirstVille Guild Hall. Heroes gather here to prove their worth through quests and rankings.").new FirstVilleGuild("FirstVille Guild Hall", "The legendary FirstVille Guild Hall. Heroes gather here to prove their worth through quests and rankings.");
        Quest questForGuildOne = new Quest("GQ1", "Goblin Hunt (GQ1)", 1, "Goblin", 3, 30, 10);
        firstVilleGuild.addQuest(questForGuildOne);
        questForGuildOne = new Quest("GQ2", "Wolf Hunt (GQ2)", 1, "Wolf", 2, 30, 10);
        firstVilleGuild.addQuest(questForGuildOne);
        questForGuildOne = new Quest("GQ3", "Witch Hunt (GQ3)", 1, "Fox Witch", 1, 30, 10);
        firstVilleGuild.addQuest(questForGuildOne);


        firstVilleSquare.setExit("w", firstVilleGuild);
        firstVilleGuild.setExit("e", firstVilleSquare);

        //EXCHANGE BOOTHS

        Hub.CurrencyExchangeBooth exchangeBooth = new Hub("Exchange Booth", "A booth for trading currencies. Perfect for merchants.").new CurrencyExchangeBooth("Exchange Booth", "A booth for trading currencies. Perfect for merchants.");
        exchangeBooth.getObjects().add("exchange booth");


        // HORSE RIDER NPCS
        String horseLines[] = {"Neigh."};
        Health horseHp = new Health(50, 50, 0);
        Npca horse = new Npca("Horsey", "Horsey", horseLines, 0, horseHp, "", Npca.QuestState.NONE);

        String riderLines[] = {
                "Need a fast ride between FirstVille and the deep Southern forest?",
                "3 copper and I'll take you straight there.",
                "If either of us dies, this deal is off forever..."
        };

        KEY_FIGURES.add(new KeyFigureSpawn("Horsey", firstVilleLane3, () -> {
            Npca npc = new Npca("Horsey", "Horsey", horseLines, 0, new Health(60, 60, 0), "", Npca.QuestState.NONE);
            firstVilleLane3.getNpc().add(npc);

        }));

        Health riderHp = new Health(80, 80, 0);
        Npca riderBill = new Npca("Bill", "Guider", riderLines, 5, riderHp, "", Npca.QuestState.NONE);

        KEY_FIGURES.add(new KeyFigureSpawn("Bill", firstVilleLane3, () -> {
            Npca npc = new Npca("Bill", "Guider", riderLines, 5, new Health(80, 80, 0), "", Npca.QuestState.NONE);
            firstVilleLane3.getNpc().add(npc);
        }));

        // STARTING LOCATION – FirstVille Streets 3
        firstVilleLane3.getNpc().add(riderBill);
        firstVilleLane3.getNpc().add(horse);


        // GUARD NPCAS!!

        String[] barracksGuard2Words = {
                "Yeah, move along, citizen. Orders are orders."
        };

        Health barracksGuard2Health = new Health(60, 55, 2);

        Npca barracksGuard2 = new Npca("Kerr", "Junior Guard", barracksGuard2Words, 4, barracksGuard2Health, "", Npca.QuestState.NONE);

        firstVilleLane12.getNpc().add(barracksGuard2);

        KEY_FIGURES.add(new KeyFigureSpawn("Kerr", firstVilleLane12, () -> {
            Npca npc = new Npca("Kerr", "Junior Guard", barracksGuard2Words, 4, new Health(60, 55, 2), "", Npca.QuestState.NONE);
            firstVilleLane12.getNpc().add(npc);
        }));

        String[] barracksGuard3Words = {
                "What're you look at, brat."
        };

        Health barracksGuard3Health = new Health(90, 90, 5);

        Npca barracksGuard3 = new Npca("Jerald", "Senior Guard", barracksGuard2Words, 9, barracksGuard3Health, "", Npca.QuestState.NONE);

        firstVilleLane39.getNpc().add(barracksGuard3);

        KEY_FIGURES.add(new KeyFigureSpawn("Jerald", firstVilleLane39, () -> {
            Npca npc = new Npca("Jerald", "Senior Guard", barracksGuard2Words, 9, new Health(90, 90, 5), "", Npca.QuestState.NONE);
            firstVilleLane39.getNpc().add(npc);
        }));

        //INNS + inkeepers!!
        String[] innkeeperLines = {
                "Welcome to the Cozy Inn. Best beds in FirstVille.",
                "A good rest is worth more than a rusty sword.",
                "Pay at the counter if you want a room."
        };

        Npca innkeeper1 = new Npca("MaraTamara", "Innkeeper", innkeeperLines,
                2, new Health(40, 40, 0), "", Npca.QuestState.NONE);

        KEY_FIGURES.add(new KeyFigureSpawn("MaraTamara", firstVilleInn, () -> {
            Npca npc = new Npca("MaraTamara", "Innkeeper", innkeeperLines, 2, new Health(40, 40, 0), "", Npca.QuestState.NONE);
            firstVilleInn.getNpc().add(npc);
        }));


        firstVilleInn = new Hub("FirstVille Cozy Inn", "A warm, quiet room monitored by an innkeeper. A bed sits in the very middle, looks comfortable. This is the type of place that would cost 15 copper.").new Inn("FirstVille Cozy Inn", "A warm, quiet room monitored by an innkeeper. This is the type of place that would cost 15 copper. \n" + "EXITS: ()", 15, 3, innkeeper1, "copper");

        firstVilleInn.getNpc().add(innkeeper1);

        firstVilleInn.setStructure("bed");

        firstVilleInn.setExit("e", firstVilleLane14);
        firstVilleInn.setExit("n", firstVilleLane15);
        firstVilleInn.setExit("w", firstVilleLane23);

        //Doors!!!!
        Door caveDoor = new Door(caveNW, forest1, false);
        caveNW.setDoor("n", caveDoor);
        forest1.setDoor("s", caveDoor);

        Door sHouseEntrance1 = new Door(forest12, sHouseEntrance, false);
        forest12.setDoor("w", sHouseEntrance1);
        sHouseEntrance.setDoor("e", sHouseEntrance1);

        Door sHouseHallwayRoom = new Door(sHouseHallway, sHouseRoom, false);
        sHouseHallway.setDoor("s", sHouseHallwayRoom);
        sHouseRoom.setDoor("n", sHouseHallwayRoom);

        Door firstVilleHouse1 = new Door(firstVilleLane12, firstVilleHouse1Main, false);
        firstVilleHouse1Main.setDoor("s", firstVilleHouse1);
        firstVilleLane12.setDoor("n", firstVilleHouse1);

        Door firstVilleHouse2 = new Door(firstVilleLane41, firstVilleShackMain, false);
        firstVilleShackMain.setDoor("n", firstVilleHouse2);
        firstVilleLane41.setDoor("n", firstVilleHouse2);

        Door firstVilleHouse3 = new Door(firstVilleLane25, firstVilleHouse2Main, false);
        firstVilleHouse2Main.setDoor("n", firstVilleHouse3);
        firstVilleLane25.setDoor("s", firstVilleHouse3);


        // GATES

        LockedDoors gateDoor = new LockedDoors(true, "Guard Key", firstVilleGate, firstVillePassage);

        firstVilleGate.setLockedDoor("n", gateDoor);
        firstVillePassage.setLockedDoor("s", gateDoor);

        LockedDoors cellBar1 = new LockedDoors(true, "Cell Key", firstVilleManHole7, firstVilleManHoleCELL);

        firstVilleManHole7.setLockedDoor("n", cellBar1);
        firstVilleManHoleCELL.setLockedDoor("s", cellBar1);

        // ITEMS

        //ADD ITEMS IN TO ROOM
        caveN.addObject("leaflet001");
        caveN.addObject("acorn");
        caveN.addObject("acorn");

        for (int i = 0; i < 2; i++) {
            caveNN.addObject("copper");
        }

        caveNN.addObject("silver");
        caveNE.addObject("leaflet002");
        caveNE.addObject("leather armor");

        for (int ii = 0; ii < 3; ii++) {
            forest1.addObject("twig");
        }

        for (int i = 0; i < 2; i++) {
            forest12.addObject("white whispberry");
        }

        forest6.addObject("trap");

        firstVilleGate.addObject("snarkflower");
        forest47.addObject("snarkflower");
        forest29.addObject("snarkflower");
        forest13.addObject("snarkflower");

        forest3.addObject("acorn");


        // MAKE CHEST STUFF HAPPEN HERE

        List<String> chestContents = Arrays.asList("copper", "snarkflower", "copper", "copper", "twig");

        Chest firstChest = new Chest(true, "KEY001", chestContents, "C");
        forest6.addChest("chest#001", firstChest);

        forest6.addObject("KEY001");

        chestContents = Arrays.asList("silver", "silver");

        Chest secondChest = new Chest(true, "KEY002", chestContents, "C");
        sHouseUpper.addChest("chest#002", secondChest);

        forest26.addObject("KEY002");

        chestContents = Arrays.asList("bear sword");

        Chest thirdChest = new Chest(true, "KEY003", chestContents, "C");
        forest47.addChest("chest#003", thirdChest);

        // ADD MOBS to room

        List<String> stuff = new ArrayList<>();

        Mob goblin = createGoblinWithRandomStats(stuff);

        forest10.getMOBS().add(goblin);

        for (int o = 0; o < 3; o++) {

            goblin = createGoblinWithRandomStats(stuff);

            forest11.getMOBS().add(goblin);
        }

        stuff.add("KEY003");
        Mob Mute = createMuteBanditWithRandomStats(stuff);
        stuff.remove("KEY003");

        forest12.getMOBS().add(Mute);

        for (int o = 0; o < 2; o++) {

            goblin = createMuteBanditWithRandomStats(stuff);

            forest12.getMOBS().add(goblin);
        }

        stuff.add("bear fur");
        stuff.add("snarkflower");
        stuff.add("bear essence");

        goblin = createBearWithRandomStats(stuff, 70, "Mama Bear");
        stuff.remove("bear fur");
        stuff.remove("snarkflower");
        stuff.remove("bear essence");


        forest47.getMOBS().add(goblin);

        stuff.add("thorn shield");
        Boss forestDevil = new Boss("forest devil", 125, 101, 10, 3, "Fierce ForestDevil's hate to be interrupted, you get the devil's stick eye. His forest embraced body curves and twists, branches stick out from nowhere. \nThe number 3 is engraved on his body in stones that seem to glow.", stuff);
        MobSkill stunSkill = new MobSkill.StunSkill("StunSkill", 3);
        forestDevil.addSkills(stunSkill);
        MobSkill vineSkill = new MobSkill.VineSkill("VineSkill", 3);
        forestDevil.addSkills(vineSkill);
        stuff.remove("thorn shield");
        forest50.getBoss().add(forestDevil);

        KEY_FIGURES.add(new KeyFigureSpawn("forest devil", forest50, () -> {
            Boss npc = new Boss("forest devil", 125, 101, 10, 3, "Fierce ForestDevil's hate to be interrupted, you get the devil's stick eye. His forest embraced body curves and twists, branches stick out from nowhere. \nThe number 3 is engraved on his body in stones that seem to glow.", stuff);
            forest50.getBoss().add(npc);
        }));

        //MAKE ITEMS AND "EQUIPMENT"
        //MAKE ITEMS EXIST IN ITEMS
        List<Item> existingItems = new ArrayList<>();

        Equipment equipment = new Equipment();

        // SUPER COOL NAME WEAPONS
        Item pirateHookMax = new Item("pirate thing", "melee", true, fire3, 85, false);
        existingItems.add(pirateHookMax);
        Item wizardStaffMax = new Item("wizard puncher", "melee", true, null, 0, false);
        existingItems.add(wizardStaffMax);
        Item wizardShieldMax = new Item("wizard thingy", "body", true, null, 200, false);
        existingItems.add(wizardShieldMax);

        //NORMAL THINGS
        Item scubaMask = new Item("scuba mask", "head", false, null, 0, false);
        existingItems.add(scubaMask);
        Item bearClaw = new Item("bear claw", "melee", true, null, 6, false);
        existingItems.add(bearClaw);
        Item baggerDagger = new Item("bagger's dagger", "melee", true, null, 7, false);
        existingItems.add(baggerDagger);
        Item sickles = new Item("sickles", "melee", true, null, 6, false);
        existingItems.add(sickles);
        Item knightBoots = new Item("knights footwear", "boots", false, null, 8, false);
        existingItems.add(knightBoots);
        Item knightChest = new Item("knights breastplate", "body", false, null, 8, false);
        existingItems.add(knightChest);
        Item knightLeggings = new Item("knights leggings", "legging", false, null, 8, false);
        existingItems.add(knightLeggings);
        Item knightHelmet = new Item("knights helm", "head", false, null, 8, false);
        existingItems.add(knightHelmet);
        Item dagger = new Item("dagger", "melee", true, null, 4, false);
        existingItems.add(dagger);
        Item leatherArmor = new Item("leather armor", "body", false, null, 4, false);
        existingItems.add(leatherArmor);
        Item FirstVillePlate = new Item("firstville guards plate", "body", false, null, 6, false);
        existingItems.add(FirstVillePlate);
        Item FirstVilleHelm = new Item("firstville guards helm", "head", false, null, 6, false);
        existingItems.add(FirstVilleHelm);
        Item FirstVilleLegs = new Item("firstville guards legs", "legging", false, null, 6, false);
        existingItems.add(FirstVilleLegs);
        Item FirstVilleBoots = new Item("firstville guards boots", "boots", false, null, 6, false);
        existingItems.add(FirstVilleBoots);
        Item copperSword = new Item("Copper Sword", "melee", true, null, 6, false);
        existingItems.add(copperSword);

        //Boss Drops
        Item thornShield = new Item("the shield of the forest boss", "melee", true, null, 9, false);
        existingItems.add(thornShield);

        //forest boss

        //NPCS

        String[] barraksMaster1Words = {
        };

        Health barracksMaster1Health = new Health(175, 172, 10);

        Npca barracksMaster1 = new Npca("Zak", "Captain of the Guard", barraksMaster1Words, 35, barracksMaster1Health, "", Npca.QuestState.NONE);

        KEY_FIGURES.add(new KeyFigureSpawn("Zak", firstVilleBarracks, () -> {
            Npca npc = new Npca("Zak", "Captain of the Guard", barraksMaster1Words, 35, new Health(172, 172, 10), "", Npca.QuestState.NONE);
            firstVilleBarracks.getNpc().add(npc);
        }));

        firstVilleBarracks.getNpc().add(barracksMaster1);


        String[] boatSellerLines = {
                "Looking to cross the sea?",
                "I sell reliable boats—one payment, yours forever.",
                "Once you own a boat, just 'use boat' at the port."
        };

        Health boatSellerHp = new Health(250, 250, 15);

        Npca boatSeller = new Npca("Jerr", "Boat Master", boatSellerLines, 12121212, boatSellerHp, "", Npca.QuestState.NONE);

        KEY_FIGURES.add(new KeyFigureSpawn("Jerr", westPort, () -> {
            Npca npc = new Npca("Jerr", "Boat Master", boatSellerLines, 35, new Health(200, 182, 15), "", Npca.QuestState.NONE);
            westPort.getNpc().add(npc);
        }));

        westPort.getNpc().add(boatSeller);

        String[] guildMasterLines = {
                "Welcome to FirstVille Guild! Say 'join' to join as Noob rank.",
                "Say 'rankings' to see leaderboards.",
                "Say 'quests' to see quests for your rank.",
                "Say 'complete [QUESTID]' after finishing a quest."
        };

        Npca guildMaster = new Npca("Tragger", "Guild Master", guildMasterLines, 9, new Health(280, 158, 2), "", Npca.QuestState.NONE);
        firstVilleGuild.getNpc().add(guildMaster);

        KEY_FIGURES.add(new KeyFigureSpawn("Tragger", firstVilleGuild, () -> {
            Npca npc = new Npca("Tragger", "Guild Master", guildMasterLines, 9, new Health(280, 158, 2), "", Npca.QuestState.NONE);
            firstVilleGuild.getNpc().add(npc);
        }));

        String[] clinicClerkLines = {
                "Welcome welcome"
        };

        Npca clerk = new Npca("Trevor", "Clinic Clerk", clinicClerkLines, 5, new Health(40, 40, 0), "", Npca.QuestState.NONE);
        firstVilleClinic.getNpc().add(clerk);

        KEY_FIGURES.add(new KeyFigureSpawn("Trevor", firstVilleClinic, () -> {
            Npca npc = new Npca("Tragger", "Clinic Clerk", clinicClerkLines, 5, new Health(40, 40, 0), "", Npca.QuestState.NONE);
            firstVilleClinic.getNpc().add(npc);
        }));

        Merchant Ragger = new Merchant("Ragger", raggerLines, "copper", raggerStock, raggerH, 2, "SQ0", Npca.QuestState.NONE);

        FirstShopOwner baggerOwner = new FirstShopOwner("copper", "Bagger", raggerLines, raggerH, 2, null, "SQ0", Npca.QuestState.NONE);
        baggerShop = new Hub("Ragger's cousin's Gear Shop (BAGGER)", "Cluttered shelves hold weapons and an anvil sits in a corner. Bagger watches you closely.\n").new FirstVilleShop("Ragger's cousin's Gear Shop (BAGGER)", "Cluttered shelves hold weapons and an anvil sits in a corner. Ragger watches you closely. \nEXITS: (N) (W)", baggerOwner);
        baggerShop.addStock(copperSword, 20);
        baggerShop.addStock(sickles, 30);
        baggerOwner.setMyShop(baggerShop);
        baggerShop.addFirstShopOwner(baggerOwner);

        KEY_FIGURES.add(new KeyFigureSpawn("Bagger", baggerShop, () -> {
            FirstShopOwner npc = new FirstShopOwner("copper", "Bagger", raggerLines, new Health(60, 60, 0), 2,  baggerShop, "SQ0", Npca.QuestState.NONE);
            baggerShop.addFirstShopOwner(npc);
            npc.setMyShop(baggerShop);

        }));

        FirstShopOwner laggerOwner = new FirstShopOwner("copper", "Lagger", raggerLines, raggerH, 2, null, "SQ0", Npca.QuestState.NONE);
        Hub.FirstVilleShop laggerShop = new Hub("Bagger's brother in law's Armory Shop (LAGGER)", "Cluttered shelves hold weapons and armor. Lagger is washing a new helmet.\n").new FirstVilleShop("Bagger's brother in law's Armory Shop (LAGGER)", "Cluttered shelves hold weapons and armor. Lagger is washing a new helmet.\nEXITS: (W)", laggerOwner);
        laggerShop.addStock(knightHelmet, 15);
        laggerShop.addStock(knightLeggings, 25);
        laggerShop.addStock(knightChest, 30);
        laggerShop.addStock(knightBoots, 15);
        laggerOwner.setMyShop(laggerShop);

        KEY_FIGURES.add(new KeyFigureSpawn("Lagger", laggerShop, () -> {
            FirstShopOwner npc = new FirstShopOwner("copper", "Lagger", raggerLines, new Health(60, 60, 0), 2,  laggerShop, "SQ0", Npca.QuestState.NONE);
            laggerShop.addFirstShopOwner(npc);
            npc.setMyShop(laggerShop);

        }));

        FirstShopOwner jaggerOwner = new FirstShopOwner("copper", "Jagger", raggerLines, raggerH, 2, null, "SQ0", Npca.QuestState.NONE);
        Hub.FirstVilleStringShop jaggerShop = new Hub("Lagger's wife and Bagger's sister(JAGGER)", "You see glowing scrolls in bookshelves.\n").new FirstVilleStringShop("Lagger's wife and Bagger's sister (JAGGER)", "You see glowing scrolls in bookshelves..\nEXITS: (S)", jaggerOwner);
        jaggerShop.addStock("fire scroll 1", 30);
        jaggerShop.addStock("protection scroll 1", 30);
        jaggerShop.addFirstShopOwner(jaggerOwner);
        jaggerOwner.setMyShop(jaggerShop);

        KEY_FIGURES.add(new KeyFigureSpawn("Jagger", jaggerShop, () -> {
            FirstShopOwner npc = new FirstShopOwner("copper", "Jagger", raggerLines, new Health(60, 60, 0), 2,  null, "SQ0", Npca.QuestState.NONE);
            jaggerShop.addFirstShopOwner(npc);
            npc.setMyShop(jaggerShop);

        }));

        FirstShopOwner steveOwner = new FirstShopOwner("copper", "Steve", raggerLines, raggerH, 2, null, "SQ0", Npca.QuestState.NONE);
        Hub.FirstVilleStringShop stevesShop = new Hub("Not part of the -agger family (STEVE)", "Various items that can assist you line the room.\n").new FirstVilleStringShop("Not part of the -agger family (STEVE)", "Various items that can assist you line the room..\nEXITS: (S)", steveOwner);
        stevesShop.addStock("apple", 1);
        stevesShop.addFirstShopOwner(steveOwner);
        steveOwner.setMyShop(stevesShop);

        KEY_FIGURES.add(new KeyFigureSpawn("Steve", stevesShop, () -> {
            FirstShopOwner npc = new FirstShopOwner("copper", "Steve", raggerLines, new Health(60, 60, 0), 2,  null, "SQ0", Npca.QuestState.NONE);
            stevesShop.addFirstShopOwner(npc);
            npc.setMyShop(stevesShop);

        }));

        //Shop Exits
        firstVilleLane4.setExit("s", baggerShop);
        firstVilleLane2.setExit("e", baggerShop);
        baggerShop.setExit("n", firstVilleLane4);
        baggerShop.setExit("w", firstVilleLane2);
        firstVilleLane5.setExit("e", laggerShop);
        laggerShop.setExit("w", firstVilleLane5);
        firstVilleSquare.setExit("e", exchangeBooth);
        exchangeBooth.setExit("w", firstVilleSquare);
        jaggerShop.setExit("n", firstVilleLane30);
        firstVilleLane30.setExit("n", jaggerShop);
        stevesShop.setExit("e", firstVilleLane20);
        firstVilleLane20.setExit("w", stevesShop);


        Guard Oliver = new Guard("Oliver", oliverLines, oliverP, 8, FirstVilleHelm, FirstVillePlate, FirstVilleLegs, FirstVilleBoots, "MQ3", Npca.QuestState.NONE);

        KEY_FIGURES.add(new KeyFigureSpawn("Oliver", firstVilleGate, () -> {
            Guard newOliver = new Guard("Oliver", oliverLines, new Health(60, 60, 0), 8, FirstVilleHelm, FirstVillePlate, FirstVilleLegs, FirstVilleBoots, "MQ3", Npca.QuestState.NONE);
            firstVilleGate.getGuard().add(newOliver);
        }));

        firstVilleGate.getGuard().add(Oliver);

        Story(cave, existingItems, equipment, enchantment1List);

    }

    public static void Story(Hub cave, List<Item> existingItems, Equipment equipment, List<Enchantment1> enchantment1s) {

        // Makes the room you are in is whatever you need to test...
        //Hub inRoom = Hub.get("West port");
        Hub inRoom = cave;

        boatLocation = Hub.get("West port");
        //List of verbs
        List<String> verbs = new ArrayList<>();
        verbs.add("ride");
        verbs.add("move");
        verbs.add("fight");
        verbs.add("use");
        verbs.add("go");
        verbs.add("pick up");
        verbs.add("grab");
        verbs.add("look");
        verbs.add("open");
        verbs.add("drop");
        verbs.add("remove");
        verbs.add("take");
        verbs.add("get");
        verbs.add("read");
        verbs.add("inspect");
        verbs.add("wait");
        verbs.add("listen");
        verbs.add("lock");
        verbs.add("close");
        verbs.add("help");
        verbs.add("fame");
        verbs.add("l");
        verbs.add("inventory");
        verbs.add("kill");
        verbs.add("attack");
        verbs.add("stats");
        verbs.add("equip");
        verbs.add("unequip");
        verbs.add("quests");
        verbs.add("talk");
        verbs.add("eat");


        List<String> verbsOnly = new ArrayList<>();
        verbsOnly.add("n");
        verbsOnly.add("s");
        verbsOnly.add("w");
        verbsOnly.add("e");
        verbsOnly.add("u");
        verbsOnly.add("d");
        verbsOnly.add("fame");
        verbsOnly.add("north");
        verbsOnly.add("south");
        verbsOnly.add("up");
        verbsOnly.add("down");
        verbsOnly.add("quests");
        verbsOnly.add("west");
        verbsOnly.add("east");
        verbsOnly.add("look");
        verbsOnly.add("time");
        verbsOnly.add("l");
        verbsOnly.add("rest");
        verbsOnly.add("wait");
        verbsOnly.add("listen");
        verbsOnly.add("help");
        verbsOnly.add("stats");
        verbsOnly.add("sleep");
        verbsOnly.add("bag");


        List<String> directions = new ArrayList<>();
        directions.add("w");
        directions.add("e");
        directions.add("s");
        directions.add("n");
        directions.add("d");
        directions.add("u");
        directions.add("west");
        directions.add("east");
        directions.add("south");
        directions.add("north");
        directions.add("up");
        directions.add("down");

        List<String> upways = new ArrayList<>();
        upways.add("up");
        upways.add("u");

        List<String> downways = new ArrayList<>();
        downways.add("down");
        downways.add("d");

        List<String> northways = new ArrayList<>();
        northways.add("north");
        northways.add("n");

        List<String> easyways = new ArrayList<>();
        easyways.add("east");
        easyways.add("e");

        List<String> westways = new ArrayList<>();
        westways.add("west");
        westways.add("w");

        List<String> southways = new ArrayList<>();
        southways.add("south");
        southways.add("s");

        List<String> exchange = new ArrayList<>();
        exchange.add("exchange");
        exchange.add("booth");
        exchange.add("exchange booth");

        List<String> movements = new ArrayList<>();
        movements.add("go");
        movements.add("move");

        List<String> rest = new ArrayList<>();
        rest.add("rest");
        rest.add("sit down");
        rest.add("laydown");
        rest.add("take a break");
        rest.add("close your eyes");

        List<String> objects = new ArrayList<>();
        //ARMOR/WEAPONS
        objects.add("firstville guards plate");
        objects.add("firstville guards helm");
        objects.add("firstville guards boots");
        objects.add("firstville guards legs");
        objects.add("leather armors");
        objects.add("leather armor");
        objects.add("bear claws");
        objects.add("bear claw");
        objects.add("bear swords");
        objects.add("bear sword");
        objects.add("pirate thing");
        objects.add("wizard thingy");
        objects.add("wizard puncher");

        //MOBS
        objects.add("blood witch");
        objects.add("fox witch");
        objects.add("forest devil");
        objects.add("mama bear");
        objects.add("mute bandit");

        //ITEMS
        objects.add("scuba mask");
        objects.add("bear essence");
        objects.add("golden apples");
        objects.add("golden apple");
        objects.add("white whispberries");
        objects.add("white whispberry");
        objects.add("wolf bane soups");
        objects.add("wolf bane soup");
        objects.add("raven eye");
        objects.add("raven eyes");
        objects.add("blood vials");
        objects.add("blood vial");
        objects.add("goblin teeth");
        objects.add("goblin tooth");
        objects.add("rabbit hides");
        objects.add("rabbit hide");
        objects.add("squirrel hides");
        objects.add("squirrel hide");
        objects.add("bear hides");
        objects.add("bear hide");
        objects.add("chicken feathers");
        objects.add("chicken feather");


        //OTHER SMALLER THINGS
        objects.add("inventory");
        objects.add("stove");
        objects.add("bill");
        objects.add("zak");
        objects.add("horsey");
        objects.add("clothes");
        objects.add("cloth");
        objects.add("leaflet");
        objects.add("dagger");
        objects.add("cauldron");
        objects.add("scroll");
        objects.add("tree");
        objects.add("wheats");
        objects.add("wheat");
        objects.add("exchange");
        objects.add("cabinet");
        objects.add("leaflet");
        objects.add("daggers");
        objects.add("dagger");
        objects.add("inventory");
        objects.add("apples");
        objects.add("apple");
        objects.add("oranges");
        objects.add("orange");
        objects.add("golds");
        objects.add("gold");
        objects.add("silvers");
        objects.add("silver");
        objects.add("coppers");
        objects.add("copper");
        objects.add("twigs");
        objects.add("twig");
        objects.add("snarkflowers");
        objects.add("snarkflower");
        objects.add("door");
        objects.add("rabbit");
        objects.add("chicken");
        objects.add("squirrel");
        objects.add("goblin");
        objects.add("bear");
        objects.add("chest");
        objects.add("key");
        objects.add("oliver");
        objects.add("bagger");
        objects.add("lagger");
        objects.add("ragger");
        objects.add("jagger");
        objects.add("tragger");
        objects.add("trevor");
        objects.add("steve");
        objects.add("maratamara");
        objects.add("gate");
        objects.add("traps");
        objects.add("trap");
        objects.add("wolfbanes");
        objects.add("wolfbane");
        objects.add("wolf");
        objects.add("cauldron");
        objects.add("scroll");
        objects.add("tree");
        objects.add("acorns");
        objects.add("acorn");
        objects.add("jerr");
        objects.add("boat");
        objects.add("button");
        objects.add("daruma");
        objects.add("badge");
        objects.add("teacup");
        objects.add("badge");
        objects.add("ball");

        List<String> yesOrYes = new ArrayList<>();
        yesOrYes.add("y");
        yesOrYes.add("yes");
        yesOrYes.add("yah");
        yesOrYes.add("sure");
        yesOrYes.add("do it");

        List<String> look = new ArrayList<>();
        look.add("l");
        look.add("look");

        List<String> wait = new ArrayList<>();
        wait.add("wait");

        List<String> use = new ArrayList<>();
        use.add("use");
        use.add("activate");


        List<String> stats = new ArrayList<>();
        stats.add("stats");

        List<String> sleep = new ArrayList<>();
        sleep.add("sleep");

        List<String> listen = new ArrayList<>();
        listen.add("listen");

        List<String> bag = new ArrayList<>();
        bag.add("bag");

        List<String> ride = new ArrayList<>();
        ride.add("ride");

        List<String> open = new ArrayList<>();
        open.add("open");

        List<String> close = new ArrayList<>();
        close.add("close");

        List<String> take = new ArrayList<>();
        take.add("take");
        take.add("pick up");
        take.add("grab");


        List<String> get = new ArrayList<>();
        get.add("get");

        List<String> read = new ArrayList<>();
        read.add("read");

        List<String> drop = new ArrayList<>();
        drop.add("drop");

        Map<String, Integer> food = new HashMap<>();
        food.put("acorn", 1);
        food.put("apple", 2);
        food.put("orange", 2);
        food.put("apple pie", 3);
        food.put("wolfs bane soup", 4);
        food.put("golden apple", 88);

        List<String> remove = new ArrayList<>();
        remove.add("remove");

        List<String> inspect = new ArrayList<>();
        inspect.add("inspect");
        inspect.add("examine");   // alt word
        inspect.add("look at");

        List<String> cabinet = new ArrayList<>();
        cabinet.add("cabinet");

        List<String> cauldron = new ArrayList<>();
        cauldron.add("cauldron");
        cauldron.add("stove");

        List<String> scroll = new ArrayList<>();
        scroll.add("scroll");

        List<String> door = new ArrayList<>();
        door.add("door");

        List<String> inventory = new ArrayList<>();//has items

        List<String> inventoryinventory = new ArrayList<>();//calls inventory
        inventoryinventory.add("inventory");

        List<String> help = new ArrayList<>();
        help.add("help");

        List<String> eat = new ArrayList<>();
        eat.add("eat");

        List<String> kill = new ArrayList<>();
        kill.add("kill");
        kill.add("fight");

        List<String> attack = new ArrayList<>();
        attack.add("attack");

        List<String> chests = new ArrayList<>();
        chests.add("chest");

        List<String> equip = new ArrayList<>();
        equip.add("equip");
        equip.add("use");

        List<String> unequip = new ArrayList<>();
        unequip.add("unequip");

        List<String> talk = new ArrayList<>();
        talk.add("talk");

        List<String> gate = new ArrayList<>();
        gate.add("gate");

        List<String> scrollTypes = new ArrayList<>();
        scrollTypes.add("fire scroll 1");
        scrollTypes.add("fire scroll 2");
        scrollTypes.add("fire scroll 3");
        scrollTypes.add("protection scroll 1");
        scrollTypes.add("protection scroll 2");
        scrollTypes.add("protection scroll 3");
        scrollTypes.add("roulette scroll 1");
        scrollTypes.add("roulette scroll 2");
        scrollTypes.add("roulette scroll 3");


        // INSERT ALL CODE THAT SETS A VARIABLE HERE!!!!!

        boolean cabinetCaveN = false;

        boolean start;

        // Asks if you want to play

        System.out.print("Enter a name: ");
        String namer = scanner.nextLine();
        System.out.println("");

        Player player = new Player(namer, 10, 10, 5, 0, 20);

        XpLv playersStats = new XpLv(1, 0);

        System.out.println("");

        if (namer.equalsIgnoreCase("monty python")) {
            System.out.println("You feel the power of MONTY flow through your vanes... You don't think you can die.");
        }

        if (namer.equalsIgnoreCase("admin0")) {

            if (namer.equalsIgnoreCase("admin0")) {
                System.out.println("Admin Code received.");

                System.out.print("Enter MAX health \n-> ");
                String setHealthMAX = scanner.nextLine().trim();
                System.out.print("Enter damage \n-> ");
                String setDamage = scanner.nextLine().trim();
                System.out.print("Enter damage resistance\n-> ");
                String setReist = scanner.nextLine().trim();
                try {
                    player.getHealth().setMaxHealth(Integer.parseInt(setHealthMAX));
                    player.getHealth().setHeealth(player.getHealth().getMaxHealth());
                    player.setAttackPower(Integer.parseInt(setDamage));
                    player.getHealth().setDamageResistance(Integer.parseInt(setReist));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numbers, using defaults.");
                }

                System.out.println("(12 is next to sHouse) Southern Forest Area #--");
                System.out.println("(50 is close to firstville) Northern Forest Area #--");
                System.out.println("(living room/kitchen etc.) Tom's Dark --- \n(clinic is n to lane3) FirstVille Streets #--");

                System.out.print("Enter target room\n-> ");
                String gotoRoomBlah = scanner.nextLine();
                if (gotoRoomBlah != null) {
                    try {
                        if (!gotoRoomBlah.equalsIgnoreCase("d") && !gotoRoomBlah.equalsIgnoreCase("g") && !gotoRoomBlah.equalsIgnoreCase("bagger") && !gotoRoomBlah.equalsIgnoreCase("")) {
                            inRoom = Hub.get(gotoRoomBlah);
                            System.out.println("Boom. You're There.");
                        } else if (gotoRoomBlah.equalsIgnoreCase("d")) {
                            System.out.println("Code accepted.");
                        } else if (gotoRoomBlah.equalsIgnoreCase("gate1")) {
                            inRoom = Hub.get("Gate of FirstVille");
                            System.out.println("You are at the gate.");
                        } else if (gotoRoomBlah.equalsIgnoreCase("bagger")) {
                            inRoom = Game.baggerShop;
                            System.out.println("MEET BAGGER - welcome");
                        } else {
                            inRoom = Hub.get("Tom's Dark Cave");
                            System.out.println("default input received.");
                        }
                    } catch (NullPointerException e) {
                        inRoom = Hub.get("Tom's Dark Cave");

                    }

                }

                System.out.println(inRoom.getObjects());

                for (int i = 0; i < 500; i++) {
                    inRoom.getObjects().add("gold");
                }

                for (int i = 0; i < 500; i++) {
                    inRoom.getObjects().add("copper");
                }

                for (int i = 0; i < 500; i++) {
                    inRoom.getObjects().add("silver");
                }

                System.out.println("$#bonus stats have been successfully distributed");
                scanner.nextLine();
            }

        } else if (namer.equalsIgnoreCase("jack sparrow")) {

            for (int i = 0; i < 300; i++) {
                inRoom.getObjects().add("gold");
            }

            for (int i = 0; i < 300; i++) {
                inRoom.getObjects().add("copper");
            }

            for (int i = 0; i < 300; i++) {
                inRoom.getObjects().add("silver");
            }

            inRoom.getObjects().add("scuba mask");

            inRoom.getObjects().add("pirate thing");

            System.out.println("WOWZA! YOU FEEL AS IF YOU CAN TAKE ON ANYTHING OUT ON THE SEA! AND YOUR RICH...");

        } else if (namer.equalsIgnoreCase("one punch man")) {

            player.getHealth().setMaxHealth(1);
            player.getHealth().setHeealth(player.getHealth().getMaxHealth());
            player.setAttackPower(999999);

            System.out.println("Yeah, I know. I'm ONE PUNCH MAN. ONE PUNCH, ONE WIN. ");

        } else if (namer.equalsIgnoreCase("wizard")) {

            player.setAttackPower(-4);

            player.getHealth().setMaxHealth(100000);
            player.getHealth().setHeealth(player.getHealth().getMaxHealth());

            inRoom.getObjects().add("wizard puncher");

            inRoom.getObjects().add("wizard thingy");

            inRoom.getObjects().add("fire scroll 1");

            inRoom.getObjects().add("protection scroll 1");

            System.out.println("I think I am a WIZARD...");

        } else if (namer.equalsIgnoreCase("cheapo")) {

            player.getHealth().setMaxHealth(100000);
            player.getHealth().setHeealth(player.getHealth().getMaxHealth());
            player.setAttackPower(-4);
            inRoom.getObjects().add("acorn");
            inRoom.getObjects().add("acorn");
            inRoom.getObjects().add("acorn");
            inRoom.getObjects().add("acorn");
            inRoom.getObjects().add("acorn");

            System.out.println("I think I am a...");
            System.out.println("");

        }

        start = true;

        System.out.println("The world is LAUNCHING...");

        System.out.println("");

        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("BUILDING the scenery...");

        System.out.println("");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Eat food during battle to heal and fill your stomach!");

        System.out.println("");

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("PREPPING the mobs for your descent...");

        System.out.println("");

        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("(hint: use help to 'help')");

        System.out.println("");

        try {
            Thread.sleep(285);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        player.displayStats(player, playersStats);

        System.out.println("");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println(inRoom.getRoomDescription());

        player.addSkills(new Skill.StunSkill("Stun", "Stuns enemy for one turn. Cooldown: 3 turns.", true, 4)); //PUT IN ONE MORE THAN COOLDOWN TURNS

        player.addSkills(new Skill.HardeningSkill("Harden", "Hardens your body for one turn. Cooldown: 1 turn", true, 2));

        for (Skill a : player.getSkills()){
            System.out.println(a.getName());
        }

        setupDayNightSchedulers();

        movingTheMobs();

        setupRespawnScheduler();

        setupHealthRegen(player);


        //While start is true, run the code
        while (start) {

            if (player.getName().equalsIgnoreCase("monty python")) {

                if (player.getHealth().isDead() || player.getHealth().getHeealth() < player.getHealth().getMaxHealth()) {
                    player.getHealth().setHeealth(player.getHealth().getMaxHealth());
                    System.out.println("The power of Monty SURGES through you! You've healed!\n");
                }
            } else if (player.getHealth().isDead()) {

                scannerOrNo = false;

                Scanner scannerdumb = new Scanner(System.in);

                System.out.println("\nSHOCK. AS IF NOTHING BUT THE CHOCO CHIPS ARE LEFT ON THE COOKIE... [enter to continue]");

                scannerdumb.nextLine();

                boolean dissapear = false;

                System.out.println("\nThe world becomes dark... [enter to continue]");

                scannerdumb.nextLine();

                System.out.println("\nOptions: [1] Respawn [2] Quit");
                System.out.print("-> ");

                String choice = scanner.nextLine();

                if (choice.equals("1")) {

                    List<Hub.Clinic> possiblePoints = new ArrayList<>();

                    for (Hub.Clinic clinic : Hub.Clinic.getClinics()) {
                        if (clinic.seeIfRegistered(player.getName())) {
                            possiblePoints.add(clinic);
                        }
                    }

                    if (possiblePoints.isEmpty()) {
                        System.out.println("No clinics registered to respawn at. ");
                        System.out.println("\nOptions: [1] Respawn at 'cave' [2] Quit");
                        System.out.print("-> ");
                        String doubleChoice = scanner.nextLine();

                        System.out.println("[Due to not registering with a clinic, your items were not carried with you during respawn.\nBut they are right where you left them! \n");
                        if (doubleChoice.equals("1")) {
                            List<String> toRemove = new ArrayList<>();
                            for (String i : inventory) {
                                toRemove.add(i);
                            }
                            for (String op : toRemove) {
                                inventory.remove(op);
                                if (equipment.getEquippedItems() != null) {
                                    for (Item i : equipment.getEquippedItems().values()) {

                                        if (i.getName().equalsIgnoreCase(op)) {

                                            unequip(i, i.getSlotType(), player, equipment);
                                            break;

                                        }

                                    }
                                }
                                inRoom.getObjects().add(op);
                            }
                            System.out.println("Respawning...");
                            inRoom = cave;
                            dissapear = true;
                            player.getHealth().setHeealth(player.getHealth().getMaxHealth());
                            player.setFullness();

                        } else {
                            System.out.println("Thanks for playing!");
                        }
                    } else {

                        System.out.println("");

                        String goTo = "gefij";
                        //SAME CODE FOR LEAFLETS ETC.

                        while (goTo != "quit") {

                            int i = 0;
                            for (Hub.Clinic clinic : possiblePoints) {
                                System.out.println((i + 1) + ". " + clinic.getRoomName());
                                i++;
                            }

                            System.out.println("Please select your destination (number) or 'quit'.");
                            System.out.print("-> ");
                            goTo = scanner.nextLine().trim();

                            Hub.Clinic destination = null;

                            try {
                                int index = Integer.parseInt(goTo) - 1;
                                if (index >= 0 && index < possiblePoints.size()) {
                                    destination = possiblePoints.get(index);
                                    inRoom = destination;
                                    int gold = 0;
                                    int silver = 0;
                                    int copper = 0;
                                    for (String p : inventory) {
                                        if (p.equalsIgnoreCase("gold")) {
                                            gold++;
                                        }
                                        if (p.equalsIgnoreCase("silver")) {
                                            silver++;
                                        }
                                        if (p.equalsIgnoreCase("copper")) {
                                            copper++;
                                        }
                                    }

                                    gold /= 2;
                                    silver /= 2;
                                    copper /= 2;

                                    for (int w = gold; w > 0; w--) {
                                        inventory.remove("gold");
                                    }
                                    for (int w = silver; w > 0; w--) {
                                        inventory.remove("silver");
                                    }
                                    for (int w = copper; w > 0; w--) {
                                        inventory.remove("copper");
                                    }

                                    dissapear = true;
                                    System.out.println("You wake up, feeling a bit lighter, but still alive. [Registering with that clinic sure saved you!]");
                                    player.getHealth().setHeealth(player.getHealth().getMaxHealth());
                                    player.setFullness();

                                    break;
                                }

                            } catch (NumberFormatException e) {
                                if (goTo.equalsIgnoreCase("quit")) {
                                    System.out.println("Thanks for playing!");
                                    break;
                                }

                                System.out.println("Your target destination was not found. ");

                            }
                        }
                    }

                } else if (choice.equals("2")) {
                    System.out.println("Thanks for playing!");
                }

                if (!dissapear) {
                    break;
                }

                scannerOrNo = true;

            }

            System.out.println("");
            scannerOrNo = false;

            if (timeChange != null && changeTime != null) {

                long secondsAgo = Duration.between(changeTime, LocalTime.now()).getSeconds();

                if (secondsAgo < 60) {
                    System.out.println("It turned " + timeChange + " " + secondsAgo + " seconds ago.");
                } else {
                    secondsAgo /= 60;
                    System.out.println("It turned " + timeChange + " " + secondsAgo + " min ago.");

                }
                timeChange = null;
                changeTime = null;
            }


            System.out.print("-> ");


            String action = scanner.nextLine();

            scannerOrNo = false;

            System.out.println("");
            scannerOrNo = true;
            boolean oneCardinol = oneDirection(action, northways, southways, easyways, westways, upways, downways);

            boolean correctFormat = oneObjectOneVerb(action, verbs, objects);
            if (oneCardinol) {
                System.out.println("You can't input multiple directions!");
            } else if (!correctFormat) {
                System.out.println("Max verbs: 1   ||   Max objects: 1   ||  Try again");
            } else {

                if (action.equalsIgnoreCase("fame")) {
                    System.out.println("FAME: " + player.getCoins());
                } else if (action.equalsIgnoreCase("book")) {

                    player.seeBook();

                } else if (action.equalsIgnoreCase("time")) {
                    String time = "DAY";
                    if (timeChange == null) {
                        time = "NIGHT";
                    }
                    System.out.println("You look up the sky: " + time);
                } else if (action.equalsIgnoreCase("quests")) {

                    int totalQuests = Player.QUESTS.size();
                    int completed = 0;

                    for (Quest q : Player.QUESTS.values()) {
                        if (q.done) completed++;
                    }

                    System.out.println("=== YOUR QUESTS (" + totalQuests + "/11 quests acquired) ==="); // RIGHT NOW THERE AREE ONLY 8 POSSIBLE QUESTS

                    System.out.println("\nCOMPLETED (" + completed + "/" + totalQuests + "):");
                    for (Quest q : Player.QUESTS.values()) {
                        if (q.done) {
                            System.out.println("  ✓ " + q.status());
                        }
                    }
                    System.out.println("\nNOT COMPLETED:");
                    for (Quest q : Player.QUESTS.values()) {
                        if (!q.done) {
                            System.out.println("  - " + q.status());
                        }
                    }

                    System.out.println("========================");
                } else if (stringContainsWordFromList(action.toLowerCase(), verbsOnly.toArray(new String[0])) && !action.contains("pick up")) {

                    if (action.toLowerCase().equals("n") || action.toLowerCase().equals("north")) {

                        Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                        inRoom = newRoom;
                        Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                        snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                    } else if (action.toLowerCase().equals("s") || action.toLowerCase().equals("south")) {
                        // Call the move method and update to inRoom
                        Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                        inRoom = newRoom;
                        Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                        snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));


                    } else if (action.toLowerCase().equals("w") || action.toLowerCase().equals("west")) {
                        // Call the move method and update to inRoom
                        Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                        inRoom = newRoom;
                        Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                        snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                    } else if (action.toLowerCase().equals("e") || action.toLowerCase().equals("east")) {
                        // Call the move method and update to inRoom
                        Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                        inRoom = newRoom;
                        Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                        snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));


                    } else if (action.toLowerCase().equals("u") || action.toLowerCase().equals("up")) {
                        // Call the move method and update to inRoom
                        Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                        inRoom = newRoom;
                        Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                        snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                    } else if (action.toLowerCase().equals("d") || action.toLowerCase().equals("down")) {

                        Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                        inRoom = newRoom;
                        Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                        snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                    } else if (stringContainsWordFromList(action.toLowerCase(), movements.toArray(new String[0]))) {

                        if (stringContainsWordFromList(action.toLowerCase(), directions.toArray(new String[0]))) {

                            if (stringContainsWordFromList(action.toLowerCase(), northways.toArray(new String[0]))) {

                                action = "n";

                                Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                                inRoom = newRoom;
                                Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                                snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                            }

                            if (stringContainsWordFromList(action.toLowerCase(), upways.toArray(new String[0]))) {
                                action = "u";
                                Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                                inRoom = newRoom;
                                Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                                snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));
                            }

                            if (stringContainsWordFromList(action.toLowerCase(), downways.toArray(new String[0]))) {
                                action = "d";
                                Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                                inRoom = newRoom;
                                Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                                snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));
                            }

                            if (stringContainsWordFromList(action.toLowerCase(), southways.toArray(new String[0]))) {

                                action = "s";

                                Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                                inRoom = newRoom;
                                Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                                snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                            }

                            if (stringContainsWordFromList(action.toLowerCase(), westways.toArray(new String[0]))) {

                                action = "w";

                                Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                                inRoom = newRoom;
                                Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                                snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                            }

                            if (stringContainsWordFromList(action.toLowerCase(), easyways.toArray(new String[0]))) {

                                action = "e";

                                Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment, food, inventory);
                                inRoom = newRoom;
                                Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                                snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                            }

                        } else {
                            System.out.println("You can't just '" + action + "'");
                        }

                    } else if (stringContainsWordFromList(action.toLowerCase(), look.toArray(new String[0]))) {
                        System.out.println(inRoom.getRoomName());
                        System.out.println(inRoom.getRoomDescription());
                        System.out.println("");

                        if (!inRoom.getObjects().isEmpty()) {
                            itemsIfAny(inRoom.getObjects(), "Items in room: ");
                        }
                        System.out.println("");

                        List<String> mobNames = new ArrayList<>();

                        for (Mob mob : inRoom.getMOBS()) {

                            mobNames.add(mob.getName());

                        }

                        if (!mobNames.isEmpty()) {
                            mobsIfAny(mobNames, "Mobs in room: ");
                        }

                        List<String> bossNames = new ArrayList<>();

                        for (Boss boss : inRoom.getBoss()) {

                            bossNames.add(boss.getName());

                        }

                        if (!bossNames.isEmpty()) {
                            mobsIfAny(bossNames, "Boss's in room: ");
                        }


                        List<String> npcNames = new ArrayList<>();

                        for (Npca npc : inRoom.getNpc()) {
                            npcNames.add(npc.getName());
                        }

                        for (Guard npc : inRoom.getGuard()) {
                            npcNames.add(npc.getName());
                        }

                        for (Merchant npc : inRoom.getMerchant()) {
                            npcNames.add(npc.getName());
                        }

                        if (inRoom.getFirstShopOwners() != null) {
                            npcNames.add(inRoom.getFirstShopOwners().getName());
                        }

                        if (!npcNames.isEmpty()) {
                            npcIfAny(npcNames, "NPC's in room: ");
                        }

                    } else if (stringContainsWordFromList(action.toLowerCase(), wait.toArray(new String[0]))) {
                        System.out.println("You are waiting (hint: nothing happens by waiting)...");
                    } else if (stringContainsWordFromList(action.toLowerCase(), listen.toArray(new String[0]))) {
                        listening(inRoom);
                    } else if (stringContainsWordFromList(action.toLowerCase(), help.toArray(new String[0]))) {
                        help(verbs);
                    } else if (stringContainsWordFromList(action.toLowerCase(), stats.toArray(new String[0]))) {
                        player.displayStats(player, playersStats);
                    } else if (stringContainsWordFromList(action.toLowerCase(), bag.toArray(new String[0]))) {

                        if (playersStats.getLevel() < 5) {
                            System.out.println("Reach LV. 5 to unlock mobile shopping!");

                        } else {

                            mobileBag(player, inventory);

                        }

                    } else if (stringContainsWordFromList(action.toLowerCase(), rest.toArray(new String[0]))) {

                        System.out.println("You can rest up to [" + (player.getStomachSize() - player.getFullness()) + "] fullness/energy points. How many points would you like to regain (1sec per 3 points)?");
                        System.out.print("-> ");
                        String healing = scanner.nextLine();
                        try {
                            int chosen = Integer.parseInt(healing);
                            System.out.println("You begin resting...");
                            try {
                                Thread.sleep(chosen * 333);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            player.addFullness(chosen);
                            if (player.getFullness() > player.getStomachSize()) {
                                player.setFullness();
                            }

                        } catch (NumberFormatException ignored) {
                            System.out.println("Something went wrong as you tried to rest and you jumped back to your feet...");
                        }

                    } else if (stringContainsWordFromList(action.toLowerCase(), sleep.toArray(new String[0]))) {

                        if (inRoom.getStructure() != null) {

                            if (!inRoom.getStructure().equalsIgnoreCase("bed")) {


                                System.out.println("(( In order to SLEEP, there must be a bed within vicinity");

                            } else {

                                boolean choco = inRoom.markSleepUsed(player);

                                if (choco) {

                                    System.out.println("You climb into the bed, lay your head down, and close your eyes...");

                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }

                                    System.out.println("YOU WAKE UP! FULL OF ENERGY!");

                                    if (timeChange == null) {

                                        System.out.println("You look around. It's clearly daytime now.");
                                        timeChange = "day";
                                        changeTime = LocalTime.now();

                                    }

                                    player.getHealth().setHeealth(player.getHealth().getMaxHealth());
                                    player.setFullness();
                                }

                            }
                        } else {
                            System.out.println("(( In order to SLEEP, there must be a bed within vicinity");
                        }

                    }

                } else if (stringContainsWordFromList(action.toLowerCase(), verbs.toArray(new String[0])) || action.contains("pick up")) {
                    if (stringContainsWordFromList(action.toLowerCase(), objects.toArray(new String[0])) || action.toLowerCase().contains("leaflet") || action.toLowerCase().contains("key")) {

                        if (stringContainsWordFromList(action.toLowerCase(), eat.toArray(new String[0]))) {

                            if (player.getFullness() < player.getStomachSize()) {
                                for (String key : food.keySet()) {

                                    if (action.contains(key)) {

                                        int hungerBonus = food.get(key);
                                        player.addFullness(hungerBonus);
                                        if (key.contains("golden")) {
                                            System.out.println("You've eaten a super food! [max health is discarded in this scenario]");
                                        } else {
                                            if (player.getFullness() > player.getStomachSize()) {
                                                player.setFullness();
                                                hungerBonus = -898;
                                            }
                                        }

                                        if (hungerBonus != -898) {
                                            System.out.println("Your fullness increases by " + hungerBonus + "! [fullness: " + player.getFullness() + "]");
                                        } else {
                                            System.out.println("You smirk as you feel your stomach fill substantially.");
                                        }

                                        inventory.remove(key);

                                        if (player.getHealth().getHeealth() < player.getHealth().getMaxHealth()) {
                                            hungerBonus *= 0.65;
                                            if (hungerBonus > 0) {
                                                player.getHealth().setHeealth(player.getHealth().getHeealth() + hungerBonus);
                                                if (player.getHealth().getHeealth() > player.getHealth().getMaxHealth()) {
                                                    player.getHealth().setHeealth(player.getHealth().getMaxHealth());
                                                }
                                                System.out.println("Your health has increased as a bonus!");
                                            }
                                        }

                                    } else {
                                        System.out.println("hmmm. Your gonna have trouble doing that...");
                                    }
                                }


                            } else {
                                System.out.println("No need to eat! You are full! ");

                            }

                        } else if (stringContainsWordFromList(action.toLowerCase(), new String[]{"ride"})) {
                            boolean didRide = false;

                            for (Npca npc : new ArrayList<>(inRoom.getNpc())) {
                                if (npc.getName().equalsIgnoreCase("Bill")) {
                                    Npca horseRef = null;
                                    for (Npca n : inRoom.getNpc()) {
                                        if (n.getName().equalsIgnoreCase("horsey")) {
                                            horseRef = n;
                                            break;
                                        }
                                    }
                                    Hub newRoom = rideWithBill(inRoom, npc, horseRef, player, inventory);
                                    inRoom = newRoom;
                                    didRide = true;
                                    break;
                                }

                            }

                            if (!didRide) {
                                System.out.println("There is nothing here you can ride.");
                            }
                        } else if (stringContainsWordFromList(action.toLowerCase(), use.toArray(new String[0]))) {

                            if (action.toLowerCase().contains("boat")) {

                                if (!player.ownsBoat()) {
                                    System.out.println("You see a boat, but it doesn't belong to you.");
                                    System.out.println("Maybe you should buy it frpm the shop keeper first.");
                                } else {

                                    if (onBoat) {
                                        onBoat = false;
                                        System.out.println("You hop out of the boat and it stays here, bobbing in the water.");
                                        boatLocation = inRoom;

                                    } else {

                                        if (boatLocation == inRoom) {
                                            onBoat = true;
                                            System.out.println("You climb into the boat.");
                                        } else {
                                            System.out.println("There is no boat here to use.");
                                        }
                                    }
                                }
                            }

                            if (stringContainsWordFromList(action.toLowerCase(), cauldron.toArray(new String[0]))) {

                                if (inRoom.getRoomName().equalsIgnoreCase("Northern Forest Area #29")) {
                                    cauldron(inventory, player);
                                } else {
                                    System.out.println("You are lacking the ability to find one to use...");
                                }

                            }

                            if (stringContainsWordFromList(action.toLowerCase(), exchange.toArray(new String[0]))) {
                                if (inRoom instanceof Hub.CurrencyExchangeBooth) {
                                    ((Hub.CurrencyExchangeBooth) inRoom).exchangeCurrency(player, inventory);
                                }
                            }

                            if (stringContainsWordFromList(action.toLowerCase(), scroll.toArray(new String[0]))) {

                                int scrollLevel = 0;
                                String chosenType = "choco";

                                if (!action.contains("fire") && !action.contains("protection") && !action.contains("roulette")) {
                                    boolean fank = false;

                                    while (!fank) {
                                        System.out.println("Please enter the type of scroll (fire | protection | roulette | or 'quit)");
                                        System.out.print("-> ");
                                        String tester = scanner.nextLine();
                                        if (tester.equalsIgnoreCase("quit")) {
                                            action = "quit";
                                            fank = true;
                                        } else if (tester.equalsIgnoreCase("fire")) {
                                            action = action + " fire";
                                            fank = true;

                                        } else if (tester.equalsIgnoreCase("roulette")) {
                                            action = action + " roulette";
                                            fank = true;

                                        } else if (tester.equalsIgnoreCase("protection")) {
                                            action = action + " protection";
                                            fank = true;

                                        } else {
                                            System.out.println("");
                                        }
                                    }
                                }

                                if (action.contains("fire") || action.contains("protection")) {
                                    if (action.contains("fire")) {
                                        chosenType = "fire";
                                    } else if (action.contains("protection")) {
                                        chosenType = "protection";

                                    } else if (action.contains("roulette")) {
                                        chosenType = "roulette";
                                    }

                                    boolean hasFire = false;

                                    for (String i : inventory) {

                                        if (i.contains(chosenType)) {
                                            hasFire = true;
                                            break;

                                        }
                                    }

                                    if (!hasFire) {
                                        System.out.println("You do not seem to have a '" + chosenType + "' scroll!");
                                        action = "quit";
                                    }

                                    if (hasFire) {
                                        boolean decidedNumber = false;
                                        while (!decidedNumber) {

                                            decidedNumber = true;

                                            scrollLevel = extractNumber(action);

                                            if (scrollLevel <= 0) {

                                                System.out.println("SCROLL LEVEL - Options: [1] [2] [3] or 'quit'");
                                                System.out.print("-> ");
                                                action = scanner.nextLine();

                                                decidedNumber = false;
                                                if (action.equalsIgnoreCase("quit")) {
                                                    System.out.println("No scroll used.");
                                                    break;
                                                }
                                            }

                                        }
                                    }

                                }

                                if (!action.equalsIgnoreCase("quit")) {
                                    String theScroll = "choco";

                                    if (scrollLevel != 1 && scrollLevel != 2 && scrollLevel != 3) {
                                        System.out.println("There is no such scroll with that level.");
                                    } else {

                                        int aScrollLevel = 0;

                                        for (String w : inventory) {
                                            if (w.contains(chosenType)) {

                                                try {

                                                    aScrollLevel = extractNumber(w);

                                                } catch (NumberFormatException e) {

                                                }

                                                if (aScrollLevel == scrollLevel) {
                                                    theScroll = w;
                                                    break;
                                                }
                                            }
                                        }

                                        if (!theScroll.equalsIgnoreCase("choco")) {

                                            if (theScroll.contains("protection")) {

                                                if (equipment.getEquippedItems() != null) {

                                                    List<Item> equippedList = new ArrayList<>(equipment.getEquippedItems().values());

                                                    for (Item item : equippedList) {

                                                        if (item.getSlotType().equalsIgnoreCase("melee")) {
                                                            equippedList.remove(item);
                                                        }

                                                    }

                                                    boolean continueA = false;
                                                    Item choice = null;


                                                    if (equippedList.isEmpty()) {
                                                        System.out.println("No armor equipped.");
                                                        continueA = true;
                                                    } else {

                                                        System.out.println("What armor would you like to apply this enchantment to?");

                                                        while (choice == null) {

                                                            int index = 1;
                                                            for (Item waga : equippedList) {
                                                                String slot = "";
                                                                for (Map.Entry<String, Item> entry : equipment.getEquippedItems().entrySet()) {
                                                                    if (entry.getValue() == waga) {
                                                                        slot = entry.getKey();
                                                                        break;
                                                                    }
                                                                }
                                                                System.out.println(index + ") [" + slot + ": " + waga.getName() + "]");
                                                                index++;
                                                            }
                                                            System.out.println("Which armor do you want to select (number or 'quit') ? ");
                                                            System.out.print("-> ");

                                                            String answer = Game.scanner.nextLine().trim().toLowerCase();

                                                            if (answer.equalsIgnoreCase("quit")) {
                                                                System.out.println("Action cancelled: quit");
                                                                continueA = true;
                                                                break;
                                                            }
                                                            try {
                                                                int chosen = Integer.parseInt(answer) - 1;
                                                                if (chosen >= 0 && chosen < equippedList.size()) {
                                                                    choice = equippedList.get(chosen);
                                                                }
                                                            } catch (NumberFormatException ignored) {
                                                                System.out.println("Please enter a number.");
                                                            }
                                                        }
                                                    }


                                                    if (!continueA) {

                                                        if (choice != null) {
                                                            System.out.println("Selected: " + choice.getName());
                                                        }

                                                        if (choice.getEnchantment1() == null) {

                                                            System.out.println("Enchantment '" + chosenType + "' level " + scrollLevel + "has been added to " + choice.getName() + "!");

                                                            for (Enchantment1 enchantment1 : enchantment1s) {
                                                                if (enchantment1.getName().equalsIgnoreCase(theScroll)) {

                                                                    System.out.println("\n[unequipping to apply boost!]");
                                                                    unequip(choice, choice.getSlotType(), player, equipment);

                                                                    choice.setEnchantment1(enchantment1);
                                                                    choice.setIncreaseNum(choice.getIncreaseNum() * 2);
                                                                    inventory.remove(theScroll);

                                                                    equip(choice, equipment, player);
                                                                    System.out.println("\n[BOOST APPLIED!]");

                                                                    break;

                                                                }

                                                            }

                                                        } else if (choice.getEnchantment1().getName().contains(chosenType)) {

                                                            if (choice.getEnchantment1().getLevel() < scrollLevel) {
                                                                System.out.println("Enchantment '" + chosenType + "' level " + scrollLevel + "has been added to " + choice.getName() + "!");
                                                                for (Enchantment1 enchantment1 : enchantment1s) {
                                                                    if (enchantment1.getName().equalsIgnoreCase(theScroll)) {

                                                                        System.out.println("\n[unequipping to apply boost!]");
                                                                        unequip(choice, choice.getSlotType(), player, equipment);

                                                                        choice.setEnchantment1(enchantment1);
                                                                        choice.setIncreaseNum(choice.getIncreaseNum() * 2);
                                                                        inventory.remove(theScroll);

                                                                        equip(choice, equipment, player);
                                                                        System.out.println("\n[BOOST APPLIED!]");

                                                                        break;

                                                                    }

                                                                }


                                                            } else {
                                                                System.out.println("You are unable to equip a scroll of lower or equal level enchantment to a said weapon");
                                                            }

                                                        } else {
                                                            System.out.println("This weapon already has an enchantment!");
                                                        }
                                                    }

                                                } else {
                                                    System.out.println("You have no equipped Items... (when enchanting you can only access equipped things)");
                                                }

                                            } else if (theScroll.contains("fire") || theScroll.contains("roulette")) {

                                                if (equipment.getItemBasedOnSlot("melee", equipment.getEquippedItems()) != null) {
                                                    Item choice = equipment.getItemBasedOnSlot("melee", equipment.getEquippedItems());
                                                    System.out.println("Would you like to apply your scroll to " + choice.getName() + "? [y/n]");
                                                    System.out.print("-> ");

                                                    String yesOrNo = scanner.nextLine();

                                                    for (String i : yesOrYes) {
                                                        if (i.equalsIgnoreCase(yesOrNo)) {
                                                            yesOrNo = "waga2010";

                                                            if (choice.getEnchantment1() == null) {

                                                                System.out.println("Enchantment '" + chosenType + "' level " + scrollLevel + "has been added to " + choice.getName() + "!");
                                                                for (Enchantment1 enchantment1 : enchantment1s) {
                                                                    if (enchantment1.getName().equalsIgnoreCase(theScroll)) {

                                                                        System.out.println("\n[unequipping to apply boost!]");
                                                                        unequip(choice, choice.getSlotType(), player, equipment);
                                                                        choice.setEnchantment1(enchantment1);
                                                                        choice.setIncreaseNum(choice.getIncreaseNum() * 2);
                                                                        inventory.remove(theScroll);

                                                                        equip(choice, equipment, player);
                                                                        System.out.println("\n[BOOST APPLIED!]");

                                                                        break;

                                                                    }

                                                                }
                                                            } else if (choice.getEnchantment1().getName().contains(chosenType)) {

                                                                if (choice.getEnchantment1().getLevel() < scrollLevel) {
                                                                    System.out.println("Enchantment '" + chosenType + "' level " + scrollLevel + "has been added to " + choice.getName() + "!");
                                                                    for (Enchantment1 enchantment1 : enchantment1s) {
                                                                        if (enchantment1.getName().equalsIgnoreCase(theScroll)) {

                                                                            System.out.println("\n[unequipping to apply boost!]");
                                                                            unequip(choice, choice.getSlotType(), player, equipment);

                                                                            choice.setEnchantment1(enchantment1);
                                                                            inventory.remove(theScroll);

                                                                            equip(choice, equipment, player);
                                                                            System.out.println("\n[BOOST APPLIED!]");

                                                                            break;

                                                                        }

                                                                    }


                                                                } else {
                                                                    System.out.println("You are unable to equip a scroll of lower or equal level enchantment to a said weapon");
                                                                }

                                                            } else {
                                                                System.out.println("This weapon already has an enchantment!");
                                                            }

                                                            break;
                                                        }
                                                    }

                                                    if (!yesOrNo.equalsIgnoreCase("waga2010")) {
                                                        System.out.println("No scrolls have been applied.\n");
                                                    }

                                                } else {
                                                    System.out.println("You don't have any weapon equipped. [scrolls must be used on equipped things]");
                                                }


                                            }

                                        } else {
                                            System.out.println("The target scroll was not found in inventory.");
                                        }


                                    }

                                }
                            }

                        } else if (stringContainsWordFromList(action.toLowerCase(), new String[]{"ride"})) {

                            boolean didRide = false;

                            for (Npca npc : inRoom.getNpc()) {
                                if (npc.getName().equalsIgnoreCase("Bill")) {
                                    inventory = talkNpc(inRoom, inventory, playersStats, npc, player);
                                    didRide = true;
                                    break;
                                }
                            }

                            if (!didRide) {
                                System.out.println("There is nothing here you can ride.");
                            }
                        } else if (stringContainsWordFromList(action.toLowerCase(), inspect.toArray(new String[0]))) {

                            String theActio = action.toLowerCase();

                            String target = getInspectTarget(theActio, verbs);

                            if (target.isEmpty()) {
                                System.out.println("");

                            }

                            // 1) try inventory first
                            boolean found = false;

                            for (String itemObj : inventory) {  // assumes existingItems is List<Item>
                                if (itemObj.equalsIgnoreCase(target)) {
                                    found = true;
                                    describeItem(target);
                                    break;

                                }
                            }

                            // 2) then try objects in the room
                            if (!found) {
                                for (String obj : inRoom.getObjects()) {
                                    if (obj.equalsIgnoreCase(target)) {
                                        found = true;
                                        describeItem(target);
                                        break;

                                    }
                                }
                            }

                            //3) then try structure in the room
                            if (!found) {
                                if (inRoom.getStructure() != null) {
                                    if (inRoom.getStructure().equalsIgnoreCase(target)) {
                                        found = true;
                                        describeItem(target);

                                        if (target.equalsIgnoreCase("tree")) {
                                            if (treeReset == true) {
                                                inRoom.getObjects().add("apple");
                                                System.out.println("\nYou look so close an apple falls from the tree onto the ground!");
                                                treeReset = false;
                                                applesFallen++;
                                                if (applesFallen >= 38) {
                                                    inRoom.getObjects().add("golden apple");
                                                    System.out.println("\nYou continue squinting so ferociously that something magical descends from the tree!");
                                                    applesFallen -= 38;
                                                }
                                            } else {
                                                System.out.println("This time, nothing falls from the tree!");
                                            }
                                        }

                                    }
                                }
                            }

                            if (!found) {
                                System.out.println("You can't find anything like that to inspect.");
                            }

                        } else if (stringContainsWordFromList(action.toLowerCase(), open.toArray(new String[0]))) {

                            if (stringContainsWordFromList(action.toLowerCase(), inventory.toArray(new String[0]))) {
                                itemsIfAny(inventory, "Items in inventory: ");
                            } else if (inRoom.getRoomName().equals("Tom's Dark Kitchen") && stringContainsWordFromList(action.toLowerCase(), cabinet.toArray(new String[0]))) {

                                if (!cabinetCaveN) {
                                    System.out.println("You open the cabinet.");
                                    cabinetCaveN = true;

                                    if (cabinetDaggerCaveN) {
                                        System.out.println("A dagger falls out and clatters to the floor...");
                                        inRoom.addObject("dagger");
                                        cabinetDaggerCaveN = false;
                                    }

                                } else {
                                    System.out.println("The cabinet is already open.");
                                }
                                //If dagger is not in room and not in inventory, it falls out and is added to room (SO MAKE SURE TO REMOVE It FROM THE ROOM AT THE START!!!!!
                            } else if (stringContainsWordFromList(action.toLowerCase(), door.toArray(new String[0]))) {
                                Door isDoor = null;
                                String doorDir = null;

                                for (String dir : Arrays.asList("n", "s", "e", "w")) {

                                    if (inRoom.getDoor(dir) != null) {

                                        isDoor = inRoom.getDoor(dir);

                                        doorDir = dir;

                                        break;

                                    }
                                }

                                if (isDoor != null) {
                                    if (!isDoor.isOpen()) {
                                        if (inRoom.getRoomName().equals("Southern Forest Area #12")) {
                                            if (playersStats.getLevel() > 3) {
                                                System.out.println("You open the door to the " + doorDir.toUpperCase() + ".");
                                                isDoor.setOpen(true);
                                            } else {
                                                System.out.println("You are not skilled or strong enough to open this door. Come back with a higher level.");
                                            }
                                        } else {
                                            System.out.println("You open the door to the " + doorDir.toUpperCase() + ".");
                                            isDoor.setOpen(true);

                                        }
                                    } else {
                                        System.out.println("The door is already open.");
                                    }
                                } else {
                                    System.out.println("There is no door here.");
                                }

                            } else if (stringContainsWordFromList(action.toLowerCase(), gate.toArray(new String[0]))) {
                                LockedDoors isGate = null;
                                String gateDir = null;

                                for (String dir : Arrays.asList("n", "s", "e", "w")) {

                                    if (inRoom.getLockedDoor(dir) != null) {

                                        isGate = inRoom.getLockedDoor(dir);

                                        gateDir = dir;

                                        break;

                                    }
                                }

                                if (isGate != null) {
                                    if (!isGate.isOpened()) {
                                        boolean opened = false;
                                        if (inRoom.getRoomName().equals("Gate of FirstVille")) {
                                            for (String item : inventory) {
                                                if (item.equalsIgnoreCase("guard pass")) {
                                                    System.out.println("You open the gate to the " + gateDir.toUpperCase() + ".");
                                                    isGate.setOpened(true);
                                                    opened = true;
                                                    break;
                                                }
                                            }

                                            if (!opened) {
                                                Map<String, Item> equipped = equipment.getEquippedItems();
                                                for (Item item : equipped.values()) {
                                                    if (item.getName().equalsIgnoreCase("firstville guards boots") || item.getName().equalsIgnoreCase("firstville guards legs") || item.getName().equalsIgnoreCase("firstville guards plate") || item.getName().equalsIgnoreCase("firstville guards helm")) {
                                                        System.out.println("You open the gate to the " + gateDir.toUpperCase() + ".");
                                                        isGate.setOpened(true);
                                                        opened = true;
                                                        break;

                                                    }
                                                }
                                            }

                                            if (!opened) {
                                                System.out.println("You cannot open this gate.");
                                            }
                                        } else {
                                            System.out.println("You open the gate to the " + gateDir.toUpperCase() + ".");
                                            isGate.setOpened(true);
                                        }

                                    } else {
                                        System.out.println("The gate is already open.");
                                    }
                                } else {
                                    System.out.println("There is no door here.");
                                }

                            } else if (stringContainsWordFromList(action.toLowerCase(), chests.toArray(new String[0]))) {

                                String theChest = null;
                                List<String> allChests = new ArrayList<>();

                                for (int i = 1; i <= 100; i++) {
                                    String chestCode = String.format("chest%03d", i);
                                    allChests.add(chestCode);
                                }

                                for (String code : allChests) {
                                    if (action.toLowerCase().contains(code.toLowerCase())) {
                                        theChest = code;
                                        break;
                                    }
                                }

                                if (inRoom.getChests() == null || inRoom.getChests().isEmpty()) {
                                    System.out.println("There is no chest here to open.");
                                    return;
                                }

                                if (theChest == null && action.toLowerCase().contains("chest")) {

                                    int chestCount = inRoom.getChests().size();

                                    if (chestCount == 1) {
                                        theChest = inRoom.getChests().keySet().iterator().next();
                                    } else {
                                        // Multiple chests -> ask player which one to open
                                        System.out.println("There is more than one chest in the room.");

                                        // Make an indexed list of chest ids
                                        List<String> chestIds = new ArrayList<>(inRoom.getChests().keySet());


                                        for (int idx = 0; idx < chestIds.size(); idx++) {
                                            String chestId = chestIds.get(idx);
                                            Chest c = inRoom.getChests().get(chestId);

                                            String title = c.getRequiredKeyName();
                                            title = title.replaceAll("[^0-9]", ""); // digits only

                                            System.out.println((idx + 1) + ") [ ID: " + title + "]");
                                        }

                                        System.out.print("Which chest do you want to open (number or id)? - ");
                                        String answer = Game.scanner.nextLine().trim().toLowerCase();

                                        String choiceId = null;

                                        try {
                                            int chosenIndex = Integer.parseInt(answer) - 1;
                                            if (chosenIndex >= 0 && chosenIndex < chestIds.size()) {
                                                choiceId = chestIds.get(chosenIndex);
                                            }
                                        } catch (NumberFormatException ignored) {
                                        }

                                        if (choiceId == null) {
                                            for (String chestId : chestIds) {
                                                if (chestId.toLowerCase().equals(answer)) {
                                                    choiceId = chestId;
                                                    break;
                                                }
                                            }
                                        }

                                        if (choiceId == null) {
                                            System.out.println("You decide not to open any chest.");
                                            return;
                                        }

                                        theChest = choiceId;

                                    }
                                }

                                if (theChest == null) {
                                    System.out.println("There is no corresponding chest here to open.");
                                    return;
                                }

                                Chest chest = inRoom.getChests().get(theChest);

                                if (chest == null) {
                                    System.out.println("There is no corresponding chest here to open.");
                                    return;
                                }

                                if (chest.isOpened()) {
                                    System.out.println("Nothing happens. The chest's magical lid has already vanished.");
                                } else {
                                    if (chest.requiresKey() && !inventory.contains(chest.getRequiredKeyName())) {
                                        System.out.println("You need " + chest.getRequiredKeyName() + " to open this chest.");
                                    } else {

                                        System.out.println("Magic swirls around " + theChest + " and its lid disappears! \n**POOF!!**");
                                        System.out.print("->> ");
                                        for (String item : chest.getContents()) {
                                            inRoom.addObject(item);
                                            System.out.print("[" + item + "] ");
                                        }

                                        System.out.println();
                                        chest.open();
                                    }

                                }
                            }

                        } else if (stringContainsWordFromList(action.toLowerCase(), close.toArray(new String[0]))) {
                            if (inRoom.getRoomName().equals("Tom's Dark Kitchen") && stringContainsWordFromList(action.toLowerCase(), cabinet.toArray(new String[0]))) {
                                if (cabinetCaveN) {
                                    System.out.println("You close the cabinet.");
                                    cabinetCaveN = false;
                                } else {
                                    System.out.println("The cabinet is already closed.");
                                }
                            } else if (stringContainsWordFromList(action.toLowerCase(), door.toArray(new String[0]))) {

                                Door isDoor = null;
                                String doorDir = null;

                                for (String dir : Arrays.asList("n", "s", "e", "w")) {

                                    if (inRoom.getDoor(dir) != null) {

                                        isDoor = inRoom.getDoor(dir);

                                        doorDir = dir;

                                        break;

                                    }
                                }

                                if (isDoor != null) {
                                    if (isDoor.isOpen()) {
                                        System.out.println("You close the door to the " + doorDir.toUpperCase() + ".");
                                        isDoor.setOpen(false);

                                    } else {
                                        System.out.println("The door is already closed.");
                                    }
                                } else {
                                    System.out.println("There is no door here.");
                                }
                            } else if (stringContainsWordFromList(action.toLowerCase(), gate.toArray(new String[0]))) {

                                LockedDoors isGate = null;
                                String gateDir = null;

                                for (String dir : Arrays.asList("n", "s", "e", "w")) {

                                    if (inRoom.getLockedDoor(dir) != null) {

                                        isGate = inRoom.getLockedDoor(dir);

                                        gateDir = dir;

                                        break;

                                    }
                                }

                                if (isGate != null) {
                                    if (isGate.isOpened()) {
                                        System.out.println("You close the gate to the " + gateDir.toUpperCase() + ".");
                                        isGate.setOpened(false);

                                    } else {
                                        System.out.println("The gate is already closed.");
                                    }
                                } else {
                                    System.out.println("There is no gate here.");
                                }
                            }


                        } else if (stringContainsWordFromList(action.toLowerCase(), take.toArray(new String[0])) || stringContainsWordFromList(action.toLowerCase(), get.toArray(new String[0])) || action.contains("pick up")) {
                            boolean validObject = false;
                            boolean allOrNo = false;

                            if (action.startsWith("get all") || action.startsWith("take all") || action.startsWith("pick up all") || action.startsWith("grab all")) {

                                String[] parts = action.split("\\s+");
                                int allIndex = -1;
                                for (int i = 0; i < parts.length; i++) {
                                    if (parts[i].equals("all")) {
                                        allIndex = i;
                                        break;
                                    }
                                }
                                if (allIndex == -1 || allIndex == parts.length - 1) {
                                    System.out.println("Get all what?");
                                    return;
                                }

                                // gret everything after "all"
                                StringBuilder sb = new StringBuilder();
                                for (int i = allIndex + 1; i < parts.length; i++) {
                                    if (i > allIndex + 1)
                                        sb.append(" ");  //add a value to the end of an existing character sequence
                                    //e.g.
                                    //StringBuilder sb = new StringBuilder("Hello");
                                    //sb.append(" World");
                                    //System.out.println(sb); // Output: Hello World
                                    sb.append(parts[i]);
                                }
                                String rawPhrase = sb.toString();  // "white whispberries"

                                // Singularize ONLY the last word
                                String[] phraseWords = rawPhrase.split("\\s+");
                                String lastWord = phraseWords[phraseWords.length - 1];  // "whispberries"
                                String singularLastWord = unPluralizer(lastWord);         // "whispberry"

                                phraseWords[phraseWords.length - 1] = singularLastWord;
                                String searchName = String.join(" ", phraseWords);
                                // String message = String.join("-", "Java", "is", "cool");
                                // message returned is: "Java-is-cool"

                                Iterator<String> it = inRoom.getObjects().iterator();
                                boolean foundAny = false;
                                while (it.hasNext()) {
                                    String obj = it.next();
                                    if (obj.equalsIgnoreCase(searchName)) {
                                        inventory.add(obj);
                                        it.remove();
                                        foundAny = true;
                                        validObject = true;
                                        Player.QUESTS.forEach((id, q) -> q.check("COLLECT_ITEM", obj, player, playersStats));

                                    }
                                }

                                if (!foundAny) {
                                    System.out.println("You don't see any " + rawPhrase + " here.");
                                }

                                if (validObject) {
                                    System.out.println("You feel a bit heavier!");
                                }
                            }

                            if (!validObject) {
                                if (action.contains("scroll")) {
                                    for (String wah : scrollTypes) {
                                        if (action.contains(wah)) {
                                            take(inRoom, wah, inventory, player, playersStats, allOrNo);
                                            validObject = true;
                                            break;
                                        }
                                    }
                                    if (!validObject) {
                                        System.out.println("Please try again [specify which scroll e.g. fire scroll 1]");
                                        validObject = true;

                                    }
                                } else {

                                    for (String obj : objects) {
                                        if (action.toLowerCase().contains(obj)) {
                                            if (action.toLowerCase().contains("all")) {
                                                allOrNo = true;
                                            }
                                            take(inRoom, obj, inventory, player, playersStats, allOrNo);
                                            validObject = true;
                                            break;

                                        }
                                    }
                                }
                            }

                            if (!validObject) {
                                System.out.println("You can't just '" + action + "'");
                            }
                        } else if (stringContainsWordFromList(action.toLowerCase(), read.toArray(new String[0]))) {

                            boolean validObject = false;

                            for (String obj : objects) {
                                if (action.toLowerCase().contains(obj)) {
                                    read(inRoom, inventory, obj, player);
                                    validObject = true;

                                    break;
                                }
                            }

                            if (!validObject) {
                                System.out.println("You can't just '" + action + "'");
                            }
                        } else if (stringContainsWordFromList(action.toLowerCase(), drop.toArray(new String[0])) || stringContainsWordFromList(action.toLowerCase(), remove.toArray(new String[0]))) {

                            boolean validObject = false;

                            if (action.startsWith("drop all")) {

                                String[] parts = action.split("\\s+");
                                int allIndex = -1;
                                for (int i = 0; i < parts.length; i++) {
                                    if (parts[i].equals("all")) {
                                        allIndex = i;
                                        break;
                                    }
                                }
                                if (allIndex == -1 || allIndex == parts.length - 1) {
                                    System.out.println("Drop all what?");
                                    return;
                                }

                                StringBuilder sb = new StringBuilder();
                                for (int i = allIndex + 1; i < parts.length; i++) {
                                    if (i > allIndex + 1) sb.append(" ");
                                    sb.append(parts[i]);
                                }
                                String rawPhrase = sb.toString();

                                String[] phraseWords = rawPhrase.split("\\s+");
                                String lastWord = phraseWords[phraseWords.length - 1];
                                String singularLastWord = unPluralizer(lastWord);

                                phraseWords[phraseWords.length - 1] = singularLastWord;
                                String searchName = String.join(" ", phraseWords);

                                boolean foundAny = false;
                                Iterator<String> it = inventory.iterator();
                                while (it.hasNext()) {
                                    String item = it.next();
                                    if (item.equalsIgnoreCase(searchName)) {
                                        inRoom.getObjects().add(item);
                                        it.remove();
                                        foundAny = true;

                                        Player.QUESTS.forEach((id, q) -> q.checkMinus("COLLECT_ITEM", item));

                                    }
                                }

                                if (!foundAny) {
                                    System.out.println("You don't have any " + rawPhrase + " to drop.");
                                } else {
                                    System.out.println("You feel a bit lighter!");
                                    validObject = true;
                                }

                            } else {


                                for (String obj : objects) {
                                    if (action.toLowerCase().contains(obj)) {
                                        drop(inRoom, obj, inventory, player, equipment, existingItems);
                                        validObject = true;

                                        break;
                                    }
                                }
                            }

                            if (!validObject) {
                                System.out.println("You can't just '" + action + "'");
                            }
                        } else if (stringContainsWordFromList(action.toLowerCase(), inventoryinventory.toArray(new String[0]))) {
                            itemsIfAny(inventory, "Items in inventory: ");
                        } else if (stringContainsWordFromList(action.toLowerCase(), attack.toArray(new String[0])) || stringContainsWordFromList(action.toLowerCase(), kill.toArray(new String[0]))) {

                            boolean mobbo = false; // 47 more questions

                            for (Npca mob : inRoom.getNpc()) {

                                String mobNameLower = mob.getName().toLowerCase();

                                if (action.toLowerCase().contains(mobNameLower)) {

                                    player.setInCombat(true);

                                    fighting = true;

                                    combatNpc(player, mob, inRoom, playersStats);

                                    player.setInCombat(false);

                                    fighting = false;

                                    mobbo = true;

                                    break;

                                }

                            }

                            for (Guard mob : inRoom.getGuard()) {

                                String mobNameLower = mob.getName().toLowerCase();

                                if (action.toLowerCase().contains(mobNameLower)) {

                                    player.setInCombat(true);

                                    fighting = true;

                                    combatGuard(player, mob, inRoom, playersStats, existingItems);

                                    player.setInCombat(false);

                                    fighting = false;

                                    mobbo = true;

                                    break;

                                }

                            }

                            if (inRoom.getFirstShopOwners() != null){

                                String mobNameLower = inRoom.getFirstShopOwners().getName().toLowerCase();

                                if (action.toLowerCase().contains(mobNameLower)) {

                                    player.setInCombat(true);

                                    fighting = true;

                                    combatShopOwner(player, inRoom.getFirstShopOwners(), inRoom, playersStats);

                                    player.setInCombat(false);

                                    fighting = false;

                                    mobbo = true;

                                }

                            }


                            for (Mob mob : inRoom.getMOBS()) {

                                String mobNameLower = mob.getName().toLowerCase();

                                if (action.toLowerCase().contains(mobNameLower)) {

                                    player.setInCombat(true);

                                    fighting = true;

                                    combat(player, mob, inRoom, playersStats, equipment, food, inventory);

                                    player.setInCombat(false);

                                    fighting = false;

                                    mobbo = true;

                                    break;

                                }

                            }

                            for (Boss boss : inRoom.getBoss()) {

                                String mobNameLower = boss.getName().toLowerCase();

                                if (action.toLowerCase().contains(mobNameLower)) {

                                    player.setInCombat(true);

                                    fighting = true;

                                    combatBoss(player, boss, inRoom, playersStats, existingItems, equipment.getEquippedItems(), equipment);

                                    player.setInCombat(false);

                                    fighting = false;

                                    mobbo = true;

                                    break;

                                }

                            }

                            if (mobbo == false) {
                                System.out.println("You couldn't find your target.");
                            }

                        } else if (stringContainsWordFromList(action.toLowerCase(), equip.toArray(new String[0]))) {

                            boolean validObject = false;

                            for (String obj : objects) {

                                if (validObject == true) {
                                    break;
                                }

                                if (action.toLowerCase().contains(obj) && inventory.contains(obj)) {

                                    for (Item item : existingItems) {

                                        if (item.getName().equals(obj)) {
                                            equip(item, equipment, player);
                                            validObject = true;
                                            break;

                                        }
                                    }

                                } else if (action.toLowerCase().contains(obj) && inRoom.getObjects().contains(obj)) {

                                    for (Item item : existingItems) {
                                        if (item.getName().equals(obj)) {

                                            inventory.add(obj);
                                            inRoom.getObjects().remove(obj);
                                            equip(item, equipment, player);
                                            validObject = true;

                                            break;


                                        }
                                    }
                                }
                            }

                            if (!validObject) {
                                System.out.println("You cannot " + action);
                            }

                        } else if (stringContainsWordFromList(action.toLowerCase(), unequip.toArray(new String[0]))) {

                            boolean validObject = false;
                            for (String obj : objects) {
                                if (action.toLowerCase().contains(obj.toLowerCase()) && inventory.contains(obj)) {
                                    for (Item item : existingItems) {
                                        if (item.getName().equals(obj)) {
                                            unequip(item, item.getSlotType(), player, equipment);
                                            validObject = true;
                                            break;

                                        }
                                    }

                                }
                            }

                            if (!validObject) {
                                System.out.println("You cannot " + action);
                            }

                        } else if (stringContainsWordFromList(action.toLowerCase(), talk.toArray(new String[0])) || stringContainsWordFromList(action.toLowerCase(), kill.toArray(new String[0]))) {

                            talking = true;

                            boolean talked = false;

                            boolean talkMob = false;


                            for (Guard npc : inRoom.getGuard()) {

                                String npcNameLower = npc.getName().toLowerCase();

                                if (action.toLowerCase().contains(npcNameLower)) {

                                    inventory = talkGuard(inRoom, inventory, playersStats, npc, player);

                                    talked = true;

                                }

                            }

                            if (!talked) {
                                for (Merchant npc : inRoom.getMerchant()) {

                                    String npcNameLower = npc.getName().toLowerCase();

                                    if (action.toLowerCase().contains(npcNameLower)) {

                                        //talkMerchant(inRoom, inventory, playersStats, npc, player);

                                        talked = true;

                                    }

                                }
                            }

                            if (!talked) {
                                for (Npca npc : new ArrayList<>(inRoom.getNpc())) {
                                    String npcNameLower = npc.getName().toLowerCase();

                                    if (action.toLowerCase().contains(npcNameLower)) {

                                        if (npc.getName().equalsIgnoreCase("Bill")) {
                                            Npca horseRef = null;
                                            for (Npca n : inRoom.getNpc()) {
                                                if (n.getName().equalsIgnoreCase("horsey")) {
                                                    horseRef = n;
                                                    break;
                                                }
                                            }

                                            Hub newRoom = rideWithBill(inRoom, npc, horseRef, player, inventory);
                                            inRoom = newRoom;

                                        } else {
                                            if (npc.getName().equalsIgnoreCase("jagger") || npc.getName().equalsIgnoreCase("jagger")) {

                                            } else {
                                                inventory = talkNpc(inRoom, inventory, playersStats, npc, player);
                                                talked = true;
                                            }

                                        }

                                        break;
                                    }
                                }
                            }

                            if (!talked) {

                                if (inRoom.getFirstShopOwners() != null) {

                                    String npcNameLower = (inRoom.getFirstShopOwners().getName());

                                    if (action.toLowerCase().contains(npcNameLower.toLowerCase())) {

                                        inventory = talkShopFirst(inRoom, inventory, playersStats, inRoom.getFirstShopOwners(), player, equipment);

                                        talked = true;

                                    }

                                }
                            }

                            if (!talked) {
                                for (Mob npc : inRoom.getMOBS()) {

                                    String npcNameLower = npc.getName().toLowerCase();

                                    if (action.toLowerCase().contains(npcNameLower)) {

                                        talkMob = true;

                                    }

                                }
                            }

                            if (!talked) {
                                for (Boss npc : inRoom.getBoss()) {

                                    String npcNameLower = npc.getName().toLowerCase();

                                    if (action.toLowerCase().contains(npcNameLower)) {

                                        talkMob = true;

                                    }

                                }
                            }
                            if (!talked) {
                                for (Npca npca : inRoom.getNpc()) {

                                    if (action.contains(npca.getName())) {

                                        inventory = talkShopFirst(inRoom, inventory, playersStats, inRoom.getFirstShopOwners(), player, equipment);

                                    }

                                }
                            }

                            if (talkMob == true) {
                                System.out.println("[Your target is unable, or lacks the intelligence to talk...]");
                            } else if (talked == false) {
                                System.out.println("[When you want to talk, you must define your target.]");
                            }

                            talking = false;

                        } else {
                            System.out.println("You can't '" + action + "' ");
                        }


                    } else {
                        //need object to do that!

                        System.out.println("You need something to receive this action (e.g., Open -> door)!");

                    }


                } else if (stringContainsWordFromList(action.toLowerCase(), objects.toArray(new String[0]))) {

                    System.out.println("You need a verb to do something!");
                } else {
                    System.out.println("I don't know that word.");
                }

            }
        }

    }

    // move method
    public static Hub move(String direction, Hub currentHub, Player player, XpLv playerStats, Equipment equipment, Map<String, Integer> food, List<String> inventory) {

        if (player.getFullness() < 1) {
            System.out.println("You are too tired to move! Rest for a while or eat some food!");
            return currentHub;
        }

        if ((currentHub.getRoomName().equalsIgnoreCase("west Port") && direction.equalsIgnoreCase("e")) || (currentHub.getRoomName().equalsIgnoreCase("east port") && direction.equalsIgnoreCase("w"))) {

            if (!onBoat) {
                System.out.println("The water is too deep to swim safely from here. Use the boat first.");
                return currentHub;
            }
        }

        if ((currentHub.getRoomName().equalsIgnoreCase("west Port") && direction.equalsIgnoreCase("w")) || (currentHub.getRoomName().equalsIgnoreCase("east port") && direction.equalsIgnoreCase("e"))) {

            if (onBoat) {
                System.out.println("For some reason it is challenging to ride your boat on land. You pound in anger as your boat stays where it is..");
                return currentHub;
            }
        }

        if ((currentHub.getRoomName().equalsIgnoreCase("Open Sea West") && direction.equalsIgnoreCase("w")) || (currentHub.getRoomName().equalsIgnoreCase("Open Sea East") && direction.equalsIgnoreCase("e"))) {

            if (!onBoat) {
                System.out.println("The dock is too high to climb from here. Try getting onto the boat first. .");
                return currentHub;
            }
        }

        if (direction.equals("north") || direction.equals("n")) {
            direction = "n";
            if (currentHub.getRoomName().equalsIgnoreCase("Northern Forest Area #50")) {
                if (!currentHub.getBoss().isEmpty()) {
                    System.out.println("The rooms boss glances at you suspiciously and blocks your path...");
                    return currentHub;
                }
            }

            if (currentHub.getRoomName().equalsIgnoreCase("Tom's Dark Kitchen")) {
                if (cabinetDaggerCaveN) {
                    System.out.println("Before you move on, try opening the cabinet! [open cabinet]");
                    return currentHub;
                }
            }
        }

        if (direction.equals("south") || direction.equals("s")) {
            direction = "s";
        }

        if (direction.equals("west") || direction.equals("w")) {
            direction = "w";
        }

        if (direction.equals("east") || direction.equals("e")) {
            direction = "e";
        }

        Hub newRoom = currentHub.getExit(direction);

        if (direction.equalsIgnoreCase("down") || direction.equalsIgnoreCase("d")) {
            direction = "d";
        }
        if (direction.equalsIgnoreCase("up") || direction.equalsIgnoreCase("u")) {
            direction = "u";
        }

        if (direction.equalsIgnoreCase("d")) {

            if (onBoat) {
                onBoat = false;
                System.out.println("You hop out of the boat into the water.");
                boatLocation = currentHub;
            }

            String helmetName = equipment.getHelmet();
            if (!helmetName.equalsIgnoreCase("scuba mask")) {
                System.out.println("You can't dive here without a proper scuba mask on your head.");
                return currentHub;
            }
        }

        if (isUnderwaterRoom(newRoom)) {
            String helmetName = equipment.getHelmet();
            if (!helmetName.equalsIgnoreCase("scuba mask")) {
                System.out.println("You feel the pressure and lack of air. You need a scuba mask to go there.");
                return currentHub;
            }
        }

        Door door = currentHub.getDoor(direction);
        if (door != null && !door.isOpen()) {
            System.out.println("There is a closed door in your way.");
            return currentHub;
        }

        LockedDoors lockedDoors = currentHub.getLockedDoor(direction);

        if (lockedDoors != null && !lockedDoors.isOpened()) {
            if (currentHub.getRoomName().equalsIgnoreCase("FirstVille Manhole CELL")) {
                System.out.println("The cell is LOCKED. A cell key is needed to unlock it.");
            } else {
                System.out.println("There is a locked gate in your way.");
            }

            return currentHub;
        }

        // If there is a new area where user tries to go, update it
        if (newRoom != null) {

            Mobs(newRoom, playerStats);


            for (Mob mob : new ArrayList<>(newRoom.getMOBS())) {

                if (mob.isAggro() && !mob.getHealth().isDead() && newRoom.containsMob(mob)) {

                    System.out.println("");

                    List<String> mobNames = new ArrayList<>();

                    for (Mob mob1 : newRoom.getMOBS()) {

                        mobNames.add(mob1.getName());

                    }

                    mobsIfAny(mobNames, "Mobs in room: ");

                    System.out.println("");

                    System.out.println(mob.getName() + " is aggro and attacks you as you enter!");
                    fighting = true;
                    combat(player, mob, newRoom, playerStats, equipment, food, inventory);
                    fighting = false;
                    if (newRoom.getMOBS().isEmpty() || player.getHealth().isDead()) {

                        break; // If I change stuff then before the for I should see what the newRoom.getMOBS's length is, and then do a "for (int i = (the length of the list)"
                    }

                }

            }

            if (!player.getHealth().isDead()) {
                System.out.println(newRoom.getRoomName());
                System.out.println(newRoom.getRoomDescription());
                System.out.println("");

                if (!newRoom.getObjects().isEmpty()) {
                    itemsIfAny(newRoom.getObjects(), "Items in room: ");
                    System.out.println("");
                }

                List<String> mobNames = new ArrayList<>();

                for (Mob mob : newRoom.getMOBS()) {

                    mobNames.add(mob.getName());

                }

                if (!mobNames.isEmpty()) {
                    mobsIfAny(mobNames, "Mobs in room: ");
                }

                List<String> bossNames = new ArrayList<>();

                for (Boss boss : newRoom.getBoss()) {

                    bossNames.add(boss.getName());

                }

                if (!bossNames.isEmpty()) {
                    mobsIfAny(bossNames, "Bosses in room: ");
                }


                List<String> npcNames = new ArrayList<>();

                for (Npca npc : newRoom.getNpc()) {
                    npcNames.add(npc.getName());
                }

                for (Guard npc : newRoom.getGuard()) {
                    npcNames.add(npc.getName());
                }

                for (Merchant npc : newRoom.getMerchant()) {
                    npcNames.add(npc.getName());
                }

                if (newRoom.getFirstShopOwners() != null) {
                    npcNames.add(newRoom.getFirstShopOwners().getName());
                }

                if (!npcNames.isEmpty()) {
                    npcIfAny(npcNames, "NPC's in room: ");
                }

                if (newRoom.getRoomName().equalsIgnoreCase("FirstVille Guild Hall")) {
                    newRoom.updateGuildActivity();  // NPCs rank up automatically
                    System.out.println("\nGuild Master Tragger shouts: 'Talk to me for guild services!'");
                }

                if (newRoom.getRoomName().equalsIgnoreCase("FirstVille Clinic")) {
                    System.out.println("\nTrevor (Clinic Clerk): 'Talk to me for clinic stuff!'");
                }


                player.lessFullness();
                return newRoom; // Return the new Hub object (room)

            }

        }

        // If there is no new area where the user tries to go, stop them
        else {
            System.out.println("You are trying to go to an impossible location.");
            return currentHub; // Return the current Hub object
        }

        if (onBoat) {
            boatLocation = newRoom;
        }

        return newRoom;
    }

    private static boolean isUnderwaterRoom(Hub room) {
        if (room == null) return false;
        String name = room.getRoomName().toLowerCase();
        return name.contains("depths") || name.contains("midwater") || name.contains("trench");
    }


    public static boolean stringContainsWordFromList(String inputStr, String[] items) {
        for (String item : items) {
            if (inputStr.matches(".*\\b" + Pattern.quote(item) + "\\b.*")) {
                return true;
            }
        }
        return false;
    }

    public static boolean oneDirection(String action, List<String> northways, List<String> southways, List<String> eastways, List<String> westways, List<String> upways, List<String> downways) {
        int directionCounter = 0;
        if (stringContainsWordFromList(action.toLowerCase(), northways.toArray(new String[0]))) {
            directionCounter++;
        }
        if (stringContainsWordFromList(action.toLowerCase(), southways.toArray(new String[0]))) {
            directionCounter++;
        }
        if (stringContainsWordFromList(action.toLowerCase(), eastways.toArray(new String[0]))) {
            directionCounter++;
        }
        if (stringContainsWordFromList(action.toLowerCase(), westways.toArray(new String[0]))) {
            directionCounter++;
        }
        if (stringContainsWordFromList(action.toLowerCase(), upways.toArray(new String[0]))) {
            directionCounter++;
        }
        if (stringContainsWordFromList(action.toLowerCase(), downways.toArray(new String[0]))) {
            directionCounter++;
        }
        if (directionCounter > 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean oneObjectOneVerb(String action, List<String> verbs, List<String> objects) {
        String[] words = action.toLowerCase().split("\\s+");
        int verbCount = 0;
        int objectCount = 0;

        for (String word : words) {
            if (verbs.contains(word)) {
                verbCount++;
            }
            if (objects.contains(word)) {
                objectCount++;
            }
        }

        return verbCount <= 1 && objectCount <= 1;
    }

    public static void listening(Hub inRoom) {
        if (inRoom.getRoomName().equals("Tom's Dark Cave")) {
            System.out.println("You hear absolutely nothing but your own breathing");
        }
    }

    public static void take(Hub inRoom, String item, List<String> inventory, Player player, XpLv playerStats, boolean takeOrNo) {

        if (item.equalsIgnoreCase("leaflet")) {

            List<String> leafletIds = new ArrayList<>();
            for (String obj : inRoom.getObjects()) {
                Leaflet a = Game.LEAFLETS.get(obj);
                if (a != null && a.getName().equalsIgnoreCase("leaflet")) {
                    leafletIds.add(obj);
                }
            }

            if (leafletIds.isEmpty()) {
                System.out.println("There is NO leaflet to take.");
                return;
            }

            // If more than one, ask which one to take
            String chosenId;
            if (leafletIds.size() == 1) {
                chosenId = leafletIds.get(0);
            } else {
                System.out.println("There are several leaflets here:");
                for (int i = 0; i < leafletIds.size(); i++) {
                    String id = leafletIds.get(i);
                    Leaflet chose = Game.LEAFLETS.get(id);
                    String title = chose != null ? chose.getTitle() : "(unknown title)";
                    System.out.println((i + 1) + ") [" + id + "] " + title);
                }
                System.out.print("Which leaflet do you want to take (number or id)? - ");

                String answer = Game.scanner.nextLine().trim().toLowerCase();
                String choice = null;

                // Try number
                try {
                    int chosen = Integer.parseInt(answer) - 1;
                    if (chosen >= 0 && chosen < leafletIds.size()) {
                        choice = leafletIds.get(chosen);
                    }
                } catch (NumberFormatException ignored) {
                }

                // Try id
                if (choice == null) {
                    for (String id : leafletIds) {
                        if (id.toLowerCase().equals(answer)) {
                            choice = id;
                            break;
                        }
                    }
                }

                if (choice == null) {
                    System.out.println("You decide not to take any leaflets.");
                    return;
                }

                chosenId = choice;
            }

            item = chosenId;
        }

        if (item.equalsIgnoreCase("key")) {

            List<String> tokenIds = new ArrayList<>();
            for (String obj : inRoom.getObjects()) {
                Token a = Game.TOKENS.get(obj);
                if (a != null && a.getName().equalsIgnoreCase("key")) {
                    tokenIds.add(obj);
                }
            }

            if (tokenIds.isEmpty()) {
                System.out.println("There is NO token to take.");
                return;
            }

            // If more than one, ask which one to take
            String chosenId;
            if (tokenIds.size() == 1) {
                chosenId = tokenIds.get(0);
            } else {
                System.out.println("There are several tokens here:");
                for (int i = 0; i < tokenIds.size(); i++) {
                    String id = tokenIds.get(i);
                    System.out.println((i + 1) + ") [" + id + "] ");
                }
                System.out.print("Which token do you want to take (number or id)? - ");

                String answer = Game.scanner.nextLine().trim().toLowerCase();
                String choice = null;

                // Try number
                try {
                    int chosen = Integer.parseInt(answer) - 1;
                    if (chosen >= 0 && chosen < tokenIds.size()) {
                        choice = tokenIds.get(chosen);
                    }
                } catch (NumberFormatException ignored) {
                }

                // Try id
                if (choice == null) {
                    for (String id : tokenIds) {
                        if (id.toLowerCase().equals(answer)) {
                            choice = id;
                            break;
                        }
                    }
                }

                if (choice == null) {
                    System.out.println("You decide not to take any treasure token [KEY] 's.");
                    return;
                }

                chosenId = choice;
            }

            item = chosenId;
        }


        List<String> itemos = inRoom.getObjects();

        int count = Collections.frequency(itemos, item);

        int chosenAmount = 0;

        if (count == 0) {
            System.out.println("There is no " + item + " (single-form) to take here -> try typing ALL when taking");

        } else {
            chosenAmount = 1;
        }

        if (count > 1) {

            System.out.println("There are " + count + " " + item + "(s) here. How many do you want to take?");
            System.out.print("-> ");
            if (!takeOrNo) {
                String takeAmt = scanner.nextLine().trim();

                try {

                    int amountToTake = Integer.parseInt(takeAmt);

                    if (amountToTake > count) {
                        System.out.println("You are trying to take more items than there are. \nTaking 1 by default.");
                    } else if (amountToTake < 0) {
                        System.out.println("You are trying to take a negative amount. \nTaking 1 by default.");
                    } else {
                        chosenAmount = amountToTake;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("You are trying to take an invalid amount. \nTaking 1 by default.");
                }
            } else {
                chosenAmount = count;

            }

        }

        for (int i = 0; i < chosenAmount; i++) {

            inventory.add(item);
            inRoom.getObjects().remove(item);
            String takenItem = item.toLowerCase();

            Player.QUESTS.forEach((id, q) -> q.check("COLLECT_ITEM", takenItem, player, playerStats));


        }

        if (chosenAmount > 0) {

            System.out.println("You have taken " + chosenAmount + " " + item + "(s)");

        } else {
            System.out.println("You have re-framed from taking anything.");
        }


    }

    public static void drop(Hub inRoom, String item, List<String> inventory, Player player, Equipment equipment, List<Item> existingItems) {

        boolean isLeaflet = false;

        if (item.equalsIgnoreCase("leaflet")) {

            List<String> leafletIds = new ArrayList<>();
            for (String obj : inventory) {
                Leaflet lf = Game.LEAFLETS.get(obj);
                if (lf != null && lf.getName().equalsIgnoreCase("leaflet")) {
                    leafletIds.add(obj);
                }
            }

            if (leafletIds.isEmpty()) {
                System.out.println("There is NO leaflet to drop.");
                return;
            }

            // If more than one, ask which one to drop
            String chosenId;
            if (leafletIds.size() == 1) {
                chosenId = leafletIds.get(0);
            } else {
                System.out.println("You have several leaflets in your inventory:");
                for (int i = 0; i < leafletIds.size(); i++) {
                    String id = leafletIds.get(i);
                    Leaflet chose = Game.LEAFLETS.get(id);
                    String title = chose != null ? chose.getTitle() : "(unknown title)";
                    System.out.println((i + 1) + ") [" + id + "] " + title);
                }
                System.out.print("Which leaflet do you want to drop (number or id)? - ");

                String answer = Game.scanner.nextLine().trim().toLowerCase();
                String choice = null;

                // Try number
                try {
                    int chosen = Integer.parseInt(answer) - 1;
                    if (chosen >= 0 && chosen < leafletIds.size()) {
                        choice = leafletIds.get(chosen);
                    }
                } catch (NumberFormatException ignored) {
                }

                // Try id
                if (choice == null) {
                    for (String id : leafletIds) {
                        if (id.toLowerCase().equals(answer)) {
                            choice = id;
                            break;
                        }
                    }
                }

                if (choice == null) {
                    System.out.println("You decide not to drop any leaflet.");
                    return;
                }

                chosenId = choice;
            }

            item = chosenId;
            isLeaflet = true;
        }

        boolean isToken = false;

        if (item.equalsIgnoreCase("key")) {

            List<String> tokenId = new ArrayList<>();
            for (String obj : inventory) {
                Token a = Game.TOKENS.get(obj);
                if (a != null && a.getName().equalsIgnoreCase("key")) {
                    tokenId.add(obj);
                }
            }

            if (tokenId.isEmpty()) {
                System.out.println("There is NO token to drop.");
                return;
            }

            // If more than one, ask which one to drop
            String chosenId;
            if (tokenId.size() == 1) {
                chosenId = tokenId.get(0);
            } else {
                System.out.println("You have several tokens in your inventory:");
                for (int i = 0; i < tokenId.size(); i++) {
                    String id = tokenId.get(i);
                    System.out.println((i + 1) + ") [" + id + "] ");
                }
                System.out.print("Which token do you want to drop (number or id)? - ");

                String answer = Game.scanner.nextLine().trim().toLowerCase();
                String choice = null;

                // Try number
                try {
                    int chosen = Integer.parseInt(answer) - 1;
                    if (chosen >= 0 && chosen < tokenId.size()) {
                        choice = tokenId.get(chosen);
                    }
                } catch (NumberFormatException ignored) {
                }

                // Try id
                if (choice == null) {
                    for (String id : tokenId) {
                        if (id.toLowerCase().equals(answer)) {
                            choice = id;
                            break;
                        }
                    }
                }

                if (choice == null) {
                    System.out.println("You decide not to drop any treasure token [KEY] 's.");
                    return;
                }

                chosenId = choice;
            }

            item = chosenId;
            isToken = true;
        }

        int count = Collections.frequency(inventory, item);

        int chosenAmount = 0;

        if (count == 0) {
            System.out.println("There is no " + item + " (single-form) in you inventory -> try typing ALL when dropping");

        } else {

            chosenAmount = 1;
        }

        if (count > 1) {

            System.out.println("You have " + count + " " + item + "(s). How many do you want to drop?");

            System.out.print("-> ");

            String takeAmt = scanner.nextLine().trim();
            try {
                int amountToTake = Integer.parseInt(takeAmt);

                if (amountToTake > count) {
                    System.out.println("You are trying to drop more items than there you have. \nDropping 1 by default.");
                    chosenAmount = 1;

                } else if (amountToTake < 0) {
                    System.out.println("You are trying to drop a negative amount. \nDropping 1 by default.");
                    chosenAmount = 1;

                } else {
                    chosenAmount = amountToTake;
                }
            } catch (NumberFormatException e) {
                System.out.println("You are trying to drop an invalid amount. \nDropping 1 by default.");
            }


        }

        for (int i = 0; i < chosenAmount; i++) {

            inventory.remove(item);
            inRoom.getObjects().add(item);
            String finalItem = item;
            Player.QUESTS.forEach((id, q) -> q.checkMinus("COLLECT_ITEM", finalItem));

        }

        if (chosenAmount >= 0) {

            Item itemToRemove = null;

            for (Item item1 : existingItems) {
                if (item1.getName().equals(item)) {
                    itemToRemove = item1;
                    break;
                }
            }

            if (isLeaflet) {
                System.out.println("You have dropped " + chosenAmount + " leaflet" + (chosenAmount > 1 ? "s." : "."));
                return; // HOW DOES ITEMS COME INTO PLAY?
            }

            if (isToken) {
                System.out.println("You have dropped " + chosenAmount + " token [KEY]" + (chosenAmount > 1 ? "s." : "."));
                return;
            }


            if (itemToRemove != null) {
                Item equippedItem = equipment.getEquipped(itemToRemove.getSlotType());
                if (equippedItem != null && equippedItem.getName().equalsIgnoreCase(itemToRemove.getName())) {
                    // Unequip first
                    equipment.unequip(itemToRemove.getSlotType(), itemToRemove, player);
                    System.out.println("(Unequipped " + itemToRemove.getName() + " before removing.)");
                }

            }

            System.out.println("You have dropped " + chosenAmount + " " + item + "(s)");


        }


    }

    public static void read(Hub inRoom, List<String> inventory, String item, Player player) {

        if (item.equalsIgnoreCase("leaflet")) {
            List<Leaflet> aqquiredLeaflets = new ArrayList<>();

            int ownedLeafs = 0;
            for (String leafId : inventory) {
                Leaflet owned = LEAFLETS.get(leafId);
                if (owned != null && owned.getName().equalsIgnoreCase("leaflet")) {
                    aqquiredLeaflets.add(owned);
                    ownedLeafs++;
                }
            }

            if (aqquiredLeaflets.isEmpty()) {
                System.out.println("You do not have any leaflets to read.");
                return;
            }

            Leaflet chosen = null;

            if (ownedLeafs > 1) {
                System.out.println("You have several leaflets:");
                for (int i = 0; i < aqquiredLeaflets.size(); i++) {
                    Leaflet owned = aqquiredLeaflets.get(i);
                    System.out.println((i + 1) + ") [" + owned.getId() + "] " + owned.getTitle());
                }
                System.out.print("Which leaflet do you want to read (number or id)? ");

                System.out.print("-> ");

                String answer = scanner.nextLine().trim().toLowerCase();

                System.out.println("");

                //try number
                try {
                    int entered = Integer.parseInt(answer) - 1;
                    if (entered >= 0 && entered < aqquiredLeaflets.size()) {
                        chosen = aqquiredLeaflets.get(entered);
                    }
                } catch (NumberFormatException ignored) {
                    //NOTHING HAPPENS AND IT WON'T CRASH
                }

                // Try id
                if (chosen == null) {
                    for (Leaflet owned : aqquiredLeaflets) {
                        if (owned.getId().toLowerCase().equals(answer)) {
                            chosen = owned;
                            break;
                        }
                    }
                }
            } else {
                for (Leaflet leaflet : aqquiredLeaflets) {
                    chosen = leaflet;
                    break;
                }
            }

            if (chosen != null) {
                printLeaflet(chosen);

            } else {
                System.out.println("You decide not to read any leaflet right now.");
            }
            return;
        }

        // user typed a specific id like "leaflet001"
        if (inventory.contains(item)) {
            Leaflet ha = LEAFLETS.get(item.toLowerCase());
            System.out.println(ha.getId());

            if (ha != null) {
                printLeaflet(ha);
                System.out.println(ha.getId());

            } else {
                System.out.println("You can't read '" + item + "'");
            }

        } else {

            if (inRoom.getObjects().contains(item)) {
                System.out.println("You can only read things in your inventory.");
            } else {
                System.out.println("There is no " + item + " here to read.");
            }
        }
    }

    private static void printLeaflet(Leaflet chose) {
        System.out.println("[You unfold the leaflet and read its headline:]");
        System.out.println(chose.getTitle());
        System.out.println("[Your eyes follow the page down...]");
        System.out.println(chose.getBody());
    }


    public static void itemsIfAny(List<String> items, String Name) {
        if (items.isEmpty()) {
            System.out.println(Name + " []");
            return;
        }
        System.out.print(Name + "");
        for (String item : new ArrayList<>(new java.util.HashSet<>(items))) {
            int count = Collections.frequency(items, item);
            System.out.print("[" + item + (count > 1 ? " (x" + count + ")] " : "] "));
        }

        System.out.println("");

    }

    public static void mobsIfAny(List<String> mobs, String name) {
        if (mobs.isEmpty()) {
            System.out.println(name + " []");
            return;
        }

        System.out.print(name + "");
        for (String mob : new ArrayList<>(new java.util.HashSet<>(mobs))) {
            int count = Collections.frequency(mobs, mob);
            System.out.print("[" + mob + (count > 1 ? " (x" + count + ")] " : "] "));
        }
        System.out.println("");
    }

    public static void npcIfAny(List<String> mobs, String name) {
        if (mobs.isEmpty()) {
            System.out.println(name + " []");
            return;
        }

        System.out.print(name + "");
        for (String mob : new ArrayList<>(new java.util.HashSet<>(mobs))) {
            System.out.print("[" + mob + "] ");
        }
        System.out.println("");
    }


    public static void help(List<String> verbs) {

        System.out.println("[VERB] + [OBJECT] = action\nSome verbs will not need an object to be used (e.g., 'look')\n=== COMMANDS ===\n       Movement: n, s, w, e, go <dir>, move <dir>       \n       Interact: open/close <obj>, inspect <obj>, look, listen, wait, read <leaflet>       \n       Self:     get/take <obj>, remove/drop <obj>, equip/unequip <obj>, inventory, stats, fame \n       Combat:   kill/attack <target>  \n       Social:   talk <npc>, quests  \n       Special:  eat <obj>, rest, use <obj>   \n       Utility:  help, quit \n================");

    }

    public static void Mobs(Hub inRoom, XpLv xpLv) {
        int chancer = 0;
        int number = 0;

        // Only run this code if player is in a Southern Forest room
        if (inRoom.getRoomName().contains("Southern Forest") || inRoom.getRoomName().contains("Northern Forest")) {

            chancer = (int) (Math.random() * 101);

            int chance = 0;

            if (timeChange != null) {
                chance = 35;
            } else {
                chance = 65;
            }

            if (chancer < chance) {

                List<String> stuff = new ArrayList<>();

                number = (int) (Math.random() * 20) + 1; // 1-20

                if (number > 0 && number < 4) {

                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int rabbitCount = Collections.frequency(mobCounts, "Rabbit");

                    if (rabbitCount < 3) {
                        Mob rabbit = createRabbitWithRandomStats(stuff);
                        inRoom.getMOBS().add(rabbit);
                        System.out.println("A rabbit hops out from the bushes.");
                    }

                } else if (number > 3 && number < 7) {

                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int chickenCount = Collections.frequency(mobCounts, "Chicken");

                    if (chickenCount < 3) {
                        Mob chicken = createChickenWithRandomStats(stuff);
                        inRoom.getMOBS().add(chicken);
                        System.out.println("A chicken clucks into the area in confusion.");
                    }

                } else if (number > 6 && number < 9) {

                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int squirrelCount = Collections.frequency(mobCounts, "Squirrel");

                    if (squirrelCount < 3) {
                        Mob squirrel = createSquirrelWithRandomStats(stuff);
                        inRoom.getMOBS().add(squirrel);
                        System.out.println("A mad squirrel falls from the trees.");
                    }

                } else if (number > 8 && number < 12) {

                    // Base Goblin
                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int goblinCount = Collections.frequency(mobCounts, "Goblin");

                    if (goblinCount < 3) {
                        Mob goblin = createGoblinWithRandomStats(stuff);
                        inRoom.getMOBS().add(goblin);
                        System.out.println("A scrawny yet barbaric goblin jumps outta nowhere.");
                    }

                } else if (number == 13 || number == 12) {

                    // Greater Goblin – strongest and rarest goblin type
                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int greaterGoblinCount = Collections.frequency(mobCounts, "Greater Goblin");

                    if (greaterGoblinCount < 2) {
                        Mob greaterGoblin = createGreaterGoblinWithRandomStats(stuff);
                        inRoom.getMOBS().add(greaterGoblin);
                        System.out.println("A towering greater goblin storms into the clearing.");
                    }

                } else if (number > 13 && number < 16) {

                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int bearCount = Collections.frequency(mobCounts, "Bear");

                    if (bearCount < 3) {
                        Mob bear = createBearWithRandomStats(stuff, 0, null);
                        inRoom.getMOBS().add(bear);
                        System.out.println("A stunning bear walks into the area.");
                    }

                } else if (number > 15 && number < 18) {

                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int wolfCount = Collections.frequency(mobCounts, "Wolf");

                    if (wolfCount < 3) {
                        Mob wolf = createWolfWithRandomStats(stuff);
                        inRoom.getMOBS().add(wolf);
                        System.out.println("A hexing wolf walks round menacingly.");
                    }

                } else if (number > 17 && number < 20) {

                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int foxWitchCount = Collections.frequency(mobCounts, "Fox Witch");

                    if (foxWitchCount < 3) {
                        Mob foxWitch = createWolfWitchWithRandomStats(stuff);
                        inRoom.getMOBS().add(foxWitch);
                        System.out.println("A chucking Fox Witch wanders around drunkenly.");
                    }

                } else if (number == 20 && xpLv.getLevel() >= 2) {

                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int bloodWitchCount = Collections.frequency(mobCounts, "Blood Witch");

                    if (bloodWitchCount < 3) {
                        Mob bloodWitch = createBloodWitchWithRandomStats(stuff);
                        inRoom.getMOBS().add(bloodWitch);
                        System.out.println("A chuckling blood witch chuckles into your line of view, egging you on.");
                    }

                }

            }
        }

    }

    private static Mob createRabbitWithRandomStats(List<String> drop) {
        int maxHealth = 8;
        int currentHealth = maxHealth;
        int damageResistance = 0;
        Health rabbitHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 3;
        boolean isAggro = false;

        if (drop.isEmpty()) {
            if ((int) (Math.random() * 2 + 1) == 1) {
                drop.add("rabbit hide");
            }
        }

        return new Mob("Rabbit", rabbitHealth, attackPower, isAggro, drop);
    }

    private static Mob createBearWithRandomStats(List<String> drop, int maxHealth, String name) {

        if (name == null) {
            name = "Bear";
        }

        if (maxHealth == 0) {
            maxHealth = 40;
        }

        int currentHealth = maxHealth;
        int damageResistance = 0;
        Health bearHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 17;
        boolean isAggro = false;

        if (drop.isEmpty()) {
            drop.add("bear hide");
        }

        return new Mob(name, bearHealth, attackPower, isAggro, drop);
    }

    private static Mob createSquirrelWithRandomStats(List<String> drop) {
        int maxHealth = 12;
        int currentHealth = maxHealth;
        int damageResistance = 0;
        Health squirrelHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 6;
        boolean isAggro = true;

        if (drop.isEmpty()) {
            if ((int) (Math.random() * 2 + 1) == 1) {
                drop.add("squirrel hide");
            }
        }

        return new Mob("Squirrel", squirrelHealth, attackPower, isAggro, drop);
    }

    private static Mob createChickenWithRandomStats(List<String> drop) {
        int maxHealth = 10;
        int currentHealth = maxHealth;
        int damageResistance = 0;
        Health rabbitHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 3;
        boolean isAggro = false;
        boolean onFire = false;

        if (drop.isEmpty()) {
            if ((int) (Math.random() * 2 + 1) == 1) {
                drop.add("chicken feather");
            }
        }

        return new Mob("Chicken", rabbitHealth, attackPower, isAggro, drop);
    }

    private static Mob createGoblinWithRandomStats(List<String> drop) {
        int maxHealth = 20;
        int currentHealth = maxHealth;
        int damageResistance = 0;
        Health goblinHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 10;
        boolean isAggro = true;

        if (drop.isEmpty()) {
            if ((int) (Math.random() * 2 + 1) == 1) {
                drop.add("goblin tooth");
            }
        }

        return new Mob("Goblin", goblinHealth, attackPower, isAggro, drop);
    }

    private static Mob createMuteBanditWithRandomStats(List<String> drop) {
        int maxHealth = 30;
        int currentHealth = maxHealth;
        int damageResistance = 3;
        Health muteHeath = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 15;
        boolean isAggro = false;

        if (drop.isEmpty()) {
            if ((int) (Math.random() * 2 + 1) == 1) {
                drop.add("cloth");
            }
        }

        return new Mob("Mute Bandit", muteHeath, attackPower, isAggro, drop);
    }

    private static Mob createWolfWithRandomStats(List<String> drop) {
        int maxHealth = 20;
        int currentHealth = maxHealth;
        int damageResistance = 0;
        Health muteHeath = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 10;
        boolean isAggro = true;

        if (drop.isEmpty()) {
            if ((int) (Math.random() * 3 + 1) != 1) {
                drop.add("wolfbane");
            }
        }

        return new Mob("Wolf", muteHeath, attackPower, isAggro, drop);
    }

    private static Mob createWolfWitchWithRandomStats(List<String> drop) {
        int maxHealth = 25;
        int currentHealth = maxHealth;
        int damageResistance = 1;
        Health muteHeath = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 15;
        boolean isAggro = false;

        if (drop.isEmpty()) {
            if ((int) (Math.random() * 3 + 1) != 1) {
                drop.add("raven eye");
            }
        }

        return new Mob("Fox Witch", muteHeath, attackPower, isAggro, drop);
    }

    private static Mob createBloodWitchWithRandomStats(List<String> drop) {
        int maxHealth = 35;
        int currentHealth = maxHealth;
        int damageResistance = 2;
        Health muteHeath = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 20;
        boolean isAggro;
        if ((int) (Math.random() * 2) == 0) {
            isAggro = false;
        } else {
            isAggro = true;
        }

        if (drop.isEmpty()) {
            if ((int) (Math.random() * 3 + 1) != 1) {
                drop.add("blood vial");
            }
        }

        return new Mob("Blood Witch", muteHeath, attackPower, isAggro, drop);
    }

    private static Mob createGolemWithRandomStats(List<String> drop) {
        int maxHealth = 30;
        int currentHealth = maxHealth;
        int damageResistance = 2;
        Health muteHeath = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 25;
        boolean isAggro;
        if ((int) (Math.random() * 2) == 0) {
            isAggro = false;
        } else {
            isAggro = true;
        }

        if (drop.isEmpty()) {
            if ((int) (Math.random() * 3 + 1) != 1) {
                drop.add("golem core");
            }
        }

        return new Mob("Golem", muteHeath, attackPower, isAggro, drop);
    }

    private static Mob createCrabWithRandomStats(List<String> drop) {
        int maxHealth = 25;
        int currentHealth = maxHealth;
        int damageResistance = 2;
        Health muteHeath = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 10;
        boolean isAggro;
        if ((int) (Math.random() * 2) == 0) {
            isAggro = false;
        } else {
            isAggro = true;
        }

        if (drop.isEmpty()) {
            if ((int) (Math.random() * 3 + 1) != 1) {
                drop.add("crab claw");
            }
        }

        return new Mob("Crab", muteHeath, attackPower, isAggro, drop);
    }

    private static Mob createGreaterGoblinWithRandomStats(List<String> drop) {
        int maxHealth = 30;          // more than Goblin (21)
        int currentHealth = maxHealth;
        int damageResistance = 1;    // tougher than normal Goblin
        Health goblinHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = 15;        // more than Goblin (16)
        boolean isAggro = true;

        if (drop.isEmpty()) {
            // better drop odds or same as base Goblin, your choice
            if ((int) (Math.random() * 2 + 1) == 1) {
                drop.add("goblin tooth");
            }
        }

        return new Mob("Greater Goblin", goblinHealth, attackPower, isAggro, drop);
    }


    public static void combat(Player player, Mob mob, Hub inRoom, XpLv playerStats, Equipment equipment, Map<String, Integer> food, List<String> inventory) {
        int dmgResist = player.getHealth().getDamageResistance();

        boolean using = false;

        int pause = 300;

        if (player.getSkills().isEmpty()) {

        } else {

            System.out.println("Would you like to your skills and allow 'eat' in this battle (y/n) ?");
            System.out.print("-> ");
            String answer = scanner.nextLine();

            if (!answer.isEmpty()) {
                if (answer.substring(0, 1).toLowerCase().equals("y")) {
                    using = true;
                } else {
                    using = false;
                }
            } else {
                using = false;
                System.out.println("No input taken...");
            }

            System.out.println("");
        }

        if (!inRoom.containsMob(mob)) {
            System.out.println("The " + mob.getName() + " has fled to another room.");
            return;
        }

        if (!using) {
            System.out.println("Would you like to speed thru this battle (y/n) ?");
            System.out.print("-> ");
            String speeder = scanner.nextLine();

            if (speeder == "y" || speeder == "yes") {
                pause = 0;
            }

        }

        String mobKey = mob.getName().toLowerCase();

        while (!player.getHealth().isDead() && !mob.getHealth().isDead()) {

            if (!inRoom.containsMob(mob)) {
                System.out.println("The " + mob.getName() + " has fled to another room.");
                break;
            }

            if (using == true) {

                System.out.println("What skill would you like to use?");
                for (Skill skill : player.getSkills()) {
                    System.out.print("[" + skill.getName() + "]  ");
                }

                System.out.println("");
                System.out.print("-> ");

                String usingNow = scanner.nextLine();
                if (usingNow.toLowerCase().equals("stun")) {
                    Skill.StunSkill.apply(player, mob);
                } else if (usingNow.toLowerCase().equals("harden")){
                    Skill.HardeningSkill.apply(player, mob);
                }else {
                    int chop = 0;
                    for (Skill skiller : player.getSkills()) {
                        if (usingNow.toLowerCase().equals(skiller.getName())) {
                            chop++;
                        }
                    }

                    if (chop <= 0) {
                        System.out.println("Unknown input.");
                    }
                }

                //TESTT

                List<String> edibleItems = new ArrayList<>();
                for (String item : inventory) {
                    if (food.containsKey(item.toLowerCase())) {
                        edibleItems.add(item);
                    }
                }

                if (edibleItems.isEmpty()) {

                    //FOOD STUFF
                } else {

                    String answer = "wag";

                    String selectedFood = null;

                    while (true) {

                        edibleItems.clear();
                        for (String item : inventory) {
                            if (food.containsKey(item.toLowerCase())) {
                                edibleItems.add(item);
                            }
                        }

                        if (edibleItems.isEmpty()) {
                            System.out.println("No edible items left!");
                            break;
                        }


                        for (int i = 0; i < edibleItems.size(); i++) {
                            String id = edibleItems.get(i);
                            int healValue = food.get(id.toLowerCase());
                            System.out.print((i + 1) + ") [" + id + "] (Food value " + healValue + " pts)");

                            if (i < edibleItems.size() - 1 && (i + 1) % 3 != 0) {
                                System.out.print("     ||      ");
                            } else if ((i + 1) % 3 == 0 || i == edibleItems.size() - 1) {
                                System.out.println("");
                            }
                        }

                        System.out.println("\nWhat food would you like to eat? (number or 'done') ");
                        System.out.print("-> ");

                        answer = Game.scanner.nextLine().trim().toLowerCase();

                        if (answer.equalsIgnoreCase("done") || answer.isEmpty()) {
                            break;
                        }

                        try {
                            int chosen = Integer.parseInt(answer) - 1;
                            if (chosen >= 0 && chosen < edibleItems.size()) {
                                selectedFood = edibleItems.get(chosen);
                                System.out.println("Eating " + selectedFood + "...");
                                inventory = eatDuringBattle(selectedFood, food, player, inventory);

                            } else {
                                if (edibleItems.size() == 1) {
                                    System.out.println("Invalid number. COME ON, there's only one choice");
                                } else {
                                    System.out.println("Invalid number. Must be: 1 - " + edibleItems.size());

                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Enter a number or 'done'.");
                        }
                    }


                }


            }
            //ATTACKING THE MOB - YOUR TURN!
            if (equipment.getItemBasedOnSlot("melee", equipment.getEquippedItems()) != null) {

                Item boom = equipment.getItemBasedOnSlot("melee", equipment.getEquippedItems());
                boom.checkPower(mob, player);
            }

            player.attack(mob);

            System.out.println("");

            if (pause != 0) {
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (mob.getHealth().isDead()) {
                String deathKey = (mobKey + "_death").toLowerCase();
                describeCombat(deathKey);

                System.out.println("The " + mob.getName() + " has died at your hands. \n");

                player.getHealth().setDamageResistance(dmgResist);

                playerStats.addXp(playerStats.calculateXp(mob.getName()));

                playerStats.calculateLv(playerStats.getXp(), playerStats.getLevel(), player);

                player.displayStats(player, playerStats);

                inRoom.getMOBS().remove(mob);

                String killedMob = mob.getName();

                Player.QUESTS.forEach((id, q) -> q.check("KILL_MOB", killedMob, player, playerStats));

                if (mob.getDrops() != null) {
                    for (String i : mob.getDrops()) {
                        inRoom.addObject(i);
                    }
                }
                break;
            } else {

                String attackKey = (mobKey + "_attack").toLowerCase();
                describeCombat(attackKey);

                mob.attack(player);
                System.out.println("");

                if (pause != 0) {
                    try {
                        Thread.sleep(pause);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (player.getHealth().isDead()) {
                    System.out.println("\nYou have been murdered by a " + mob.getName() + " (and a fork... and Tom. And his spoon.)\n\n");
                    player.getHealth().setDamageResistance(dmgResist);
                    player.getHealth().setHeealth(-1291212112);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                }

            }

            for (Skill skill : player.getSkills()) {
                skill.reduceCooldown();
            }

        }


    }

    public static void combatNpc(Player player, Npca npc, Hub inRoom, XpLv playerStats) {
        int dmgResist = player.getHealth().getDamageResistance();

        boolean using = false;

        if (player.getSkills().isEmpty()) {

        } else {

            System.out.println("Would you like to your skills in this battle (y/n) ?");
            System.out.print("-> ");
            String answer = scanner.nextLine();

            if (!answer.isEmpty()) {
                if (answer.substring(0, 1).toLowerCase().equals("y")) {
                    using = true;
                } else {
                    using = false;
                }
            } else {
                using = false;
                System.out.println("No input taken...");
            }

            System.out.println("");
        }

        System.out.print(npc.getHealth().getHeealth());
        while (!player.getHealth().isDead() && !npc.getHealth().isDead()) {

            if (using == true) {

                System.out.println("What skill would you like to use?");
                for (Skill skill : player.getSkills()) {
                    System.out.print("[" + skill.getName() + "]  ");
                }
                System.out.println("");
                System.out.print("-> ");

                String usingNow = scanner.nextLine();
                if (usingNow.toLowerCase().equals("stun")) {
                    Skill.StunSkill.applyNpc(player, npc);

                } else if (usingNow.toLowerCase().equals("harden")){
                    Skill.HardeningSkill.applyNpc(player, npc);
                } else {
                    int chop = 0;
                    for (Skill skiller : player.getSkills()) {
                        if (usingNow.toLowerCase().equals(skiller.getName())) {
                            chop++;
                        }
                    }

                    if (chop <= 0) {
                        System.out.println("Unknown input.");
                    }
                }


            }

            System.out.println("");

            player.attackNpc(npc);

            System.out.println("");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (npc.getHealth().isDead()) {
                System.out.println(npc.getName() + " has died at your hands. \n");

                player.getHealth().setDamageResistance(dmgResist);

                playerStats.addXp(playerStats.calculateXp(npc.getName()));

                playerStats.calculateLv(playerStats.getXp(), playerStats.getLevel(), player);

                player.displayStats(player, playerStats);

                inRoom.getNpc().remove(npc); //remove an object that has the name Rabbit

                if (npc.getName().equalsIgnoreCase("Jerr")) {
                    if (!player.ownsBoat()) {
                        System.out.println("Jerr's boat has been passed down to you through his demise...");
                        player.setOwnsBoat(true);
                    }
                }

                break;

            } else {

                npc.attack(player);
                System.out.println("");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (player.getHealth().isDead()) {
                    System.out.println("\nYou have been murdered by " + npc.getName() + " (and a fork... and Tom. And his spoon.)\n\n");
                    player.getHealth().setDamageResistance(dmgResist);
                    player.getHealth().setHeealth(-1291212112);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                }

            }

            for (Skill skill : player.getSkills()) {
                skill.reduceCooldown();
            }

        }


    }

    public static void combatShopOwner(Player player, FirstShopOwner npc, Hub inRoom, XpLv playerStats) {
        int dmgResist = player.getHealth().getDamageResistance();

        boolean using = false;

        if (player.getSkills().isEmpty()) {

        } else {

            System.out.println("Would you like to your skills in this battle (y/n) ?");
            System.out.print("-> ");
            String answer = scanner.nextLine();

            if (!answer.isEmpty()) {
                if (answer.substring(0, 1).toLowerCase().equals("y")) {
                    using = true;
                } else {
                    using = false;
                }
            } else {
                using = false;
                System.out.println("No input taken...");
            }

            System.out.println("");
        }

        System.out.print(npc.getHealth().getHeealth());
        while (!player.getHealth().isDead() && !npc.getHealth().isDead()) {

            if (using == true) {

                System.out.println("What skill would you like to use?");
                for (Skill skill : player.getSkills()) {
                    System.out.print("[" + skill.getName() + "]  ");
                }
                System.out.println("");
                System.out.print("-> ");

                String usingNow = scanner.nextLine();
                if (usingNow.toLowerCase().equals("stun")) {
                    Skill.StunSkill.applyNpc(player, npc);
                } else if (usingNow.toLowerCase().equals("harden")){
                    Skill.HardeningSkill.applyNpc(player, npc);
                } else {
                    int chop = 0;
                    for (Skill skiller : player.getSkills()) {
                        if (usingNow.toLowerCase().equals(skiller.getName())) {
                            chop++;
                        }
                    }

                    if (chop <= 0) {
                        System.out.println("Unknown input.");
                    }
                }


            }

            System.out.println("");

            player.attackNpc(npc);

            System.out.println("");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (npc.getHealth().isDead()) {
                System.out.println(npc.getName() + " has died at your hands. \n");

                player.getHealth().setDamageResistance(dmgResist);

                playerStats.addXp(playerStats.calculateXp(npc.getName()));

                playerStats.calculateLv(playerStats.getXp(), playerStats.getLevel(), player);

                player.displayStats(player, playerStats);

                inRoom.removeFirstShopOwner();

                return;

            } else {

                npc.attack(player);
                System.out.println("");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (player.getHealth().isDead()) {
                    System.out.println("\nYou have been murdered by " + npc.getName() + " (and a fork... and Tom. And his spoon.)\n\n");
                    player.getHealth().setDamageResistance(dmgResist);
                    player.getHealth().setHeealth(-1291212112);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return;
                }

            }

            for (Skill skill : player.getSkills()) {
                skill.reduceCooldown();
            }

        }


    }

    public static void combatGuard(Player player, Guard npc, Hub inRoom, XpLv playerStats, List<Item> existingItems) {
        int dmgResist = player.getHealth().getDamageResistance();

        boolean using = false;

        if (player.getSkills().isEmpty()) {

        } else {

            System.out.println("Would you like to your skills in this battle (y/n) ?");
            System.out.print("-> ");
            String answer = scanner.nextLine();

            if (!answer.isEmpty()) {
                if (answer.substring(0, 1).toLowerCase().equals("y")) {
                    using = true;
                } else {
                    using = false;
                }
            } else {
                using = false;
                System.out.println("No input taken...");
            }

            System.out.println("");
        }

        System.out.println(npc.getHealth().getHeealth());
        while (!player.getHealth().isDead() && !npc.getHealth().isDead()) {

            if (using == true) {

                System.out.println("What skill would you like to use?");
                for (Skill skill : player.getSkills()) {
                    System.out.print("[" + skill.getName() + "]  ");
                }
                System.out.println("");
                System.out.print("-> ");

                String usingNow = scanner.nextLine();
                if (usingNow.toLowerCase().equals("stun")) {
                    Skill.StunSkill.applyGuard(player, npc);
                } else if (usingNow.toLowerCase().equals("harden")){
                    Skill.HardeningSkill.applyGuard(player, npc);
                } else {
                    int chop = 0;
                    for (Skill skiller : player.getSkills()) {
                        if (usingNow.toLowerCase().equals(skiller.getName())) {
                            chop++;
                        }
                    }

                    if (chop <= 0) {
                        System.out.println("Unknown input.");
                    }
                }


            }

            System.out.println("");

            player.attackGuard(npc);

            System.out.println("");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (npc.getHealth().isDead()) {

                System.out.println(npc.getName() + " has died at your hands. \n");

                player.getHealth().setDamageResistance(dmgResist);

                if (npc.getName().equalsIgnoreCase("Oliver")) {
                    Item z = npc.getItem();
                    existingItems.add(z);
                    inRoom.getObjects().add(z.getName().toLowerCase());
                    System.out.println("The only salvaged item from the battle lays on the ground.");
                }


                playerStats.addXp(playerStats.calculateXp(npc.getName()));

                playerStats.calculateLv(playerStats.getXp(), playerStats.getLevel(), player);

                player.displayStats(player, playerStats);

                inRoom.getGuard().remove(npc); //remove an object that has the name Rabbit

                break;
            } else {

                npc.attack(player);
                System.out.println("");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (player.getHealth().isDead()) {
                    System.out.println("\nYou have been murdered by " + npc.getName() + " (and a fork... and Tom. And his spoon.)\n\n");
                    player.getHealth().setDamageResistance(dmgResist);
                    player.getHealth().setHeealth(-1291212112);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                }

            }

            for (Skill skill : player.getSkills()) {
                skill.reduceCooldown();
            }

        }


    }

    public static void combatBoss(Player player, Boss boss, Hub inRoom, XpLv playerStats, List<Item> existingItems, Map<String, Item> eqI, Equipment eq) {
        int dmgResist = player.getHealth().getDamageResistance();

        System.out.println("THE GREAT " + boss.getName() + " RECOGNIZES YOU AS IT'S PREY.");
        boolean using = false;

        int pause = 300;

        if (player.getSkills().isEmpty()) {

        } else {

            System.out.println("Would you like to your skills in this battle (y/n) ?");
            System.out.print("-> ");
            String answer = scanner.nextLine();

            if (!answer.isEmpty()) {
                if (answer.substring(0, 1).toLowerCase().equals("y")) {
                    using = true;
                } else {
                    using = false;
                }
            } else {
                using = false;
                System.out.println("No input taken...");
            }

            System.out.println("");
        }

        if (!using) {
            System.out.println("Would you like to speed thru this battle (y/n) ?");
            System.out.print("-> ");
            String speeder = scanner.nextLine();

            if (speeder == "y" || speeder == "yes") {
                pause = 0;
            }

        }

        while (!player.getHealth().isDead() && !boss.getHealth().isDead()) {

            if (using == true) {

                System.out.println("What skill would you like to use?");
                for (Skill skill : player.getSkills()) {
                    System.out.print("[" + skill.getName() + "]  ");
                }
                System.out.println("");
                System.out.print("-> ");

                String usingNow = scanner.nextLine();
                if (usingNow.toLowerCase().equals("stun")) {
                    Skill.StunSkill.applyBoss(player, boss);
                } else if (usingNow.toLowerCase().equals("harden")){
                    Skill.HardeningSkill.applyBoss(player, boss);
                } else {
                    int chop = 0;
                    for (Skill skiller : player.getSkills()) {
                        if (usingNow.toLowerCase().equals(skiller.getName())) {
                            chop++;
                        }
                    }

                    if (chop <= 0) {
                        System.out.println("Unknown input.");
                    }
                }


            }

            System.out.println("");
            if (eq.getItemBasedOnSlot("melee", eq.getEquippedItems()) != null) {
                Item boom = eq.getItemBasedOnSlot("melee", eq.getEquippedItems());
                boom.checkPowerBoss(boss);
            }
            // PLAYER ATTACK BOSS
            player.attackBoss(boss);

            System.out.println("");

            if (pause != 0) {
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (boss.getHealth().isDead()) {

                System.out.println(boss.getName() + " has died at your hands. \n");

                player.getHealth().setDamageResistance(dmgResist);

                playerStats.addXp(playerStats.calculateXp(boss.getName()));

                playerStats.calculateLv(playerStats.getXp(), playerStats.getLevel(), player);

                player.displayStats(player, playerStats);

                inRoom.getBoss().remove(boss); //remove an object that has the name Rabbit

                break;

            } else {

                boss.attack(player, eq, eqI);
                System.out.println("");

                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (player.getHealth().isDead()) {
                    System.out.println("\nYou have been murdered by " + boss.getName() + " (and a fork... and Tom. And his spoon.)\n\n");
                    player.getHealth().setDamageResistance(dmgResist);
                    player.getHealth().setHeealth(-1291212112);

                    if (pause != 0) {
                        try {
                            Thread.sleep(pause);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    break;
                }

            }

            for (Skill skill : player.getSkills()) {
                skill.reduceCooldown();
            }

            for (MobSkill skill : boss.getSkills()) {
                skill.reduceCooldown();
            }

        }


    }


    public static void equip(Item item, Equipment equipment, Player player) {
        equipment.equip(item, player);
    }

    public static void unequip(Item item, String slot, Player player, Equipment equipment) {
        equipment.unequip(slot, item, player);
    }

    public static List<String> talkGuard(Hub inRoom, List<String> inventory, XpLv playerStats, Guard npc, Player player) {
        if (!npc.getName().equalsIgnoreCase("Oliver")) {
            npc.talk();
            return inventory;
        }

        Quest oliverQuest = Player.QUESTS.get(npc.getQuestId()); // e.g. "SQ2" #syncing quests
        boolean hasQuest = (oliverQuest != null);
        boolean done = hasQuest && oliverQuest.done;

        // 1) First time → full 3 lines + choice
        if (npc.getQuestState() == Npca.QuestState.NONE) {
            npc.sayLine(0);
            scanner.nextLine();

            npc.sayLine(1);
            scanner.nextLine();

            npc.sayLine(2); // "Would you like to take on my quest?"

            System.out.print("Type 'a' to accept the quest and 'b' to decline: ");
            String a = scanner.nextLine().trim().toLowerCase();

            if (a.startsWith("a") && !done) {
                npc.setQuestState(Npca.QuestState.ACCEPTED);
                oliverQuest = new Quest("MQ3", "Collect 5 SnarkFlowers for Oliver", 2, "snarkflower", 5, 50, 20); // main quest - get flowers
                Player.QUESTS.put("MQ3", oliverQuest);
                System.out.println("Quest added: " + oliverQuest.status());
                //CHECK IF QUEST IS ALReADY COMPLETED
                if (Collections.frequency(inventory, "snarkflower") >= 5) {
                    oliverQuest.setSkip();
                    oliverQuest.check("COLLECT_ITEM", "snarkflower", player, playerStats);
                } else {
                    return inventory;
                }
            } else {
                npc.setQuestState(Npca.QuestState.OFFERED);
                npc.sayLine(3);
                return inventory;

            }
        }

        // 2) Player declined earlier (OFFERED): only last 2 lines
        if (npc.getQuestState() == Npca.QuestState.OFFERED) {
            npc.sayLine(1);
            npc.sayLine(2); // you can change this to a “still here if you want help” line
            System.out.print("Type a to accept the quest and b to decline: ");
            String a = scanner.nextLine().trim().toLowerCase();

            if (a.startsWith("a") && !done) {
                npc.setQuestState(Npca.QuestState.ACCEPTED);
                oliverQuest = new Quest("MQ3", "Collect 5 SnarkFlowers for Oliver", 2, "snarkflower", 5, 50, 20); // main quest - get flowers
                Player.QUESTS.put("MQ3", oliverQuest);
                System.out.println("Quest added: " + oliverQuest.status());
                //CHECK IF QUEST IS ALReADY COMPLETED
                if (Collections.frequency(inventory, "snarkflower") >= 5) {
                    oliverQuest.setSkip();
                    oliverQuest.check("COLLECT_ITEM", "snarkflower", player, playerStats);
                } else {
                    return inventory;
                }
            } else {
                npc.sayLine(3);
                return inventory;
            }
        }

        // 3) Player accepted but not done yet
        if (npc.getQuestState() == Npca.QuestState.ACCEPTED && !inventory.contains("guard pass")) {

            if (oliverQuest.isDone()) {

                System.out.println("Oliver (Guard) says: \"Awesome, you already have them!\"");
                scanner.nextLine();

                System.out.println("Oliver (Guard) says: \"Trade them for the guard pass?\" ");
                scanner.nextLine();

                System.out.println("Type 'a' to accept the quest and 'b' to decline: ");
                String a = scanner.nextLine().trim().toLowerCase();
                if (a.equalsIgnoreCase("a")) {
                    inventory.add("guard pass");
                    System.out.println("You received a Guard Pass for 5 SnarkFlowers");
                    // Mark quest done and reward
                    oliverQuest.done = true;
                    npc.setQuestState(Npca.QuestState.COMPLETED);
                    for (int i = 0; i < 5; i++) {
                        inventory.remove("snarkflower");
                    }
                    return inventory;
                } else {
                    System.out.println("Oliver (Guard) says: \"Aw shucks, that's too bad. Maybe later...\"");
                    return inventory;
                }
            } else {
                System.out.println("Oliver (Guard) says: \"Come back when you have enough.\"");
            }
            return inventory;
        }

        // 4) After completion → simple thank‑you / post‑quest line
        if (npc.getQuestState() == Npca.QuestState.COMPLETED) {
            System.out.println("Oliver (Guard) nods. \"Thanks again for the help.\"");
            return inventory;
        }

        return inventory;
    }

    public static List<String> talkNpc(Hub inRoom, List<String> inventory, XpLv playerStats, Npca npc, Player player) {

        String choice = "7";

        if (npc.getName().equalsIgnoreCase("Tragger")) {
            System.out.println("GuildMaster Tragger (Guild Master) says: What do you want?");

            while (choice != "6") {
                System.out.println("[1] Join Guild [2] View Rankings [3] View Quests [4] Report Quest [5] Accept Quest [6] Quit");
                System.out.print("-> ");

                choice = Game.scanner.nextLine().trim();

                if (choice.equals("1")) {
                    inRoom.joinGuild(player.getName());
                } else if (choice.equals("2")) {
                    inRoom.displayRankingBoard();
                } else if (choice.equals("3")) {
                    inRoom.displayQuestBoard(player.getName());
                } else if (choice.equals("4")) {

                    System.out.println("\n=== YOUR QUESTS ===");
                    for (Map.Entry<String, Quest> entry : Player.QUESTS.entrySet()) {
                        Quest q = entry.getValue();
                        if (q.getId().startsWith("GQ")) {  // Only guild quests
                            System.out.println("- " + q.status());
                        }
                    }

                    System.out.print("\nWhich COMPLETED guild quest to report? (GQ1, GQ2, etc): ");
                    String questId = Game.scanner.nextLine().trim();

                    Quest quest = Player.QUESTS.get(questId);
                    if (quest != null && quest.isDone()) {
                        inRoom.completeQuest(player.getName(), questId);
                        System.out.println("Quest verified! Rewards logged with guild.");
                    } else {
                        System.out.println("ERROR: Quest not found or not completed! Complete it first.");
                    }
                } else if (choice.equals("5")) {
                    inRoom.acceptQuests(player.getName(), player);
                } else {
                    System.out.println("GuildMaster Tragger: 'Choose 1-4 or leave me alone!'");
                }
            }
        } else if (npc.getName().equalsIgnoreCase("Trevor")) {

            System.out.println("Trevor (Clinic Clerk) says: \"Welcome welcome...\"");
            System.out.println("Trevor (Clinic Clerk) says: \"And what would you like to do...?\"");
            System.out.println("Options: [1] register [2] leave");
            String doer = scanner.nextLine();
            if (doer.equalsIgnoreCase("1")) {
                inRoom.applyToClinic(player);
            } else {
                System.out.println("Trevor (Clinic Clerk) says:  \"and... goodbye, don't disrupt the injured...\"");
            }
        } else if (npc.getName().equalsIgnoreCase("Zak")) {

            ArrayList<String> wordONES = new ArrayList<>();

            wordONES.add("divine");
            wordONES.add("furious");
            wordONES.add("thunderous");
            wordONES.add("ceremonial");
            wordONES.add("immaculate");
            wordONES.add("war-blessed");
            wordONES.add("overenthusiastic");
            wordONES.add("slightly judgmental");
            wordONES.add("heroically unnecessary");
            wordONES.add("dramatically over-polished");
            wordONES.add("aggressively moisturized");

            ArrayList<String> wordTWOS = new ArrayList<>();

            wordTWOS.add("helmet");
            wordTWOS.add("cannon");
            wordTWOS.add("boot");
            wordTWOS.add("soup ladle");
            wordTWOS.add("chapel bell");
            wordTWOS.add("training dummy");
            wordTWOS.add("ration tin");
            wordTWOS.add("commander’s emergency teacup");
            wordTWOS.add("ceremonial protein shaker");
            wordTWOS.add("sacred dumbbell (150KG)");

            ArrayList<String> wordTHREES = new ArrayList<>();

            wordTHREES.add("royal");
            wordTHREES.add("imperial");
            wordTHREES.add("regimental");
            wordTHREES.add("field marshal’s");
            wordTHREES.add("disciplinary");
            wordTHREES.add("tribunal");
            wordTHREES.add("budget review");
            wordTHREES.add("promotion board");
            wordTHREES.add("extended family visit");
            wordTHREES.add("very disappointed aunt’s");
            wordTHREES.add("unexpected deity audit");

            String word1 = wordONES.get((int) (Math.random() * 10));
            String word2 = wordTWOS.get((int) (Math.random() * 10));
            String word3 = wordTHREES.get((int) (Math.random() * 10));

            System.out.println("By the time I’m done with you, this barracks will shine like a " + word1 + " " + word2 + " at a " + word3 + " inspection.");

        } else if (npc.getName().equalsIgnoreCase("MaraTamara")) {

            System.out.println("Mara (Innkeeper) says: \"Need a room or just wandering?\"");
            System.out.println("Options: [1] pay for room  [2] ask about stay  [3] leave");
            System.out.print("-> ");
            String ch = scanner.nextLine().trim();

            Hub.Inn innRoom = (Hub.Inn) inRoom;

            if (ch.equals("1")) {

                boolean ok = innRoom.startStay(player, inventory);

            } else if (ch.equals("2")) {
                if (innRoom.hasActiveStay(player)) {
                    System.out.println("Mara (Innkeeper): \"You still have an active room. Go down the hall and sleep whenever.\"");
                } else {
                    System.out.println("Mara (Innkeeper): \"You don't have a room right now. Pay some copper if you want to stay.\"");
                }

            } else {
                System.out.println("Mara (Innkeeper): \"Alright, don't scare the other guests.\"");
            }
        } else if (npc.getName().equalsIgnoreCase("Jerr")) {

            // Already own boat?
            if (player.ownsBoat()) {
                System.out.println("Jerr (Boatman): \"You already have a boat, friend.\"");
                return inventory;
            }

            System.out.println("Jerr (Boatman): \"A seaworthy boat will cost you 30 silver. Buy it? (y/n)\"");
            System.out.print("-> ");
            String ans = Game.scanner.nextLine().trim().toLowerCase();

            if (ans.equals("yes") || ans.equals("y")) {
                int price = 30;
                int silverCount = Collections.frequency(inventory, "silver");

                if (silverCount >= price) {
                    for (int i = 0; i < price; i++) {
                        inventory.remove("silver");
                    }
                    player.setOwnsBoat(true);
                    System.out.println("Jerr (Boatman): \"Pleasure doing business. The boat at West Port is now yours.\"");
                } else {
                    System.out.println("Jerr (Boatman): \"Come back when you can actually afford it.\"");
                }
            } else {
                System.out.println("Jerr (Boatman): \"Suit yourself.\"");
            }
        } else {

            npc.sayLine(0);

        }

        return inventory;


    }

    public static List<String> talkShopFirst(Hub inRoom, List<String> inventory, XpLv playerStats, FirstShopOwner npc, Player player, Equipment equipment) {
        System.out.println(npc.getName() + " (Shop Keeper): \"Welcome to my shop!\" [enter to continue]");
        scanner.nextLine();

        String choice = "8";
        while (choice != "3") {
            System.out.println("[1] Buy  [2] Sell  [3] Leave");
            System.out.print("-> ");

            choice = scanner.nextLine().trim();

            if (choice.isEmpty()) {
                System.out.println(npc.getName() + " stares blankly as you say nothing...");
                return inventory;
            }

            if (choice.equals("1")) {
                inventory = npc.buyItem(player, inventory);

            } else if (choice.equals("2")) {
                inventory = npc.sellItem(player, inventory, equipment);

            } else {
                System.out.println("Thanks for visiting " + player.getName() + "! *you are shoo-ed away...");
                return inventory;
            }
        }

        return inventory;
    }

    private static String getInspectTarget(String action, List<String> verbs) {

        for (String v : verbs) {
            if (action.startsWith(v + " ")) {

                return action.substring(v.length()).trim();
            }
            if (action.equals(v)) {

                return "";
            }
        }

        return action;
    }

    public static void describeItem(String itemName) {
        String yum = itemName.toLowerCase();
        if (INSPECT_DESCRIPTIONS.containsKey(yum)) {
            System.out.println(INSPECT_DESCRIPTIONS.get(yum));
        } else {
            System.out.println("You look closer at the " + itemName + ", but nothing special stands out.");
        }
    }

    public static void describeCombat(String key) {
        if (COMBAT_DESCRIPTIONS.containsKey(key)) {
            System.out.println(COMBAT_DESCRIPTIONS.get(key));
        }
    }

    public static List<String> cauldron(List<String> invin, Player player) {

        if (player.QUESTS.get("SQ4") == null) {
            Player.QUESTS.put("SQ4", new Quest("SQ4", "Craft Wolfs Bane Soup", 2, "wolfs bane soup", 1, 100, 20));
            System.out.println("[[ YOU'VE UNLOCKED A QUEST ]]");
            System.out.println("");
        }

        List<String> craftable = new ArrayList<>();
        craftable.add("wolfs bane soup");
        craftable.add("apple pie");

        System.out.println("What would you like to craft?\n");
        for (String a : craftable) {
            System.out.println(a);
        }
        System.out.println("");

        System.out.print("-> ");

        String craft = scanner.nextLine();

        int doable = 0;

        for (String i : craftable) {
            if (i.equalsIgnoreCase(craft)) {
                doable++;
                break;
            }
        }

        if (doable != 0) {

            if (craft.equalsIgnoreCase("wolfs bane soup")) {
                int wolfbane = 0;
                int ravenEye = 0;
                int bloody = 0;


                for (String w : invin) {

                    if (w == "wolfbane") {
                        wolfbane++;
                    }
                    if (w == "raven eye") {
                        ravenEye++;
                    }
                    if (w == "blood vial") {
                        bloody++;
                    }
                }

                if (wolfbane >= 3 && ravenEye >= 1 && bloody >= 1) {
                    System.out.println("Success! You've made a Wolfs bane Soup!");
                    invin.add("wolfs bane soup");
                    for (int i = 0; i < 3; i++) {
                        invin.remove("wolfbane");
                    }
                    invin.remove("raven eye");
                    invin.remove("blood vial");
                    return invin;

                } else {
                    System.out.println("You are lacking the materials to craft this: Wolfbane x3, raven eye, blood vial");
                    return invin;

                }
            } else if (craft.equalsIgnoreCase("apple pie")) {

                int apple = 0;
                int wheat = 0;

                for (String w : invin) {

                    if (w == "apple") {
                        apple++;
                    }
                    if (w == "wheat") {
                        wheat++;
                    }
                }

                if (apple >= 2 && wheat >= 2) {
                    System.out.println("Success! You've made Apple Pie!");
                    invin.add("apple pie");
                    for (int i = 0; i < 2; i++) {
                        invin.remove("apple");
                        invin.remove("wheat");
                    }

                    return invin;

                } else {
                    System.out.println("You are lacking the materials to craft this: Apple x3, Wheat x3");
                    return invin;

                }
            }
        } else {
            System.out.println("Yeah, you are gonna have to go somewhere else to craft that...");
            return invin;
        }

        return invin;


    }

    private static int extractNumber(String text) {
        Pattern pattern = Pattern.compile("\\b(1|2|3)\\b");  // tries to find any 1,2,3 if there are any
        Matcher matcher = pattern.matcher(text.toLowerCase());
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }

    public static List<String> eatDuringBattle(String target, Map<String, Integer> food, Player player, List<String> inventory) {

        for (String key : food.keySet()) {

            if (target.equalsIgnoreCase(key)) {

                int hungerBonus = food.get(key);

                if (key.contains("golden")) {
                    System.out.println("You've eaten a super food! [max health is discarded in this scenario]");
                    player.addFullness(hungerBonus);

                    System.out.println("Your fullness increases by " + hungerBonus + "! [fullness: " + player.getFullness() + "]");

                    inventory.remove(key);

                } else {

                    if (player.getFullness() < player.getStomachSize()) {

                        player.addFullness(hungerBonus);

                        if (player.getStomachSize() < player.getFullness()) {
                            player.setFullness();
                            hungerBonus = -898;
                        }

                        if (hungerBonus != -898) {
                            System.out.println("Your fullness increases by " + hungerBonus + "! [fullness: " + player.getFullness() + "]");
                        } else {
                            System.out.println("You smirk as you feel your stomach fill substantially.");
                        }
                    } else {
                        System.out.println("Your fullness is not affected as you already full!");
                    }

                }

                inventory.remove(key);

                if (player.getHealth().getHeealth() < player.getHealth().getMaxHealth()) {
                    hungerBonus *= 0.65;
                    if (hungerBonus > 0) {
                        player.getHealth().setHeealth(player.getHealth().getHeealth() + hungerBonus);
                        if (player.getHealth().getHeealth() > player.getHealth().getMaxHealth()) {
                            player.getHealth().setHeealth(player.getHealth().getMaxHealth());
                        }
                        System.out.println("Your health has increased!");
                    } else {

                        player.getHealth().setHeealth(player.getHealth().getHeealth() + 1);
                        if (player.getHealth().getHeealth() > player.getHealth().getMaxHealth()) {
                            player.getHealth().setHeealth(player.getHealth().getMaxHealth());
                        }
                        System.out.println("Your health has increased!");

                    }
                }

                return inventory;

            }
        }

        System.out.println("Your gonna have some trouble eating that...");

        return inventory;

    }

    public static String unPluralizer(String word) {
        if (word == null || word.length() == 0) return word;

        word = word.toLowerCase().trim();

        // irregulars first
        if (word.equals("berries")) return "berry";
        if (word.equals("leaves")) return "leaf";
        if (word.equals("clothes")) return "cloth";
        if (word.equals("teeth")) return "tooth";
        if (word.equals("boots")) return "boots";
        if (word.equals("legs")) return "legs";


        if (word.endsWith("ies") && word.length() > 3) {
            return word.substring(0, word.length() - 3) + "y";
        }

        if (word.endsWith("s") && word.length() > 1) {
            return word.substring(0, word.length() - 1);
        }

        return word;
    }

    public static Hub rideWithBill(Hub current, Npca riderBill, Npca horse, Player player, List<String> inventory) {

        boolean horseDead = (horse == null || horse.getHealth().isDead());

        if (horseDead) {

            System.out.println("Guider Bill glares at you silently. There will be no more rides.");

            return current;
        }

        System.out.println("Bill (Guider) says: \"3 copper and I'll take you between FirstVille Lane 3 and Southern Forest Area 21.\"");
        System.out.println("Options: [1] ride  [2] leave");
        System.out.print("-> ");
        String ch = Game.scanner.nextLine().trim();

        if (!ch.equals("1")) {
            System.out.println("Guider Bill shrugs and looks away.");
            return current;
        }

        int copper = Collections.frequency(inventory, "copper");
        if (copper < 3) {
            System.out.println("Bill (Guider) says: \"You don't have enough copper. Come back with 3.\"");
            return current;
        }

        for (int i = 0; i < 3; i++) {
            inventory.remove("copper");
        }

        Hub target;

        if (current.getRoomName().equalsIgnoreCase("FirstVille Streets #3")) {
            target = Hub.get("Southern Forest Area #21");
        } else if (current.getRoomName().equalsIgnoreCase("Southern Forest Area #21")) {
            target = Hub.get("FirstVille Streets #3");
        } else {
            System.out.println("Bill (Guider) says: \"I only run between FirstVille Lane 3 and Southern Forest Area 21.\"");
            return current;

        }

        if (target == null) {
            System.out.println("Something is wrong with the travel route.");
            return current;
        }

        // move NPCs between rooms
        current.getNpc().remove(riderBill);
        current.getNpc().remove(horse);
        target.getNpc().add(riderBill);
        target.getNpc().add(horse);

        System.out.println("You ride with " + riderBill.getName() + " on " + (horse != null ? horse.getName() : "the horse") + ".");
        System.out.println("You arrive at " + target.getRoomName() + ".");

        return target;
    }

    public static void mobileBag(Player player, List<String> inventory) {

        System.out.println("##-MOBILE SHOPPING CART-##");
        System.out.println("Rank - " + player.getMobileCraft());

        if (!player.getMobileCraft().equalsIgnoreCase("platinum")) {

            System.out.print("[1] SHOP    ||     [2] UPGRADE RANK");

            int command = 1;

            try {
                command = scanner.nextInt();
            } catch (NumberFormatException Ignored) {
                System.out.println("Invalid Input");
            }

            if (command == 2) {

                int cost = 0;

                String upgraded = "";

                if (player.getMobileCraft().equalsIgnoreCase("bronze")) {

                    cost = 200;
                    upgraded = "silver";

                } else if (player.getMobileCraft().equalsIgnoreCase("silver")) {

                    cost = 500;
                    upgraded = "gold";

                } else if (player.getMobileCraft().equalsIgnoreCase("gold")) {

                    cost = 800;
                    upgraded = "platinum";

                }

                System.out.println("Current RANK - '" + player.getMobileCraft().toUpperCase() + "'    //     Upgrade Cost: " + cost + " fame");
                System.out.print("[1] Upgrade    ||     [2] Pass");
                try {
                    command = scanner.nextInt();
                } catch (NumberFormatException Ignored) {
                    System.out.println("Invalid Input.");
                }

                if (command == 1) {
                    if (player.getCoins() >= cost) {
                        player.addCoins(-cost);
                    }
                    System.out.println("**UPGRADED**");
                    player.setMobileCraft(upgraded);
                }

            }


        }

        Map<String, Integer> bronze = new HashMap<>();
        bronze.put("", 2);

        Map<String, Integer> silver = new HashMap<>();
        bronze.put("", 2);

        Map<String, Integer> gold = new HashMap<>();
        bronze.put("", 2);

        Map<String, Integer> platinum = new HashMap<>();
        bronze.put("", 2);

        // SHOPPING CODE

        int index = 1;

        List<Map.Entry<String, Integer>> itemsList;

        if (player.getMobileCraft().equalsIgnoreCase("bronze")) {
            itemsList = new ArrayList<>(bronze.entrySet());
            for (Map.Entry<String, Integer> entry : bronze.entrySet()) {
                System.out.println(index + ") " + entry.getKey() + " - " + entry.getValue() + " fame");
                index++;
            }

        } else if (player.getMobileCraft().equalsIgnoreCase("silver")) {
            itemsList = new ArrayList<>(silver.entrySet());
            for (Map.Entry<String, Integer> entry : silver.entrySet()) {
                System.out.println(index + ") " + entry.getKey() + " - " + entry.getValue() + " fame");
                index++;
            }

        } else if (player.getMobileCraft().equalsIgnoreCase("gold")) {
            itemsList = new ArrayList<>(gold.entrySet());
            for (Map.Entry<String, Integer> entry : gold.entrySet()) {
                System.out.println(index + ") " + entry.getKey() + " - " + entry.getValue() + " fame");
                index++;
            }
        } else {
            itemsList = new ArrayList<>(platinum.entrySet());
            for (Map.Entry<String, Integer> entry : platinum.entrySet()) {
                System.out.println(index + ") " + entry.getKey() + " - " + entry.getValue() + " fame");
                index++;
            }
        }


        System.out.print("What would you like to buy? (number or 'leave'): ");
        String blah = scanner.nextLine().trim();

        if (blah.equalsIgnoreCase("leave")) {
            return;
        }

        try {
            int choice = Integer.parseInt(blah) - 1;
            if (choice >= 0 && choice < itemsList.size()) {
                Map.Entry<String, Integer> selected = itemsList.get(choice);
                String itemName = selected.getKey();
                int price = selected.getValue();

                if (player.getCoins() >= price) {
                    player.addCoins(-price);
                }

                System.out.println("You bought " + itemName + " for " + price + " fame!");

                inventory.add(itemName);

            } else {
                System.out.println("Unable to identify target.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input.");
        }

    }

}