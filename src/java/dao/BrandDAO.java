package dao;

import entity.Brand;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class BrandDAO extends DBConnection {

    public Brand findByID(int id) {
        Brand b = null;
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from brand where id=" + id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                b = new Brand(rs.getInt("id"), rs.getString("brand_name"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return b;
    }

    public void create(Brand b) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "insert into brand(brand_name) values('" + b.getBrand_name() + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update(Brand b) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "update brand set brand_name='" + b.getBrand_name() + "' where id=" + b.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Brand b) {
        try {
            Statement st = this.getConnection().createStatement();
            String query = "delete from brand where id =" + b.getId();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Brand> getList() {
        List<Brand> list = new ArrayList<>();
        try {
            Statement st = this.getConnection().createStatement();
            String query = "select * from brand order by id asc";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new Brand(rs.getInt("id"), rs.getString("brand_name")));
            }
            
            st.isClosed();
            rs.isClosed();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}
