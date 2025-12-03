class Quest {
    String id, name;                          // id is like "MQ1", name is what shows up like "Leave Cave"
    int goalType;                             // 1=kill stuff, 2= get items, 3 = go to a place
    String target;                            // what is target? "goblin" or "twig" or room name etc
    int needed, current = 0;                  // how many you need vs how many you have
    int rewardXp, rewardGold;                 // reward - gold (specifically for your PerBag which you can access anywhere.
    boolean done = false;                     // did player finish it yet?

    public Quest(String id, String name, int type, String target, int needed, int xp, int gold) {
        this.id = id;
        this.name = name;
        this.goalType = type;
        this.target = target;
        this.needed = needed;
        this.rewardXp = xp;
        this.rewardGold = gold;
    }

    public String getId(){
        return id;
    }

    public void check(String typeStr, String targetStr, Player player) {
        if (done || !target.equalsIgnoreCase(targetStr)) return;  // if already done OR wrong target, skip


        if ((goalType == 1 && typeStr.equals("KILL_MOB")) ||     // if kill quest AND they killed right mob
                (goalType == 2 && typeStr.equals("COLLECT_ITEM")) || // OR item quest AND they picked up right item
                (goalType == 3 && typeStr.equals("VISIT_LOCATION"))) // OR location quest AND they went right place
        {
            current++;                                            // add 1 to their progress
            if (current >= needed) {                              // if they hit the goal number
                done = true;                                      // mark quest done
                System.out.println(name + " COMPLETE! +" + rewardGold + " coins (to be used in playerBAG only) **"); // tell player they won
                player.addCoins(rewardGold);

                if (this.getId().equals("MQ1"));{
                    Player.QUESTS.put("MQ2", new Quest("MQ2", "Leave Southern Forest", 3, "Northern Forest Area #26", 1, 50, 5));  // main quest - go outside cave
                    System.out.println("You have unlocked a new quest! [type: 'quests'] to see quests]");
                }
            }
        }

    }

    public String status() {
        return name + ": " + current + "/" + needed + " [" + target + "]" +  (done ? " - (DONE)" : " - (NOT-DONE) "); // make string like "Escape Cave: 1/1 Southern Forest Area #1 (DONE)"
    }
}
