package dao;

import entity.Cargo;
import entity.Users;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class CargoDAO extends DBConnection {

    private UsersDAO usersDAO;

    public Cargo findByID(int id) {
        Cargo c = null;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from cargo where id=" + id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Users u = this.getUsersDAO().findByID(rs.getInt("users_id"));
                c = new Cargo(rs.getInt("id"), u, rs.getInt("cargo_tracking_no"), rs.getString("delivery_date"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    public void create(Cargo c) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "insert into cargo(users_id,cargo_tracking_no,delivery_date) values(" + c.getUsers().getId() + " , " + c.getCargo_tracking_no() + ", '" + c.getDelivery_date() + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Cargo c) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "update cargo set users_id=" + c.getUsers().getId() + ", cargo_tracking_no=" + c.getCargo_tracking_no() + ", delivery_date='" + c.getDelivery_date() + "' where id=" + c.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Cargo c) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "delete from cargo where id=" + c.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Cargo> getList() {
        List<Cargo> list = new ArrayList<>();
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from cargo order by id asc";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Users u = this.getUsersDAO().findByID(rs.getInt("users_id"));
                list.add(new Cargo(rs.getInt("id"), u, rs.getInt("cargo_tracking_no"), rs.getString("delivery_date")));
            }
            st.isClosed();
            rs.isClosed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public UsersDAO getUsersDAO() {
        if (usersDAO == null) {
            this.usersDAO = new UsersDAO();
        }
        return usersDAO;
    }

    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

}
