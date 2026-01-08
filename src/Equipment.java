import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Item {
    private String name;
    private String slotType; //bow, melee, head, body,legs
    private boolean isOffensive;
    private String enchantment;

    public Item(String name, String slotType, boolean isOffensive, String enchantment) {
        this.name = name;
        this.slotType = slotType.toLowerCase();
        this.isOffensive = isOffensive;
        this.enchantment = enchantment;
    }
    public String getName() { return name; }
    public String getSlotType() { return slotType; }
    public boolean isOffensive() { return isOffensive; }
    public String getEnchantment(){ return enchantment; }
    public void setEnchantment(String enchantment){ this.enchantment = enchantment; }

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
        if(item.getEnchantment() != null){
            if (item.getEnchantment().equals("fire")){
                player.setAttackPower(0);
            }
        }

        if(item.getName().equals("knights helm")){
            player.getHealth().setDamageResistance(player.getHealth().getDamageResistance() + 3);
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

    public Map<String, Item> getEquippedItems(){ return equippedItems;}

}

