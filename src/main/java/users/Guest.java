package users;

public class Guest implements GuestIF, Payable {

    protected String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Guest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void pay() {
        System.out.println("Сумма оплачена со счета мобильного телефона "
                + this.phoneNumber);
    }
}
