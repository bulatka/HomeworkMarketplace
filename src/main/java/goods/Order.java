package goods;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

public class Order {
    private HashMap<Goods, Integer> basket = new HashMap<>();
    private int ID;

    public Order(HashMap<Goods, Integer> basket) {
        this.ID = Math.abs(Objects.hash(LocalDateTime.now()));
        this.basket = basket;
    }

    public HashMap<Goods, Integer> getBasket() {
        return basket;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Заказ №" + ID + "\n[" + basket + "]";
    }
}
