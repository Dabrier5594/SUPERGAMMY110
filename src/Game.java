import java.util.*;
import java.util.regex.Pattern;

//                    NOTES FOR IMPROVEMENTS:

// 1)  MAKE IT SO YOU ACTUALLY DIE

// 2) MAKE IT SO YOU GET SPAWN KILL THINGS. ADD XP AND LEVELS

// 3) MAKE IT SO YOU CAN EQUIP WEAPONS AND ARMOR

public class Game {
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

        Hub forest2 = new Hub("Southern Forest Area #2" , "The Southern Area of the Great Makiss Forest. \nOnly minor prey lay in wait in this forest. \nEXITS:  " );

        Hub forest4 = new Hub("Southern Forest Area #4", "The Southern Area of the Great Makiss Forest. \nThe air feels heavy here, and the forest is unusually silent. The ground is littered with broken twigs, as though something recently passed through... \nEXITS: " );

        Hub forest3 = new Hub("Southern Forest Area #3", "The Southern Area of the Great Makiss Forest. \nThorny bushes block much of the way south, their barbs glinting sharply. However, the path seems clearer to the north, where faint sunlight filters through. \nEXITS: " );

        Hub forest5 = new Hub("Southern Forest Area #5", "The Southern Area of the Great Makiss Forest. \nReminiscence of something eating a chicken is visible under scattered leaves...\nEXITS: " );

        Hub forest6 = new Hub("Southern Forest Area #6", "The Southern Area of the Great Makiss Forest. \nThe trees grow apart here, their canopies still blotting out much of the sunlight. The air feels damp, and the dull chest#001 sits quietly in the middle of the area... \nEXITS: ");

        Hub forest7 = new Hub("Southern Forest Area #7", "The Southern Area of the Great Makiss Forest. \nThick ferns and wildflowers cover the forest floor. A small trail, barely visible, weaves between the towering oaks and leads South. \nEXITS: ");

        Hub forest8 = new Hub("Southern Forest Area #8", "The Southern Area of the Great Makiss Forest. \nThe chirping of birds fills the air as soft beams of light break through the leaves. The ground here is softer, marked by faint paw prints. \nEXITS: ");

        Hub forest9 = new Hub("Southern Forest Area #9", "The Southern Area of the Great Makiss Forest. \nA clearing opens up, surrounded by mossy boulders. A faint breeze carries the scent of pine and damp earth. Something moves just beyond sight... \nEXITS: ");

        Hub forest10 = new Hub("Southern Forest Area #10", "The Southern Area of the Great Makiss Forest. \nDark vines creep up the tree trunks, their tendrils twisting toward the faint light above. The sound of dripping water echoes through the silence. \nEXITS: ");

        Hub forest11 = new Hub("Southern Forest Area #11", "The Southern Area of the Great Makiss Forest. \nThe forest floor dips here, forming a shallow hollow where mushrooms glow faintly in the dim light. The atmosphere feels eerily calm. \nEXITS: ");

        Hub forest12 = new Hub("Southern Forest Area #12", "The Southern Area of the Great Makiss Forest. \nAncient trees dominate this part of the forest, their roots rising like walls of wood. A faint trail of claw marks runs along one of the trunks... \nEXITS: ");

        Hub forest13 = new Hub("Southern Forest Area #13", "The Southern Area of the Great Makiss Forest. \nYou find yourself in a foggy grove. The mist curls around your legs and blurs the outlines of the trees. Strange, muffled sounds echo from the distance. \nEXITS: ");

        Hub forest14 = new Hub("Southern Forest Area #14", "The Southern Area of the Great Makiss Forest. \nThe terrain here becomes rocky, with exposed roots clawing through the soil. You can hear a faint trickle of water somewhere to the west. \nEXITS: ");

        Hub forest15 = new Hub("Southern Forest Area #15", "AThe Southern Area of the Great Makiss Forest.  \nwide patch of sunlight filters through the canopy, illuminating a small pool of crystal-clear water. Tiny insects hover above its surface. \nEXITS: ");

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

        //Doors!!!!
        Door caveDoor = new Door(caveNW, forest1, false);
        caveNW.setDoor("n", caveDoor);
        forest1.setDoor("s", caveDoor);



        caveN.addObject("newspaper1");
        caveNN.addObject("gold");
        for (int i = 0; i < 2; i ++) {
            caveNN.addObject("silver");
        }
        caveNN.addObject("copper");
        caveNE.addObject("newspaper2");
        for (int ii = 0; ii < 3; ii ++) {
            forest1.addObject("twig");
        }

        List<String> chestContents = Arrays.asList("copper");

        Chest firstChest = new Chest(true, "treasure-token#001", chestContents, "C");
        forest6.addChest("chest#001", firstChest);

        forest6.addObject("treasure-token#001");

        Story(cave);

    }

    public static void Story(Hub cave) {


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




        // Makes the room you are in "cave"
        // INSERT ALL CODE THAT SETS A VARIABLE HERE!!!!!
        Hub inRoom = cave;


        boolean cabinetCaveN = false;
        boolean cabinetDaggerCaveN = true;

        boolean start;

        // Asks if you want to play

        System.out.print("Enter a name: ");
        String namer = scanner.nextLine();
        System.out.println("");

        Player player =  new Player(namer, 10,10,3, 0);

        System.out.print("Play (yes or no)? \n-> ");
        String play = scanner.nextLine();

        System.out.println("");

        //Checks if you said yes or not
        if (play.toLowerCase().equals("yes") || play.toLowerCase().equals("y")) {

            start = true;

            System.out.println("Okay! Starting... (hint: use help to 'help')!");//

            System.out.println("");

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            player.displayStats(player);

            System.out.println("");

            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            System.out.println(cave.getRoomDescription());

            player.getSkills().add(new Skill.StunSkill("Stun", "Stuns enemy for one turn. Cooldown: 3 turns.", true, 3));
        }

        else {
            start = false;
        }

        //While start is true, run the code
        while (start) {

            System.out.println("");

            System.out.print("-> ");
            String action = scanner.nextLine();

            System.out.println("");

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

                            inRoom = move(action.toLowerCase(), inRoom, player);



                        }
                        else if (action.toLowerCase().equals("s") || action.toLowerCase().equals("south")) {
                            // Call the move method and update to inRoom
                            inRoom = move(action.toLowerCase(), inRoom, player);



                        }
                        else if (action.toLowerCase().equals("w") || action.toLowerCase().equals("west")) {
                            // Call the move method and update to inRoom
                            inRoom = move(action.toLowerCase(), inRoom, player);


                        }
                        else if (action.toLowerCase().equals("e") || action.toLowerCase().equals("east")) {
                            // Call the move method and update to inRoom
                            inRoom = move(action.toLowerCase(), inRoom, player);



                        }
                        else if (stringContainsWordFromList(action.toLowerCase(), movements.toArray(new String[0]))) {

                            if (stringContainsWordFromList(action.toLowerCase(), directions.toArray(new String[0]))) {

                                if (stringContainsWordFromList(action.toLowerCase(), northways.toArray(new String[0]))) {

                                    action = "n";

                                    inRoom = move(action.toLowerCase(), inRoom, player);


                                }

                                if (stringContainsWordFromList(action.toLowerCase(), southways.toArray(new String[0]))) {

                                    action = "s";

                                    inRoom = move(action.toLowerCase(), inRoom, player);


                                }

                                if (stringContainsWordFromList(action.toLowerCase(), westways.toArray(new String[0]))) {

                                    action = "w";

                                    inRoom = move(action.toLowerCase(), inRoom,player);


                                }

                                if (stringContainsWordFromList(action.toLowerCase(), easyways.toArray(new String[0]))) {

                                    action = "e";

                                    inRoom = move(action.toLowerCase(), inRoom, player);


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
                            player.displayStats(player);
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

                                        System.out.println("You open the door to the " + doorDir.toUpperCase() + ".");
                                        isDoor.setOpen(true);

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
                                                    System.out.println("Magic swirls around the chest and its lid disappears! \n**POOF!!**");
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
                                    drop(inRoom, obj, inventory);
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

                                    combat(player, mob , inRoom);

                                    mobbo = true;

                                    break;

                                }

                            }

                            if (mobbo == false){
                                System.out.println("You couldn't find your target.");
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
    public static Hub move(String direction, Hub currentHub, Player player) {

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

                    combat(player, mob, newRoom);

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

    public static void drop(Hub inRoom, String item, List<String> inventory){

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

        System.out.println("In order to call a command you need to enter in the FOLLOWING FORMAT: \n[VERB] + [OBJECT] = action\nSome verbs will not need an object to be used (e.g., 'look')\nHere are a list of possible commands: \n move, go || open, close || look, listen, wait, read || get, drop, take, remove || kill, attack || stats || help" );

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
        int maxHealth = (int)(Math.random() * 4 + 5); //5-7
        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health squirrelHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int)(Math.random() * 7 + 3); //3-9
        boolean isAggro = true;

        return new Mob("Squirrel", squirrelHealth, attackPower, isAggro);
    }

    private static Mob createChickenWithRandomStats() {
        int maxHealth = (int)(Math.random() * 3 + 9); //9-11
        int currentHealth = maxHealth;  // start at full health
        int damageResistance = 0;       // example damage resistance
        Health rabbitHealth = new Health(maxHealth, currentHealth, damageResistance);

        int attackPower = (int)(Math.random() * 3 + 1);
        boolean isAggro = false;

        return new Mob("Chicken", rabbitHealth, attackPower, isAggro);
    }






    public static void combat(Player player, Mob mob, Hub inRoom){
        boolean using = false;

        if (player.getSkills().isEmpty()){

        }
        else {
            System.out.println("Would you like to you skills and items in this battle or just use what you already have (y/n) ?");
            System.out.print("-> ");
            String answer = scanner.nextLine();

            if (answer.substring(0, 1).toLowerCase().equals("y")) {
                using = true;
            } else {
                using = false;
            }

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
                inRoom.getMOBS().removeIf(mob1 -> mob.getName().equals(targetMobName)); //remove an object that has the name Rabbit


                player.displayStats(player);
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
                    System.out.println("You have been killed");
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

    public static void chests(Hub inRoom, String obj){

        //Make so chest doesn't keep restorcking, do something like cabinet.
        //MAKE A CLASS THAT CUTS VINES. SO I CAN MAKE THIS CHEST BE COVERED IN VINES AND ONLY BE OPENABLE WHEN CUT BY VINES

    }



}