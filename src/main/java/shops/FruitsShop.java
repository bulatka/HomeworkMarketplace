package shops;

import goods.Goods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FruitsShop implements ShopIF {
    final private List<Goods> store = new ArrayList<>();
    final private int RECOMMENDATION_RATING = 8;

    @Override
    public void addItem(Goods item) {
        store.add(item);
        System.out.println("Фрукт " + item + " добавлен в продажу");
    }

    @Override
    public void printAvailableGoods(List<Goods> store) {
        int i = 0;
        for (Goods item : store) {
            System.out.println(i + ". " + item);
            i++;
        }
    }

    @Override
    public void removeItem(Goods item) {
        store.remove(item);
        System.out.println("Фрукт " + item + " удален из продажи");
    }

    @Override
    public void sortGoodsByName(List<Goods> store) {
        Collections.sort(store);
        printAvailableGoods(store);
    }

    @Override
    public void searchByName(List<Goods> store, String name) {
        for (Goods item : store) {
            if (item.getName().equals(name))
                System.out.println("Найдено: " + item);
        }
    }

    @Override
    public void recommendGoods(List<Goods> store) {
        for (Goods item : store) {
            if (item.getRating() >= RECOMMENDATION_RATING)
                System.out.println("Рекомендуем к покупке: " + item);
        }
    }
}
