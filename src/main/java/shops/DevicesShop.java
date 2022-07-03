package shops;

import goods.Goods;
import users.Guest;
import users.User;

import java.util.*;

public class DevicesShop implements ShopIF {
    final private List<Goods> store = new ArrayList<>();
    final private int RECOMMENDATION_RATING = 7;
    private HashMap<String, User> usersList = new HashMap<>();

    private HashMap<Goods, Integer> basket = new HashMap<>();

    @Override
    public void addUser(User user) {
        usersList.put(user.getLogin(), user);
        System.out.println("Пользователь магазина электроники с логином " + user.getLogin()
                + " добавлен.");
    }

    @Override
    public void addItem(Goods item) {
        store.add(item);
        System.out.println("Устройство " + item + " добавлено в продажу");
    }

    @Override
    public void printAvailableGoods() {
        int i = 1;
        for (Goods item : store) {
            System.out.println(i + ". " + item);
            i++;
        }
    }

    @Override
    public void removeItem(Goods item) {
        store.remove(item);
        System.out.println("Устройство " + item + " удалено из продажи");
    }

    @Override
    public void sortGoodsByName() {
        Collections.sort(store);
        printAvailableGoods();
    }
    @Override
    public void addToBasket(int itemInt, int quantity) {
        Goods item = store.get(itemInt);
        basket.put(item, quantity);
        System.out.println("Товар " + item + " " + quantity + " добавлен в корзину");
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
            if (item.getRating() > RECOMMENDATION_RATING)
                System.out.println("Рекомендуем к покупке: " + item);
        }
    }

    @Override
    public void printMenu() {
        System.out.println("1. Сортировка товаров по имени.\n"
                + "2. Поиск товара по имени.\n"
                + "3. Рекомендовано к покупке.\n");
        Scanner sc = new Scanner(System.in);
        int ch = Integer.parseInt(sc.nextLine());
        switch (ch) {
            case 1:
                this.sortGoodsByName();
                break;
            case 2:
                System.out.println("Введите имя товара для поиска: ");
                String name = sc.nextLine();
                this.searchByName(name);
                break;
            case 3:
                this.recommendGoods();
                break;
        }

    }

    @Override
    public void printWelcome() {
        System.out.println("Добро пожаловать в магазин \"Электроника\"\n"
                + "Выберите параметры авторизации:\n"
                + "1. Гостевой вход для быстрого заказа.\n"
                + "2. Авторизация пользователя.\n"
                + "3. Регистрация.\n"
                + "0. Выход.");

        Scanner sc = new Scanner(System.in);
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            default:
                System.out.println("Введен неверный пункт меню.\n");
                printWelcome();
            case 1:
                System.out.println("Введите номер телефона: ");
                String phoneNumber = sc.nextLine();
                Guest newGuest = new Guest(phoneNumber);
                System.out.println("Вы вошли как гость c номером телефона " + newGuest.getPhoneNumber());
                break;
            case 2:
                System.out.println("Введите логин:");
                String login = sc.nextLine();
                System.out.println("Введите пароль:");
                String pass = sc.nextLine();
                if (usersList.containsKey(login)) {
                    User exp = usersList.get(login);
                    if (exp.getPassword().equals(pass)) {
                        System.out.println("Вы вошли успешно вошли под логином " + exp.getLogin());
                    } else {
                        System.out.println("Неправильный логин или пароль");
                    }
                } else {
                    System.out.println("Логин не найден.");
                    printWelcome();
                }
                break;
            case 3:
                System.out.println("Введите логин:");
                login = sc.nextLine();
                while (usersList.containsKey(login)) {
                    System.out.println("Логин уже существует. Введите другой: ");
                    login = sc.nextLine();
                }
                String pass1 = "pass1";
                String pass2 = "pass2";
                while (!pass1.equals(pass2)) {
                    System.out.println("Введите пароль:");
                    pass1 = sc.nextLine();
                    System.out.println("Введите пароль повторно:");
                    pass2 = sc.nextLine();
                    if (!pass1.equals(pass2)) System.out.println("Пароли не совпадают. Попробуйте еще раз.");
                    else System.out.println("Пароль введен успешно.");
                }
                System.out.println("Введите ваш возраст:");
                int age = sc.nextInt();
                User newUser = new User(login, pass1, age);
                this.addUser(newUser);
                System.out.println("Авторизуйтесь заново.");
                printWelcome();
            case 0:
                break;
        }


    }
}
