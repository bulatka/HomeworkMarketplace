package goods;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

public class Order {
    private HashMap<Goods, Integer> order = new HashMap<>();
    private int ID;

    public Order(HashMap<Goods, Integer> basket) {
        this.ID = Objects.hash(LocalDateTime.now());
        this.order = basket;
    }

    public HashMap<Goods, Integer> getOrder() {
        return order;
    }

    public int getID() {
        return ID;
    }
}
