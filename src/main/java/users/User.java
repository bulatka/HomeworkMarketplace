package users;

public class User implements UserIF {
    protected String login;
    protected String password;

    public User(String login, String password, int age) {
        this.login = login;
        this.password = password;
    }
    @Override
    public String getLogin() {
        return login;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void pay() {
        System.out.println("Сумма оплачена с баланса пользователя "
                + this.getLogin() + "!");
    }
}
