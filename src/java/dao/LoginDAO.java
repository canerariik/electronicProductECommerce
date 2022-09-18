package dao;

import entity.Users;
import java.sql.Statement;
import util.DBConnection;
import java.sql.ResultSet;

public class LoginDAO extends DBConnection {

    public void login(Users u) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from users where user_name=" + u.getUser_name();
            ResultSet rs = st.executeQuery(query);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void userControl(Users u) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from users where user_name='" + u.getUser_name() + "' and user_password=" + u.getUser_password();
            ResultSet rs = st.executeQuery(query);
            
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
