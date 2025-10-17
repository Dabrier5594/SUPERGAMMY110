import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Game {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create the initial room
        Hub cave = new Hub("Tom's Dark Cave", "The back of the ancient cave where Tom the hermit lived for many years.");

        Hub caveN = new Hub("Tom's Dark Kitchen", "Still in Tom's cave, but now you have moved into his kitchen.");

        // Attatch the rooms together
        cave.setExit("n", caveN);
        caveN.setExit("s", cave);

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
        verbs.add("pull");
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

        List<String> verbsOnly = new ArrayList<>();
        verbs.add("n");
        verbs.add("s");
        verbs.add("w");
        verbs.add("e");
        verbs.add("north");
        verbs.add("south");
        verbs.add("west");
        verbs.add("east");
        verbs.add("look");

        List<String> objects = new ArrayList<>();
        objects.add("w");
        objects.add("e");
        objects.add("s");
        objects.add("n");
        objects.add("west");
        objects.add("east");
        objects.add("south");
        objects.add("north");


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

            for (String verb : verbs) {
                if (action.contains(verb)) {
                    break;
                }
            }

            //checks to see if input has verb
            for (String verb : verbs) {
                if (action.contains(verb)) {
                boolean verbHere = true;
                break;

            }


            //checks to see if input has an object for the verb to act on if needed (open, close etc.)
                if (objects.contains(actionParsed)) {

                    //CHANGE TO MAKE IF THE VERB WAS MOVE OR GO, THEN CHECK IF IT HAS A DIRECTION, IF NOT, MOVE WHERE?

                    // If user moves north update room (if possible)
                    if (action.toLowerCase().equals("n") || action.toLowerCase().equals("north")) {
                        // Call the move method and update to inRoom
                        inRoom = move(action.toLowerCase(), inRoom);

                        System.out.println(inRoom.getRoomDescription());
                    }

                    if (action.toLowerCase().equals("s") || action.toLowerCase().equals("south")) {
                        // Call the move method and update to inRoom
                        inRoom = move(action.toLowerCase(), inRoom);

                        System.out.println(inRoom.getRoomDescription());
                    }

                    if (action.toLowerCase().equals("w") || action.toLowerCase().equals("west")) {
                        // Call the move method and update to inRoom
                        inRoom = move(action.toLowerCase(), inRoom);

                        System.out.println(inRoom.getRoomDescription());
                    }

                    if (action.toLowerCase().equals("e") || action.toLowerCase().equals("east")) {
                        // Call the move method and update to inRoom
                        inRoom = move(action.toLowerCase(), inRoom);

                        System.out.println(inRoom.getRoomDescription());

                    }

                }

                else if (verbsOnly.contains(actionParsed)) {



                }

                else {
                    //need object to do that!

                }
            }

            else if (objects.contains(actionParsed)){

                System.out.println("You need a verb to do something!");
            }

            else {
                System.out.println("I don't know that word.");
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

            return newRoom; // Return the new Hub object (room)

        }

        // If there is no new area where the user tries to go, stop them
        else {
            System.out.println("You are trying to go to an impossible location.");
            return currentHub; // Return the current Hub object
        }
    }
}