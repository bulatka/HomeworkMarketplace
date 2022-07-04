package shops;

import goods.Goods;
import users.User;
import users.UserIF;

import java.util.*;

public class DevicesShop implements ShopIF, ShopFeaturesIF {

    final private List<Goods> store = new ArrayList<>();
    final private int RECOMMENDATION_RATING = 7;
    final private HashMap<String, UserIF> usersList = new HashMap<>();
    private final HashMap<UserIF, HashMap<Integer, Order>> ordersMap = new HashMap<>();
    private HashMap<Goods, Integer> basket = new HashMap<>();
    private UserIF authUser = null;

    @Override
    public void addUser(UserIF user) {
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
        printMarket();
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
        if (basket.containsKey(item)) {
            int qty = basket.get(item) + quantity;
            basket.put(item, qty);
        } else {
            basket.put(item, quantity);
        }
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
        label:
        while (true) {
            System.out.println("Введите номер позиции и количество (через пробел) для добавления в корзину,\n"
                    + "0 для вызова меню,\n"
                    + "\"end\" для выхода.");
            System.out.println("Введите \"заказ\" для перехода к оформлению заказа");
            String str = sc.nextLine();
            switch (str) {
                case "заказ":
                    order();
                    break;
                case "0":
                    printMenu();
                    break;
                case "end":
                    break label;
                default:
                    String[] basket = str.split(" ");
                    addToBasket(Integer.parseInt(basket[0]), Integer.parseInt(basket[1]));
                    break;
            }
        }
    }

    @Override
    public void printMenu() {
        System.out.println("1. Сортировка товаров по имени.\n"
                + "2. Поиск товара по имени.\n"
                + "3. Рекомендовано к покупке.\n"
                + "4. Перейти в корзину.\n"
                + "5. Ваши заказы. Повторить или отменить заказ.\n");

        Scanner sc = new Scanner(System.in);
        int ch = Integer.parseInt(sc.nextLine());
        label:
        switch (ch) {
            case 1 -> sortGoodsByName();
            case 2 -> {
                System.out.println("Введите имя товара для поиска: ");
                String name = sc.nextLine();
                searchByName(name);
            }
            case 3 -> recommendGoods();
            case 4 -> printBasket();
            case 5 -> {
                System.out.println("Ваши заказы:");
                System.out.println(ordersMap.get(authUser));
                System.out.println("1. Повторить заказ.\n" +
                        "2. Отменить заказ.\n" +
                        "0. Предыдущее меню.");
                ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        reOrder();
                        printMarket();
                        break;
                    case 2:
                        cancelOrder();
                        printMarket();
                        break;
                    case 0:
                        break label;
                    default:
                        System.out.println("Введен неверный пункт меню. Попробуйте снова.");
                        printMenu();
                        break;
                }
            }
        }
    }

    public void printBasket() {
        if (basket.isEmpty()) {
            System.out.println("Ваша корзина пуста!");
        } else {
            System.out.println("Ваша корзина:");
            System.out.println(basket);
            final int[] sum = {0};
            basket.forEach((g, q) -> sum[0] = sum[0] + g.getPrice() * q);
            System.out.println("Общая сумма заказа: " + sum[0] + "$");
        }
    }

    @Override
    public void order() {
        printBasket();
        if (basket.isEmpty()) {
            printMarket();
        } else {
            System.out.println("Необходимо оплатить заказ!\n"
                    + "Для оплаты введите указанное ниже число либо 0 для отмены");
            Random random = new Random();
            int key = random.nextInt(1000, 9999);
            System.out.println(key);
            Scanner sc = new Scanner(System.in);
            int keyInput = sc.nextInt();
            if (keyInput == key) {
                authUser.pay();
                Order order = new Order(new HashMap<>(basket));
                orderToMap(order);
            } else {
                System.out.println("Вы ввели неверный код! Попробуте снова");
                order();
            }
        }
    }

    @Override
    public void orderToMap(Order order) {
        if (ordersMap.containsKey(authUser)) {
            HashMap<Integer, Order> userOrders = ordersMap.get(authUser);
            userOrders.put(order.getID(), order);
            ordersMap.put(authUser, userOrders);
            System.out.println("Создан заказ №" + order.getID());
            System.out.println(order);
            basket.clear();
        } else {
            HashMap<Integer, Order> newUserOrders = new HashMap();
            newUserOrders.put(order.getID(), order);
            ordersMap.put(authUser, newUserOrders);
            System.out.println("Создан заказ №" + order.getID());
            System.out.println(order);
            basket.clear();
        }
    }

    @Override
    public void reOrder() {
        System.out.println("Введите номер заказа для повтора");
        Scanner sc = new Scanner(System.in);
        int inputID = sc.nextInt();
        HashMap<Integer, Order> orderList = ordersMap.get(authUser);
        basket = new HashMap<>(orderList.get(inputID).getBasket());
        order();
    }

    @Override
    public void cancelOrder() {
        System.out.println("Введите номер заказа для отмены");
        Scanner sc = new Scanner(System.in);
        int inputID = sc.nextInt();
        HashMap<Integer, Order> orderList = ordersMap.get(authUser);
        orderList.remove(inputID);
        ordersMap.put(authUser, orderList);
        System.out.println("Заказ №" + inputID + "отменен." +
                "\nДеньги возвращены на баланс пользователя " + authUser.getLogin() + "!");
        System.out.println("Ваши заказы:");
        System.out.println(ordersMap.get(authUser).toString());
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
                    UserIF exp = usersList.get(login);
                    if (exp.getPassword().equals(pass)) {
                        System.out.println("Вы вошли успешно вошли под логином " + exp.getLogin());
                        authUser = exp;
                        printAvailableGoods();
                        break;
                    } else {
                        System.out.println("Неправильный логин или пароль");
                        printWelcome();
                    }
                } else {
                    System.out.println("Логин не найден.");
                    printWelcome();
                }
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
                UserIF newUser = new User(login, pass1, age);
                addUser(newUser);
                System.out.println("Авторизуйтесь заново.");
                printWelcome();
            case 0:
                break;
        }
    }
}
