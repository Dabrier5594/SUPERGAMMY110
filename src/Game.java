import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Game {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create the initial room
        Hub cave = new Hub("Tom's Dark Cave", "The back of the ancient cave where Tom the hermit lived for many years.");

        Hub caveN = new Hub("Tom's Dark Kitchen", "Still in Tom's cave, but now you have moved into his kitchen which consists of a cabinet and a counter.");

        // Attatch the rooms together
        cave.setExit("n", caveN);
        caveN.setExit("s", cave);

        caveN.addObject("newspaperA");
        caveN.addObject("dagger");


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
        verbs.add("take");
        verbs.add("read");
        verbs.add("push");
        verbs.add("wait");
        verbs.add("listen");
        verbs.add("lock");
        verbs.add("close");
        verbs.add("n");
        verbs.add("s");
        verbs.add("w");
        verbs.add("e");
        verbs.add("north");
        verbs.add("south");
        verbs.add("west");
        verbs.add("east");
        verbs.add("l");

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
        objects.add("newspaperA");
        objects.add("dagger");


        List<String> look = new ArrayList<>();
        look.add("l");
        look.add("look");

        List<String> wait = new ArrayList<>();
        wait.add("wait");

        List<String> listen = new ArrayList<>();
        listen.add("listen");

        List<String> open = new ArrayList<>();
        listen.add("open");

        List<String> cabinet = new ArrayList<>();
        listen.add("cabinet");

        List<String> inventory = new ArrayList<>();

        // Makes the room you are in "cave"
        Hub inRoom = cave;

        boolean start;

        // Asks if you want to play
        System.out.print("Play (yes or no)? \n-> ");
        String play = scanner.nextLine();

        //Checks if you said yes or not
        if (play.toLowerCase().equals("yes") || play.toLowerCase().equals("y")) {

            start = true;

            System.out.println("Okay! Starting...!");
            System.out.println(cave.getRoomDescription());
        }

        else {
            start = false;
        }

        //While start is true, run the code
        while (start) {
            System.out.print("-> ");
            String action = scanner.nextLine();

            boolean oneCardinol = oneDirection(action, northways, southways, easyways, westways);

            if (oneCardinol){
                System.out.println("You can't input multiple directions!");
            }

            else {

                if (stringContainsWordFromList(action.toLowerCase(), verbs.toArray(new String[0]))) {

                    if (stringContainsWordFromList(action.toLowerCase(), verbsOnly.toArray(new String[0]))) {

                        if (action.toLowerCase().equals("n") || action.toLowerCase().equals("north")) {

                            inRoom = move(action.toLowerCase(), inRoom);


                        }
                        else if (action.toLowerCase().equals("s") || action.toLowerCase().equals("south")) {
                            // Call the move method and update to inRoom
                            inRoom = move(action.toLowerCase(), inRoom);


                        }
                        else if (action.toLowerCase().equals("w") || action.toLowerCase().equals("west")) {
                            // Call the move method and update to inRoom
                            inRoom = move(action.toLowerCase(), inRoom);


                        }
                        else if (action.toLowerCase().equals("e") || action.toLowerCase().equals("east")) {
                            // Call the move method and update to inRoom
                            inRoom = move(action.toLowerCase(), inRoom);



                        }
                        else if (stringContainsWordFromList(action.toLowerCase(), movements.toArray(new String[0]))) {

                            if (stringContainsWordFromList(action.toLowerCase(), directions.toArray(new String[0]))) {

                                if (stringContainsWordFromList(action.toLowerCase(), northways.toArray(new String[0]))) {

                                    action = "n";

                                    inRoom = move(action.toLowerCase(), inRoom);


                                }

                                if (stringContainsWordFromList(action.toLowerCase(), southways.toArray(new String[0]))) {

                                    action = "s";

                                    inRoom = move(action.toLowerCase(), inRoom);


                                }

                                if (stringContainsWordFromList(action.toLowerCase(), westways.toArray(new String[0]))) {

                                    action = "w";

                                    inRoom = move(action.toLowerCase(), inRoom);


                                }

                                if (stringContainsWordFromList(action.toLowerCase(), easyways.toArray(new String[0]))) {

                                    action = "e";

                                    inRoom = move(action.toLowerCase(), inRoom);


                                }

                            }

                            else {
                                System.out.println("You can't just '" + action + "'");
                            }

                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), look.toArray(new String[0]))) {
                            System.out.println(inRoom.getRoomDescription());
                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), wait.toArray(new String[0]))) {
                            System.out.println("You are waiting (hint: nothing happens by waiting)...");
                        }

                        else if (stringContainsWordFromList(action.toLowerCase(), listen.toArray(new String[0]))) {
                            listening(inRoom);
                        }

                    }

                    else if (stringContainsWordFromList(action.toLowerCase(), objects.toArray(new String[0]))) {

                        if (stringContainsWordFromList(action.toLowerCase(), open.toArray(new String[0]))) {
                            if (inRoom.getRoomName().equals("caveN") && stringContainsWordFromList(action.toLowerCase(), cabinet.toArray(new String[0]))) {
                                if (inRoom.getRoomName().equals("caveN")){
                                    System.out.println("You open the cabinet.");
                                    //If dagger is not in room and not in inventory, it falls out and is added to room (SO MAKE SURE TO REMOVE It FROM THE ROOM AT THE START!!!!!
                                }
                            }
                        }
                        if (true) {
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
    public static Hub move(String direction, Hub currentHub) {

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
        Hub newRoom = currentHub.getExit(direction);

        // If there is a new area where user tries to go, update it
        if (newRoom != null) {
            System.out.println(newRoom.getRoomDescription());

            System.out.println("Items here: " + newRoom.getObjects());
            if (newRoom.getObjects() == null || newRoom.getObjects().isEmpty()){
                System.out.println("Nothing...");
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

    public static void listening(Hub inRoom) {
        if (inRoom.getRoomName().equals("cave")){
            System.out.println("You hear absolutely nothing but your own breathing");
        }
    }

    public static void open(Hub inRoom) {

    }

}