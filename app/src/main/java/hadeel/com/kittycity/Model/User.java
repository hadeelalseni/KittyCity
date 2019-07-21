package hadeel.com.kittycity.Model;

import java.util.ArrayList;

public class User{
    private String username;
    private String email;
    private String password;
    private ArrayList<Integer> kitties;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Integer> getKitties() {
        return kitties;
    }

    public void setKitties(ArrayList<Integer> kitties) {
        this.kitties = kitties;
    }
}
