import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;import java.util.Timer;
import java.util.TimerTask;


//                    NOTES FOR IMPROVEMENTS:

// Make rouge bandits

//  ADD THINGS TO CORRESPONDING PLACES (FOREST & S HOUSE)

//  CREATE SKILLS FOR M FOREST BOSS, CREATE BOSS

//  CREATE FIRST SMALL TOWN

//  CREATE FIRST SMALL TOWN BOSS

//  CREATE SECOND TOWN. CREATE GUILD WITH QUESTS AND A CARD AND EVERYTHING

public class Game {

    public static volatile boolean timeOfDay = true; // true = day, false = night

    public static volatile boolean scannerOrNo = false; // true = scanning, false = no scanning

    public static void setupDayNightSchedulers() {
        long threeMinutes = 1000;  // 5 minutes in ms

        Timer dayTimer = new Timer();
        dayTimer.scheduleAtFixedRate(new TimeDayNotifier(), threeMinutes, threeMinutes);

    }

    static class TimeDayNotifier extends TimerTask {
        @Override
        public void run() {


            System.out.println("HYEU");
            if (scannerOrNo) { // prints if not scanning h
                if (!timeOfDay) {
                    System.out.println("(It is turning day...) xxx ");
                    System.out.print("-> ");
                    timeOfDay = true;
                }

                else  {
                    System.out.println("(It is turning night...) xxx ");
                    System.out.print("-> ");
                    timeOfDay = false;
                }
            }

            // NEED TO BE SO THAT IF IT PRINTS FROM BELOW CODE, IT JUST PRINTS MOST RECENT TIME CAHNGEW AND SAY HOW LONG AGO IT TURNED THAT TIME.

            else {

                if (!timeOfDay) {
                    LocalTime waiter = LocalTime.now(); // Get the current time
                    while(!scannerOrNo){

                    }
                    System.out.println("(It is turning day...) xxx " + waiter.getSecond());
                    System.out.print("-> ");
                    timeOfDay = true;
                }

                else  {
                    LocalTime waiter = LocalTime.now(); // Get the current time
                    while(!scannerOrNo){

                    }
                    System.out.println("(It is turning night...) xxx " + waiter.getSecond());
                    System.out.print("-> ");
                    timeOfDay = false;
                }
            }

        }
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // TOMS CAVE
        Hub cave = new Hub("Tom's Dark Cave", "The back of the ancient cave where Tom the hermit lived for many years. \nEXITS: (N) ");

        Hub caveN = new Hub("Tom's Dark Kitchen", "Still in Tom's cave, but now you have moved into his kitchen which consists of a cabinet and a counter. \nEXITS: (N) (S)");

        Hub caveNN = new Hub("Tom's Treasure Room", "Still in Tom's cave, but now you have moved into his treasure room which has no furniture. \nEXITS: (W) (E) (S)" );

        Hub caveNE = new Hub("Tom's Living Room", "Still in Tom's cave, but now you have moved into his living room which. \nEXITS: (W)" );

        Hub caveNW = new Hub("Tom's Dark Entrance", "Still in Tom's cave, but now you have moved to the cave's entrance, where a door stands. \nEXITS: (N) (E)" );

        // S FOREST

        Hub forest1 = new Hub("Southern Forest Area #1", "Just outside Tom's cave and just inside The Southern Area of the Great Makiss Forest. but now you have moved to the cave's entrance, where a door stands tall. \nEXITS: (S) (N) " );

        Hub forest2 = new Hub("Southern Forest Area #2" , "The Southern Area of the Great Makiss Forest. \nOnly minor prey lay in wait in this forest. \nEXITS: (S) (W) " );

        Hub forest4 = new Hub("Southern Forest Area #4", "The Southern Area of the Great Makiss Forest. \nThe air feels heavy here, and the forest is unusually silent. The ground is littered with broken twigs, as though something recently passed through... \nEXITS: (E) (W)" );

        Hub forest3 = new Hub("Southern Forest Area #3", "The Southern Area of the Great Makiss Forest. \nThorny bushes block much of the way south, their barbs glinting sharply. However, the path seems clearer to the north, where faint sunlight filters through. \nEXITS: (E) (W)" );

        Hub forest5 = new Hub("Southern Forest Area #5", "The Southern Area of the Great Makiss Forest. \nReminiscence of something eating a chicken is visible under scattered leaves...\nEXITS: (E) (N)" );

        Hub forest6 = new Hub("Southern Forest Area #6", "The Southern Area of the Great Makiss Forest. \nThe trees grow apart here, their canopies still blotting out much of the sunlight. The air feels damp, and the dull chest#001 sits quietly in the middle of the area... \nEXITS: (S) (N) (W)");

        Hub forest7 = new Hub("Southern Forest Area #7", "The Southern Area of the Great Makiss Forest. \nThick ferns and wildflowers cover the forest floor. A small trail, barely visible, weaves between the towering oaks and leads South. \nEXITS: (S) (W)");

        Hub forest8 = new Hub("Southern Forest Area #8", "The Southern Area of the Great Makiss Forest. \nThe chirping of birds fills the air as soft beams of light break through the leaves. The ground here is softer, marked by faint paw prints. \nEXITS: (E) (S)");

        Hub forest9 = new Hub("Southern Forest Area #9", "The Southern Area of the Great Makiss Forest. \nA clearing opens up, surrounded by mossy boulders. A faint breeze carries the scent of pine and damp earth. Something moves just beyond sight... \nEXITS: (N) (E) (W) ");

        Hub forest10 = new Hub("Southern Forest Area #10", "The Southern Area of the Great Makiss Forest. \nA wooden goblin watchtower overlooks the dense woods, faint sounds echo from its guarded platform. \nEXITS: (E) (W)");

        Hub forest11 = new Hub("Southern Forest Area #11", "The Southern Area of the Great Makiss Forest. \nA goblin camp with crudely made tents surrounds a roaring fire, shadows flickering with restless movement. \nEXITS: (E) (N)");

        Hub forest12 = new Hub("Southern Forest Area #12", "The Southern Area of the Great Makiss Forest. \nAn open clearing dotted with wildflowers and a calm, shallow pond reflecting the sky above. \nEXITS: (S) (N)");

        Hub forest13 = new Hub("Southern Forest Area #13", "The Southern Area of the Great Makiss Forest. \nAn abandoned house overrun with vines and moss, the air heavy with age and forgotten stories. The house's door is made of heavy steel\nEXITS: (S) (E)");


        //S HOUSE ROOMS
        Hub sHouseEntrance = new Hub("Abandon House's Entrance", "Inside an abandon house located in the Southern part of the forest. \nThe scent of damp wood and dust fills the air, and every creak of the old floorboards echoes through the empty house.\nEXITS: (E) (N) (S)");

        Hub sHouseEntranceLeft = new Hub("Abandon House's Southern Room", "Inside an abandon house located in the Southern part of the forest. \nTorn wallpaper hangs loosely, and a broken chair rests in the corner. The faint sound of wind howling through unseen cracks adds an eerie rhythm.\nEXITS: (N)");

        Hub sHouseEntranceRight = new Hub("Abandon House's Northern Room\"", "Inside an abandon house located in the Southern part of the forest. \nEXITS: The northern room is slightly larger, packed with dust-covered furniture and an old fireplace filled with cold ashes.(S) (W)");

        Hub sHouseHallway = new Hub("Abandon House's Hallway\"", "Inside an abandon house located in the Southern part of the forest. \nA faint draft snakes through cracks in the walls. A closed wooden door separates the hallway from a nearby room to the West.\nEXITS: (E) (S)");

        Hub sHouseRoom = new Hub("Abandon House's Suite Room\"", "Inside an abandon house located in the Southern part of the forest. \nThis was once a grand suite, now fallen to decay.\nEXITS: (N) (S)");

        Hub sHouseStairs = new Hub("Abandon House's Staircase\"", "Inside an abandon house located in the Southern part of the forest. \nThe narrow staircase ascends with a groan at every step. Dust dances in the beams of pale light filtering from above.\nEXITS: (N) (S [upwards])");

        Hub sHouseUpper = new Hub("Abandon House's Upper Room\"", "Inside an abandon house located in the Southern part of the forest. \nThe upper room smells of mildew and forgotten time. A rusty chest sits in the corner...\nEXITS: (N [downwards)");


        Hub forest14 = new Hub("Southern Forest Area #14", "The Southern Area of the Great Makiss Forest. \nDense thickets and thorn bushes threaten to slow travelers, whispering secrets among the branches. \nEXITS: (W) (N)");

        Hub forest15 = new Hub("Southern Forest Area #15", "The Southern Area of the Great Makiss Forest. \nA hidden hideout for  rouge bandits with makeshift barricades and loot scattered around. \nEXITS: (S) (E)");

        Hub forest16 = new Hub("Southern Forest Area #16", "The Southern Area of the Great Makiss Forest. \nA ruined stone shrine, partially crumbled, radiates a faint mystical glow. \nEXITS: (W) (E) (N)");

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

        Hub forest29 = new Hub("Northern Forest Area #29", "The Northern Area of the Great Makiss Forest. \nA hidden glade glowing faintly with ethereal blue light and glowing plants. \nEXITS: (S) (E)");

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

        Hub forest49 = new Hub("Northern Forest Area #49", "The Northern Area of the Great Makiss Forest. \nA clearing just before the forest gate, quiet but tense as danger nears. \nEXITS: (W) (N)");

        Hub forest50 = new Hub("Northern Forest Area #50", "The Northern Area of the Great Makiss Forest. \nThe forest gate stands here, guarded by a fierce mini-boss—beyond lies the bridge to new adventures. \nEXITS: ");


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



        //ADD ITEMS IN TO ROOM
        caveN.addObject("newspaper1");

        for (int i = 0; i < 2; i ++) {
            caveNN.addObject("copper");
        }

        caveNN.addObject("silver");
        caveNE.addObject("newspaper2");
        caveNE.addObject("leather armor");

        for (int ii = 0; ii < 3; ii ++) {
            forest1.addObject("twig");
        }

        for (int i = 0; i < 2; i++) {
            forest12.addObject("white whispberries");
        }

        // MAKE CHEST STUFF HAPPEN HERE

        List<String> chestContents = Arrays.asList("copper");

        Chest firstChest = new Chest(true, "treasure-token#001", chestContents, "C");
        forest6.addChest("chest#001", firstChest);

        forest6.addObject("treasure-token#001");

        chestContents = Arrays.asList("silver", "silver");

        Chest secondChest = new Chest(true, "treasure-token#002", chestContents, "C");
        sHouseUpper.addChest("chest#002", secondChest);

        forest26.addObject("treasure-token#002");

        // ADD MOBS

        Mob goblin = createGoblinWithRandomStats();
        forest10.getMOBS().add(goblin);

        for (int o = 0; o < 3; o++){

            goblin = createGoblinWithRandomStats();

            forest11.getMOBS().add(goblin);
        }



        Story(cave);

    }

    public static void Story(Hub cave) {

        // Makes the room you are in "cave"
        Hub inRoom = cave;

        //MAKE ITEMS AND "EQUIPMENT"

        Equipment equipment = new Equipment();

        Item dagger = new Item("dagger", "melee", true);
        Item leatherArmor = new Item("leather armor", "body", false);


        //MAKE ITEMS EXIST IN ITEMS
        List<Item> existingItems = new ArrayList<>();
        existingItems.add(dagger);
        existingItems.add(leatherArmor);

        //List of verbs
        List<String> verbs = new ArrayList<>();
        verbs.add("move");
        verbs.add("go");
        verbs.add("look");
        verbs.add("open");
        verbs.add("drop");
        verbs.add("remove");
        verbs.add("take");
        verbs.add("get");
        verbs.add("read");
        verbs.add("wait");
        verbs.add("listen");
        verbs.add("lock");
        verbs.add("close");
        verbs.add("help");
        verbs.add("n");
        verbs.add("s");
        verbs.add("w");
        verbs.add("e");
        verbs.add("north");
        verbs.add("south");
        verbs.add("west");
        verbs.add("east");
        verbs.add("l");
        verbs.add("inventory");
        verbs.add("kill");
        verbs.add("attack");
        verbs.add("stats");
        verbs.add("heal");
        verbs.add("equip");
        verbs.add("unequip");


        List<String> verbsOnly = new ArrayList<>();
        verbsOnly.add("n");
        verbsOnly.add("s");
        verbsOnly.add("w");
        verbsOnly.add("e");
        verbsOnly.add("north");
        verbsOnly.add("south");
        verbsOnly.add("west");
        verbsOnly.add("east");
        verbsOnly.add("look");
        verbsOnly.add("l");
        verbsOnly.add("wait");
        verbsOnly.add("listen");
        verbsOnly.add("help");
        verbsOnly.add("stats");
        verbsOnly.add("heal");



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
        objects.add("newspaper1");
        objects.add("dagger");
        objects.add("leather armor");
        objects.add("inventory");
        objects.add("gold");
        objects.add("silver");
        objects.add("copper");
        objects.add("twig");
        objects.add("door");
        objects.add("rabbit");
        objects.add("chicken");
        objects.add("squirrel");
        objects.add("chest");
        objects.add("treasure-token#001");
        objects.add("treasure-token#002");
        objects.add("treasure-token#003");
        objects.add("treasure-token#004");
        objects.add("treasure-token#005");
        objects.add("white whispberries");



        List<String> look = new ArrayList<>();
        look.add("l");
        look.add("look");

        List<String> wait = new ArrayList<>();
        wait.add("wait");

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

        List<String> get = new ArrayList<>();
        get.add("get");

        List<String> read = new ArrayList<>();
        read.add("read");

        List<String> drop = new ArrayList<>();
        drop.add("drop");

        List<String> remove = new ArrayList<>();
        remove.add("remove");

        List<String> cabinet = new ArrayList<>();
        cabinet.add("cabinet");

        List<String> door = new ArrayList<>();
        door.add("door");

        List<String> inventory = new ArrayList<>();

        List<String> inventoryinventory = new ArrayList<>();
        inventoryinventory.add("inventory");

        List<String> help = new ArrayList<>();
        help.add("help");

        List<String> kill = new ArrayList<>();
        kill.add("kill");

        List<String> attack = new ArrayList<>();
        attack.add("attack");

        List<String> heal = new ArrayList<>();
        heal.add("heal");

        List<String> chests = new ArrayList<>();
        chests.add("chest");

        List<String> equip = new ArrayList<>();
        equip.add("equip");

        List<String> unequip = new ArrayList<>();
        unequip.add("unequip");




        // INSERT ALL CODE THAT SETS A VARIABLE HERE!!!!!

        boolean cabinetCaveN = false;
        boolean cabinetDaggerCaveN = true;

        boolean start;

        // Asks if you want to play

        System.out.print("Enter a name: ");
        String namer = scanner.nextLine();
        System.out.println("");

        Player player =  new Player(namer, 10,10,3, 0);

        XpLv playersStats = new XpLv(1, 0);

        System.out.print("Play (yes or no)? \n-> ");
        String play = scanner.nextLine();

        System.out.println("");

        //Checks if you said yes or not
        if (play.equalsIgnoreCase("yes") || play.equalsIgnoreCase("y")) {

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


            System.out.println(cave.getRoomDescription());

            player.getSkills().add(new Skill.StunSkill("Stun", "Stuns enemy for one turn. Cooldown: 3 turns.", true, 3));

            setupDayNightSchedulers();
        }

        else {
            start = false;
        }



        //While start is true, run the code
        while (start) {

            if (player.getHealth().isDead()){

                System.out.println("\nThe world becomes dark...");
                break;

            }

            System.out.println("");
            scannerOrNo = false;

            System.out.print("-> ");
            String action = scanner.nextLine();
            scannerOrNo = false;

            System.out.println("");
            scannerOrNo = true;
            boolean oneCardinol = oneDirection(action, northways, southways, easyways, westways);

            boolean correctFormat = oneObjectOneVerb(action, verbs, objects);
            if (oneCardinol){
                System.out.println("You can't input multiple directions!");
            }

            else if (!correctFormat) {
                System.out.println("Max verbs: 1   ||   Max objects: 1   ||  Try again");
            }

            else {

                if (stringContainsWordFromList(action.toLowerCase(), verbs.toArray(new String[0]))) {

                    if (stringContainsWordFromList(action.toLowerCase(), verbsOnly.toArray(new String[0]))) {

                        if (action.toLowerCase().equals("n") || action.toLowerCase().equals("north")) {

                            inRoom = move(action.toLowerCase(), inRoom, player, playersStats);



                        }
                        else if (action.toLowerCase().equals("s") || action.toLowerCase().equals("south")) {
                            // Call the move method and update to inRoom
                            inRoom = move(action.toLowerCase(), inRoom, player, playersStats);



                        }
                        else if (action.toLowerCase().equals("w") || action.toLowerCase().equals("west")) {
                            // Call the move method and update to inRoom
                            inRoom = move(action.toLowerCase(), inRoom, player, playersStats);


                        }
                        else if (action.toLowerCase().equals("e") || action.toLowerCase().equals("east")) {
                            // Call the move method and update to inRoom
                            inRoom = move(action.toLowerCase(), inRoom, player, playersStats);



                        }
                        else if (stringContainsWordFromList(action.toLowerCase(), movements.toArray(new String[0]))) {

                            if (stringContainsWordFromList(action.toLowerCase(), directions.toArray(new String[0]))) {

                                if (stringContainsWordFromList(action.toLowerCase(), northways.toArray(new String[0]))) {

                                    action = "n";

                                    inRoom = move(action.toLowerCase(), inRoom, player, playersStats);


                                }

                                if (stringContainsWordFromList(action.toLowerCase(), southways.toArray(new String[0]))) {

                                    action = "s";

                                    inRoom = move(action.toLowerCase(), inRoom, player, playersStats);


                                }

                                if (stringContainsWordFromList(action.toLowerCase(), westways.toArray(new String[0]))) {

                                    action = "w";

                                    inRoom = move(action.toLowerCase(), inRoom,player, playersStats);


                                }

                                if (stringContainsWordFromList(action.toLowerCase(), easyways.toArray(new String[0]))) {

                                    action = "e";

                                    inRoom = move(action.toLowerCase(), inRoom, player, playersStats);


                                }

                            }

                            else {
                                System.out.println("You can't just '" + action + "'");
                            }

                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), look.toArray(new String[0]))) {
                            System.out.println(inRoom.getRoomDescription());
                            System.out.println(inRoom.getRoomName());
                            System.out.println("");

                            itemsIfAny(inRoom.getObjects(), "Items in room: ");
                            System.out.println("");

                            List<String> mobNames = new ArrayList<>();

                            for (Mob mob : inRoom.getMOBS()){

                                mobNames.add(mob.getName());

                            }

                            mobsIfAny(mobNames, "Mobs in room: ");

                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), wait.toArray(new String[0]))) {
                            System.out.println("You are waiting (hint: nothing happens by waiting)...");
                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), listen.toArray(new String[0]))) {
                            listening(inRoom);
                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), help.toArray(new String[0]))) {
                            help(verbs);
                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), stats.toArray(new String[0]))) {
                            player.displayStats(player, playersStats);
                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), heal.toArray(new String[0]))) {
                            heal(player);
                        }

                    }

                    else if (stringContainsWordFromList(action.toLowerCase(), objects.toArray(new String[0]))) {

                        if (stringContainsWordFromList(action.toLowerCase(), open.toArray(new String[0]))) {

                            if (stringContainsWordFromList(action.toLowerCase(), inventory.toArray(new String[0]))){
                                itemsIfAny(inventory, "Items in inventory: ");
                            }

                            else if (inRoom.getRoomName().equals("Tom's Dark Kitchen") && stringContainsWordFromList(action.toLowerCase(), cabinet.toArray(new String[0]))) {

                                if (!cabinetCaveN){
                                    System.out.println("You open the cabinet.");
                                    cabinetCaveN = true;

                                    if (cabinetDaggerCaveN){
                                        System.out.println("A dagger falls out and clatters to the floor...");
                                        inRoom.addObject("dagger");
                                        cabinetDaggerCaveN = false;
                                    }

                                }
                                else {
                                    System.out.println("The cabinet is already open.");
                                }
                                //If dagger is not in room and not in inventory, it falls out and is added to room (SO MAKE SURE TO REMOVE It FROM THE ROOM AT THE START!!!!!
                            }

                            else if (stringContainsWordFromList(action.toLowerCase(), door.toArray(new String[0]))) {

                                Door isDoor = null;
                                String doorDir = null;

                                for (String dir : Arrays.asList("n", "s", "e", "w")){

                                    if (inRoom.getDoor(dir) != null){

                                        isDoor = inRoom.getDoor(dir);

                                        doorDir = dir;

                                        break;

                                    }
                                }

                                if (isDoor != null){

                                    if (!isDoor.isOpen()) {
                                        if (inRoom.getRoomName().equals("forest12")){
                                            if (playersStats.getLevel() > 3){
                                                System.out.println("You open the door to the " + doorDir.toUpperCase() + ".");
                                                isDoor.setOpen(true);
                                            }
                                            else {
                                                System.out.println("You are not skilled or strong enough to open this door. Come back with a higher level.");
                                            }
                                        }

                                    }

                                    else{
                                        System.out.println("The door is already open.");
                                    }
                                }

                                else {
                                    System.out.println("There is no door here.");
                                }
                            }

                            else if (stringContainsWordFromList(action.toLowerCase(), chests.toArray(new String[0]))) {

                                String theChest = "";
                                List<String> allChests = new ArrayList<>();

                                for (int i = 1; i <= 100; i++) {
                                    String chestCode = String.format("chest#%03d", i); //% = formatting to fill in any extra space left empty while width is not full|| 0 Says to fill in with 0, 3 sayd width is 3, d = is integer and should be formatted as continues
                                    allChests.add(chestCode);
                                }

                                for (String haga : allChests) {
                                    if (action.toLowerCase().contains(haga.toLowerCase())) {
                                        theChest = haga;
                                        break;
                                    }
                                }

                                if (inRoom.getChests() != null && !inRoom.getChests().isEmpty()) {
                                    for (String chestName : inRoom.getChests().keySet()) {
                                        if (chestName.equals(theChest)) {
                                            Chest chest = inRoom.getChests().get(chestName);

                                            if (chest.isOpened()) {
                                                System.out.println("Nothing happens. The chest's magical lid has already vanished.");
                                            } else {
                                                if (chest.requiresKey() && !inventory.contains(chest.getRequiredKeyName())) {
                                                    System.out.println("You need " + chest.getRequiredKeyName() + " to open this chest.");
                                                } else {
                                                    System.out.println("Magic swirls around " + chestName + " and its lid disappears! \n**POOF!!**");
                                                    System.out.print("->> ");
                                                    for (String item : chest.getContents()) {
                                                        inRoom.addObject(item);
                                                        System.out.print("[" + item + "] ");

                                                    }
                                                    System.out.println("");
                                                    chest.open();
                                                }
                                            }
                                        }
                                        else {
                                            System.out.println("There is no corresponding chest here to open.");
                                        }

                                        break;

                                    }
                                }

                            }

                        }



                        else if (stringContainsWordFromList(action.toLowerCase(), close.toArray(new String[0]))) {
                            if (inRoom.getRoomName().equals("Tom's Dark Kitchen") && stringContainsWordFromList(action.toLowerCase(), cabinet.toArray(new String[0]))) {
                                if (cabinetCaveN) {
                                    System.out.println("You close the cabinet.");
                                    cabinetCaveN = false;
                                }
                                else {
                                    System.out.println("The cabinet is already closed.");
                                }
                            }

                            else if (stringContainsWordFromList(action.toLowerCase(), door.toArray(new String[0]))) {

                                Door isDoor = null;
                                String doorDir = null;

                                for (String dir : Arrays.asList("n", "s", "e", "w")){

                                    if (inRoom.getDoor(dir) != null){

                                        isDoor = inRoom.getDoor(dir);

                                        doorDir = dir;

                                        break;

                                    }
                                }

                                if (isDoor != null){
                                    if (isDoor.isOpen()) {
                                        System.out.println("You close the door to the " + doorDir.toUpperCase() + ".");
                                        isDoor.setOpen(false);

                                    }

                                    else{
                                        System.out.println("The door is already closed.");
                                    }
                                }

                                else {
                                    System.out.println("There is no door here.");
                                }
                            }


                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), take.toArray(new String[0])) || stringContainsWordFromList(action.toLowerCase(), get.toArray(new String[0]))) {
                            boolean validObject = false;

                            for (String obj : objects) {
                                if (action.toLowerCase().contains(obj)) {
                                    take(inRoom, obj, inventory);
                                    validObject = true;
                                    break;
                                }
                            }

                            if (!validObject){
                                System.out.println("You can't just '" + action + "'");
                            }
                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), read.toArray(new String[0]))) {

                            boolean validObject = false;


                            for (String obj : objects) {
                                if (action.toLowerCase().contains(obj)) {
                                    read(inRoom, inventory, obj);
                                    validObject = true;

                                    break;
                                }
                            }

                            if (!validObject){
                                System.out.println("You can't just '" + action + "'");
                            }
                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), drop.toArray(new String[0])) || stringContainsWordFromList(action.toLowerCase(), remove.toArray(new String[0]))) {

                            boolean validObject = false;


                            for (String obj : objects) {
                                if (action.toLowerCase().contains(obj)) {
                                    drop(inRoom, obj, inventory, player, equipment, existingItems);
                                    validObject = true;

                                    break;
                                }
                            }

                            if (!validObject){
                                System.out.println("You can't just '" + action + "'");
                            }
                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), inventoryinventory.toArray(new String[0]))){
                            itemsIfAny(inventory, "Items in inventory: ");
                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), attack.toArray(new String[0])) ||stringContainsWordFromList(action.toLowerCase(), kill.toArray(new String[0]))) {

                            boolean mobbo = false;

                            for (Mob mob : inRoom.getMOBS()) {

                                String mobNameLower = mob.getName().toLowerCase();

                                if (action.toLowerCase().contains(mobNameLower)) {

                                    combat(player, mob , inRoom, playersStats);

                                    mobbo = true;

                                    break;

                                }

                            }

                            if (mobbo == false){
                                System.out.println("You couldn't find your target.");
                            }

                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), equip.toArray(new String[0]))) {

                            boolean validObject = false;

                            for (String obj : objects) {
                                if (action.toLowerCase().contains(obj) && inventory.contains(obj)) {

                                    for (Item item : existingItems){

                                        if (item.getName().equals(obj)){
                                            equip(item, equipment, player);
                                            validObject = true;
                                            break;

                                        }
                                    }

                                }
                            }

                            if (!validObject){
                                System.out.println("You cannot " + action);
                            }

                        }
                        else if (stringContainsWordFromList(action.toLowerCase(), unequip.toArray(new String[0]))) {

                            boolean validObject = false;
                            for (String obj : objects) {
                                if (action.toLowerCase().contains(obj.toLowerCase()) && inventory.contains(obj)) {
                                    for (Item item : existingItems){
                                        if (item.getName().equals(obj)){
                                            unequip(item, item.getSlotType(), player, equipment);
                                            validObject = true;
                                            break;

                                        }
                                    }

                                }
                            }

                            if (!validObject){
                                System.out.println("You cannot " + action);
                            }

                        }


                        else {
                            System.out.println("You can't '" + action + "' ");
                        }


                    }



                    else {
                        //need object to do that!

                        System.out.println("You need something to receive this action (e.g., Open -> door)!");

                    }



                } else if (stringContainsWordFromList(action.toLowerCase(), objects.toArray(new String[0]))) {

                    System.out.println("You need a verb to do something!");
                }
                else {
                    System.out.println("I don't know that word.");
                }

            }
        }
    }

    // move method
    public static Hub move(String direction, Hub currentHub, Player player, XpLv playerStats) {

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

        Hub newRoom = currentHub.getExit(direction);

        // If there is a new area where user tries to go, update it
        if (newRoom != null) {

            System.out.println(newRoom.getRoomName());
            System.out.println(newRoom.getRoomDescription());

            itemsIfAny(newRoom.getObjects(), "Items in room: ");

            Mobs(newRoom);



            for (Mob mob : newRoom.getMOBS()) {

                if (mob.isAggro() && !mob.getHealth().isDead()) {

                    System.out.println("");

                    List<String> mobNames = new ArrayList<>();

                    for (Mob mob1 : newRoom.getMOBS()){

                        mobNames.add(mob1.getName());

                    }

                    mobsIfAny(mobNames, "Mobs in room: ");

                    System.out.println("");

                    System.out.println(mob.getName() + " is aggro and attacks you as you enter!");

                    combat(player, mob, newRoom, playerStats);

                    if (newRoom.getMOBS().isEmpty()){

                        break; // If I change stuff then before the for I should see what the newRoom.getMOBS's length is, and then do a "for (int i = (the length of the list)"
                    }

                }

            }

            List<String> mobNames = new ArrayList<>();


            for (Mob mob : newRoom.getMOBS()){

                mobNames.add(mob.getName());

            }

            mobsIfAny(mobNames, "Mobs in room: ");
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
        if (stringContainsWordFromList(action.toLowerCase(), northways.toArray(new String[0]))){
            directionCounter ++;
        }
        if (stringContainsWordFromList(action.toLowerCase(), southways.toArray(new String[0]))){
            directionCounter ++;
        }
        if (stringContainsWordFromList(action.toLowerCase(), eastways.toArray(new String[0]))){
            directionCounter ++;
        }
        if (stringContainsWordFromList(action.toLowerCase(), westways.toArray(new String[0]))){
            directionCounter ++;
        }

        if (directionCounter > 1){
            return true;
        }
        else {
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
        if (inRoom.getRoomName().equals("Tom's Dark Cave")){
            System.out.println("You hear absolutely nothing but your own breathing");
        }
    }

    public static void take(Hub inRoom, String item, List<String> inventory){

        List<String> itemos = inRoom.getObjects();

        int count = Collections.frequency(itemos, item);

        int chosenAmount = 0;

        if (count == 0){
            System.out.println("There is no " + item + " (single-form) to take here");

        }

        else {
            chosenAmount = 1;
        }

        if (count > 1){
            System.out.println("There are " + count + " " + item + "(s) here. How many do you want to take?");
            System.out.print("-> ");

            if (scanner.hasNextInt()) {

                int amountToTake = scanner.nextInt();
                scanner.nextLine();

                if (amountToTake > count){
                    System.out.println("You are trying to take more items than there are. \nTaking 1 by default.");

                }
                else if (amountToTake < 0) {
                    System.out.println("You are trying to take a negative amount. \nTaking 1 by default.");
                }

                else {
                    chosenAmount = amountToTake;
                }

            }

            else {

                System.out.println("You are trying to take an invalid amount. \nTaking 1 by default.");
                scanner.nextLine();
            }

        }

        for (int i = 0; i < chosenAmount; i++){

            inventory.add(item);
            inRoom.getObjects().remove(item);

        }

        if (chosenAmount > 0){

            System.out.println("You have taken " + chosenAmount + " " + item + "(s)");

        }

        else {
            System.out.println("You have reframed from taking anything.");
        }



    }

    public static void drop(Hub inRoom, String item, List<String> inventory, Player player, Equipment equipment, List<Item> existingItems){

        int count = Collections.frequency(inventory, item);

        int chosenAmount = 0;

        if (count == 0){
            System.out.println("There is no " + item + " (single-form) in you inventory");

        }
        else {

            chosenAmount = 1;
        }

        if (count > 1){
            System.out.println("You have " + count + " " + item + "(s). How many do you want to drop? \n->");

            if (scanner.hasNextInt()) {

                int amountToTake = scanner.nextInt();
                scanner.nextLine();

                if (amountToTake > count){
                    System.out.println("You are trying to drop more items than there you have. \nDropping 1 by default.");

                }
                else if (amountToTake < 0) {
                    System.out.println("You are trying to drop a negative amount. \nDropping 1 by default.");
                }

                else {
                    chosenAmount = amountToTake;
                }

            }

            else {

                System.out.println("You are trying to drop an invalid amount. \nDropping 1 by default.");
                scanner.nextLine();
            }

        }

        for (int i = 0; i < chosenAmount; i++){

            inventory.remove(item);
            inRoom.getObjects().add(item);

        }

        if (chosenAmount >= 0){

            Item itemToRemove = null;

            for (Item item1 : existingItems) {
                if (item1.getName().equals(item)) {
                    itemToRemove = item1;
                    break;
                }
            }

            Item equippedItem = equipment.getEquipped(itemToRemove.getSlotType());
            if (equippedItem != null && equippedItem.getName().equalsIgnoreCase(itemToRemove.getName())) {
                // Unequip first
                equipment.unequip(itemToRemove.getSlotType(), itemToRemove, player);
                System.out.println("(Unequipped " + itemToRemove.getName() + " before removing.)");
            }

            System.out.println("You have dropped " + chosenAmount + " " + item + "(s)");


        }



    }

    public static void read(Hub inRoom, List<String> inventory, String item) {

        if (inventory.contains(item)) {

            if (item.toLowerCase().equals("newspaper1")) {
                System.out.println("[You unfold the newspaper and read its headline:]");
                System.out.println("Welcome, to the world of Tim.");
                System.out.println("[You eyes follow the page down...]");
                System.out.println("world is one which mysteries and secrets about itself. \nPrepare yourself, to delve deeper into the world of Tim and experience the un-imaginable. \nHint: (Use 'help' to get help)");
            }

            else if (item.toLowerCase().equals("newspaper2")) {
                System.out.println("[You unfold the newspaper and read its headline:]");
                System.out.println("The Money Challenges");
                System.out.println("[You eyes follow the page down...]");
                System.out.println("The world is in a money crisis, and people are going on more dangerous quests to get more money. \nOne gold is 10 silver, 1 silver is 5 copper. \nHint: (More dangerous quests make more money! But they are also much harder to complete...)");
            }

            else {
                System.out.println("You can't read '" + item + "'");
            }

        }
        else {

            if (inRoom.getObjects().contains(item)) {
                System.out.println("You can only read things in your inventory.");
            } else {
                System.out.println("There is no " + item + " here to read.");
            }
        }
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


    public static void help(List<String> verbs) {

        System.out.println("In order to call a command you need to enter in the FOLLOWING FORMAT: \n\n[VERB] + [OBJECT] = action\n\nSome verbs will not need an object to be used (e.g., 'look')\nHere are a list of possible commands:\n move, go || open, close || look, listen, wait, read || get, drop, take, remove || \nkill, attack || stats || help" );

    }

    public static void Mobs(Hub inRoom) {
        int chancer = 0;
        int number = 0;

        // Only run this code if player is in a Southern Forest room
        if (inRoom.getRoomName().contains("Southern Forest")) {

            chancer = (int)(Math.random() * 101);

            if (chancer < 20) {

                // Random number between 0–3 to decide mob type
                number = (int)(Math.random() * 7) + 1;

                if (number > 0 && number > 4) {

                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int rabbitCount = Collections.frequency(mobCounts, "Rabbit");

                    if (rabbitCount < 3) {
                        Mob rabbit = createRabbitWithRandomStats();
                        inRoom.getMOBS().add(rabbit);
                        System.out.println("A rabbit hops out from the bushes.");
                    }
                }

                else if (number > 3 && number < 7) {


                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int chickenCount = Collections.frequency(mobCounts, "Chicken");

                    if (chickenCount < 3) {
                        Mob chicken = createChickenWithRandomStats();
                        inRoom.getMOBS().add(chicken);
                        System.out.println("A chicken clucks into the area in confusion.");
                    }

                }

                else {

                    List<String> mobCounts = new ArrayList<>();

                    for (Mob mob : inRoom.getMOBS()) {
                        String name = mob.getName();
                        mobCounts.add(name);
                    }

                    int squirrelCount = Collections.frequency(mobCounts, "Squirrel");

                    if (squirrelCount < 3) {
                        Mob squirrel = createSquirrelWithRandomStats();
                        inRoom.getMOBS().add(squirrel);
                        System.out.println("A mad squirrel falls from the trees.");
                    }

                }



            }
        }
    }

    private static Mob createRabbitWithRandomStats() {
        int maxHealth = (int)(Math.random() * 2 + 6); //6-7
        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health rabbitHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int)(Math.random() * 4 + 1);
        boolean isAggro = false;

        return new Mob("Rabbit", rabbitHealth, attackPower, isAggro);
    }

    private static Mob createSquirrelWithRandomStats() {
        int maxHealth = (int)(Math.random() * 4 + 5); //5-8
        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health squirrelHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int)(Math.random() * 7 + 3); //3-9
        boolean isAggro = true;

        return new Mob("Squirrel", squirrelHealth, attackPower, isAggro);
    }

    private static Mob createChickenWithRandomStats() {
        int maxHealth = (int)(Math.random() * 4 + 9); //9-11
        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health rabbitHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int)(Math.random() * 3 + 1);
        boolean isAggro = false;

        return new Mob("Chicken", rabbitHealth, attackPower, isAggro);
    }

    private static Mob createGoblinWithRandomStats() {
        int maxHealth = (int)(Math.random() * 6 + 12); //12-17
        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health goblinHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int)(Math.random() * 10 + 2);
        boolean isAggro = false;

        return new Mob("Goblim", goblinHealth, attackPower, isAggro);
    }






    public static void combat(Player player, Mob mob, Hub inRoom, XpLv playerStats){
        boolean using = false;

        if (player.getSkills().isEmpty()){

        }
        else {

            System.out.println("Would you like to you skills and items in this battle or just use what you already have (y/n) ?");
            System.out.print("-> ");
            String answer = scanner.nextLine();

            if (!answer.isEmpty()) {
                if (answer.substring(0, 1).toLowerCase().equals("y")) {
                    using = true;
                } else {
                    using = false;
                }
            }

            else {using = false; System.out.println("No input taken...");}

            System.out.println("");
        }


        while (!player.getHealth().isDead() && !mob.getHealth().isDead()){

            if (using == true){

                System.out.println("What skill would you like to use?");
                for (Skill skill : player.getSkills())  {
                    System.out.print("[" + skill.getName() + "]  ");
                }
                System.out.println("");
                System.out.print("-> ");

                String usingNow = scanner.nextLine();
                if (usingNow.toLowerCase().equals("stun")){
                    Skill.StunSkill.apply(player, mob);
                }

                else {
                    int chop = 0;
                    for (Skill skiller : player.getSkills())  {
                        if (usingNow.toLowerCase().equals(skiller.getName())){
                            chop++;
                        }
                    }

                    if (chop <= 0){
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

            if (mob.getHealth().isDead()){
                System.out.println("The " + mob.getName() + " has died at your hands. \n");

                String targetMobName = mob.getName();

                playerStats.addXp(playerStats.calculateXp(mob.getName()));

                playerStats.calculateLv(playerStats.getXp(), playerStats.getLevel(), player);

                player.displayStats(player, playerStats);

                inRoom.getMOBS().removeIf(mob1 -> mob.getName().equals(targetMobName)); //remove an object that has the name Rabbit

                break;
            }

            else {

                mob.attack(player);
                System.out.println("");

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (player.getHealth().isDead()){
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

    public static void heal(Player player) {

        if (player.getHealth().getHeealth() == player.getHealth().getMaxHealth()){
            System.out.println("You are already maxed out in health.");
        }

        else{

            System.out.println("You drop into unconsciousness and begin to heal...");
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("You have healed!");

            player.getHealth().setHeealth(player.getHealth().getMaxHealth());

        }

    }

    public static void equip(Item item, Equipment equipment, Player player){
        equipment.equip(item, player);
    }

    public static void unequip(Item item, String slot, Player player, Equipment equipment){
        equipment.unequip(slot, item, player);
    }

}