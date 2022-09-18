package dao;

import entity.Privilege;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class PrivilegeDAO extends DBConnection {

    public Privilege findByID(int id) {
        Privilege p = null;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from privilege where id=" + id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                p = new Privilege(rs.getInt("id"), rs.getString("role"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    public void create(Privilege p) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "insert into privilege(role) values('" + p.getRole() + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Privilege p) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "update privilege set role='" + p.getRole() + "' where id=" + p.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Privilege p) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "delete from privilege where id=" + p.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Privilege> getList() {
        List<Privilege> list = new ArrayList<>();
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from privilege order by id asc";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new Privilege(rs.getInt("id"), rs.getString("role")));
            }
            st.isClosed();
            rs.isClosed();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}
