package dao;

import entity.Privilege;
import entity.Users;
import java.sql.ResultSet;
import java.sql.Statement;
import util.DBConnection;

public class RegisterDAO extends DBConnection {

    private PrivilegeDAO privilegeDAO;

    public Users findByID(int id) {
        Users u = null;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from users where id=" + id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Privilege p = this.getPrivilegeDAO().findByID(rs.getInt("privilege_id"));
                u = new Users(rs.getInt("id"), p, rs.getString("user_name"), rs.getString("user_password"), rs.getString("user_address"), rs.getString("user_phone"), rs.getString("user_mail"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    //priv ekleyince db'ye gitmiyor..
    public void register(Users u) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "insert into users(user_name,user_password,user_address,user_phone,user_mail) values('" + u.getUser_name() + "', '" + u.getUser_password() + "' , '" + u.getUser_address() + "' ,'" + u.getUser_phone() + "','" + u.getUser_mail() + "')";
            st.executeQuery(query);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public PrivilegeDAO getPrivilegeDAO() {
        if (privilegeDAO == null) {
            this.privilegeDAO = new PrivilegeDAO();
        }
        return privilegeDAO;
    }

    public void setPrivilegeDAO(PrivilegeDAO privilegeDAO) {
        this.privilegeDAO = privilegeDAO;
    }

}
