package users;

import goods.Goods;

public interface UserIF {
    void reOrder(int orderID);

    void cancelOrder(int orderID);
}
