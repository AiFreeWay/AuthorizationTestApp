package aifree.com.authorizationtestapp.domain.models;


public class Phone {

    private String phone;

    public Phone() {
    }

    public Phone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isValid() {
        return phone!=null && phone.length()>0;
    }
}
