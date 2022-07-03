package shops;

import goods.Goods;

import java.util.List;

public interface ShopIF {

    void addItem(Goods item);

    void printAvailableGoods(List<Goods> store);

    void removeItem(Goods item);

    void sortGoodsByName(List<Goods> store);


    void searchByName(List<Goods> store, String name);

    void recommendGoods(List<Goods> store);

    void printWelcome();
}
