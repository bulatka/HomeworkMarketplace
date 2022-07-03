package users;

import goods.Goods;
import goods.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User implements Payable, UserIF, GuestIF {
    protected String login;
    protected String password;
    protected int age;

    protected List<Order> ordersList = new ArrayList<>();
    protected HashMap<Goods, Integer> basket = new HashMap<>();


    public User(String login, String password, int age) {
        this.login = login;
        this.password = password;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void pay() {
        System.out.println("Сумма оплачена со счета физлица!");
    }

    @Override
    public void reOrder(int orderID) {
        System.out.println("Заказ " + orderID + " создан повторно!");
    }

    @Override
    public void cancelOrder(int orderID) {
        System.out.println("Заказ " + orderID + " отменен!");
    }

    @Override
    public void addToBasket(Goods item, int quantity) {
        basket.put(item, quantity);
        System.out.println("Товар " + item + " " + quantity + " добавлен в корзину");
    }

    @Override
    public void order(HashMap<Goods, Integer> basket) {
        Order newOrder = new Order(basket);
        System.out.println("Создан заказ №" + newOrder.getID() + ".");
        ordersList.add(newOrder);
    }
}
