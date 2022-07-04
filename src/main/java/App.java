import goods.Goods;
import shops.DevicesShop;
import shops.ShopIF;
import users.User;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        ShopIF shop = new DevicesShop();
        Goods phone = new Goods("Iphone 13", 1299, 9);
        Goods phone1 = new Goods("Iphone 12", 999, 8);
        Goods watch = new Goods("MiBand 6", 59, 8);
        Goods speaker = new Goods("JBL GO", 49, 7);
        Goods macbook = new Goods("Macbook Pro", 1999, 6);
        Goods headphones = new Goods("HTC Headphones", 29, 10);
        shop.addItem(phone);
        shop.addItem(phone1);
        shop.addItem(watch);
        shop.addItem(speaker);
        shop.addItem(macbook);
        shop.addItem(headphones);

        shop.addUser(new User("test", "pass", 33));
        System.out.println("\n\n");

        shop.printWelcome();
        shop.printAvailableGoods();
        shop.printMarket();
        }
    }

