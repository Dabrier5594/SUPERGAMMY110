import java.util.Scanner;

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

        // Creating start boolean
        boolean start;

        // Makes the room you are in "cave"
        Hub inRoom = cave;

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
        if (start){

            while(true) {
                System.out.print("-> ");

                String action = scanner.nextLine();

                // If user said "look" then description of current room prints
                if (action.toLowerCase().equals("look")) {
                    System.out.println(inRoom.getRoomDescription());
                }

                // If user moves north update room (if possible)
                if (action.toLowerCase().equals("n") || action.toLowerCase().equals("north")) {
                    // Call the move method and update to inRoom
                    inRoom = move(action.toLowerCase(), inRoom);
                }
            }
        }
    }

    // move method
    public static Hub move(String direction, Hub currentHub) {

        Hub newRoom = currentHub.getExit(direction);

        // If there is a new area where user tries to go, update it
        if (newRoom != null) {

            System.out.println("You move " + direction + ".");
            return newRoom; // Return the new Hub object

        }

        // If there is no new area where the user tries to go, stop them
        else {
            System.out.println("You are trying to go to an impossible location.");
            return currentHub; // Return the current Hub object
        }
    }
}