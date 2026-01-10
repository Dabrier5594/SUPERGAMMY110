import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;import java.util.Timer;
import java.util.TimerTask;


//                    NOTES FOR IMPROVEMENTS:

// MAKE IT SO THAT MOBS RESPAWN AFTER 5 NIGHTS

//checkovs gun bonus stuff too!!!!

// MAKE SO IF YOU ENTER NOTHING WHEN TALKING TO BAGGER YOU GET ATTACKED.

//add enchanment level caps at each village but bosses can drop items/enchantments that are 1-2 lvels higher than the village

// inspect (create an array for items with mini descriptions), armor - chest plate, fix prints, IH = something,add a take ALL, map,

// MAKE IT SO IF YOU ARE INSIDE FIRSTVILLE AND YOU TAKE OFF ARMOR, PEOPLE WILL KICK YOU OUT.

// Make rouge bandits

//  ADD THINGS TO PLACES

// MAKE ENCHANCEMENTS AND ITEMS

//  CREATE FIRST SMALL TOWN

//  CREATE FIRST SMALL TOWN BOSS

//  CREATE SECOND TOWN. CREATE GUILD WITH QUESTS AND A CARD AND EVERYTHING

public class Game {

    //DESCRIPTIONS:

    public static final Map<String, String> INSPECT_DESCRIPTIONS = new HashMap<>();

    static {
        INSPECT_DESCRIPTIONS.put("dagger", "A simple dagger. Not very fancy, but it gets the stabbing done.");
        INSPECT_DESCRIPTIONS.put("leather armor", "Light leather armor. Better than going shirtless into a goblin nest.");
        INSPECT_DESCRIPTIONS.put("thornshield", "The shield of the forest boss. Covered in sharp thorns that deter attackers.");
        INSPECT_DESCRIPTIONS.put("copper", "Shiny copper coins. 5 make 1 silver.");
        INSPECT_DESCRIPTIONS.put("silver", "Silver coins. 10 make 1 gold.");
        INSPECT_DESCRIPTIONS.put("gold", "Gold coins. The real deal.");
        INSPECT_DESCRIPTIONS.put("cabinet", "A sturdy wooden cabinet. Might be hiding something useful inside.");
        INSPECT_DESCRIPTIONS.put("leaflet", "A crumpled leaflet with faded ink. It mentions something about Firstville’s annual fair.");
        INSPECT_DESCRIPTIONS.put("twig", "Just a brittle twig. Maybe useful for starting a fire... or poking something suspicious.");
        INSPECT_DESCRIPTIONS.put("snarkflower", "A rare flower that seems to glare at you. Smells faintly of sarcasm.");
        INSPECT_DESCRIPTIONS.put("door", "A sturdy door. It doesn’t seem too eager to open for strangers.");
        INSPECT_DESCRIPTIONS.put("rabbit", "A small rabbit munching on grass. It looks harmless—probably.");
        INSPECT_DESCRIPTIONS.put("chicken", "A plump chicken strutting around proudly. Clearly unaware of its place on the food chain.");
        INSPECT_DESCRIPTIONS.put("squirrel", "A jittery squirrel. It eyes your pockets as if it knows you’ve got nuts.");
        INSPECT_DESCRIPTIONS.put("goblin", "A foul little goblin. Smells like spoiled mushrooms and bad decisions.");
        INSPECT_DESCRIPTIONS.put("chest", "A heavy chest. It radiates the promise of treasure and the danger of traps.");
        INSPECT_DESCRIPTIONS.put("ih", "A strange insignia etched with the letters ‘IH’. Feels oddly important.");
        INSPECT_DESCRIPTIONS.put("white whispberry", "A glowing white berry that hums softly. It’s said to restore focus to the weary.");
        INSPECT_DESCRIPTIONS.put("bagger", "A shady fellow with too many bags. Hard to tell what’s in them—or if you want to know.");
        INSPECT_DESCRIPTIONS.put("ragger", "A rough-looking traveler wrapped in tattered rags. His eyes burn with stubborn will.");
        INSPECT_DESCRIPTIONS.put("gate", "A large iron gate, locked and imposing. Beyond it lies unknown trouble—or freedom.");
        INSPECT_DESCRIPTIONS.put("firstville guards plate", "Reinforced plate armor issued to Firstville’s guards. Reliable and shiny.");
        INSPECT_DESCRIPTIONS.put("firstville guards helm", "A steel helm with the Firstville crest. Offers good protection and limited vision.");
        INSPECT_DESCRIPTIONS.put("firstville guards boots", "Heavy boots that clank with every step. Ideal for patrols, not stealth missions.");
        INSPECT_DESCRIPTIONS.put("firstville guards legs", "Solid metal greaves made to withstand a goblin’s blade or a villager’s kick.");
    }

    // OTHER CLASS SCOPE VARIABLES!
    public static volatile boolean timeOfDay = true; // true = day, false = night

    public static volatile boolean scannerOrNo = false; // true = scanning, false = no scanning

    public static volatile String timeChange = null; // day or night

    public static volatile LocalTime changeTime = null; //when change happened

    public static volatile int nightCounter = 0; // true = day, false = night


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
                if (player.isInCombat()) {
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

            if (!scannerOrNo) { // SCANNER IS ACTIVE - HOLD LATEST CHANGES
                timeChange = timeOfDay ? "day" : "night";
                changeTime = LocalTime.now();
                timeOfDay = !timeOfDay;


            } else {

                System.out.println("It is turning " + (timeOfDay ? "day" : "night") + "...");

                timeOfDay = !timeOfDay;

            }

        }
    }

    static class MoveMob extends TimerTask {
        @Override
        public void run() {
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

    //LEAFLETS
    public static final Map<String, Leaflet> LEAFLETS = new HashMap<>();

    static {
        LEAFLETS.put("leaflet001",
                new Leaflet("leaflet001", "leaflet",
                        "Welcome to the world of Tim", "Thisxz world is one which mysteries and secrets about itself. \nPrepare yourself, to delve deeper into the world of Tim and experience the un-imaginable. \nHint: (Use 'help' to get help)"));

        LEAFLETS.put("leaflet002",
                new Leaflet("leaflet002", "leaflet",
                        "The Money Challenges", "The world is in a money crisis, and people are going on more dangerous quests to get more money. \nOne gold is 10 silver, 1 silver is 5 copper. \nHint: (More dangerous quests make more money! But they are also much harder to complete...)"));

        LEAFLETS.put("leaflet003",
                new Leaflet("leaflet003", "leaflet",
                        "Hunter's Notes (1/2)", "date: 01/43/2494 - Bear fight \nWell, I suppose it was less of a fight and more of a sacrifice. (Also, one of the BANDITS stole my key...) Damn it, if only I had known, like those goblins did, that to kill that bear family you had to use a [note was torn here]  \nHint: (Use 'help' to get help)"));

        LEAFLETS.put("leaflet004",
                new Leaflet("leaflet004", "leaflet",
                        "Hunter's Notes (2/2)", "date: 01/43/2494 - Bear fight \nbear claw! (Oh yeah, one of e'm damn goblins stole my claw! Hope they die...) \nHint: (Use 'help' to get help)"));

    }

    //CHEST TOKENS

    public static final Map<String, Token> TOKENS = new HashMap<>();

    static {
        TOKENS.put("IH001",
                new Token(001, "IH"));

        TOKENS.put("IH002",
                new Token(002, "IH"));

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

        Player.QUESTS.put("MQ1", new Quest("MQ1", "Leave The Cave", 3, "Southern Forest Area #1", 1, 50, 5));  // main quest - go outside cave
        Player.QUESTS.put("SQ1", new Quest("SQ1", "Kill Goblins", 1, "goblin", 3, 30, 15));                 // side quest - kill 3 goblins
        Player.QUESTS.put("SQ2", new Quest("SQ2", "Collect Twigs", 2, "twig", 5, 20, 10));                  // side quest - get 5 twigs
        Player.QUESTS.put("SQ3", new Quest("SQ3", "Go Up Some Stairs", 3, "Abandon House's Upper Room", 1, 40, 25)); // side quest - go upstairs

        // TOMS CAVE

        Hub cave = new Hub("Tom's Dark Cave", "The back of the ancient cave where Tom the hermit lived for many years. \nEXITS: (N) ");

        Hub caveN = new Hub("Tom's Dark Kitchen", "Still in Tom's cave, but now you have moved into his kitchen which consists of a counter and just a counter. \nEXITS: (N) (S)");

        Hub caveNN = new Hub("Tom's Dark Treasure Room", "Still in Tom's cave, but now you have moved into his treasure room which has no furniture. \nEXITS: (W) (E) (S)");

        Hub caveNE = new Hub("Tom's Dark Living Room", "Still in Tom's cave, but now you have moved into his living room which. \nEXITS: (W)");

        Hub caveNW = new Hub("Tom's Dark Entrance", "Still in Tom's cave, but now you have moved to the cave's entrance, where a door stands. \nEXITS: (N) (E)");

        // S FOREST

        Hub forest1 = new Hub("Southern Forest Area #1", "Just outside Tom's cave and just inside The Southern Area of the Great Makiss Forest. but now you have moved to the cave's entrance. \nEXITS: (S) (N) ");

        Hub forest2 = new Hub("Southern Forest Area #2", "The Southern Area of the Great Makiss Forest. \nOnly minor prey lay in wait in this forest. \nEXITS: (S) (W) ");

        Hub forest4 = new Hub("Southern Forest Area #4", "The Southern Area of the Great Makiss Forest. \nThe air feels heavy here, and the forest is unusually silent. The ground is littered with broken twigs, as though something recently passed through... \nEXITS: (E) (W)");

        Hub forest3 = new Hub("Southern Forest Area #3", "The Southern Area of the Great Makiss Forest. \nThorny bushes block much of the way south, their barbs glinting sharply. However, the path seems clearer to the north, where faint sunlight filters through. \nEXITS: (E) (W)");

        Hub forest5 = new Hub("Southern Forest Area #5", "The Southern Area of the Great Makiss Forest. \nReminiscence of something eating a chicken is visible under scattered leaves...\nEXITS: (E) (N)");

        Hub forest6 = new Hub("Southern Forest Area #6", "The Southern Area of the Great Makiss Forest. \nThe trees grow apart here, their canopies still blotting out much of the sunlight. The air feels damp, and the dull chest#001 sits quietly in the middle of the area... \nEXITS: (S) (N) (W)");

        Hub forest7 = new Hub("Southern Forest Area #7", "The Southern Area of the Great Makiss Forest. \nSmells like bear blood... Clue for bear hunt. One that must have ended poorly for the Poacher. \nEXITS: (S) (W)");

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

        Hub sHouseStairs = new Hub("Abandon House's Staircase", "Inside an abandon house located in the Southern part of the forest. \nThe narrow staircase ascends with a groan at every step. Dust dances in the beams of pale light filtering from above.\nEXITS: (N) (S [upwards])");

        Hub sHouseUpper = new Hub("Abandon House's Upper Room", "Inside an abandon house located in the Southern part of the forest. \nThe upper room smells of mildew and forgotten time. A rusty chest sits in the corner...\nEXITS: (N [downwards])");


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

        Hub firstVilleGate = new Hub("Gate of FirstVille", "The Great Gate of FirstVille.\nYou stand in a clearing, opposite from the forest, in front of a great booming gate: (S) (N)");
        firstVilleGate.setStructure("gate");

        Hub firstVillePassage = new Hub("The Great Passage into FirstVille", "A short passage that reaches the Great Gate and the town square. \nTo the south stand wood line gates, to the north you see bustling people and lights. \nEXITS: (N) (S)");
        firstVillePassage.setStructure("gate");

        Hub firstVilleSquare = new Hub("FirstVille Square", "The bustling heart of FirstVille. Merchants shout their wares and townsfolk mill about. \nEXITS: (N) (S)");

        Hub firstVilleLane1 = new Hub("FirstVille Streets #1", "North of the Town Square, houses line the streets, children can be seen playing in them. \nEXITS: (N) (S)");

        Hub firstVilleLane2 = new Hub("FirstVille Streets #2", "The bustling heart of FirstVille. Merchants shout their wares and townsfolk mill about. \nEXITS: (W) (S) (E)");

        Hub firstVilleLane3 = new Hub("FirstVille Streets #3", "The bustling heart of FirstVille. Merchants shout their wares and townsfolk mill about. \nEXITS: (E)");

        Hub firstVilleLane4 = new Hub("FirstVille Streets #4", "You hear the clanging of hammers on metal \nEXITS: (E) (S) (N)");

        Hub firstVilleLane5 = new Hub("FirstVille Streets #5", "You see smoke rising from chimneys and smell smoke and molten metal \nEXITS: (E) (S) (N)");






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
        sHouseStairs.setExit("s", sHouseUpper);
        sHouseUpper.setExit("n", sHouseStairs);

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

        LockedDoors gateDoor = new LockedDoors(true, "Guard Key", firstVilleGate, firstVillePassage);

        firstVilleGate.setLockedDoor("n", gateDoor);
        firstVillePassage.setLockedDoor("s", gateDoor);

        // ITEMS

        //ADD ITEMS IN TO ROOM
        caveN.addObject("leaflet001");

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

        forest12.addObject("leaflet003");


        firstVilleGate.addObject("snarkflower");
        forest16.addObject("snarkflower");
        forest47.addObject("snarkflower");
        forest29.addObject("snarkflower");
        forest13.addObject("snarkflower");



        // MAKE CHEST STUFF HAPPEN HERE

        List<String> chestContents = Arrays.asList("copper");

        Chest firstChest = new Chest(true, "IH001", chestContents, "C");
        forest6.addChest("chest#001", firstChest);

        forest6.addObject("IH001");

        chestContents = Arrays.asList("silver", "silver");

        Chest secondChest = new Chest(true, "IH002", chestContents, "C");
        sHouseUpper.addChest("chest#002", secondChest);

        forest26.addObject("IH002");

        chestContents = Arrays.asList("bear amulet");

        Chest thirdChest = new Chest(true, "IH003", chestContents, "C");
        forest47.addChest("chest#003", thirdChest);

        // ADD MOBS to room

        List<String> stuff = new ArrayList<>() ;
        stuff.add("bear claw");
        Mob goblin = createGoblinWithRandomStats(stuff);
        stuff.remove("bear claw");

        forest10.getMOBS().add(goblin);

        for (int o = 0; o < 3; o++) {

            goblin = createGoblinWithRandomStats(stuff);

            forest11.getMOBS().add(goblin);
        }

        stuff.add("IH003");
        stuff.add("leaflet004");
        Mob Mute = createMuteBanditWithRandomStats(stuff);
        stuff.remove("IH003");
        stuff.remove("leaflet004");

        forest12.getMOBS().add(Mute);

        for (int o = 0; o < 2; o++) {

             goblin = createMuteBanditWithRandomStats(stuff);

            forest12.getMOBS().add(goblin);
        }

        stuff.add("bear fur");
        goblin = createBearWithRandomStats(stuff, 70, "Mama Bear");
        stuff.remove("bear fur");

        forest47.getMOBS().add(goblin);

        stuff.add("thorn shield");
        List<MobSkill> skillsForestDevil = new ArrayList<>();
        Boss forestDevil = new Boss("forest devil", 80, 80, 8, 3, "Fierce ForestDevil's hate to be interrupted, you get the devil's stick eye. His forest embraced body curves and twists, branches stick out from nowhere. \nThe number 3 is engraved on his body in stones that seem to glow.", stuff);
        forestDevil.getSkills().add(new MobSkill.StunSkill("Stun", 3));
        stuff.remove("thorn shield");
        forest50.getBoss().add(forestDevil);




        //enchancements
        Enchantments fire = new Enchantments("Fire", "Lights enemies on fire", 1);
        Enchantments healthBoost = new Enchantments("Health Boost", "Gives you a higher max health", 1);

        //MAKE ITEMS AND "EQUIPMENT"

        Equipment equipment = new Equipment();

        Item bearClaw = new Item("bear claw", "melee", true, null);
        Item baggerDagger = new Item("Bagger's Dagger", "melee", true, null);
        Item sickles = new Item("Sickles", "melee", true,null);
        Item knightBoots = new Item("Knights Footwear", "boots",false, null);
        Item knightChest = new Item("Knights Breastplate", "body",false, null);
        Item knightLeggings = new Item("Knights Leggings", "legging",false, null);
        Item knightHelmet = new Item("Knights Helm", "head",false, null);
        Item dagger = new Item("dagger", "melee", true, null);
        Item leatherArmor = new Item("leather armor", "body", false, null);
        Item FirstVillePlate = new Item("firstville guards plate", "body", false, null);
        Item FirstVilleHelm = new Item("firstville guards helm", "head", false, null);
        Item FirstVilleLegs = new Item("firstville guards legs", "legging", false, null);
        Item FirstVilleBoots = new Item("firstville guards boots", "boots", false, null);



        //Boss Drops
        Item thornShield = new Item("the shield of the forest boss", "melee", true, null);

        //forest boss

        //NPCS
        Merchant Ragger = new Merchant("Ragger", raggerLines, raggerStock, raggerH, 2, "SQ0", Npca.QuestState.NONE);

        FirstShopOwner baggerOwner = new FirstShopOwner("copper", "Bagger", raggerLines, raggerH, 2, null, "SQ0", Npca.QuestState.NONE);
        Hub.FirstVilleShop baggerShop = new Hub("Ragger's cousin's Gear Shop (BAGGER)", "Cluttered shelves hold weapons and an anvil sits in a corner. Bagger watches you closely.\n").new FirstVilleShop("Ragger's cousin's Gear Shop (BAGGER)", "Cluttered shelves hold weapons and an anvil sits in a corner. Ragger watches you closely. \nEXITS: (S)", baggerOwner);
        Item copperSword = new Item("Copper Sword", "melee", true, null);
        baggerShop.addStock(copperSword, 20);
        baggerShop.addStock(sickles, 30);
        baggerOwner.setMyShop(baggerShop);
        baggerShop.getFirstShopOwners().add(baggerOwner);
        baggerShop.setExit("w", firstVilleLane2);
        firstVilleLane2.setExit("e", baggerShop);


        FirstShopOwner laggerOwner = new FirstShopOwner("copper", "Lagger", raggerLines, raggerH, 2, null, "SQ0", Npca.QuestState.NONE);
        Hub.FirstVilleShop laggerShop = new Hub("Bagger's brother in law's Armory Shop (LAGGER)", "Cluttered shelves hold weapons and armor. Lagger starts washing a new helmet.\n").new FirstVilleShop("Bagger's brother in law's Armory Shop (LAGGER)", "Cluttered shelves hold weapons and armor. Lagger starts washing a new helmet.\nEXITS: (S)", laggerOwner);
        laggerShop.addStock(knightHelmet,15);
        laggerShop.addStock(knightLeggings,25);
        laggerShop.addStock(knightChest,30);
        laggerShop.addStock(knightBoots,15);

        //Shop Exits
        firstVilleLane4.setExit("s", baggerShop);
        firstVilleLane2.setExit("e", baggerShop);
        baggerShop.setExit("n", firstVilleLane4);
        baggerShop.setExit("w", firstVilleLane2);
        firstVilleLane5.setExit("e", laggerShop);
        laggerShop.setExit("w", firstVilleLane5);




        Guard Oliver = new Guard("Oliver", oliverLines, oliverP, 8, FirstVilleHelm, FirstVillePlate, FirstVilleLegs, FirstVilleBoots, "MQ4", Npca.QuestState.NONE);

        firstVilleGate.getGuard().add(Oliver);

        //MAKE ITEMS EXIST IN ITEMS
        List<Item> existingItems = new ArrayList<>();
        existingItems.add(dagger);
        existingItems.add(leatherArmor);


        Story(cave, existingItems, equipment);

    }

    public static void Story(Hub cave, List<Item> existingItems, Equipment equipment) {

        // Makes the room you are in "cave"
        Hub inRoom = cave;

        //List of verbs
        List<String> verbs = new ArrayList<>();
        verbs.add("move");
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
        verbs.add("coins");
        verbs.add("l");
        verbs.add("inventory");
        verbs.add("kill");
        verbs.add("attack");
        verbs.add("stats");
        verbs.add("equip");
        verbs.add("unequip");
        verbs.add("quests");
        verbs.add("talk");


        List<String> verbsOnly = new ArrayList<>();
        verbsOnly.add("n");
        verbsOnly.add("s");
        verbsOnly.add("w");
        verbsOnly.add("e");
        verbsOnly.add("coins");
        verbsOnly.add("north");
        verbsOnly.add("south");
        verbsOnly.add("quests");
        verbsOnly.add("west");
        verbsOnly.add("east");
        verbsOnly.add("look");
        verbsOnly.add("l");
        verbsOnly.add("wait");
        verbsOnly.add("listen");
        verbsOnly.add("help");
        verbsOnly.add("stats");


        List<String> directions = new ArrayList<>();
        directions.add("w");
        directions.add("e");
        directions.add("s");
        directions.add("n");
        directions.add("west");
        directions.add("east");
        directions.add("south");
        directions.add("north");

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


        List<String> movements = new ArrayList<>();
        movements.add("go");
        movements.add("move");

        List<String> objects = new ArrayList<>();
        objects.add("cabinet");
        objects.add("leaflet");
        objects.add("dagger");
        objects.add("leather armor");
        objects.add("inventory");
        objects.add("gold");
        objects.add("silver");
        objects.add("copper");
        objects.add("forest devil");
        objects.add("twig");
        objects.add("snarkflower");
        objects.add("door");
        objects.add("rabbit");
        objects.add("chicken");
        objects.add("squirrel");
        objects.add("goblin");
        objects.add("chest");
        objects.add("ih");
        objects.add("white whispberry");
        objects.add("oliver");
        objects.add("bagger");
        objects.add("ragger");
        objects.add("gate");
        objects.add("trap");
        objects.add("bear claw");
        objects.add("bear amulet");
        objects.add("wolfbane");
        objects.add("raven eye");
        objects.add("blood vial");
        objects.add("cauldron");
        objects.add("firstville guards plate");
        objects.add("firstville guards helm");
        objects.add("firstville guards boots");
        objects.add("firstville guards legs");


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

        List<String> listen = new ArrayList<>();
        listen.add("listen");

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

        List<String> door = new ArrayList<>();
        door.add("door");

        List<String> inventory = new ArrayList<>();//has items

        List<String> inventoryinventory = new ArrayList<>();//calls inventory
        inventoryinventory.add("inventory");

        List<String> help = new ArrayList<>();
        help.add("help");

        List<String> kill = new ArrayList<>();
        kill.add("kill");

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


        // INSERT ALL CODE THAT SETS A VARIABLE HERE!!!!!

        boolean cabinetCaveN = false;
        boolean cabinetDaggerCaveN = true;

        boolean start;

        // Asks if you want to play

        System.out.print("Enter a name: ");
        String namer = scanner.nextLine();
        System.out.println("");

        Player player = new Player(namer, 10, 10, 5, 0, 20);

        XpLv playersStats = new XpLv(1, 0);

        System.out.print("Play (yes or no)? \n-> ");
        String play = scanner.nextLine();

        System.out.println("");

        //Checks if you said yes or not
        if (play.equalsIgnoreCase("yes") || play.equalsIgnoreCase("y") || play.equalsIgnoreCase("admin0")) {

            if (play.equalsIgnoreCase("admin0")) {
                System.out.println("Admin Code received.");

                System.out.print("Enter MAX health \n-> ");
                String setHealthMAX = scanner.nextLine().trim();
                System.out.print("Enter damage \n-> ");
                String setDamage = scanner.nextLine().trim();
                System.out.print("Enter damage resistance\n-> ");
                String setReist = scanner.nextLine().trim();
                try {
                    player.getHealth().setMaxHealth(Integer.parseInt(setHealthMAX));
                    player.setAttackPower(Integer.parseInt(setDamage));
                    player.getHealth().setDamageResistance(Integer.parseInt(setReist));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numbers, using defaults.");
                }

                System.out.println("(12 is next to sHouse) Southern Forest Area #--");
                System.out.println("(50 is close to firstville) Northern Forest Area #--");
                System.out.println("(living room/kitchen etc.) Tom's Dark ---");
                System.out.print("Enter target room\n-> ");
                String gotoRoomBlah = scanner.nextLine();
                if (gotoRoomBlah != null) {
                    inRoom = Hub.get(gotoRoomBlah);
                    System.out.println("Boom. You're There.");
                }

                for (int i = 0; i < 200; i++) {
                    inRoom.getObjects().add("gold");
                }

                System.out.println("$#bonus stats have been successfully distributed");
                scanner.nextLine();
            }

            start = true;

            System.out.println("Okay! Starting... (hint: use help to 'help')!");//

            System.out.println("");

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            player.displayStats(player, playersStats);

            System.out.println("");

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            System.out.println(inRoom.getRoomDescription());

            player.getSkills().add(new Skill.StunSkill("Stun", "Stuns enemy for one turn. Cooldown: 3 turns.", true, 3));

            setupDayNightSchedulers();

            movingTheMobs();

            setupHealthRegen(player);
        } else {
            start = false;
        }


        //While start is true, run the code
        while (start) {

            if (player.getHealth().isDead()) {

                System.out.println("\nThe world becomes dark...");
                break;

            }

            System.out.println("");
            scannerOrNo = false;

            if (timeChange != null && changeTime != null) {

                long secondsAgo = java.time.Duration.between(changeTime, LocalTime.now()).getSeconds();

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
            boolean oneCardinol = oneDirection(action, northways, southways, easyways, westways);

            boolean correctFormat = oneObjectOneVerb(action, verbs, objects);
            if (oneCardinol) {
                System.out.println("You can't input multiple directions!");
            } else if (!correctFormat) {
                System.out.println("Max verbs: 1   ||   Max objects: 1   ||  Try again");
            } else {

                if (action.equalsIgnoreCase("coins")) {
                    System.out.println("COINS: " + player.getCoins());
                }

                if (action.equalsIgnoreCase("quests")) {

                    int totalQuests = Player.QUESTS.size();
                    int completed = 0;

                    for (Quest q : Player.QUESTS.values()) {
                        if (q.done) completed++;
                    }

                    System.out.println("=== YOUR QUESTS (" + totalQuests + "/6 quests acquired) ==="); // RIGHT NOW THERE AREE ONLY 5 POSSIBLE QUESTS

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
                }

                if (stringContainsWordFromList(action.toLowerCase(), verbsOnly.toArray(new String[0]))) {

                    if (action.toLowerCase().equals("n") || action.toLowerCase().equals("north")) {

                        Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment);
                        inRoom = newRoom;
                        Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                        snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                    } else if (action.toLowerCase().equals("s") || action.toLowerCase().equals("south")) {
                        // Call the move method and update to inRoom
                        Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment);
                        inRoom = newRoom;
                        Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                        snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));


                    } else if (action.toLowerCase().equals("w") || action.toLowerCase().equals("west")) {
                        // Call the move method and update to inRoom
                        Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment);
                        inRoom = newRoom;
                        Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                        snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                    } else if (action.toLowerCase().equals("e") || action.toLowerCase().equals("east")) {
                        // Call the move method and update to inRoom
                        Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment);
                        inRoom = newRoom;
                        Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                        snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));


                    } else if (stringContainsWordFromList(action.toLowerCase(), movements.toArray(new String[0]))) {

                        if (stringContainsWordFromList(action.toLowerCase(), directions.toArray(new String[0]))) {

                            if (stringContainsWordFromList(action.toLowerCase(), northways.toArray(new String[0]))) {

                                action = "n";

                                Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment);
                                inRoom = newRoom;
                                Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                                snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                            }

                            if (stringContainsWordFromList(action.toLowerCase(), southways.toArray(new String[0]))) {

                                action = "s";

                                Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment);
                                inRoom = newRoom;
                                Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                                snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                            }

                            if (stringContainsWordFromList(action.toLowerCase(), westways.toArray(new String[0]))) {

                                action = "w";

                                Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment);
                                inRoom = newRoom;
                                Map<String, Quest> snapshot = new HashMap<>(Player.QUESTS);
                                snapshot.forEach((id, q) -> q.check("VISIT_LOCATION", newRoom.getRoomName(), player, playersStats));

                            }

                            if (stringContainsWordFromList(action.toLowerCase(), easyways.toArray(new String[0]))) {

                                action = "e";

                                Hub newRoom = move(action.toLowerCase(), inRoom, player, playersStats, equipment);
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

                        for (FirstShopOwner npc : inRoom.getFirstShopOwners()) {
                            npcNames.add(npc.getName());
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
                    }

                } else if (stringContainsWordFromList(action.toLowerCase(), verbs.toArray(new String[0]))) {

                    if (stringContainsWordFromList(action.toLowerCase(), objects.toArray(new String[0])) || action.toLowerCase().contains("leaflet") || action.toLowerCase().contains("ih")) {

                        if (stringContainsWordFromList(action.toLowerCase(), use.toArray(new String [0]))){

                            if (stringContainsWordFromList(action.toLowerCase(), cauldron.toArray(new String [0]))){

                                cauldron(inventory);

                            }
                        }


                        else if (stringContainsWordFromList(action.toLowerCase(), inspect.toArray(new String[0]))) {

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

                                }
                            }

                            // 2) then try objects in the room
                            if (!found) {
                                for (String obj : inRoom.getObjects()) {
                                    if (obj.equalsIgnoreCase(target)) {
                                        found = true;
                                        describeItem(target);

                                    }
                                }
                            }

                            //3) then try structure in the room
                            if (!found) {
                                if (inRoom.getStructure() != null){
                                    if (inRoom.getStructure().equalsIgnoreCase(target)) {
                                        found = true;
                                        describeItem(target);

                                    }
                                }
                            }

                            if (!found) {
                                System.out.println("You can't find anything like that to inspect.");
                            }

                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), open.toArray(new String[0]))) {

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


                        } else if (stringContainsWordFromList(action.toLowerCase(), take.toArray(new String[0])) || stringContainsWordFromList(action.toLowerCase(), get.toArray(new String[0]))) {
                            boolean validObject = false;

                            for (String obj : objects) {
                                if (action.toLowerCase().contains(obj)) {
                                    take(inRoom, obj, inventory, player, playersStats);
                                    validObject = true;
                                    break;
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


                            for (String obj : objects) {
                                if (action.toLowerCase().contains(obj)) {
                                    drop(inRoom, obj, inventory, player, equipment, existingItems);
                                    validObject = true;

                                    break;
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

                                    combatNpc(player, mob, inRoom, playersStats);

                                    player.setInCombat(false);

                                    mobbo = true;

                                    break;

                                }

                            }

                            for (Guard mob : inRoom.getGuard()) {

                                String mobNameLower = mob.getName().toLowerCase();

                                if (action.toLowerCase().contains(mobNameLower)) {

                                    player.setInCombat(true);

                                    combatGuard(player, mob, inRoom, playersStats, existingItems);

                                    player.setInCombat(false);

                                    mobbo = true;

                                    break;

                                }

                            }


                            for (Mob mob : inRoom.getMOBS()) {

                                String mobNameLower = mob.getName().toLowerCase();

                                if (action.toLowerCase().contains(mobNameLower)) {

                                    player.setInCombat(true);

                                    combat(player, mob, inRoom, playersStats, equipment);

                                    player.setInCombat(false);

                                    mobbo = true;

                                    break;

                                }

                            }

                            for (Boss boss : inRoom.getBoss()) {

                                String mobNameLower = boss.getName().toLowerCase();

                                if (action.toLowerCase().contains(mobNameLower)) {

                                    player.setInCombat(true);

                                    combatBoss(player, boss, inRoom, playersStats, existingItems, equipment.getEquippedItems(), equipment);

                                    player.setInCombat(false);

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
                                if (action.toLowerCase().contains(obj) && inventory.contains(obj)) {

                                    for (Item item : existingItems) {

                                        if (item.getName().equals(obj)) {
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

                            boolean talked = false; // 47 more questions

                            for (Guard npc : inRoom.getGuard()) {

                                String npcNameLower = npc.getName().toLowerCase();

                                if (action.toLowerCase().contains(npcNameLower)) {

                                    talkGuard(inRoom, inventory, playersStats, npc, player);

                                    talked = true;

                                    break;

                                }

                            }

                            if (!talked) {
                                for (Merchant npc : inRoom.getMerchant()) {

                                    String npcNameLower = npc.getName().toLowerCase();

                                    if (action.toLowerCase().contains(npcNameLower)) {

                                        //talkMerchant(inRoom, inventory, playersStats, npc, player);

                                        talked = true;

                                        break;

                                    }

                                }
                            }

                            if (!talked) {
                                for (Npca npc : inRoom.getNpc()) {

                                    String npcNameLower = npc.getName().toLowerCase();

                                    if (action.toLowerCase().contains(npcNameLower)) {

                                        talkNpc(inRoom, inventory, playersStats, npc, player);

                                        talked = true;

                                        break;

                                    }

                                }
                            }

                            if (!talked) {
                                for (FirstShopOwner npc : inRoom.getFirstShopOwners()) {

                                    String npcNameLower = npc.getName().toLowerCase();

                                    if (action.toLowerCase().contains(npcNameLower)) {

                                        talkShopFirst(inRoom, inventory, playersStats, npc, player);

                                        talked = true;

                                        break;

                                    }

                                }
                            }

                            if (talked == false) {
                                System.out.println("[When you want to talk, you must define your target.]");
                            }

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
    public static Hub move(String direction, Hub currentHub, Player player, XpLv playerStats, Equipment equipment) {

        if (direction.equals("north")) {
            direction = "n";
        }

        if (direction.equals("south")) {
            direction = "s";
        }

        if (direction.equals("west")) {
            direction = "w";
        }

        if (direction.equals("east")) {
            direction = "e";
        }


        Door door = currentHub.getDoor(direction);
        if (door != null && !door.isOpen()) {
            System.out.println("There is a closed door in your way.");
            return currentHub;
        }

        LockedDoors lockedDoors = currentHub.getLockedDoor(direction);

        if (lockedDoors != null && !lockedDoors.isOpened()) {
            System.out.println("There is a locked gate in your way.");
            return currentHub;
        }

        Hub newRoom = currentHub.getExit(direction);

        // If there is a new area where user tries to go, update it
        if (newRoom != null) {

            System.out.println(newRoom.getRoomName());
            System.out.println(newRoom.getRoomDescription());

            if (!newRoom.getObjects().isEmpty()) {
                itemsIfAny(newRoom.getObjects(), "Items in room: ");
            }

            Mobs(newRoom);


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

                    combat(player, mob, newRoom, playerStats, equipment);
                    if (newRoom.getMOBS().isEmpty()) {

                        break; // If I change stuff then before the for I should see what the newRoom.getMOBS's length is, and then do a "for (int i = (the length of the list)"
                    }

                }

            }

            List<String> mobNames = new ArrayList<>();


            for (Mob mob : newRoom.getMOBS()) {

                mobNames.add(mob.getName());

            }

            if (!mobNames.isEmpty()) {
                mobsIfAny(mobNames, "Mobs in room: ");
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

            if (!npcNames.isEmpty()) {
                npcIfAny(npcNames, "NPC's in room: ");
            }

            return newRoom; // Return the new Hub object (room)

        }

        // If there is no new area where the user tries to go, stop them
        else {
            System.out.println("You are trying to go to an impossible location.");
            return currentHub; // Return the current Hub object
        }
    }

    public static boolean stringContainsWordFromList(String inputStr, String[] items) {
        for (String item : items) {
            if (inputStr.matches(".*\\b" + Pattern.quote(item) + "\\b.*")) {
                return true;
            }
        }
        return false;
    }

    public static boolean oneDirection(String action, List<String> northways, List<String> southways, List<String> eastways, List<String> westways) {
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

    public static void take(Hub inRoom, String item, List<String> inventory, Player player, XpLv playerStats) {

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

        if (item.equalsIgnoreCase("ih")) {

            List<String> tokenIds = new ArrayList<>();
            for (String obj : inRoom.getObjects()) {
                Token a = Game.TOKENS.get(obj);
                if (a != null && a.getName().equalsIgnoreCase("ih")) {
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
                    System.out.println("You decide not to take any treasure token [IH] 's.");
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
            System.out.println("There is no " + item + " (single-form) to take here");

        } else {
            chosenAmount = 1;
        }

        if (count > 1) {
            System.out.println("There are " + count + " " + item + "(s) here. How many do you want to take?");
            System.out.print("-> ");

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

        if (item.equalsIgnoreCase("ih")) {

            List<String> tokenId = new ArrayList<>();
            for (String obj : inventory) {
                Token a = Game.TOKENS.get(obj);
                if (a != null && a.getName().equalsIgnoreCase("ih")) {
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
                    System.out.println("You decide not to drop any treasure token [IH] 's.");
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
            System.out.println("There is no " + item + " (single-form) in you inventory");

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
                System.out.println("You have dropped " + chosenAmount + " token [IH]" + (chosenAmount > 1 ? "s." : "."));
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

            for (String leafId : inventory) {
                Leaflet owned = LEAFLETS.get(leafId);
                if (owned != null && owned.getName().equalsIgnoreCase("leaflet")) {
                    aqquiredLeaflets.add(owned);
                }
            }

            if (aqquiredLeaflets.isEmpty()) {
                System.out.println("You do not have any leaflets to read.");
                return;
            }

            if (aqquiredLeaflets.size() == 1) {
                Leaflet owned = aqquiredLeaflets.get(0);
                printLeaflet(owned);
                if (owned.getId().equalsIgnoreCase("leaflet003")){
                    if (player.QUESTS.get("MQ3") == null){

                        Player.QUESTS.put("MQ3", new Quest("MQ3", "Slay Forest Mama Bear", 1, "mama bear", 1, 200, 30));

                    }
                }
                return;
            }

            System.out.println("You have several leaflets:");
            for (int i = 0; i < aqquiredLeaflets.size(); i++) {
                Leaflet owned = aqquiredLeaflets.get(i);
                System.out.println((i + 1) + ") [" + owned.getId() + "] " + owned.getTitle());
            }
            System.out.print("Which leaflet do you want to read (number or id)? - ");

            String answer = scanner.nextLine().trim().toLowerCase();

            Leaflet chosen = null;

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

            if (chosen != null) {
                printLeaflet(chosen);
                if (chosen.getId().equalsIgnoreCase("leaflet003")){
                    if (player.QUESTS.get("MQ3") == null){

                        Player.QUESTS.put("MQ3", new Quest("MQ3", "Slay Forest Mama Bear", 1, "mama bear", 1, 200, 30));

                    }
                }
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
                if (ha.getId().equalsIgnoreCase("leaflet003")){
                    if (player.QUESTS.get("MQ3") == null){

                        Player.QUESTS.put("MQ3", new Quest("MQ3", "Slay Forest Mama Bear", 1, "mama bear", 1, 200, 30));

                    }
                }
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

        System.out.println("In order to call a command you need to enter in the FOLLOWING FORMAT: \n\n[VERB] + [OBJECT] = action\n\nSome verbs will not need an object to be used (e.g., 'look')\nHere are a list of possible commands:\n    -> move, go || open, close || look, listen, wait, read || get, drop, take, remove \n       kill, attack || stats, inventory, coins, quests || help || ...");

    }

    public static void Mobs(Hub inRoom) {
        int chancer = 0;
        int number = 0;

        // Only run this code if player is in a Southern Forest room
        if (inRoom.getRoomName().contains("Southern Forest")) {

            chancer = (int) (Math.random() * 101);

            if (chancer < 20) {

                List<String> stuff = new ArrayList<>();

                number = (int) (Math.random() * 15) + 1; //1-15

                if (number > 0 && number > 4) {

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

                } else if (number > 6 && number < 12){

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

                } else if (number > 11 && number < 14){

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

                }


            }
        }
    }

    private static Mob createRabbitWithRandomStats(List<String> drop) {
        int maxHealth = (int) (Math.random() * 2 + 6); //6-7
        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health rabbitHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int) (Math.random() * 4 + 1);
        boolean isAggro = false;

        if (drop.isEmpty()){
            if ((int)(Math.random() * 2 + 1) == 1){
                drop.add("rabbit hide");
            }
        }

        return new Mob("Rabbit", rabbitHealth, attackPower, isAggro, drop);
    }

    private static Mob createBearWithRandomStats(List<String> drop, int maxHealth, String name) {

        if (name == null){
            name = "Bear";
        }

        if (maxHealth == 0){
            maxHealth = (int) (Math.random() * 20 + 30); //6-7
        }

        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health bearHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int) (Math.random() * 13 + 10);
        boolean isAggro = true;

        if (drop.isEmpty()){
            drop.add("bear hide");
        }

        return new Mob(name, bearHealth, attackPower, isAggro, drop);
    }

    private static Mob createSquirrelWithRandomStats(List<String> drop) {
        int maxHealth = (int) (Math.random() * 4 + 5); //5-8
        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health squirrelHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int) (Math.random() * 7 + 3); //3-9
        boolean isAggro = true;

        if (drop.isEmpty()){
            if ((int)(Math.random() * 2 + 1) == 1){
                drop.add("squirrel hide");
            }
        }

        return new Mob("Squirrel", squirrelHealth, attackPower, isAggro, drop);
    }

    private static Mob createChickenWithRandomStats(List<String> drop) {
        int maxHealth = (int) (Math.random() * 4 + 9); //9-11
        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health rabbitHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int) (Math.random() * 3 + 1);
        boolean isAggro = false;
        boolean onFire = false;

        if (drop.isEmpty()){
            if ((int)(Math.random() * 2 + 1) == 1){
                drop.add("chicken feather");
            }
        }

        return new Mob("Chicken", rabbitHealth, attackPower, isAggro, drop);
    }

    private static Mob createGoblinWithRandomStats(List<String> drop) {
        int maxHealth = (int) (Math.random() * 6 + 12); //12-17
        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health goblinHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int) (Math.random() * 10 + 2);
        boolean isAggro = true;

        if (drop.isEmpty()){
            if ((int)(Math.random() * 2 + 1) == 1){
                drop.add("goblin tooth");
            }
        }

        return new Mob("Goblin", goblinHealth, attackPower, isAggro, drop);
    }

    private static Mob createMuteBanditWithRandomStats(List<String> drop) {
        int maxHealth = (int) (Math.random() * 6 + 12); //12-17
        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health muteHeath = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int) (Math.random() * 10 + 8);
        boolean isAggro = false;

        if (drop.isEmpty()){
            if ((int)(Math.random() * 2 + 1) == 1){
                drop.add("cloth");
            }
        }

        return new Mob("MuteBandit", muteHeath, attackPower, isAggro, drop);
    }


    public static void combat(Player player, Mob mob, Hub inRoom, XpLv playerStats, Equipment equipment) {
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

        if (!inRoom.containsMob(mob)) {
            System.out.println("The " + mob.getName() + " has fled to another room.");
            return;
        }

        if (mob.getName().toLowerCase().contains("bear")){
            if(equipment.getItemBasedOnSlot("melee", equipment.getEquippedItems()).getName().equalsIgnoreCase("bear claw")){
                mob.setHealth();
                mob.setAttackPower();
                System.out.println("You are fighting a bear with a bear claw! The bear is shocked and has been drastically immobilized.");
            }
        }

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

            player.attack(mob);


            System.out.println("");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (mob.getHealth().isDead()) {
                System.out.println("The " + mob.getName() + " has died at your hands. \n");

                playerStats.addXp(playerStats.calculateXp(mob.getName()));

                playerStats.calculateLv(playerStats.getXp(), playerStats.getLevel(), player);

                player.displayStats(player, playerStats);

                System.out.println("New max health -> " + player.getHealth().getMaxHealth());

                System.out.println("New attack damage -> " + player.getAttackPower());

                inRoom.getMOBS().remove(mob); //remove an object that has the name Rabbit

                String killedMob = mob.getName();

                Player.QUESTS.forEach((id, q) -> q.check("KILL_MOB", killedMob, player, playerStats));

                if (mob.getDrops() != null){
                    for (String i : mob.getDrops()){
                        inRoom.addObject(i);
                    }
                }
                break;
            } else {

                mob.attack(player);
                System.out.println("");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (player.getHealth().isDead()) {
                    System.out.println("\nYou have been murdered by a " + mob.getName() + " (and a fork... and Tom. And his spoon.)\n\n");

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

                playerStats.addXp(playerStats.calculateXp(npc.getName()));

                playerStats.calculateLv(playerStats.getXp(), playerStats.getLevel(), player);

                player.displayStats(player, playerStats);

                inRoom.getNpc().remove(npc); //remove an object that has the name Rabbit

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

    public static void combatGuard(Player player, Guard npc, Hub inRoom, XpLv playerStats, List<Item> existingItems) {

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

        System.out.println("THE GREAT " + boss.getName() + " RECOGNIZES YOU AS IT'S PREY.");
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

            player.attackBoss(boss);

            System.out.println("");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (boss.getHealth().isDead()) {

                System.out.println(boss.getName() + " has died at your hands. \n");

                playerStats.addXp(playerStats.calculateXp(boss.getName()));

                playerStats.calculateLv(playerStats.getXp(), playerStats.getLevel(), player);

                player.displayStats(player, playerStats);

                inRoom.getBoss().remove(boss); //remove an object that has the name Rabbit

                break;

            } else {

                boss.attack(player, eq, eqI);
                System.out.println("");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (player.getHealth().isDead()) {
                    System.out.println("\nYou have been murdered by " + boss.getName() + " (and a fork... and Tom. And his spoon.)\n\n");

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
                oliverQuest = new Quest("MQ4", "Collect 5 SnarkFlowers for Oliver", 2, "snarkflower", 5, 20, 2); // main quest - get flowers
                Player.QUESTS.put("MQ4", oliverQuest);
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
                oliverQuest = new Quest("MQ4", "Collect 5 SnarkFlowers for Oliver", 2, "snarkflower", 5, 20, 2); // main quest - get flowers
                Player.QUESTS.put("MQ4", oliverQuest);
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

    public static void talkNpc(Hub inRoom, List<String> inventory, XpLv playerStats, Npca npc, Player player) {
        npc.sayLine(0);
        scanner.nextLine();

        npc.sayLine(1);
        scanner.nextLine();

        npc.sayLine(2);
    }

    public static List<String> talkShopFirst(Hub inRoom, List<String> inventory, XpLv playerStats, FirstShopOwner npc, Player player) {

        System.out.println(npc.getName() + " (Shop Keeper): \"Welcome to my shop!\"");
        scanner.nextLine();

        System.out.println("[1] Buy  [2] Sell  [3] Leave");

        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            npc.buyItem(player, inventory);
            return inventory;

        } else if (choice.equals("2")) {
            System.out.println("Selling not ready yet. Come back later!");
            return inventory;

        } else {
            System.out.println("Thanks for visiting " + player.getName() + "! *you are shoo-ed away...");
            return inventory;
        }

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
        String lowerName = itemName.toLowerCase();
        if (INSPECT_DESCRIPTIONS.containsKey(lowerName)) {
            System.out.println(INSPECT_DESCRIPTIONS.get(lowerName));
        }
        else {
            System.out.println("You look closer at the " + itemName + ", but nothing special stands out.");
        }
    }

    public static List<String> cauldron(List<String> invin){

        List<String> craftable = new ArrayList<>();
        craftable.add("wolfs bane potion");

        System.out.println("What would you like to craft?");
        for (String a : craftable){
            System.out.println(a);
        }

        System.out.print("-> ");
        String craft = scanner.nextLine();

        int doable = 0;

        for (String i : craftable){
            if (i == craft){
                doable++;
                break;
            }
        }

        if (doable !=0){

            if (craft == "wolfs bane potion"){
                int wolfbane = 0;
                int ravenEye = 0;
                int bloody = 0;


                for (String w : invin){

                    if (w == "wolfbane"){
                        wolfbane++;
                    }
                    if (w == "raven eye"){
                        ravenEye++;
                    }
                    if (w == "blood vial"){
                        bloody++;
                    }
                }

                if (wolfbane >= 3 && ravenEye >=1 && bloody >=1){
                    System.out.println("Success! You've made a Wolfs Bane Potion!");
                    invin.add("wolfs bane potion");
                    for (int i = 0; i<3; i++){
                        invin.remove("wolfbane");
                    }
                    invin.remove("raven eye");
                    invin.remove("blood vial");
                    return invin;

                }

                else {
                    System.out.println("You are lacking the materials to craft this: Wolfbane x3, raven eye, blood vial");
                    return invin;

                }
            }
        }

        else {
            System.out.println("Yeah, you are gonna have to go somewhere else to craft that...");
            return invin;
        }

        return invin;


    }


}