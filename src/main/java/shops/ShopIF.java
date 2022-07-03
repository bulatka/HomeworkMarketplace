package shops;

import goods.Goods;
import users.User;

import java.util.List;

public interface ShopIF {
    void addUser(User user);
    void addItem(Goods item);

    void printAvailableGoods();

    void removeItem(Goods item);

    void sortGoodsByName();


    void searchByName(String name);

    void recommendGoods();

    void printWelcome();
}
