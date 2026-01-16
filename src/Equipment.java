import java.util.HashMap;
import java.util.Map;

class Item {
    private String name;
    private String slotType; //melee, body, head, legging, boots,
    private boolean isOffensive;
    private Enchantment1 enchantment1;


    private String inspectDescription;

    public Item(String name, String slotType, boolean isOffensive, Enchantment1 enchantment1) {
        this.name = name;
        this.slotType = slotType.toLowerCase();
        this.isOffensive = isOffensive;
        this.enchantment1 = enchantment1;

        this.inspectDescription = "Nothing special about this item.";

    }

    public String getName() { return name; }
    public String getSlotType() { return slotType; }
    public boolean isOffensive() { return isOffensive; }
    public Enchantment1 getEnchantment1(){ return enchantment1; }
    public void setEnchantment1(Enchantment1 enchantment1){ this.enchantment1 = enchantment1; }

    //inspect classes
    public void setInspectDescription(String inspectDescription) {
        this.inspectDescription = inspectDescription;
    }

    public String getInspectDescription() {
        return inspectDescription;
    }

    public void checkPower(Mob mob){
        if (enchantment1 != null){
            enchantment1.usePower(enchantment1, mob);
        }
    }

    public void checkPowerBoss(Boss mob){
        if (enchantment1 != null){
            enchantment1.usePowerBoss(enchantment1, mob);
        }
    }


}

class Equipment {

    private Map<String, Item> equippedItems = new HashMap<>();

    public Item getItemBasedOnSlot(String slotType, Map<String, Item> equippedItems){
        for (Map.Entry<String, Item> entry: equippedItems.entrySet()){
            Item name = entry.getValue();
            if (name.getSlotType().equalsIgnoreCase(slotType)){
                return name;
            }
        }

        return null;
    }

    public boolean equip(Item item, Player player) {

        String slot = item.getSlotType();

        if (equippedItems.containsKey(slot)) {
            System.out.println("You already have something equipped in this place.");
            return false;

        }

        equippedItems.put(slot, item);
        System.out.println("You have equipped '" + item.getName() + "'!\n");
        if(item.getEnchantment1() != null){
            if (item.getEnchantment1().equals("fire")){
                player.setAttackPower(0);

            }
            if(item.getEnchantment1().equals("protection")){
                if(item.getSlotType() == "helmet") {
                    player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 2);
                }


                if(item.getSlotType() == "chest") {
                    player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 4);
                }


                if(item.getSlotType() == "leggings") {
                    player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 3);
                }


                if(item.getSlotType() == "boots") {
                    player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 2);
                }

            }
        }

        if(item.getName().equals("knights helm")){
            player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 3);
        }

        if(item.getName().equals("admin sword")){
            player.setAttackPower(3);
        }

        if(item.getName().equals("knights breastplate")){
            player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 6);
        }
        if(item.getName().equals("knights leggings")){
            player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 5);
        }
        if(item.getName().equals("knights footwear")){
            player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 3);
        }

        if (item.getName().equals("dagger")){
            player.setAttackPower(3); //adds 3 to total attack power
        }

        if (item.getName().equals("bear claw")){
            player.setAttackPower(5); //adds 3 to total attack power
        }

        if (item.getName().equals("leather armor")){
            player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 3);
        }

        if (item.getName().equals("firstville guards plate")){
            player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 5);
            System.out.println("Observers think you are a guard of FirstVille!");
        }

        if (item.getName().equals("firstville guards helm")){
            player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 2);
            System.out.println("Observers think you are a guard of FirstVille!");
        }

        if (item.getName().equals("firstville guards legs")){
            player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 3);
            System.out.println("Observers think you are a guard of FirstVille!");
        }

        if (item.getName().equals("firstville guards boots")){
            player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 2);
            System.out.println("Observers think you are a guard of FirstVille!");
        }

        return true;

    }

    public void unequip(String slot, Item item, Player player) {
        slot = slot.toLowerCase();
        if (equippedItems.containsKey(slot)) {
            System.out.println("You have unequipped '" + item.getName() + "'!\n");
            equippedItems.remove(slot);

            if (item.getName().equals("dagger")){
                player.setAttackPower(-3); //takes 3 from total attack power
            }

            if (item.getName().equals("admin sword")){
                player.setAttackPower(-3); //takes 3 from total attack power
            }

            if (item.getName().equals("bear claw")){
                player.setAttackPower(-5); //takes 3 from total attack power
            }


            if (item.getName().equals("leather armor")){
                player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() - 3);

            }

            if (item.getName().equals("firstville guards helm")){
                player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() - 2);
                System.out.println("Observers are losing faith you are a guard of FirstVille!");

            }

            if (item.getName().equals("firstville guards plate")){
                player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() - 5);
                System.out.println("Observers are losing faith you are a guard of FirstVille!");
            }

            if (item.getName().equals("firstville guards legs")){
                player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() - 3);
                System.out.println("Observers are losing faith you are a guard of FirstVille!");
            }

            if (item.getName().equals("firstville guards boots")){
                player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() - 2);
                System.out.println("Observers are losing faith you are a guard of FirstVille!");
                if (player.getHealth().getHeealth() > player.getHealth().getMaxHealth()){
                    player.getHealth().setHeealth(player.getHealth().getMaxHealth());
                }
            }
        }
        else{ System.out.println("You are unable to perform this action."); }

    }

    public Item getEquipped(String slot) {

        return equippedItems.get(slot.toLowerCase());
    }

    public String getWeapon(){
        if (equippedItems.get("melee") != null) {
            return equippedItems.get("melee").getName().toLowerCase();
        } else {
            return "";
        }
    }

    public String getHelmet(){
        if (equippedItems.get("head") != null) {
            return equippedItems.get("head").getName().toLowerCase();
        } else {
            return "";
        }    }

    public String getBody(){
        if (equippedItems.get("body") != null) {
            return equippedItems.get("body").getName().toLowerCase();
        } else {
            return "";
        }    }

    public String getLegs(){
        if (equippedItems.get("legging") != null) {
            return equippedItems.get("legging").getName().toLowerCase();
        } else {
            return "";
        }    }

    public String getBoots(){
        if (equippedItems.get("boots") != null) {
            return equippedItems.get("boots").getName().toLowerCase();
        } else {
            return "";
        }    }

    public Map<String, Item> getEquippedItems(){ return equippedItems;}

}

