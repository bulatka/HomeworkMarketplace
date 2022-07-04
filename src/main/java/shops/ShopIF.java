package shops;

import goods.Goods;
import goods.Order;
import users.User;

public interface ShopIF {

    void addToBasket(int item, int quantity);

    void addUser(User user);

    void addItem(Goods item);

    void printAvailableGoods();

    void removeItem(Goods item);

    void sortGoodsByName();

    void searchByName(String name);

    void recommendGoods();

    void printWelcome();

    void printMarket();

    void printMenu();

    void order();

    void reOrder();

    void cancelOrder();

    void orderToMap(Order order);
}
