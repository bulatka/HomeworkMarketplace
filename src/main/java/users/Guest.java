package users;

import goods.Goods;
import goods.Order;

import java.util.HashMap;

public class Guest implements GuestIF, Payable {

    protected String phoneNumber;
    protected HashMap<Goods, Integer> basket = new HashMap<>();

    @Override
    public void addToBasket(Goods item, int quantity) {
        basket.put(item, quantity);
        System.out.println("Товар " + item + " " + quantity + " добавлен в корзину");
    }

    @Override
    public void order(HashMap<Goods, Integer> basket) {
        Order newOrder = new Order(basket);
        System.out.println("Создан заказ №" + newOrder.getID() + ".");
    }

    @Override
    public void pay() {
        System.out.println("Сумма оплачена со счета мобильного телефона!");
    }
}
