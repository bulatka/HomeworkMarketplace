package shops;

import goods.Goods;
import users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FruitsShop implements ShopIF {
    final private List<Goods> store = new ArrayList<>();
    final private int RECOMMENDATION_RATING = 8;
    private HashMap<String, User> usersList = new HashMap<>();

    @Override
    public void addUser(User user) {
        usersList.put(user.getLogin(), user);
        System.out.println("Пользователь магазина электроники с логином " + user.getLogin()
                + " добавлен.");
    }

    @Override
    public void addItem(Goods item) {
        store.add(item);
        System.out.println("Фрукт " + item + " добавлен в продажу");
    }

    @Override
    public void printAvailableGoods() {
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
    public void sortGoodsByName() {
        Collections.sort(store);
        printAvailableGoods();
    }

    @Override
    public void searchByName(String name) {
        for (Goods item : store) {
            if (item.getName().equals(name))
                System.out.println("Найдено: " + item);
        }
    }

    @Override
    public void recommendGoods() {
        for (Goods item : store) {
            if (item.getRating() >= RECOMMENDATION_RATING)
                System.out.println("Рекомендуем к покупке: " + item);
        }
    }

    @Override
    public void printWelcome() {

    }
}
