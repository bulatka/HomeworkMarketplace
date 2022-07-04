package users;

public class User implements Payable {
    protected String login;
    protected String password;

    public User(String login, String password, int age) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void pay() {
        System.out.println("Сумма оплачена с баланса пользователя "
                + this.getLogin() + "!");
    }
}
