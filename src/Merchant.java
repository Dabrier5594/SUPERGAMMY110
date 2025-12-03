import java.util.*;

public class Merchant {

    private String name;
    private Map<String, Integer> prices = new HashMap<>();

    public Merchant(String name, Map<String, Integer> pricesObj){
        this.name = name;
        this.prices = pricesObj;
    }

    public String getName(){
        return name;
    }

    public List<String> getItems(Player player){
        return new ArrayList<>(this.prices.keySet());
    }

    public List<Integer> getPrices(Player player) {
        return new ArrayList<>(this.prices.values());
    }

}


