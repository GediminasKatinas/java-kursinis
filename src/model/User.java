package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User implements Serializable {
    private String login;
    private String psw;
    public static ArrayList<User> users = new ArrayList<>();
    private boolean loggedin;


public User(){}
    public User(String login, String psw) {
        this.login = login;
        this.psw = psw;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public String getLogin() {
return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

   @Override
   public String toString() {
      return "User{" +
              "login='" + login + '\'' +
              ", psw='" + psw + '\'' +
               '}';
    }
    public void overWriteSystem(User importeduser) {
        this.login = importeduser.getLogin();
        this.psw =importeduser.getPsw();
    }
}
