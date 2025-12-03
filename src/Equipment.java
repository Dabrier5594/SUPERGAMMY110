import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Item {
    private String name;
    private String slotType; //bow, melee, head, body,legs
    private boolean isOffensive;

    public Item(String name, String slotType, boolean isOffensive) {
        this.name = name;
        this.slotType = slotType.toLowerCase();
        this.isOffensive = isOffensive;
    }
    public String getName() { return name; }
    public String getSlotType() { return slotType; }
    public boolean isOffensive() { return isOffensive; }

}

class Equipment {
    private Map<String, Item> equippedItems = new HashMap<>();

    public boolean equip(Item item, Player player) {
        String slot = item.getSlotType();

        if (equippedItems.containsKey(slot)) {
            System.out.println("You already have something equipped in this place.");
            return false;

        }
        equippedItems.put(slot, item);
        System.out.println("You have equipped '" + item.getName() + "'!\n");

        if (item.getName().equals("dagger")){
            player.setAttackPower(3); //adds 3 to total attack power
        }
        if (item.getName().equals("leather armor")){
            player.getHealth().setMaxHealth(player.getHealth().getDamageResistance() + 3);
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

            if (item.getName().equals("leather armor")){
                player.getHealth().setMaxHealth(player.getHealth().getDamageResistance() - 3);
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

}

