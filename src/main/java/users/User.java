package users;

public class User implements Payable {
    protected String login;
    protected String password;
    protected int age;

    public User(String login, String password, int age) {
        this.login = login;
        this.password = password;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void pay() {
        System.out.println("Сумма оплачена с баланса пользователя "
                + this.login + "!");
    }
}
