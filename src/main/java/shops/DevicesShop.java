package shops;

import goods.Goods;
import goods.Order;
import users.User;

import java.util.*;

public class DevicesShop implements ShopIF {

    final private List<Goods> store = new ArrayList<>();
    final private int RECOMMENDATION_RATING = 7;
    private HashMap<String, User> usersList = new HashMap<>();
    private HashMap<User, List<Order>> ordersMap = new HashMap<>();
    private HashMap<Goods, Integer> basket = new HashMap<>();
    private User authUser = new User(null, null, 0);

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
        Goods item = store.get(itemInt - 1);
        basket.put(item, quantity);
        System.out.println("Товар " + item + " -- " + quantity + "шт добавлен в корзину");
    }

    @Override
    public void searchByName(String name) {
        List<Goods> foundedItems = new ArrayList<>();
        for (Goods item : store) {
            if (item.getName().contains(name))
                foundedItems.add(item);
        }
        if (foundedItems.isEmpty()) System.out.println("Ничего не найдено");
        else System.out.println("Найдено: " + foundedItems.size() + " совпадений!");
        for (Goods item : foundedItems) {
            System.out.println(item);
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
    public void printMarket() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Введите номер позиции и количество (через пробел) для добавления в корзину,\n"
                    + "0 для вызова меню,\n"
                    + "\"end\" для выхода.");
            System.out.println("Введите \"заказ\" для перехода к оформлению заказа");
            String str = sc.nextLine();
            if (str.equals("заказ")) {
                order();
                continue;
            } else if (str.equals("0")) {
                printMenu();
                continue;
            } else {
                String[] basket = str.split(" ");
                addToBasket(Integer.parseInt(basket[0]), Integer.parseInt(basket[1]));
                continue;
            }


        }
    }

    @Override
    public void printMenu() {
        System.out.println("1. Сортировка товаров по имени.\n"
                + "2. Поиск товара по имени.\n"
                + "3. Рекомендовано к покупке.\n"
                + "4. Перейти в корзину.\n"
                + "5. Повторить или отменить заказ.\n");

        Scanner sc = new Scanner(System.in);
        int ch = Integer.parseInt(sc.nextLine());
        switch (ch) {
            case 1:
                sortGoodsByName();
                break;
            case 2:
                System.out.println("Введите имя товара для поиска: ");
                String name = sc.nextLine();
                searchByName(name);
                break;
            case 3:
                recommendGoods();
                break;
            case 4:
                if (basket.isEmpty()) {
                    System.out.println("Ваша корзина пуста!");
                } else {
                    System.out.println("Ваша корзина:");
                    System.out.println(basket);
                }
                break;
            case 5:
                System.out.println("Ваши заказы:");
                List<Order> orList = this.ordersMap.get(authUser.getLogin());
                System.out.println(orList);
        }
    }

    @Override
    public void order() {
        Order order = new Order(basket);
        System.out.println(order);
        basket.clear();
        if (ordersMap.containsKey(authUser)) {
            List<Order> userOrders = ordersMap.get(authUser);
            userOrders.add(order);
            ordersMap.put(authUser, userOrders);
            System.out.println("Создан заказ №" + order.getID());
            System.out.println(order.getBasket());
        } else {
            List<Order> newUserOrders = new ArrayList<>();
            newUserOrders.add(order);
            ordersMap.put(authUser, newUserOrders);
            System.out.println("Создан заказ №" + order.getID());
            System.out.println(order.getBasket());
        }
    }

    @Override
    public void printWelcome() {
        System.out.println("Добро пожаловать в магазин \"Электроника\"\n"
                + "Выберите параметры авторизации:\n"
                + "1. Авторизация пользователя.\n"
                + "2. Регистрация.\n"
                + "0. Выход.");

        Scanner sc = new Scanner(System.in);
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            default:
                System.out.println("Введен неверный пункт меню.\n");
                printWelcome();
            case 1:
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
                        printWelcome();
                    }
                } else {
                    System.out.println("Логин не найден.");
                    printWelcome();
                }
                break;
            case 2:
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
                addUser(newUser);
                System.out.println("Авторизуйтесь заново.");
                printWelcome();
            case 0:
                break;
        }
    }
}
