package shops;

import goods.Goods;
import users.UserIF;

public interface ShopIF {

    void addToBasket(int item, int quantity);

    void addUser(UserIF user);

    void addItem(Goods item);

    void printAvailableGoods();

    void removeItem(Goods item);

    void printWelcome();

    void printMarket();

    void printMenu();

    void order();

    void reOrder();

    void cancelOrder();

    void orderToMap(Order order);

    void printBasket();
}
