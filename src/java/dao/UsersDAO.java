package dao;

import entity.Privilege;
import entity.Users;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class UsersDAO extends DBConnection {

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

    public void create(Users u) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "insert into users(privilege_id,user_name,user_password,user_address,user_phone,user_mail) values(" + u.getPrivilege().getId() + ", '" + u.getUser_name() + "', '" + u.getUser_password() + "' , '" + u.getUser_address() + "' ,'" + u.getUser_phone() + "','" + u.getUser_mail() + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Users u) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "update users set privilege_id=" + u.getPrivilege().getId() + ", user_name='" + u.getUser_name() + "', user_password='" + u.getUser_password() + "', user_address='" + u.getUser_address() + "', user_phone='" + u.getUser_phone() + "', user_mail='" + u.getUser_mail() + "'  where id=" + u.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Users u) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "delete from users where id=" + u.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Users> getList() {
        List<Users> list = new ArrayList<>();
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from users order by id asc";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Privilege p = this.getPrivilegeDAO().findByID(rs.getInt("privilege_id"));
                list.add(new Users(rs.getInt("id"), p, rs.getString("user_name"), rs.getString("user_password"), rs.getString("user_address"), rs.getString("user_phone"), rs.getString("user_mail")));
            }

            st.isClosed();
            rs.isClosed();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
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
