package entity;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private String mobilephone;
    private String email;
    private int city;
    private String delivery_address;
    private int user_rights;
    private Date register_time;

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public int getUser_rights() {
        return user_rights;
    }

    public void setUser_rights(int user_rights) {
        this.user_rights = user_rights;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}