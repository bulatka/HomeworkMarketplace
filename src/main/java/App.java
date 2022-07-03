import goods.Goods;
import shops.DevicesShop;
import shops.ShopIF;
import users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        ShopIF shop = new DevicesShop();
        Goods phone = new Goods("Iphone 13", 1299, 9);
        Goods watch = new Goods("MiBand 6", 59, 8);
        Goods speaker = new Goods("JBL GO", 49, 7);
        Goods macbook = new Goods("Macbook Pro", 1999, 6);
        Goods headphones = new Goods("HTC Headphones", 29, 10);
        shop.addItem(phone);
        shop.addItem(watch);
        shop.addItem(speaker);
        shop.addItem(macbook);
        shop.addItem(headphones);

        shop.addUser(new User("test", "pass", 33));
        System.out.println("\n\n");

        shop.printWelcome();
        shop.printAvailableGoods();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Введите номер позиции и количество для добавления в корзину или 0 для вызова меню.");
            System.out.println("Введите \"заказ\" для перехода к оформлению заказа");
            String stbasket = sc.nextLine();
            if (stbasket.equals("заказ")) {
                // ORDERING to do
            }
            if (stbasket.equals("0")) {
                shop.printMenu();
            } else {
                String[] basket = stbasket.split(" ");
                shop.addToBasket(Integer.parseInt(basket[1]), Integer.parseInt(basket[2]));
            }
            //TO DO ORDER, PAY, FruitShop
        }
    }
}
