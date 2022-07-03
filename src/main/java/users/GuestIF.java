package users;

import goods.Goods;

import java.util.HashMap;

public interface GuestIF {
    void addToBasket(Goods item, int quantity);
    void order(HashMap<Goods, Integer> basket);
}
