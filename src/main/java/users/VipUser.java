package users;

import java.util.Random;

public class VipUser extends User implements UserIF {
    protected int discount;
    final protected int DISCOUNT_MIN = 1;
    final protected int DISCOUNT_MAX = 7;

    public VipUser(String login, String password, int age) {
        super(login, password, age);
        Random random = new Random();
        this.discount = random.nextInt(DISCOUNT_MIN, DISCOUNT_MAX);
    }

    @Override
    public void pay() {
        System.out.println("Сумма оплачена с баланса пользователя "
                + this.getLogin() + " со скидкой " + this.discount + "%!");
    }
}
