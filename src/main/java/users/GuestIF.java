package users;

import goods.Goods;

import java.util.HashMap;

public interface GuestIF {
    void order(HashMap<Goods, Integer> basket);
}
