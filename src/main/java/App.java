import shops.DevicesShop;
import shops.ShopIF;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ShopIF shop = new DevicesShop();
        Scanner scanner = new Scanner(System.in);
        shop.printWelcome();
        System.out.println("Добро пожаловать!");
    }
}
