import goods.Goods;
import shops.FruitsShop;
import shops.ShopIF;
import users.User;
import users.VipUser;

public class AppFruits {
    public static void main(String[] args) {

        ShopIF shop = new FruitsShop();
        Goods apple = new Goods("Яблоко", 1, 9);
        Goods pineapple = new Goods("Ананас", 5, 8);
        Goods melon = new Goods("Дыня", 9, 2);
        Goods watermelon = new Goods("Арбуз", 4, 8);
        Goods banana = new Goods("Банан", 1, 7);
        Goods orange = new Goods("Апельсин", 2, 6);
        Goods mango = new Goods("Манго", 11, 10);
        shop.addItem(apple);
        shop.addItem(pineapple);
        shop.addItem(mango);
        shop.addItem(melon);
        shop.addItem(watermelon);
        shop.addItem(banana);
        shop.removeItem(orange);

        shop.addUser(new User("test", "pass", 33));
        shop.addUser(new VipUser("vip", "vip", 21));
        System.out.println("\n\n");

        shop.printWelcome();
    }
}